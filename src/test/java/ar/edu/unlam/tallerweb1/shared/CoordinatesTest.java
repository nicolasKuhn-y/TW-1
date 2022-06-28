package ar.edu.unlam.tallerweb1.shared;

import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;
import ar.edu.unlam.tallerweb1.modelo.Coordinates;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CoordinatesTest {

    @Test
    public void itShouldReturnAValidCoordinatesObject() {
        double lat = 25.0;
        double lng = 25.564;

        Coordinates coordinates =  new Coordinates(lat, lng);

        Assertions.assertThat(coordinates.getLatitude()).isEqualTo(lat);
        Assertions.assertThat(coordinates.getLongitude()).isEqualTo(lng);
    }


    @Test(expected = InvalidCoordinatesException.class)
    public void itShouldThrowAnErrorIfOneOfTheValuesIsIncorrect() {
        double lat = 400;
        double lng = 25.564;

        new Coordinates(lat, lng);
    }
}
