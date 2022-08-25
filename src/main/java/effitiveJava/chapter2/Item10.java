package effitiveJava.chapter2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class Item10 {

    @Test
    public void equals_symmetry_test() {
        User user = new User("Publish");
        String publish = "publish";
        Assert.assertEquals(user, publish);
        Assert.assertNotEquals(publish, user);
    }

}

class User {
    private final String str;

    User(String str) {
        this.str = Objects.requireNonNull(str);
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof User) {
            return str.equalsIgnoreCase(((User) o).str);
        } else if (o instanceof String) {
            return str.equalsIgnoreCase((String) o);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
