package org.example.ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Nikolay Melnikov
 */
public class FlightCalculator {
    private ArrayList<Ticket> tickets;

    /**
     * Для начала получаем коллекцию из времени перелета всех рейсов,
     * для удобства ведем расчет в минутах.
     */
    public List<Long> getAllTimeOfFlights() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        List<Long> flightTimes = new ArrayList<>();
        tickets.forEach(ticket -> {
            LocalDateTime departure = LocalDateTime.parse(ticket.getDeparture_date() + " " + ticket.getDeparture_time(), dtf);
            LocalDateTime arrival = LocalDateTime.parse(ticket.getArrival_date() + " " + ticket.getArrival_time(), dtf);
            long minutes = ChronoUnit.MINUTES.between(departure, arrival);

            flightTimes.add(minutes);
        });


        return flightTimes;
    }

    /**
     * Получение среднего значения продолжительности перелетов.
     */

    public Long getAverageTimeOfFlights() {
        List<Long> flightTimes = getAllTimeOfFlights();
        long sum = 0;
        for (Long time: flightTimes) {
            sum += time;
        }
        return sum / flightTimes.size();
    }

    /**
     * Получение персентиля, в данном случае, метод позволяет получить любой персентиль.
     */

    public long getPercentile(List<Long> data, int percentile) {
        Collections.sort(data);
        int index = (int) Math.ceil(percentile/100.0 * data.size());
        return data.get(index - 1);
    }
}
