import mapstruct.UserPo;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.junit.Test;

import java.util.Date;

/**
 * @author gjd
 */
public class AvroSchemaTest {
    @Test public void getSchema() {
        UserPo userPo =
            UserPo.builder().id(1L).gmtCreate(new Date()).buyerId(666L).userNick("测试mapstruct").age(18L).build();
        Schema schema = ReflectData.get().getSchema(UserPo.class);
        System.out.println(schema);
    }
}
