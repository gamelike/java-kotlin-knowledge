package json.jackson.avro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import json.jackson.avro.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.util.Utf8;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @author violet
 * @since 2023/11/14
 */
@Slf4j
public class AvroTest {
    // note: AvroSchema is Jackson type that wraps "native" Avro Schema object:
    private final String SCHEMA_JSON = """
            {
            "type": "record",
            "name": "Employee",
            "fields": [
             {"name": "name", "type": "string"},
             {"name": "age", "type": "int"},
             {"name": "emails", "type": {"type": "array", "items": "string"}},
             {"name": "boss", "type": ["Employee","null"]}
            ]}
            """;
    Schema raw = new Schema.Parser().setValidate(true).parse(SCHEMA_JSON);

    @Test
    public void test_jackson_avro_schema_write() throws IOException {
        Employee e = new Employee("employee", 22, new String[]{"test@com.cn", "a@gmail.com"},
                null);
        AvroSchema avroSchema = new AvroSchema(raw);
        AvroMapper mapper = new AvroMapper();
        byte[] binaryEncode = mapper.writer(avroSchema)
                .writeValueAsBytes(e);
        log.info("employee: {}", new String(binaryEncode));
        SpecificDatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(raw);
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(binaryEncode, null);
        GenericRecord employee = datumReader.read(null, decoder);
        log.info("employee: {}", employee);
        Assert.assertEquals(e.name(), ((Utf8) employee.get("name")).toString());
        Assert.assertEquals(e.age(), (int) employee.get("age"));
        //noinspection unchecked
        Assert.assertArrayEquals(e.emails(), ((GenericData.Array<Utf8>) employee.get("emails")).stream()
                .map(Utf8::toString).toArray(String[]::new));
        Assert.assertNull(employee.get("boss"));
    }

    @Test
    public void test_jackson_avro_schema_read() throws IOException {
        GenericData.Record record = new GenericData.Record(raw);
        record.put("name", "employee");
        record.put("age", 22);
        record.put("emails", List.of("test@com.cn", "aaaa@gmail.com"));
        record.put("boss", null);
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
        ) {
            GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(raw);
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(os, null);
            datumWriter.write(record, encoder);
            encoder.flush();
            try (InputStream ins = new ByteArrayInputStream(os.toByteArray())) {
                AvroSchema avroSchema = new AvroSchema(raw);
                AvroMapper avroMapper = new AvroMapper();
                Employee employee = avroMapper.reader(avroSchema).readValue(ins, Employee.class);
                //noinspection unchecked
                Assert.assertArrayEquals(employee.emails(), ((List<String>) record.get("emails")).toArray(String[]::new));
                Assert.assertEquals(employee.age(), record.get("age"));
                Assert.assertEquals(employee.name(), record.get("name"));
                Assert.assertNull(employee.boss());
            }
        }
    }


}
