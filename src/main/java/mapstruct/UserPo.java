package mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gjd
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
  private Long id;
  private Date gmtCreate;
  private Date createTime;
  private Long buyerId;
  private Long age;
  private String userNick;
  private String userVerified;
  private String attributes;
}
