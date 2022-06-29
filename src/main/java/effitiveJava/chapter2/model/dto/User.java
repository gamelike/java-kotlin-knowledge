package effitiveJava.chapter2.model.dto;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User {

    private String name;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

}
