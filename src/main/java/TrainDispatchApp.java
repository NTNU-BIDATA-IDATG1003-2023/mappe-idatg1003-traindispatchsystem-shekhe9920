import trainmanager.TrainManager;

/**
 * The {@code TrainDispatchApp} class serves as the main entry point for the program,
 * responsible for initializing essential components orchestrating the interaction between
 * the user and train management system.
 * </p>
 * The application allows the user to manage view train dispatch information, including train trips,
 * departure stations, destinations, departure times, train numbers, and more.
 *
 * <p>This class provides the following key functionalities:
 *
 * <blockquote><pre>
 * 1. Initializes a `TrainManager` instance for train number allocation
 *    and management.
 * 2. Initializes a `TrainDispatchUI` instance for user interaction
 *    and train dispatch information display.
 * 3. Calls the `init` method to set up initial train trips in the system.
 * 4. Calls the `start` method to launch the user interface
 *    and present train trip details in a tabular format.
 * </pre></blockquote>
 *
 * <p>To execute the Train Dispatch System, create an instance of this class and
 * run the `main` method.
 *
 * @author Karwan Shekhe
 * @version 0.0.1
 * @since 0.0.1
 */
public class TrainDispatchApp {

  /**
   * This main entry point of the Train Dispatch System Application.
   * This method initiates the program's operation by creating essential components
   * and launching the user interface to manage and display train dispatch information.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Create an instance of TrainManager to manage train allocations.
    TrainManager trainManager = new TrainManager();
    // Create an instance of TrainDispatchUI
    // to manage user interface and train dispatch information.
    TrainDispatchUserInterface trainDispatchUserInterface =
        new TrainDispatchUserInterface(trainManager);

    // Initialize and add train trips to the system.
    trainDispatchUserInterface.init();

    // Start the user interface and display train trip information.
    trainDispatchUserInterface.start();
  }
}