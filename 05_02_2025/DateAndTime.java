import java.time.*;
import static java.time.Month.*;
import java.util.*;
public class DateAndTime {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        LocalTime lt = LocalTime.now();
        System.out.println(lt);
        System.out.println(lt.getHour());
        System.out.println(lt.getMinute());
        System.out.println(lt.getSecond());
        System.out.println(lt.getNano());

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        System.out.println(ld.withMonth(11));
        System.out.println(ld.withMonth(Month.SEPTEMBER.getValue()));

        LocalDate d2 = LocalDate.of(1983,SEPTEMBER, 10);
        System.out.println(d2);

        LocalTime t1 = LocalTime.now(ZoneId.of("America/Panama"));
        System.out.println(t1);

        Set<String> s = ZoneId.getAvailableZoneIds();
        s.forEach(System.out::println);
    }   
}
