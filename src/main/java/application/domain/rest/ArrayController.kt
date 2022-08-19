package application.domain.rest

import application.rest.PensieveService
import com.sun.jna.StringArray
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ArrayController(val service: PensieveService) {

    @PostMapping("test")
    fun array(array: StringArray): String {
        return array.toString();
    }

    @GetMapping("feign-call")
    fun feignCall(): String {
        return service.getDataBaseMessage("DATABASE_ES", null)
    }

}