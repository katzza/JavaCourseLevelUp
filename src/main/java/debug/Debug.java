package debug;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class Debug {
    public static void main(String[] args) {

        LocalDateTime kwtVon1 = Instant.ofEpochMilli(1587625200000L).atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime();
        LocalDateTime kwtVon = Instant.ofEpochMilli(1587625200000L).atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime().truncatedTo(ChronoUnit.MINUTES);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
              assertThat(kwtVon.toLocalTime()).
                as("Zeitfenster (" + LocalTime.parse("09:00:00", timeFormat) + ") befor KWT(" + kwtVon.toLocalTime() + ")")
                .isBeforeOrEqualTo(LocalTime.parse("09:00:00", timeFormat));
    }
}
