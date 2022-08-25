package classloader;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 自定义的类加载器
 */
@Slf4j
public class CustomClassLoader extends ClassLoader {

    private final String classpath;

    public CustomClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            byte[] classDate = getData(name);
            return defineClass(name, classDate, 0, classDate.length);
        } catch (IOException e) {
            log.error("加载类 {} 出错", name);
            throw new RuntimeException(e);
        }
    }

    private byte[] getData(String name) throws IOException {
        String path = classpath + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
        try (InputStream in = new FileInputStream(path);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            log.error("文件不存在");
            throw new RuntimeException(e);
        }
    }
}
