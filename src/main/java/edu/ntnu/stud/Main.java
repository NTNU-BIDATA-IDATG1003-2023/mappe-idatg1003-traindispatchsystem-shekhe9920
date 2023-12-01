package edu.ntnu.stud;

import edu.ntnu.stud.register.TrainRegister;
import edu.ntnu.stud.userinterface.InformationDisplay;
import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import edu.ntnu.stud.userinterface.UserInterface;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * The {@code TrainDispatchApp} class serves as the main entry point for the
 * Train Dispatch System application.
 * It is responsible for initializing essential components orchestrating the interaction between
 * the user and train management system.
 *
 * <p>The application allows the user to manage view train dispatch information,
 * including train trips, departure stations, destinations, departure times,
 * train numbers, and more.
 *
 * <p>This class provides the following key functionalities:
 * <ul>
 *     <li>Initializes a {@code edu.ntnu.stud.userinterface.TrainDispatchUserInterface} instance for train number allocation
 *         and management.</li>
 *     <li>Calls the {@code init} method to set up initial train trips in the system.</li>
 *     <li>Calls the {@code start} method int the {@code TrainDispatchUserInterface}
 *         class to launch the user interface and present
 *         train trip details in a tabular format.</li>
 * </ul>
 *
 * <p>To execute the Train Dispatch System, create an instance of this class and
 * run the {@code main} method.
 *
 * @author Karwan Shekhe
 * @version 0.0.3 (Version of this class)
 * @since 0.0.3 (Introduced in Version 0.0.3 of the Train Dispatch System application)
 */
public class Main {

  /**
   * The main entry point of the Train Dispatch System Application.
   * Initiates the program's operation by launching the user interface
   * to manage and display train dispatch information.
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Initializes the register.TrainDispatchInitializer
    TrainRegister initializer = new TrainRegister();
    initializer.initializeDepartureRegister();


    // Gets the train dispatch list from the initializer
    Iterator<TrainDispatchSystem> trainDispatchIterator =
        initializer.getTrainDispatchListIterator();


    // Creates an instance of TrainDispatchUserInterface and pass the list
    UserInterface ui = new UserInterface(trainDispatchIterator, initializer);

    // Calls the start method
    ui.start();
  }
  // Commit test
}