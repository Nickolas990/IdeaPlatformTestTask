package org.example;

import com.google.gson.Gson;
import org.example.interfaces.Converter;
import org.example.ticket.FlightCalculator;
import org.example.util.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Для маппинга данных из json-файла я воспользоволся библиотекой Google Gson.
 * При помощи утилитного класса ResourceLoader создается объект InputStream, который предназначен для загрузки
 * данных из json-файла.
 * Для удобного отображения данных, воспользуемся функциональным интерфейсом, так как калькулятор передает данные в минутах.
 * Парсим объект калькулятора из json-файла.
 * Получаем данные обо всех продолжительностях перелетов из калькулятора.
 * Выводим данные в консоль.
 */
public class App {
    public static void main(String[] args) {

        Gson g = new Gson();
        FlightCalculator flightCalculator;

        try (InputStream fileFromResource = ResourceLoader.getFileFromResource("tickets.json");
             BufferedReader src = new BufferedReader(new InputStreamReader(fileFromResource, StandardCharsets.UTF_8))) {
            Converter<Long, String> converterToHoursAndMinutes = time -> time / 60 + "h " + time % 60 + "m";
            flightCalculator = g.fromJson(src, FlightCalculator.class);
            List<Long> alltimes = flightCalculator.getAllTimeOfFlights();
            System.out.println("Среднее время полета: " + converterToHoursAndMinutes.convert(flightCalculator.getAverageTimeOfFlights()));
            System.out.println("90-й персентиль: " + converterToHoursAndMinutes.convert(flightCalculator.getPercentile(alltimes, 90)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
