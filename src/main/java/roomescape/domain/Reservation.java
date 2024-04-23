package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reservation {
    private static final Long DEFAULT_ID_VALUE = 0L;

    private final Long id;
    private final ClientName clientName;
    private final LocalDate date;
    private final LocalTime time;

    public Reservation(final ClientName clientName, final LocalDate date, final LocalTime time) {
        this(DEFAULT_ID_VALUE, clientName, date, time);
    }

    public Reservation(final Long id, final ClientName clientName, final LocalDate date, final LocalTime time) {
        validateReservationDateAndTime(date, time);
        this.id = id;
        this.clientName = clientName;
        this.date = date;
        this.time = time;
    }

    private void validateReservationDateAndTime(final LocalDate date, final LocalTime time) {
        LocalDateTime reservationLocalDateTime = LocalDateTime.of(date, time);
        if (reservationLocalDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("현재 날짜보다 이전 날짜를 예약할 수 없습니다.");
        }
    }

    public Reservation initializeIndex(final Long reservationId) {
        return new Reservation(reservationId, clientName, date, time);
    }

    public Long getId() {
        return id;
    }

    public ClientName getClientName() {
        return clientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
