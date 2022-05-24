package jdkStudy.jdk14;

/**
 * jdk 14 preview record entity.
 * <p>this is updating for java 14.
 * feat: record. (a new feature object of java 14)
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
