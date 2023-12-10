package edu.ntnu.stud.userinterface;

import edu.ntnu.stud.register.TrainRegister;
import java.util.Scanner;



/**
 * The {@code UserInterfaceManager} handles the user interface for the Train Dispatch System.
 * It collaborates with various classes,
 * including {@code TrainManager} and {@code ConfigurationAppOptions},
 * to provide an interactive experience for managing and viewing data.
 *
 * <p>Key features:
 * <ul>
 *     <li>Displays train dispatch information in a tabular format.</li>
 *     <li>Allows adding new train departures, assigning tracks, and adding delays.</li>
 *     <li>Enables searching for train departures based on numbers, destinations,
 *     or departure times.</li>
 *     <li>Facilitates updating station times based on user input.</li>
 * </ul>
 *
 * <p>Usage:
 * <blockquote><pre>
 *{@code
 *   // Initialize the TrainDispatchInitializer
 *   TrainRegister initializer = new TrainRegister();
 *   initializer.initializeDepartureRegister();
 *
 *   // Creates an instance of TrainDispatchUserInterface and pass the list
 *   UserInterfaceManager ui = new UserInterfaceManager(initializer);
 *
 *   // Create an instance of {@code UserInterfaceManager} and pass the list
 *   UserInterfaceManager uiManager = new UserInterfaceManager(trainDispatchList);
 *
 *   // Calls the start method
 *   uiManager.start();
 * }
 * </pre></blockquote>
 *
 * <p>The class uses a menu-driven approach, allowing users to choose from various options.
 * It integrates feedback messages for enhanced user interaction and
 * provides real-time system status updates.
 * </p>
 *
 * @author Karwan Shekhe
 * @version 0.1.4 (Version of this class.)
 * @since 0.0.3 (Introduced in Version 0.0.3 of the Train Dispatch System application)
 */
public class UserInterfaceManager {

  private final UserOptionsManager userOptions;
  private final TrainRegister trainRegister;
  private final InformationDisplay display;
  private final Scanner scanner;

  // Flag to indicate if the user wants to exit the application:
  private boolean exitRequested = false;


  // ANSI escape codes for colors and text formatting:
  private static final String LIGHT_YELLOW = "\u001B[93m";  // Light yellow
  private static final String ANSI_RED = "\u001B[31m";      // Red
  private static final String ANSI_RESET = "\u001B[0m";     // Reset


  // Error message for invalid choice:
  private static final String INVALID_CHOICE =
      (ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);


  // Page separator:
  private static final String PAGE_SEPARATOR =
      LIGHT_YELLOW + "---------------------------------------------------"
          + "--------------------------------------------------" + ANSI_RESET;


  // Menu options:
  private static final int RETURN_TO_MAIN_MENU = 0;
  private static final int DISPLAY_TRAIN_DISPATCH_TABLE = 1;
  private static final int ADD_NEW_TRAIN_DEPARTURE = 2;
  private static final int ADD_DELAY_FOR_TRAIN_DEPARTURE = 3;
  private static final int ASSIGN_TRACK_TO_TRAIN_DEPARTURE = 4;
  private static final int SEARCH_DEPARTURE_BASED_ON_TRAIN_NUMBER = 5;
  private static final int SEARCH_DEPARTURE_BASED_ON_DESTINATION = 6;
  private static final int SEARCH_DEPARTURE_BASED_ON_DEPARTURE_TIME = 7;
  private static final int SORT_DEPARTURE_LIST_BASED_ON_DEPARTURE_TIME = 8;
  private static final int REMOVE_SPECIFIC_DEPARTURE = 9;
  private static final int REMOVE_ALL_DEPARTURES = 10;
  private static final int REMOVE_DEPARTURES_THAT_HAVE_PASSED = 11;
  private static final int UPDATE_STATION_TIME = 12;
  private static final int DISPLAY_GUIDE_MESSAGE = 13;
  private static final int EXIT_APPLICATION = 14;


  /**
   * Constructs a new {@code UserInterface} object. This constructor initializes
   * the {@code TrainRegister}, {@code Scanner}, {@code ConfigurationUserOptions}
   * and {@code InformationDisplay} objects.
   *
   * @param trainRegister The train register to be used.
   * @since 0.0.3
   */
  public UserInterfaceManager(TrainRegister trainRegister) {
    this.trainRegister = trainRegister;
    this.trainRegister.initializeDepartureRegister();
    this.scanner = new Scanner(System.in);
    this.userOptions = new UserOptionsManager(trainRegister);
    this.display = new InformationDisplay(trainRegister, System.out);
  }



