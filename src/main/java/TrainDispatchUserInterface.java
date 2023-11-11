import edu.ntnu.stud.TrainDispatchSystem;
import guiutility.ConfigurationAppOptions;
import guiutility.FeedBackMessages;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Set;
import trainmanager.TrainManager;



/**
 * The {@code TrainDispatchUserInterface} class manages the user interface of the
 * Train Dispatch System.
 * It is responsible for presenting train dispatch information and simplifying user interaction.
 * This class collaborates with the {@code TrainManager}, {@code TrainDispatchSystem},
 * {@code FeedBackMessages} and {@code ConfigurationAppOptions} classes
 * to provide a comprehensive user experience for handling and viewing train-related data.
 *
 * <p>The primary functionalities of this class include:
 * <ul>
 *     <li>Displaying train dispatch information in a tabular format,
 *         including departure stations, destinations, departure times, tracks,
 *         train lines, and allocated train numbers.</li>
 *     <li>Adding new train departures to the system.</li>
 *     <li>Assigning tracks to train departures.</li>
 *     <li>Adding delays to train departures.</li>
 *     <li>Searching for train departures based on train numbers, destinations,
 *         or departure times.</li>
 *     <li>Updating station times based on user input.</li>
 * </ul>
 *
 * <p>To utilize this class, you can create an instance of {@code TrainDispatchUserInterface} and
 * call the {@code start} method to initilate the user interface. Ensure that train trips
 * are initialized using the {@code TrainDispatchInitializer} class before
 * using this user interface.
 *
 * <p>The class follows a menu-driven approach where the user can choose from various options.
 *
 * <p>This class incorporates feedback messages through the {@code FeedBackMessages} utility,
 * enhancing user intreaction and providing information about the systems's status.
 *
 * <p>Example:
 * <blockquote><pre>
 *   // Initialize the TrainDispatchInitializer
 *   TrainDispatchInitializer initializer = new TrainDispatchInitializer();
 *   initializer.init();
 *
 *   // Get the train dispatch list from the initializer
 *   Set<TrainDispatchSystem> trainDispatchList =
 *       initializer.getTrainDispatchList();
 *
 *   // Create an instance of TrainDispatchUserInterface and pass the list
 *   TrainDispatchUserInterface ui =
 *       new TrainDispatchUserInterface(trainDispatchList);
 *
 *   // Calls the start method
 *   ui.start();
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.0.9 (Version of this class.)
 * @since 0.0.3 (Introduced in Version 0.0.3 of the Train Dispatch System application)
 */
public class TrainDispatchUserInterface {
  final FeedBackMessages feedBackMessages;
  private Set<TrainDispatchSystem> trainDispatchList;
  private TrainManager trainManager;
  private final Scanner scanner;
  private ConfigurationAppOptions configurationAppOptions;


  /**
   * Initializes the TrainDispatchUserInterface with a collection of train dispatch systems.
   *
   * @param trainDispatchList A HashSet of TrainDispatchSystem instances representing train trips.
   * @since 0.0.3
   */
  public TrainDispatchUserInterface(Set<TrainDispatchSystem> trainDispatchList) {
    this.trainDispatchList = trainDispatchList;
    this.trainManager = new TrainManager();
    this.scanner = new Scanner(System.in);
    feedBackMessages = new FeedBackMessages(this.trainDispatchList);
    this.configurationAppOptions =
        new ConfigurationAppOptions(feedBackMessages, trainDispatchList, scanner);
  }

  /**
   * Starts the user interface.
   *
   * @since 0.0.1
   */
  public void start() {
    feedBackMessages.displayCurrentTime();
    feedBackMessages.displayWelcomeText();
    int choice;
    do {
      choice = displayMenu();
      processUserChoice(choice);
    } while (choice != 9);
    scanner.close();
  }


  /**
   * Displays the main menu, ang get the user's choice.
   *
   * @return An integer representing the user's choice (1-9).
   * @since 0.0.4
   */
  public int displayMenu() {
    //Scanner scanner = new Scanner(System.in);
    feedBackMessages.displayMenuContent();

    int option;
    try {
      option = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      feedBackMessages.INVALID_CHOICE();
      return displayMenu();
    }

    return option;
  }


  /**
   * Processes the user's choice.
   *
   * @param choice The user's choice (1-9).
   * @since 0.0.4
   */
  // kansje bytte til en fornuftig navn
  public void processUserChoice(int choice) {
    while (choice != 9) {
      switch (choice) {
        case 1:

          // View train dispatch information
          feedBackMessages.displayInformationTable();
          break;
        case 2:

          // Add a new train departure
          configurationAppOptions.addNewTrainDeparture();
          break;
        case 3:

          // Assign a track to a train departure
          configurationAppOptions.assignTrackToTrainDeparture();
          break;
        case 4:

          // Add a delay to a train departure
          configurationAppOptions.addDelayToTrainDeparture();
          break;
        case 5:

          // Search for a train departure based on train number
          configurationAppOptions.searchTrainDepartureByTrainNumber();
          break;
        case 6:

          // Search for a train departure based on destination
          configurationAppOptions.searchTrainDepartureByDestination();
          break;
        case 7:

          // Search for a train departure based on departure time
          configurationAppOptions.searchTrainDepartureByDepartureTime();
          break;
        case 8:

          // Update station time from user input
          configurationAppOptions.updateStationTimeFromUserInput();
          break;
        case 9:
          // Exit the application
          feedBackMessages.EXITING_APPLICATION();  // Displays a message before exiting
          return;
        default:
          feedBackMessages.INVALID_CHOICE();       // Display an error message for invalid choice
          break;
      }

      feedBackMessages.CONTINUE_OR_EXIT();         // Ask the user if they want to continue or exit.
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        feedBackMessages.INVALID_CHOICE();        // Display an error message for invalid choice
      }
    }
  }
}
