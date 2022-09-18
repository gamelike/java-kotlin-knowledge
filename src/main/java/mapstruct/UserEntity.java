package mapstruct;

import lombok.Data;

import java.util.Date;

/**
 * @author gjd
 */
@Data
public class UserEntity {
  private Long id;
  private Date gmtCreate;
  private Date createTime;
  private Long buyerId;
  private Long age;
  private String userNick1;
  private String userVerified;
  private Attributes attributes;
}
