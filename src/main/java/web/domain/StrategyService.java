package web.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import web.design.model.SearchType;
import web.design.model.po.StrategyEntity;

import java.util.List;

/**
 * @author violet
 * @since 2022/11/3
 */
@Tag(name = "strategy-controller")
@RequestMapping("strategy")
public interface StrategyService {

    @Operation(description = "query")
    @GetMapping("{type}")
    @ResponseBody
    List<StrategyEntity> queryStrategyEntityList(@PathVariable SearchType type);

    @Operation(description = "save")
    @PostMapping("{type}/save")
    @ResponseBody
    StrategyEntity saveStrategyEntity(@RequestBody StrategyEntity entity, @PathVariable SearchType type);

}
