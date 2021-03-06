package ar.edu.unlam.tallerweb1.servicios.hospital;

import ar.edu.unlam.tallerweb1.modelo.Hospital;
import ar.edu.unlam.tallerweb1.servicios.location.Location;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Hospital> {
    private final Location currentLocation;

    public DistanceComparator(Location location) {
        this.currentLocation = location;
    }

    @Override
    public int compare(Hospital o1, Hospital o2) {
        Double distance1 = currentLocation.getDistanceTo(o1.getCoordinates());
        Double distance2 = currentLocation.getDistanceTo(o2.getCoordinates());

        return distance1.compareTo(distance2);
    }
}
