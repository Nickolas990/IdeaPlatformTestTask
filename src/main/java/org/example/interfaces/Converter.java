package org.example.interfaces;

/**
 * Функциональный интерфейс Converter. Максимально простой и удобный.
 */

@FunctionalInterface
public interface Converter<T, N> {
    N convert(T t);
}
