package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Double latitude;

    private Double longitude;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    @Column(length = 500)
    private String address;

    @Column(name = "appointments_mount")
    private Integer appointmentsAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(latitude, longitude);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAppointmentsAmount() {
        return appointmentsAmount;
    }

    public void setAppointmentsAmount(Integer appointmentsAmount) {
        this.appointmentsAmount = appointmentsAmount;
    }

    public void reduceAppointmentsAmount(int quantity) {
        setAppointmentsAmount(getAppointmentsAmount() - quantity);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
