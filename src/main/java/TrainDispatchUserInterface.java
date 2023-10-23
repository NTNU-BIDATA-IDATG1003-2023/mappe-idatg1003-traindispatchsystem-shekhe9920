import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.HashSet;


/**
 * The {@code TrainDispatchUI} class is responsible for managing the user interface of
 * the Train Dispatch System.
 * It handles the initialization of train trips and the presentation of train dispatch
 * information in a tabular format.
 * This class interface with the {@code TrainManager} and {@code TrainDispatchSystem} classes
 * to provide a comprehensive
 * user experience for managing and viewing train-related data.
 *
 * <p>The primary functionalities of this class include:
 *
 * <blockquote><pre>
 * 1. Initializing train trips by creating instance of {code TrainDispatchSystem}
 *     and manually allocating train numbers.
 * 2. Display train dispatch information in a tabular format, including departure
 *     stations, destinations, departure times, tracks, train lines and allocated train numbers
 * </pre></blockquote>
 *
 * <p>To utilize this class, you can create an instance of {@code TrainDispatchUI}, call the
 * {@code init} method to set up initial train trips, and call the {@code start} method to lunch the
 * user interface and present train dispatch information.
 *
 * @author Karwan Shekhe
 * @version 0.0.2
 * @since 0.0.1
 */
public class TrainDispatchUserInterface {
  private HashSet<TrainDispatchSystem> trainDispatchList = new HashSet<>();


  /**
   * Initializes the train trips in the system.
   * Creates instances of {@code TrainDispatchSystem} and manually allocates train numbers.
   * This method prepares the initial dataset of train dispatch information for the display.
   *
   * @since 0.0.1
   */
  public void init() {
    // Create TrainDispatchSystem instances and allocate train numbers
    TrainDispatchSystem trainDispatch0 = new TrainDispatchSystem("Gjøvik", "Oslo",
        LocalTime.of(8, 55), "F1", 4);
    TrainDispatchSystem trainDispatch1 = new TrainDispatchSystem("Gjøvik", "Bergen",
        LocalTime.of(8, 55), "F1", 2);
    TrainDispatchSystem trainDispatch2 = new TrainDispatchSystem("Gjøvik", "Lillehammer",
        LocalTime.of(8, 55), "F1", 2);
    TrainDispatchSystem trainDispatch3 = new TrainDispatchSystem("Gjøvik", "Tromsø",
        LocalTime.of(15, 0), "F2", 5);
    TrainDispatchSystem trainDispatch4 = new TrainDispatchSystem("Gjøvik", "Gardermoen",
        LocalTime.of(13, 15), "F13", 2);

    // Create TrainDispatchSystem instances and allocate train numbers.
    trainDispatchList.add(trainDispatch0);
    trainDispatchList.add(trainDispatch1);
    trainDispatchList.add(trainDispatch2);
    trainDispatchList.add(trainDispatch3);
    trainDispatchList.add(trainDispatch4);


    // Manually set train numbers
    String trainNumber0 = "123456";
    trainDispatch0.setTrainNumber(trainNumber0);
    String trainNumber1 = "641453";
    trainDispatch1.setTrainNumber(trainNumber1);
    String trainNumber2 = "765763";
    trainDispatch2.setTrainNumber(trainNumber2);
    String trainNumber3 = "808797";
    trainDispatch3.setTrainNumber(trainNumber3);
    String trainNumber4 = "946878";
    trainDispatch4.setTrainNumber(trainNumber4);

  }

  /**
   * Starts the user interface for displaying train dispatch information in a tabular format.
   * It prints a table with columns for departure station, destination, departure time,
   * track, line and train number.
   *
   * @since 0.0.1
   */
  public void start() {
    // Display the table header
    System.out.println("----------------------------------------"
        + "------------------------------------------");
    System.out.println("| Departure Station | Destination | "
        + "Departure Time | Track | Line | Train Number |");
    System.out.println("-----------------------------------"
        + "-----------------------------------------------");

    // Display information about train trips in a tabular format
    for (TrainDispatchSystem trainDispatch : trainDispatchList) {

      System.out.printf("| %-17s | %-11s | %-13s  | %-5d | %-4s | %-12s |%n",
          trainDispatch.getDepartureStation(),
          trainDispatch.getDestination(),
          trainDispatch.getDepartureTime(),
          trainDispatch.getTrack(),
          trainDispatch.getLine(),
          trainDispatch.getTrainNumber());
    }

    // Display the table footer
    System.out.println("------------------------------------------"
        + "----------------------------------------");
  }
}
