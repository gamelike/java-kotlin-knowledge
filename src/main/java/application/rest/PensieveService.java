package application.rest;

import application.rest.fallback.PensieveFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author violet
 */
@FeignClient(name = "pensieve", url = "10.7.212.41:22100", fallback = PensieveFallback.class)
@Service
public interface PensieveService {

    @GetMapping("/pensieve/resources/types/typesStorage")
    String getDataBaseMessage(@RequestParam("dataSourceType") String databaseType,
                              @RequestParam(value = "typesAliasName", required = false) String typesAliasName);

}
