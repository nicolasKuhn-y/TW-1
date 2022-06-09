package ar.edu.unlam.tallerweb1.utils;

import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LocationTest {

    @Test(expected = InvalidCoordinatesException.class)
    public void itShouldThrowAnErrorIfOneOfTheValuesIsIncorrect() {
        double lat = 400;
        double lng = 25.564;

        Location.createWithCoordinates(lat, lng);
    }

    @Test
    public void itShouldReturnDistanceBetweenCurrentAndGivenPointInMeters() {
        Location location = Location.createWithCoordinates(1.0, 1.0);

        double distance = location.getDistanceTo(0.0, 0.0);

        double expectedDistance = 157143.0;

        Assertions.assertThat(distance).isEqualTo(expectedDistance);
    }

}
