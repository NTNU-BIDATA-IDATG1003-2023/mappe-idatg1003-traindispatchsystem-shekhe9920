import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

/**
 * The {@code TrainDispatchInitializer} class is responsible for initializing train trips
 * and creating instances of {@code TrainDispatchSystem}.
 * It populates a collection of train dispatch systems and allocates train numbers for each trip.
 * This class serves as a crucial step in setting up the Train Dispatch System for user interaction.
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * To use this class, create an instance of {@code TrainDispatchInitializer} and call the
 * {@code init} method to set up train trips.
 * The created {@code TrainDispatchSystem} instances are added to a collection,
 * and train numbers are allocated manually.
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.0.2 (Version of this class)
 * @since 0.0.5 (Introduced in Version 0.0.5 of the Train Dispatch System application)
 */
public class TrainDispatchInitializer {

  public static Set<TrainDispatchSystem> trainDispatchList = new TreeSet<>();

  /**
   * Constructs an instance of TrainDispatchInitializer.
   * The constructor is intentionally left empty to indicate that no
   * specific initialization logic is required.
   * The class relies on the init method for its primary functionality.
   */
  public TrainDispatchInitializer() {
  }

  /**
   * Initializes train trips and populates the trainDispatchList collection with
   * {@code TrainDispatchSystem} instances.
   * It also manually allocates train numbers for each trip and associates them with the
   * corresponding systems.
   *
   * @since 0.0.1
   */

  public void init() {

    final String departure_station = "Gjøvik";

    // Create TrainDispatchSystem instances (using the constants) and allocate train numbers
    TrainDispatchSystem trainDispatch0 = new TrainDispatchSystem(departure_station, "Oslo",
        LocalTime.of(8, 55), "F1", 1, "101");
    TrainDispatchSystem trainDispatch1 = new TrainDispatchSystem(departure_station, "Bergen",
        LocalTime.of(8, 55), "F1", 2, "102");
    TrainDispatchSystem trainDispatch2 = new TrainDispatchSystem(departure_station, "Lillehammer",
        LocalTime.of(8, 55), "F1", 3, "103");
    TrainDispatchSystem trainDispatch3 = new TrainDispatchSystem(departure_station, "Tromsø",
        LocalTime.of(15, 0), "F2", 4, "104");
    TrainDispatchSystem trainDispatch4 = new TrainDispatchSystem(departure_station, "Gardermoen",
        LocalTime.of(13, 15), "F13", 5, "105");

    // Creating 'TrainDispatchSystem' instances and adding them to the list.
    trainDispatchList.add(trainDispatch0);
    trainDispatchList.add(trainDispatch1);
    trainDispatchList.add(trainDispatch2);
    trainDispatchList.add(trainDispatch3);
    trainDispatchList.add(trainDispatch4);

  }

  /**
   * Retrieves the collection of initialized TrainDispatchSystem instances.
   *
   * @return A HashSet containing the initialized train dispatch systems.
   * @since 0.0.1
   */
  public Set<TrainDispatchSystem> getTrainDispatchList() {
    return trainDispatchList;
  }

}
