import controllers.FlightController;
import view.FlightView;

public class Main {
    public static void main(String[] args) {
        FlightView view = new FlightView();
        FlightController controller = new FlightController(view);
        controller.start();
    }
}
