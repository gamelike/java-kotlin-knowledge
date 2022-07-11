package application.domain.rest

import com.sun.jna.StringArray
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("array")
class ArrayController {

    @PostMapping("test")
    fun array(array: StringArray): String {
        return array.toString();
    }

}