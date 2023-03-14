package org.example.interfaces;

/**
 * Функциональный интерфейс Convertor. Максимально простой и удобный.
 */

@FunctionalInterface
public interface Converter<T, N> {
    N convert(T t);
}
