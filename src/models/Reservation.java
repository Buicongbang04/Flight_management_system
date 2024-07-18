package models;

import java.io.Serializable;

public class Reservation implements Serializable {
    public static final long serialVersionUID = 1L;
    private String reservationId;
    private String flightNumber;
    private Passenger passenger;

    public Reservation(String reservationId, String flightNumber, Passenger passenger) {
        this.reservationId = reservationId;
        this.flightNumber = flightNumber;
        this.passenger = passenger;
    }

    // Getters and setters

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return String.format("Reservation ID: %s\nFlight number: %s\nPassenger: %s\n", this.reservationId, this.flightNumber, this.passenger);
    }
}
