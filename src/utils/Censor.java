package utils;

import models.Flight;
import java.util.List;

public class Censor {
    public boolean checkFlightID(List<Flight> flights, Flight flight) {
        for(Flight f : flights) {
            if (f.getFlightNo().equals(flight.getFlightNo())) {
                return true;
            }
        }
        return false;
    }
}
