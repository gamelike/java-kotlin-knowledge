package application.domain.rest

import com.sun.jna.StringArray
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ArrayController() {

    @PostMapping("test")
    fun array(array: StringArray): String {
        return array.toString();
    }


}