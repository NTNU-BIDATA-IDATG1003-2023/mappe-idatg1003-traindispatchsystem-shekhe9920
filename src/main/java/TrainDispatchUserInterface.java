import edu.ntnu.stud.TrainDispatchSystem;
import java.util.HashSet;



/**
 * The {@code TrainDispatchUserInterface} class manages the user interface of the
 * Train Dispatch System.
 * It is responsible for presenting train dispatch information.
 * This class collaborates with the {@code TrainManager} and {@code TrainDispatchSystem}
 * classes to provide a comprehensive user experience for handling and viewing train-related data.
 *
 * <p>The primary functionalities of this class include:
 *
 * <blockquote><pre>
 *   Display train dispatch information in a tabular format, including departure
 *   stations, destinations, departure times, tracks, train lines and allocated
 *   train numbers
 * </pre></blockquote>
 *
 * <p>To utilize this class, you can create an instance of {@code TrainDispatchUserInterface} and
 * call the start method to present train dispatch information.
 * Ensure that train trips are initialized using the {@code TrainDispatchInitializer} class before
 * using this user interface
 *
 * @author Karwan Shekhe
 * @version 0.0.3
 * @since 0.0.1
 */
public class TrainDispatchUserInterface {
  private HashSet<TrainDispatchSystem> trainDispatchList;

  /**
   * Initializes the TrainDispatchUserInterface with a collection of train dispatch systems.
   *
   * @param trainDispatchList A HashSet of TrainDispatchSystem instances representing train trips.
   * @since 0.0.3
   */
  public TrainDispatchUserInterface(HashSet<TrainDispatchSystem> trainDispatchList) {
    this.trainDispatchList = trainDispatchList;
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
