package controllers;

import models.BoardingPass;
import models.Crew;
import models.Flight;
import models.Reservation;
import utils.Censor;
import utils.ReadFile;
import view.FlightView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightController {
    private final static String FILE_PATH_FLIGHT = "src/stores/flights.dat";
    private final static String FILE_PATH_RESERVATION = "src/stores/reservations.dat";
    private final static String FILE_PATH_CREW = "src/stores/crews.dat";
    private List<Flight> flightDB = new ArrayList<>();
    private List<Reservation> reservationDB = new ArrayList<>();
    private List<Crew> crewDB = new ArrayList<>();
    private FlightView view;
    private Censor censor = new Censor();
    private int tick;

    public FlightController(FlightView view) {
        this.view = view;
    }

    public void start() {
        while (true) {
            view.displayMenu();
            int choice = view.getUserChoice();
            switch (choice) {
                case 1:
                    while(true) {
                        view.flightManagementMenu();
                        tick = view.getUserChoice();
                        if(tick == 1) {
                            manageFlightSchedule();
                        }
                        else if (tick == 2) {
                            System.out.println("Redirect to main page...");
                            break;
                        } else {
                            System.out.println("This is not a valid option!");
                        }
                    }
                    break;
                case 2:
                    while(true) {
                        view.reservationMenu();
                        tick = view.getUserChoice();
                        if(tick == 1) {
                            handleAvailableFlights();
                        } else if (tick == 2) {
                            handlePassengerReservation();
                        } else if (tick == 3) {
                            System.out.println("Redirect to main page...");
                            break;
                        }else{
                            System.out.println("This is not a valid option!");
                        }
                    }
                    break;
                case 3:
                    while(true) {
                        view.passengerMenu();
                        tick = view.getUserChoice();
                        if(tick == 1) {
                            handlePassengerCheckIn();
                        } else if (tick == 2) {
                            System.out.println("Redirect to main page...");
                            break;
                        }else {
                            System.out.println("This is not a valid option!");
                        }
                    }
                    break;
                case 4:
                    manageCrew();
                    break;
                case 5:
                    saveDataToFile();
                    break;
                case 6:
                    while(true){
                        view.loadFileMenu();
                        tick = view.getUserChoice();
                        if(tick == 1) {
                            System.out.println("Loading file...");
                            flightDB = listFlight();
                            reservationDB = listReservation();
                            crewDB = listCrew();
                            System.out.println("Load file successful!");
                        }else if (tick == 2) {
                            printFlightsFromFile();
                        } else if (tick == 3) {
                            System.out.println("Redirect to main page...");
                            break;
                        }else {
                            System.out.println("This is not a valid option!");
                        }
                    }

                    break;
                case 7:
                    view.displayExitMessage();
                    return;
                default:
                    view.displayInvalidChoiceMessage();
            }
        }
    }

    private void manageFlightSchedule() {
            Flight flight = view.getFlightDetails();
            try {
                if (censor.checkFlightID(flightDB, flight)) {
                    throw new Exception("This flight ID is exist!");
                }
                flightDB.add(flight);
                this.updateFlight(flight);
                view.displayFlightAdded();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

    private void handleAvailableFlights() {
        String departure = view.getDeparture();
        String destination = view.getDestination();
        LocalDate arrivalDate = view.getArrivalDate();

        if(flightDB.isEmpty()){
            System.out.println("There is no flights in the database!");
            return;
        }

        if (departure.isEmpty() && destination.isEmpty() && arrivalDate == null) {
            this.showAvailableFlights(flightDB);
        }
        List<Flight> availableFlights;
        if(!departure.isEmpty() && !destination.isEmpty() && !(arrivalDate == null)){
            availableFlights = searchFlightByAll(departure, destination, arrivalDate);
        } else if(!departure.isEmpty() && !destination.isEmpty()) {
            availableFlights = searchFlightByDepartureAndDestination(destination, departure);
        } else if (!departure.isEmpty() && !(arrivalDate == null)) {
            availableFlights = searchFlightByDepartureAndArrivalDate(departure, arrivalDate);
        } else if (!destination.isEmpty() && !(arrivalDate == null)) {
            availableFlights = searchFlightByArrivalDateAndDestination(arrivalDate, destination);
        } else if (!departure.isEmpty()) {
            availableFlights = searchFlightByDeparture(departure);
        } else if (!destination.isEmpty()) {
            availableFlights = searchFlightByDestination(destination);
        }else {
            availableFlights = searchFlightByArrivalDate(arrivalDate);
        }

        showAvailableFlights(availableFlights);
    }

    private List<Flight> searchFlightByDeparture(String departure) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if (flight.getDepartureCity().equals(departure)) {
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private List<Flight> searchFlightByDestination(String destination) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if (flight.getDestinationCity().equals(destination)) {
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private List<Flight> searchFlightByArrivalDate(LocalDate arrivalDate) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if (flight.getArrivalTime().toLocalDate().equals(arrivalDate)){
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private List<Flight> searchFlightByAll(String departure, String destination, LocalDate arrivalDate) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if(flight.getDepartureCity().equals(departure) &&
                    flight.getDestinationCity().equals(destination) &&
                    flight.getArrivalTime().toLocalDate().equals(arrivalDate)){
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private List<Flight> searchFlightByDepartureAndDestination(String departure, String destination) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if (flight.getDepartureCity().equals(departure) && flight.getDestinationCity().equals(destination)) {
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private List<Flight> searchFlightByArrivalDateAndDestination(LocalDate arrivalDate, String destination) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if (flight.getArrivalTime().toLocalDate().equals(arrivalDate) && flight.getDestinationCity().equals(destination)) {
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private List<Flight> searchFlightByDepartureAndArrivalDate(String departure, LocalDate arrivalDate) {
        List<Flight> validFlight = new ArrayList<>();
        for (Flight flight : flightDB) {
            if (flight.getDepartureCity().equals(departure) && flight.getArrivalTime().toLocalDate().equals(arrivalDate)) {
                validFlight.add(flight);
            }
        }
        return validFlight;
    }

    private void showAvailableFlights(List<Flight> availableFlights) {
        if (availableFlights.isEmpty()) {
            System.out.println("There is no flights in the database!");
            return;
        }
        for (Flight flight : availableFlights) {
            System.out.println(flight);
        }
    }

    private void handlePassengerReservation() {
        Reservation reservation = view.getReservationDetails();
        reservationDB.add(reservation);
        this.updateReservation(reservation);
        view.displayReservationAdded(reservation.getReservationId());
    }

    private void handlePassengerCheckIn() {
        String reservationId = view.getReservationIdForCheckIn();

        Reservation reservation = reservationDB.stream()
                .filter(r -> r.getReservationId().equals(reservationId))
                .findFirst()
                .orElse(null);

        if (reservation != null) {
            List<Integer> availableSeats = displayAvailableSeat(reservation);
            BoardingPass boardingPass = null;
            try {
                System.out.println(availableSeats);
                int seat = view.getSeats();
                if (!availableSeats.contains(seat)) {
                    throw new Exception();
                }
                reservation.getPassenger().setSeat(seat);
                reservation.getPassenger().setCheckIn(true);
                boardingPass = new BoardingPass(reservation, reservation.getPassenger());
                reservation.getPassenger().setBoardingPass(boardingPass);
            } catch (Exception e) {
                System.out.println("Seat is not available!");
            }
            view.displayBoardingPass(boardingPass);
        } else {
            view.displayReservationNotFound();
        }
    }

    private Flight searchFlightById(String flightId) {
        for (Flight flight : flightDB) {
            if (flight.getFlightNo().equals(flightId)) {
                return flight;
            }
        }
        return null;
    }

    private List<Integer> displayAvailableSeat(Reservation reservation) {
        Flight flight = this.searchFlightById(reservation.getFlightNumber());
        List<Integer> availableSeats = new ArrayList<>();
        if(flight != null){
            int[] flightSeats = flight.getAvailableSeat();
            for (int i = 0; i < flightSeats.length; i++){
                if(flightSeats[i] == 0) availableSeats.add(i);
            }
        }
        return availableSeats;
    }

    private void manageCrew() {
        Crew crew = view.getCrewDetails();
        crewDB.add(crew);
        this.updateCrew(crew);
        view.displayCrewAdded();
    }

    private void saveDataToFile() {
        view.saveFlight(flightDB, FILE_PATH_FLIGHT);
        view.saveReservation(reservationDB, FILE_PATH_RESERVATION);
        view.saveCrew(crewDB, FILE_PATH_CREW);
        view.displayDataSaved();
    }

    private List<Flight>  listFlight(){
        return ReadFile.readFromBinary(FILE_PATH_FLIGHT);
    }

    private List<Reservation>  listReservation(){
        return ReadFile.readFromBinary(FILE_PATH_RESERVATION);
    }

    private List<Crew>  listCrew(){
        return ReadFile.readFromBinary(FILE_PATH_CREW);
    }

    private void updateFlight(Flight editFlight){
        List<Flight> flights = listFlight();
        boolean hasExist = flights.stream().anyMatch(flight -> flight.getFlightNo().equals(editFlight.getFlightNo()));

        List<Flight> updateFlights;
        if(!hasExist){
            updateFlights = new ArrayList<>(flights);
            updateFlights.add(editFlight);
        }else {
            updateFlights = new ArrayList<>();

            for (Flight flight : flights){
                if(flight.getFlightNo().equals(editFlight.getFlightNo())){
                    updateFlights.add(editFlight);
                }else {
                    updateFlights.add(flight);
                }
            }
        }
        view.saveFlight(updateFlights, FILE_PATH_FLIGHT);
    }

    private void updateReservation(Reservation editReservation){
        List<Reservation> reservations = listReservation();
        boolean hasExist = reservations.stream().anyMatch(reservation -> reservation.getReservationId().equals(editReservation.getReservationId()));

        List<Reservation> updateReservations;
        if(!hasExist){
            updateReservations = new ArrayList<>(reservations);
            updateReservations.add(editReservation);
        }else {
            updateReservations = new ArrayList<>();

            for (Reservation reservation : reservations){
                if(reservation.getReservationId().equals(editReservation.getReservationId())){
                    updateReservations.add(editReservation);
                }else {
                    updateReservations.add(reservation);
                }
            }
        }
        view.saveReservation(updateReservations, FILE_PATH_RESERVATION);
    }

    private void updateCrew(Crew editCrew){
        List<Crew> crews = listCrew();
        boolean hasExist = crews.stream().anyMatch(crew -> crew.getCrewId().equals(editCrew.getCrewId()));

        List<Crew> updateCrews;
        if(!hasExist){
            updateCrews = new ArrayList<>(crews);
            updateCrews.add(editCrew);
        }else {
            updateCrews = new ArrayList<>();

            for (Crew crew : crews){
                if(crew.getCrewId().equals(editCrew.getCrewId())){
                    updateCrews.add(editCrew);
                }else {
                    updateCrews.add(crew);
                }
            }
        }
        view.saveCrew(updateCrews, FILE_PATH_CREW);
    }

    private void printFlightsFromFile() {
        List<Flight> flights = listFlight().stream().sorted(
                (o1, o2) -> o1.getDepartureTime().compareTo(o2.getDepartureTime())
        ).toList();
        view.displayDataLoaded(flights);
    }
}

