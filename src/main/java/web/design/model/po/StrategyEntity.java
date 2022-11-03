package web.design.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author violet
 * @since 2022/11/3
 */
@Table(name = "strategy_entity")
@Entity
@Data
@Accessors(chain = true)
public class StrategyEntity {
    @Id
    private long id;
    @Column(name = "name", length = 32)
    private String name;
    @Column(name = "strategy_name", length = 32)
    private String strategy;
    @Column(name = "address",length = 64)
    private String address;
    @Column(name = "text",length = 256)
    private String text;

}
