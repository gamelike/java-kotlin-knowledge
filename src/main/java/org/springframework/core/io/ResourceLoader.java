package org.springframework.core.io;

/**
 * @author gjd
 */
public interface ResourceLoader {

  Resource getResource(String location);
}
