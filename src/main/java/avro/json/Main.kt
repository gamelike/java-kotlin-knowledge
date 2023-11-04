package avro.json

import avro.model.User
import avro.schema.SchemaUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DecoderFactory
import org.apache.avro.io.EncoderFactory
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.TimeUnit

fun main() {
    producer()
    consumer()
}

val blockingQueue = SynchronousQueue<String>()

fun consumer() {
    println("---started-consumer-thread---")
    Thread {
        while (true) {
            TimeUnit.SECONDS.sleep(10)
            val poll = blockingQueue.poll(10000, TimeUnit.MILLISECONDS)
            if (poll != null) {
                try {
                    val datumReader = GenericDatumReader<GenericRecord>(SchemaUtil.user())
                    val jsonDecoder = DecoderFactory.get().jsonDecoder(SchemaUtil.user(), poll)
                    val genericRecord = datumReader.read(null, jsonDecoder)
                    println(genericRecord)
                    // avro to json
                    val objectMapper = ObjectMapper().registerModule(
                        KotlinModule.Builder()
                            .withReflectionCacheSize(512)
                            .configure(KotlinFeature.NullToEmptyCollection, true)
                            .configure(KotlinFeature.NullToEmptyMap, true)
                            .configure(KotlinFeature.NullIsSameAsDefault, true)
                            .configure(KotlinFeature.SingletonSupport, true)
                            .configure(KotlinFeature.StrictNullChecks, true)
                            .build()
                    )
                    val user = objectMapper.readValue(genericRecord.toString(), User::class.java)
                    println(user)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }.start()
}

fun producer() {
    println("---started-producer-thread---")
    Thread {
        while (true) {
            println("-----producer----")
            val user = User(UUID.randomUUID().toString(), Random(10).nextInt(), UUID.randomUUID().toString())
            val writer = GenericDatumWriter<GenericRecord>(SchemaUtil.user())
            val outputStream = ByteArrayOutputStream()
            val record = GenericData.Record(SchemaUtil.user())
            record.put("name", user.name)
            record.put("favorite_number", user.favoriteNumber)
            record.put("favorite_color", user.favoriteColor)
            val jsonEncoder = EncoderFactory.get().jsonEncoder(SchemaUtil.user(), outputStream)
            writer.write(record, jsonEncoder)
            jsonEncoder.flush()
            outputStream.use {
                blockingQueue.offer(String(it.toByteArray()), 20000, TimeUnit.MILLISECONDS)
            }
            TimeUnit.SECONDS.sleep(10)
        }
    }.start()
}