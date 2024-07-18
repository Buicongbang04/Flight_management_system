package view;

import models.*;
import utils.ReadFile;
import utils.WriteFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class FlightView {
    private Scanner scanner = new Scanner(System.in);
    public void displayMenu() {
        System.out.println("Flight Management System");
        System.out.println("1. Flight Schedule Management");
        System.out.println("2. Handle Passenger Reservation and Booking");
        System.out.println("3. Handle Passenger Check-In and Seat Allocation");
        System.out.println("4. Manage Crew and Administrator Access");
        System.out.println("5. Save Data to File");
        System.out.println("6. Print All Lists from File");
        System.out.println("7. Quit");
        System.out.print("Enter your choice: ");
    }

    public void flightManagementMenu(){
        System.out.println("1. Add Flight.");
        System.out.println("2. Return to main page.");
    }

    public void reservationMenu(){
        System.out.println("1. Search Available Flight");
        System.out.println("2. Reservation Flight");
        System.out.println("3. Return to main page.");
    }

    public void passengerMenu(){
        System.out.println("1. Check In.");
        System.out.println("2. Return to main page.");
    }

    public void loadFileMenu(){
        System.out.println("1. Load Data To Collection");
        System.out.println("2. Print Flights From File(Order By Date)");
        System.out.println("3. Return to main page.");
    }

    public int getUserChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    public Flight getFlightDetails() {
        String flightNumber;
        do{
            System.out.print("Enter flight number: ");
            flightNumber = scanner.nextLine();
            if(flightNumber.matches("^F[0-9]{4}$")){
                break;
            }
            System.out.println("Invalid flight number! The flight number must be FXXXX.");
        }while(true);

        System.out.print("Enter departure city: ");
        String departureCity = scanner.nextLine();
        System.out.print("Enter destination city: ");
        String destinationCity = scanner.nextLine();
        System.out.print("Enter departure time (yyyy-MM-dd HH:mm): ");
        LocalDateTime departureTime;
        while(true) {
            try {
                departureTime  = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                break;
            } catch (Exception e) {
                System.out.println("Invalid departure time or check the format!");
            }
        }

        System.out.print("Enter arrival time (yyyy-MM-dd HH:mm): ");
        LocalDateTime arrivalTime;
        while(true) {
            try {
                arrivalTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                break;
            } catch (Exception e) {
                System.out.println("Invalid arrival time or check the format!");
            }
        }

        System.out.print("Enter available seats: ");
        int availableSeats = Integer.parseInt(scanner.nextLine());
        return new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, new int[availableSeats]);
    }

    public void displayFlightAdded() {
        System.out.println("Flight added successfully.");
    }

    public LocalDate getArrivalDate(){
        System.out.println("Enter arrival date(yyyy-MM-dd): ");
        String arrivalDate = scanner.nextLine();
        return arrivalDate.isEmpty() ? null : LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDeparture(){
        System.out.println("Enter departure city: ");
        String departureCity = scanner.nextLine();
        return departureCity.isEmpty() ? "" : departureCity;
    }

    public String getDestination(){
        System.out.println("Enter destination city: ");
        String destinationCity = scanner.nextLine();
        return destinationCity.isEmpty() ? "" : destinationCity;
    }

    public Reservation getReservationDetails() {
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter passenger age: ");
        int passengerAge = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter passenger phone: ");
        String passengerPhone = scanner.nextLine();
        System.out.println("Enter passenger address: ");
        String passengerAddress = scanner.nextLine();
        Passenger passenger = new Passenger(passengerName, passengerAge, passengerPhone, passengerAddress);
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        String reservationId = "R" + ((int) (Math.random() * 1000 + 1));
        return new Reservation(reservationId, flightNumber, passenger);
    }

    public void displayReservationAdded(String reservationId) {
        System.out.println("Reservation added successfully.");
        System.out.println("Reservation ID:" + reservationId);
    }

    public String getReservationIdForCheckIn() {
        System.out.print("Enter reservation ID: ");
        return scanner.nextLine();
    }

    public int getSeats(){
        System.out.println("Enter your seat: ");
        int seat = scanner.nextInt();
        scanner.nextLine();
        return seat;
    }

    public void displayBoardingPass(BoardingPass boardingPass) {
        System.out.println("Check-in successful. Generating boarding pass...");
        System.out.println("Boarding Pass: " + boardingPass);
    }

    public void displayReservationNotFound() {
        System.out.println("Reservation ID not found.");
    }

    public Crew getCrewDetails() {
        System.out.print("Enter crew ID: ");
        String crewId = scanner.nextLine();
        System.out.print("Enter crew name: ");
        String name = scanner.nextLine();
        System.out.print("Enter crew role: ");
        String role = scanner.nextLine();

        return new Crew(crewId, name, role);
    }

    public void displayCrewAdded() {
        System.out.println("Crew added successfully.");
    }

    public void saveFlight(List<Flight> flights, String path) {
        WriteFile.writeToFile(path, flights);
    }

    public void saveReservation(List<Reservation> reservation, String path) {
        WriteFile.writeToFile(path, reservation);
    }

    public void saveCrew(List<Crew> crews, String path) {
        WriteFile.writeToFile(path, crews);
    }

    public void displayDataSaved() {
        System.out.println("Data saved to file successfully.");
    }

    public List<Flight> loadFlightData(String path) {
        System.out.println("Loading flight data to file...");
        List<Flight> flights = ReadFile.readFromBinary(path);
        return flights;
    }
    public void displayDataLoaded(List<Flight> flights) {
        System.out.println("Printing flight data from file...");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public void displayExitMessage() {
        System.out.println("Exiting...");
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice. Please try again.");
    }
}
