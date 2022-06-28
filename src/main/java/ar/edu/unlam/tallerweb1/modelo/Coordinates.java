package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;

public class Coordinates {
    private final Double latitude;
    private final Double longitude;

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        validateParameters();
    }

    private void validateParameters() {
        if (!isLatitudeValid() || !isLongitudeValid())
            throw new InvalidCoordinatesException("Alguno de los valores lat o long es incorrecto.");
    }

    private boolean isLatitudeValid() {
        return latitude > -90 && latitude < 90;
    }

    private boolean isLongitudeValid() {
        return longitude > -180 && longitude < 180;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

}