  /**
   * Starts the user interface, allowing users to interact with the Train Dispatch System.
   *
   * @since 0.0.1
   */
  public void start() {
    try {

      System.out.println(PAGE_SEPARATOR);
      display.displayWelcomeText();
      display.displayCurrentStationAndTime();

      while (!exitRequested) {          // Loops until the user requests to exit the application
        int choice = displayMenu();
        processUserChoice(choice);
      }
    } finally {
      closeScanner();                  // Closes the scanner
    }
  }



  /**
   * Closes the scanner used for user input.
   *
   * @since 0.1.1
   */
  private void closeScanner() {
    if (scanner != null) {
      scanner.close();     // Closes the scanner
    }
  }



  /**
   * Displays the main menu, and get the user's choice.
   *
   * @return An integer representing the user's choice.
   * @since 0.0.4
   */
  public int displayMenu() {
    display.displayMenuContent();

    int option;
    try {
      option = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println(INVALID_CHOICE);  // Displays the error message for invalid input
      return displayMenu();                // Prompts the user to enter a valid choice
    }

    return option;
  }


  /**
   * Prompts the user to choose between returning to the main menu or exiting the application.
   * This method is called after the user has executed a functionality.
   * <p>
   * It prevents other inputs when the user is not in the main menu.
   * </p>
   *
   * @return  An integer representing the user's choice.
   * @throws NumberFormatException if the user enters a non-integer value.
   * @since 0.0.
   */
  private int promptForMenuOrExit() {
    String invalidChoice = "\nPlease enter 0 (back to the menu) or 14 (exit the application):";
    System.out.println(PAGE_SEPARATOR);
    System.out.println(LIGHT_YELLOW + "Do you want to go back to the main menu (enter 0 below), "
        + "or to exit the application (enter 14 below)?" + ANSI_RESET);

    while (true) {

      try {
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == EXIT_APPLICATION || choice == RETURN_TO_MAIN_MENU) {
          return choice; // Returns the valid choice
        } else {
          // Displays an error message for invalid choice
          System.out.println(ANSI_RED + "Invalid choice. " + ANSI_RESET + invalidChoice);
        }
      } catch (NumberFormatException e) {
        // Displays an error message for invalid input
        System.err.println(ANSI_RED + "Invalid input." + ANSI_RESET + invalidChoice);
      }
    }
  }


  /**
   * Processes the user's choice and executes the corresponding functionality.
   *
   * @param choice The user's choice.
   * @since 0.0.4
   */
  public void processUserChoice(int choice) {
    while (choice != EXIT_APPLICATION) {
      switch (choice) {

        case DISPLAY_TRAIN_DISPATCH_TABLE:

          display.displayTrainDispatchListTable(trainRegister.getTrainDispatchListIterator());
          break;

        case ADD_NEW_TRAIN_DEPARTURE:

          userOptions.addNewTrainDeparture();
          break;

        case ADD_DELAY_FOR_TRAIN_DEPARTURE:

          userOptions.setDelayForTrainDeparture();
          break;

        case ASSIGN_TRACK_TO_TRAIN_DEPARTURE:

          userOptions.assignNewTrackToTrainDeparture();
          break;

        case SEARCH_DEPARTURE_BASED_ON_TRAIN_NUMBER:

          userOptions.searchDepartureBasedOnTrainNumber();
          break;

        case SEARCH_DEPARTURE_BASED_ON_DESTINATION:

          userOptions.searchDepartureBasedOnDestination();
          break;

        case SEARCH_DEPARTURE_BASED_ON_DEPARTURE_TIME:

          userOptions.searchDepartureBasedOnDepartureTime();
          break;

        case SORT_DEPARTURE_LIST_BASED_ON_DEPARTURE_TIME:

          userOptions.sortDepartureListBasedOnDepartureTime();
          break;

        case REMOVE_SPECIFIC_DEPARTURE:

          userOptions.removeSpecificTrainDeparture();
          break;

        case REMOVE_ALL_DEPARTURES:

          userOptions.removeAllTrainDepartures();
          break;

        case REMOVE_DEPARTURES_THAT_HAVE_PASSED:

          userOptions.removeExpiredTrainsAndUpdateList();
          break;

        case UPDATE_STATION_TIME:

          userOptions.updateStationTime();
          break;

        case DISPLAY_GUIDE_MESSAGE:

          display.displayGuideMessage();
          break;

        default:
          // Displays an error message for invalid choice
          System.out.println(INVALID_CHOICE);
          break;
      }

      // Asking the user if they want to continue or exit.
      choice = promptForMenuOrExit();

      if (choice == EXIT_APPLICATION) {

        // Exits the loop when the user selects option 14
        display.displayGoodbyeMessage();
        exitRequested = true;

        return;
      } else if (choice == RETURN_TO_MAIN_MENU) {

        // Returns to the main menu when the user selects option 0
        System.out.println(PAGE_SEPARATOR);
        return;
      }
    }
  }
}
