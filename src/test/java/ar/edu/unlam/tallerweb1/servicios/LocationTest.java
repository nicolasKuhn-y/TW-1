package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Coordinates;
import ar.edu.unlam.tallerweb1.servicios.location.Location;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LocationTest {

    @Test
    public void itShouldReturnDistanceBetweenCurrentAndGivenPointInMeters() {
        Location location = Location.createWithCoordinates(1.0, 1.0);

        Coordinates givenCoordinates = new Coordinates(0.0, 0.0);

        double distance = location.getDistanceTo(givenCoordinates);

        double expectedDistance = 157143.0;

        Assertions.assertThat(distance).isEqualTo(expectedDistance);
    }

}
