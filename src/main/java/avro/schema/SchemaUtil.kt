package avro.schema

import org.apache.avro.Schema

object SchemaUtil {
    fun user() = Schema.Parser().parse(
        """
       {
            "namespace": "example.avro",
            "type": "record",
            "name": "User",
            "fields": [
                {"name": "name", "type": "string"},
                {"name": "favorite_number",  "type": ["int", "null"]},
                {"name": "favorite_color", "type": ["string", "null"]}
            ]
        } 
    """.trimIndent()
    )
}