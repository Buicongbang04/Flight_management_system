package models;

import java.io.Serializable;

public class Passenger extends Person implements Serializable {
    public static final long serialVersionUID = 1L;
    private String phoneNumber;
    private String address;
    private boolean checkIn;
    private BoardingPass boardingPass;
    private int seat;

    public Passenger(String name, int age, String phoneNumber, String address) {
        super(name, age);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.checkIn = false;
        this.boardingPass = null;
        this.seat = -1;
    }

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return super.toString() + "\nphoneNumber=" + phoneNumber + "\naddress=" + address;
    }
}
