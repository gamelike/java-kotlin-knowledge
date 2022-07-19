package jdkStudy.jdk8.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author violet
 * @since 2022/7/19
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Entity {

    private String id;

    private String alarmIndexingId;

    private LocalDateTime createTime;

    private LocalDateTime eventTime;

    private List<String> influence;

    private String result;

}
