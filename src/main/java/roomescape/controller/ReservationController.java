package roomescape.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.model.Reservation;
import roomescape.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> getReservations() {
        final List<Reservation> reservations = reservationService.findAll();
        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }

    @PostMapping
    public ReservationResponse save(@RequestBody final ReservationRequest reservationRequest) {
        final long id = reservationService.save(reservationRequest);
        return new ReservationResponse(id, reservationRequest.name(),
                reservationRequest.date(), reservationRequest.time());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        final List<Reservation> reservations = reservationService.findAll();
        final Optional<Reservation> findReservation = reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findAny();
        if (findReservation.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }
        reservations.remove(findReservation.get());
        return ResponseEntity.ok()
                .build();
    }
}
