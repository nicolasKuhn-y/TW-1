package ar.edu.unlam.tallerweb1.utils;

import ar.edu.unlam.tallerweb1.exceptions.InvalidCoordinatesException;

public class Location {
    private final double currentLatitude;
    private final double currentLongitude;

    private Location(double currentLatitude, double currentLongitude) {
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
    }

    public static Location createWithCoordinates(double currentLatitude, double currentLongitude) {
        if (!isLatitudeValid(currentLatitude) || !isLongitudeValid(currentLongitude)) {
            throw new InvalidCoordinatesException("Alguno de los valores lat o long es incorrecto.");
        }

        return new Location(currentLatitude, currentLongitude);
    }

    /**
     * Devuelve la distancia de la posicion actual respecto a otra en metros.
     **/
    public double getDistanceTo(double givenLatitude, double givenLongitude) {
        double theta = currentLongitude - givenLongitude;

        double dist = Math.sin(deg2rad(currentLatitude)) * Math.sin(deg2rad(givenLatitude))
                + Math.cos(deg2rad(currentLatitude)) * Math.cos(deg2rad(givenLatitude))
                * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // millas nauticas
        dist = dist * 1852; // metros x milla nautica

        return Math.round(dist); // Redondeamos la distancia en metros
    }


    private static boolean isLatitudeValid(double latitude) {
        return latitude > -90 && latitude < 90;
    }

    private static boolean isLongitudeValid(double longitude) {
        return longitude > -180 && longitude < 180;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
