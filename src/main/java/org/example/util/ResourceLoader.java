package org.example.util;

import java.io.InputStream;
import java.util.Objects;

/**
 * Утилитный класс для работы с файлами помещенными в папку resources.
 *
 * @author Nikolay Melnikov
 */
public class ResourceLoader {

    private ResourceLoader() {
    }

    public static InputStream getFileFromResource(String filename) {
        InputStream resource = ResourceLoader.class.getClassLoader().getResourceAsStream(filename);
        if (Objects.isNull(resource)) {
            throw new IllegalArgumentException("File not found");
        } else {
            return resource;
        }
    }
}
