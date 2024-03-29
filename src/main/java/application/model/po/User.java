package application.model.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;
import java.util.Objects;

/**
 * @author violet.
 */
@Entity
@Table(name = "t_user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@FilterDef(name = "filter",
        parameters = @ParamDef(name = "username",type = String.class))
@Filter(name = "filter",
        condition = "username = :username")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user",targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
