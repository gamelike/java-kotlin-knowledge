package jdk17;

/**
 * jdk 17 record entity.
 * <p>this is updating for java 17.
 * feat: record. (a new feature object of java 17)
 * </p>
 *
 * @author violet
 */
public record RecordEntity(String name, int age) {
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
