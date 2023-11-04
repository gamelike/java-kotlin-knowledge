package avro.model

import com.fasterxml.jackson.annotation.JsonProperty

data class User (
    val name: String?,
    @JsonProperty("favorite_number")
    val favoriteNumber: Int?,
    @JsonProperty("favorite_color")
    val favoriteColor: String?
)