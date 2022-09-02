package mapstruct;

import java.util.Date;

/**
 * @author gjd
 */
public class MapStructTest {
  public static void main(String[] args) {
    testNormal();
  }

  public static void testNormal() {
    System.out.println("-----------testNormal-----start------");
    String attributes = "{\"id\":2,\"name\":\"测试123\"}";
    UserPo userPo = UserPo.builder()
        .id(1L)
        .gmtCreate(new Date())
        .buyerId(666L)
        .userNick("测试mapstruct")
//        .userVerified("ok")
        .age(18L)
        .attributes(attributes)
        .build();
    System.out.println("1234" + userPo);
    UserEntity userEntity = IPersonMapper.INSTANCT.po2entity(userPo);
    System.out.println(userEntity);
    System.out.println("testNormal -end");
  }
}
