import edu.ntnu.stud.TrainDispatchSystem;
import java.time.LocalTime;
import java.util.HashSet;
import trainmanager.TrainManager;

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
 * @version 0.0.1
 * @since 0.0.1
 */
public class TrainDispatchInitializer {

  private HashSet<TrainDispatchSystem> trainDispatchList = new HashSet<>();
  private TrainManager trainManager = new TrainManager();

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
        LocalTime.of(8, 55), "F1", 1);
    TrainDispatchSystem trainDispatch1 = new TrainDispatchSystem(departure_station, "Bergen",
        LocalTime.of(8, 55), "F1", 2);
    TrainDispatchSystem trainDispatch2 = new TrainDispatchSystem(departure_station, "Lillehammer",
        LocalTime.of(8, 55), "F1", 3);
    TrainDispatchSystem trainDispatch3 = new TrainDispatchSystem(departure_station, "Tromsø",
        LocalTime.of(15, 0), "F2", 4);
    TrainDispatchSystem trainDispatch4 = new TrainDispatchSystem(departure_station, "Gardermoen",
        LocalTime.of(13, 15), "F13", 5);

    // Create TrainDispatchSystem instances and allocate train numbers.
    trainDispatchList.add(trainDispatch0);
    trainDispatchList.add(trainDispatch1);
    trainDispatchList.add(trainDispatch2);
    trainDispatchList.add(trainDispatch3);
    trainDispatchList.add(trainDispatch4);


    // Manually set train numbers
    //String trainNumber0 = "123456";
    trainDispatch0.setTrainNumber("101");
    //String trainNumber1 = "641453";
    trainDispatch1.setTrainNumber("102");
    //String trainNumber2 = "765763";
    trainDispatch2.setTrainNumber("103");
    //String trainNumber3 = "808797";
    trainDispatch3.setTrainNumber("104");
    //String trainNumber4 = "9458745";
    trainDispatch4.setTrainNumber("105");

    //trainManager.markTrainNumberAsAllocated(trainDispatch0.getTrainNumber(), trainDispatch0);
    //trainManager.markTrainNumberAsAllocated("123456", trainDispatch0);
  }

  /**
   * Retrieves the collection of initialized TrainDispatchSystem instances.
   *
   * @return A HashSet containing the initialized train dispatch systems.
   * @since 0.0.1
   */
  public HashSet<TrainDispatchSystem> getTrainDispatchList() {
    return trainDispatchList;
  }
}
