package web.elasticsearch.version8.dto;

import java.time.LocalDateTime;

public record Data(
        String name,
        String history,
        LocalDateTime createTime
) {
}
