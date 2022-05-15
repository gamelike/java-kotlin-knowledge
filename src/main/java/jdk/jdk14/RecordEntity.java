package jdk.jdk14;<<<<<<< HEAD:src/main/java/jdk17/RecordEntity.java
package jdk17;
=======
package jdk.jdk14;
>>>>>>> 8b6f87f... feat(jdk14): record class and standard switch. delete CMS and ParallelScvenge + SerialOld GC:src/main/java/jdk14/RecordEntity.java

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
