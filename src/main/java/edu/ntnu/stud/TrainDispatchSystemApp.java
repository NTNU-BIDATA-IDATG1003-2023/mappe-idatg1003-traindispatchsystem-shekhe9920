package edu.ntnu.stud;

import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.userinterface.UserInterfaceManager;



/**
 * The {@code Main} class serves as the entry point for the
 * <strong>Train Dispatch System Application</strong>.
 * It starts by setting up the train register and launching the {@code UserInterfaceManager},
 * enabling the handling and display of train dispatch information.
 *
 * <p>This class is responsible for directing the application's startup by initializing
 * the {@code TrainRegister} and initiating the {@code UserInterfaceManager}.
 * The main method starts the program, providing users with a interface
 * for interacting with and managing train dispatch data.</p>
 *
 * @author Karwan Shekhe
 * @version 0.0.3 (Version of this class)
 * @since 0.0.3 (Introduced in Version 0.0.3 of the Train Dispatch System application)
 */
public class TrainDispatchSystemApp {

  /**
   * The main entry point of the Train Dispatch System Application.
   * Initiates the program's operation by launching the user interface
   * to manage and display train dispatch information.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Initializing the register
    TrainRegister initializer = new TrainRegister();
    initializer.initializeDepartureRegister();

    // Creating an instance of UserInterfaceManager and pass the initializer
    UserInterfaceManager ui = new UserInterfaceManager(initializer);

    // Calls the start method
    ui.start();
  }
}