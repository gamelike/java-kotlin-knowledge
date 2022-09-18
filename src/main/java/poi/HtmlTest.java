package poi;

import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author violet
 * @since 2022/9/17
 */
public class HtmlTest {

    public List<String[]> test(File file) {
        List<String[]> list = Lists.newArrayList();
        try (FileInputStream fis = new FileInputStream(file)) {
            StringBuilder str = new StringBuilder();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = fis.read()) != -1) {
                fis.read(buffer,0 ,len);
                str.append(new String(buffer));
            }
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Test
    public void test() {
        File file = new File("/home/zhao/Downloads/2022年Java编程题考试-初级/Java(初级)_X0001/Java_exam1","download.html");
        test(file);
    }

}
