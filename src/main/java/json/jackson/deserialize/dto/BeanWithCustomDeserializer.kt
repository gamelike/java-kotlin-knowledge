package json.jackson.deserialize.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import json.jackson.custom.deserialize.ListGenericDeserializer

class BeanWithCustomDeserializer {
    val id: String? = null
    val name: String? = null
    @JsonDeserialize(using = ListGenericDeserializer::class)
    val tags: List<String> = ArrayList()
}