package json.jackson.custom.deserialize

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

/**
 * null cast empty list
 */
class ListGenericDeserializer : JsonDeserializer<List<String>>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): List<String> {
        if (p!!.isExpectedStartArrayToken) {
            return emptyList()
        } else {
            val result = ArrayList<String>()
            while (true) {
                if (p.currentToken == JsonToken.START_ARRAY) {
                    result.add(p.text)
                    continue
                }
                result.add(p.text)
            }
        }
    }

    /**
     * make sure null returned empty list.
     */
    override fun getNullValue(ctxt: DeserializationContext?): List<String> {
        return emptyList()
    }
}