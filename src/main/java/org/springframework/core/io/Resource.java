package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gjd
 */
public interface Resource {
  InputStream getInputStream() throws IOException;
}
