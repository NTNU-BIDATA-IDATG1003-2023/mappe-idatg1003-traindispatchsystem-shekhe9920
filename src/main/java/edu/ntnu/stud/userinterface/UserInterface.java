package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.register.TrainManager;
import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.util.Iterator;
import java.util.Scanner;



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
 * <p>To utilize this class, you can create an instance of {@code edu.ntnu.stud.userinterface.TrainDispatchUserInterface} and
 * call the {@code start} method to initilate the user interface. Ensure that train trips
 * are initialized using the {@code edu.ntnu.stud.register.TrainDispatchInitializer} class before
 * using this user interface.
 *
 * <p>The class follows a menu-driven approach where the user can choose from various options.
 *
 * <p>This class incorporates feedback messages through the {@code FeedBackMessages} utility,
 * enhancing user intreaction and providing information about the systems's status.
 *
 * <p>Example:
 * <blockquote><pre>
 *   // Initialize the edu.ntnu.stud.register.TrainDispatchInitializer
 *   edu.ntnu.stud.register.TrainDispatchInitializer initializer = new edu.ntnu.stud.register.TrainDispatchInitializer();
 *   initializer.init();
 *
 *   // Get the train dispatch list from the initializer
 *   Set<TrainDispatchSystem> trainDispatchList =
 *       initializer.getTrainDispatchList();
 *
 *   // Create an instance of edu.ntnu.stud.userinterface.TrainDispatchUserInterface and pass the list
 *   edu.ntnu.stud.userinterface.TrainDispatchUserInterface ui =
 *       new edu.ntnu.stud.userinterface.TrainDispatchUserInterface(trainDispatchList);
 *
 *   // Calls the start method
 *   ui.start();
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.1.2 (Version of this class.)
 * @since 0.0.3 (Introduced in Version 0.0.3 of the Train Dispatch System application)
 */
public class UserInterface {
  private InformationDisplay display;
  private TrainRegister trainRegister;
  private TrainManager trainManager;
  private Scanner scanner;
  private ConfigureUserOptions userOptions;


  /**
   * Constructs a new {@code UserInterface} object. This constructor initializes
   * the {@code TrainRegister}, {@code Scanner}, {@code ConfigurationUserOptions}
   * and {@code InformationDisplay} objects.
   *
   * @param trainDispatchList The train dispatch list.
   * @since 0.0.3
   */
  public UserInterface(Iterator<TrainDispatchSystem> trainDispatchList,
      TrainRegister trainRegister) {

    this.trainRegister = trainRegister;
    this.trainManager = new TrainManager();
    this.trainRegister.initializeDepartureRegister();
    this.scanner = new Scanner(System.in);
    this.userOptions = new ConfigureUserOptions(trainRegister, trainManager);
    this.display = new InformationDisplay(trainDispatchList);

  }

  /**
   * Starts the user interface.
   *
   * @since 0.0.1
   */
  private boolean exitRequested = false;
  public void start() {
    try {
      display.displayCurrentStationAndTime();
      display.displayWelcomeText();

      while (!exitRequested) {
        int choice = displayMenu();
        processUserChoice(choice);
      }
    } finally {
      closeScanner();
    }
  }

  /**
   * Closes the scanner.
   *
   * @since 0.1.1
   */
  private void closeScanner() {
    if (scanner != null) {
      scanner.close();
    }
  }


  /**
   * Displays the main menu, ang get the user's choice.
   *
   * @return An integer representing the user's choice (1-9).
   * @since 0.0.4
   */
  public int displayMenu() {
    display.displayMenuContent();

    int option;
    try {
      option = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid choice. Please try again.");
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

  public void processUserChoice(int choice) {
    while (choice != 9) {
      switch (choice) {

        case 1:
          // View train dispatch information table
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          display.displayTrainDispatchListTable(trainRegister.getTrainDispatchListIterator());
          break;

        case 2:
          // Add a new train departure
          userOptions.addNewTrainDeparture();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        case 3:
          // Set delay for a train departure
          userOptions.setDelayForTrainDeparture();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        case 4:
          // Assign a track to a train departure
          userOptions.searchDepartureBasedOnTrainNumber();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        case 5:
          // Add a delay to a train departure
          userOptions.searchDepartureBasedOnDestination();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        case 6:
          // Search for a train departure based on train number
          userOptions.searchDepartureBasedOnDepartureTime();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        case 7:
          // Sort departures based on departure time
          userOptions.sortDepartureListBasedOnDepartureTime();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        case 8:

          userOptions.updateTrainDepartureList();
          display.updateIterator(trainRegister.getTrainDispatchListIterator());
          break;

        default:
          // Displays an error message for invalid choice
          System.out.println("Invalid choice. Please try again.");
          break;
      }

      // Asking the user if they want to continue or exit.
      System.out.println("Do you want to go back to the main menu (press 0), "
          + "or to exit the application (press 9)?");
      try {
        choice = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        // Displaying an error message for invalid choice
        System.out.println("Invalid choice. Please try again.");
      }

      if (choice == 9) {
        // Exits the loop when the user selects option 9
        System.out.println("Exiting the application. Goodbye!"); // Displays a message before exiting
        exitRequested = true;
        return;
      } else if (choice == 0) {

        return;
      }
    }
  }
}
