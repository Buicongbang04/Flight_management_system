package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
    public static final long serialVersionUID = 1L;
    private String flightNo;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime; // Check valid time
    private LocalDateTime arrivalTime; // Check valid time
    private int[] availableSeat;

    public Flight(String flightNo, String departureCity, String destinationCity, LocalDateTime departureTime, LocalDateTime arrivalTime, int[] availableSeat) {
        this.flightNo = flightNo;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeat = availableSeat;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int[] getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int[] availableSeat) {
        this.availableSeat = availableSeat;
    }

    @Override
    public String toString() {
        return String.format("""
                        Flight Number: %s
                        Departure City: %s
                        Destination City: %s
                        Departure Time: %s
                        Arrival Time: %s
                        Seats: %d""",
                             this.flightNo, this.departureCity, this.destinationCity, this.departureTime, this.arrivalTime, this.availableSeat.length);
    }
}
