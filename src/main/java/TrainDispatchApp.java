
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
 * 1. Initializes a {@code TrainDispatchUserInterface} instance for train number allocation
 *    and management.
 * 2. Calls the {@code init} method to set up initial train trips in the system.
 * 3. Calls the {@code start} method to launch the user interface
 *    and present train trip details in a tabular format.
 * </pre></blockquote>
 *
 * <p>To execute the Train Dispatch System, create an instance of this class and
 * run the {@code main} method.
 *
 * @author Karwan Shekhe
 * @version 0.0.2
 * @since 0.0.1
 */
public class TrainDispatchApp {

  /**
   * The main entry point of the Train Dispatch System Application.
   * Initiates the program's operation by launching the user interface
   * to manage and display train dispatch information.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Creates an instance of TrainDispatchUserInterface
    TrainDispatchUserInterface trainDispatchUserInterface
        = new TrainDispatchUserInterface();

    // Initialize and add train trips to the system.
    trainDispatchUserInterface.init();

    // Start the user interface and display train trip information.
    trainDispatchUserInterface.start();
  }
}