package roomescape.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import roomescape.model.Reservation;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ReservationDaoTest {

    @Autowired
    ReservationDao reservationDao;

    @DisplayName("예약 저장")
    @Test
    void saveReservation() {
        final List<Reservation> beforeSaving = reservationDao.findAll();
        reservationDao.save("레디", "2024-02-03", "15:00");
        final List<Reservation> afterSaving = reservationDao.findAll();

        assertAll(
                () -> assertThat(beforeSaving).isEmpty(),
                () -> assertThat(afterSaving).hasSize(1));
    }
}
