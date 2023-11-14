package json.jackson.avro.model;

/**
 * @author violet
 * @since 2023/11/14
 */
public record Employee(String name, int age, String[] emails, Employee boss) {
}
