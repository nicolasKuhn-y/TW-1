package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.repositorios.reserve.ReserveRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Rollback
public class ReserveRepositoryTest extends SpringTest {

    @Autowired
    private ReserveRepository reserveRepository;

    @Test
    public void itShouldGetAllTheReservesGivenUser() {
        User user = new User();
        user.setId(1L);

        Reserve reserve1 = new Reserve();
        Reserve reserve2 = new Reserve();

        reserve1.setUser(user);
        reserve2.setUser(user);

        this.session().save(user);
        this.session().save(reserve1);
        this.session().save(reserve2);

        List<Reserve> reservesByUser = reserveRepository.getReservesByUser(user.getId());

        Assertions.assertThat(reservesByUser).hasSize(2);
    }


    @Test
    public void itShouldSaveReserve() {
        Reserve reserve = new Reserve();

        Reserve createdReserve = reserveRepository.makeReserve(reserve);

        Assertions.assertThat(createdReserve).isNotNull();
    }

}
