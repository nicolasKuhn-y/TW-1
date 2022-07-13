package ar.edu.unlam.tallerweb1.modelo;

public class CalendarReserve {
    private final String hospitalName;
    private final String date;

    public CalendarReserve(String hospitalName, String date) {
        this.hospitalName = hospitalName;
        this.date = date;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CalendarReserve{" +
                "hospitalName='" + hospitalName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
