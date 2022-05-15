package jdk.jdk15;

/**
 * if @Target() is FIELD, the annotation can be applied to private field.
 * if @Target() is METHOD, the annotation can be applied to method.
 *
 * @author violet.
 */
public record Doc(String name, String content) {
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
