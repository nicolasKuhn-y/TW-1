package ar.edu.unlam.tallerweb1.utils;


import ar.edu.unlam.tallerweb1.shared.Coordinates;

public class Location {
    private final Coordinates coordinates;

    private Location(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public static Location createWithCoordinates(double currentLatitude, double currentLongitude) {
        return new Location(new Coordinates(currentLatitude, currentLongitude));
    }

    /**
     * Devuelve la distancia de la posicion actual respecto a otra en metros.
     **/
    public double getDistanceTo(Coordinates givenCoordinates) {
        double theta = coordinates.getLongitude() - givenCoordinates.getLongitude();

        double dist = Math.sin(deg2rad(coordinates.getLatitude())) * Math.sin(deg2rad(givenCoordinates.getLatitude()))
                + Math.cos(deg2rad(coordinates.getLatitude())) * Math.cos(deg2rad(givenCoordinates.getLatitude()))
                * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // millas nauticas
        dist = dist * 1852; // metros x milla nautica

        return Math.round(dist); // Redondeamos la distancia en metros
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
