package models;

public class BoardingPass {
    private Reservation reservation;
    private Passenger passenger;
    public BoardingPass(Reservation reservation, Passenger passenger){
        this.reservation = reservation;
        this.passenger = passenger;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return String.format("Reservation: %s\nCheck In: %b\nSeat: %d", reservation, passenger.isCheckIn(), passenger.getSeat());
    }
}
