package application.model.po;

import application.model.constant.HistoryType;

import javax.persistence.*;

/**
 * @author violet.
 */
@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 32)
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "type")
    @Enumerated(value = EnumType.ORDINAL)
    private HistoryType type;

    public History() {
    }

    public History(Integer id, String title, HistoryType type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HistoryType getType() {
        return type;
    }

    public void setType(HistoryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
