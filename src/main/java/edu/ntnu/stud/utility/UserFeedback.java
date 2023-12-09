package edu.ntnu.stud.utility;

/**
 * The {@code UserFeedback} class provides feedback messages to the user during interactions with
 * the Train Dispatch System application.
 *
 * <p>This class includes predefined feedback messages for different scenarios, such as entering
 * train details, encountering errors, or completing tasks successfully. It utilizes ANSI color
 * codes for terminal output, making feedback messages visually distinctive.
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * To use this class, create an instance of {@code UserFeedback} and
 * call the desired feedback methods,
 * such as {@code feedbackMessage}, {@code errorFeedBack}, or {@code taskCompletedFeedBack},
 * providing the type of feedback expected. The class will return the corresponding feedback message
 * based on the specified type.
 *
 * Example:
 * {@code
 * UserFeedback feedback = new UserFeedback();
 * String enterTrainNumberMessage = feedback.feedbackMessage("enterTrainNumber");
 * System.out.println(enterTrainNumberMessage);
 * }
 * </pre></blockquote>
 *
 * <p>Feedback messages are printed to the console with different formatting, including color-coded
 * text and underlined error messages for better user visibility.
 *
 * @author Karwan Shekhe
 * @version 0.0.7 (Version of this class)
 * @since 0.0.7 (Introduced in Version 0.0.7 of the Train Dispatch System application)
 */
public class UserFeedback {

  // ANSI COLOR CODES FOR TERMINAL OUTPUT:
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_BLACK = "\u001B[30m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_LIGHT_YELLOW = "\u001B[93m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSU_BLUE = "\u001B[34m";
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_CYAN = "\u001B[36m";
  private static final String ANSI_WHITE = "\u001B[37m";
  private static final String ANSI_BOLD = "\u001B[1m";
  private static final String ANSI_UNNBOLD = "\u001B[21m";
  private static final String UNDER_DOUBLE_LINE = "\u001B[4m";
  private final String STOP_UNDER_DOUBLE_LINE = "\u001B[24m";
  public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";



  /**
   * Generates a user prompt message based on the specified type.
   *
   * @param type The type of prompt message to generate.
   * @return The user prompt message for the specified type.
   */
  public String feedbackMessage(String type) {
    return switch (type) {
      case "enterTrainNumber" -> "Please enter the train number: ";

      case "enterDepartureStation" -> "Please enter the departure station: ";

      case "enterDestination" -> "Please enter the destination: ";

      case "enterDepartureTime" -> "Please enter the departure time: ";

      case "enterTrainLine" -> "Please enter the train line: ";

      case "enterTrackNumber" -> "Please enter the track number: ";

      case "enterTrainNumberToSetDelay" -> "Please enter the number for the delayed train:";

      case "enterNewStationTime" -> "Enter the new station time (HH:mm)";

      default -> "";
    };
  }



  /**
   * Generates an error feedback message based on the specified type.
   *
   * @param type The type of error feedback message to generate.
   * @return The error feedback message for the specified type.
   */
  public String errorFeedBack(String type) {
    return switch (type) {

      case "notFound" -> "Train departure was not found. ";

      case "invalidTrainNumber" -> "Invalid train number. Please try again.";

      case "invalidDepartureStation" -> "Invalid departure station. Please try again.";

      case "invalidDestination" -> "Invalid destination. Please try again.";

      case "invalidDepartureTime" -> "Invalid departure time. Please try again.";

      case "invalidTrainLine" -> "Invalid train line. Please try again.";

      case "invalidTimeFormat" -> "Invalid time format. Please use HH:mm format (e.g., 08:30). "
          + "(Press 'enter' to try again)";

      case "failedToSetDelay" -> "Failed to set the delay.";

      case "trainNotAdded" -> "Error: Train was not added. Please try again. ";
      default -> "";
    };
  }


  /**
   * Generates a success feedback message based on the specified type.
   *
   * @param type The type of success feedback message to generate.
   * @return The success feedback message for the specified type.
   */
  public String taskCompletedFeedBack(String type) {
    return switch (type) {

      case "addedSuccessfully" -> "Train added successfully.";

      case "trackAssigned" -> "Track assigned successfully";

      case "updatedSuccessfully" -> "Departures updated successfully.";

      case "sortedSuccessfully" -> "Train departures sorted successfully.";

      case "delayAdded" -> "Delay set successfully.";

      case "stationTimeUpdated" -> "Station time updated successfully.";

      default -> "";
    };
  }



  /**
   * Logs and displays feedback messages to the user based on the specified type.
   *
   * @param type The type of feedback message to log and display.
   */
  public void logFeedback(String type) {
    String message = feedbackMessage(type);
    String warning = errorFeedBack(type);
    String taskCompleted = taskCompletedFeedBack(type);

    if (!message.isEmpty()) {
      System.out.println(ANSI_BLACK + ANSI_YELLOW_BACKGROUND + message + ANSI_RESET);
    }

    if (!warning.isEmpty()) {
      System.err.println(ANSI_RED + UNDER_DOUBLE_LINE
          + warning + STOP_UNDER_DOUBLE_LINE + ANSI_RESET);
    }

    if (!taskCompleted.isEmpty()) {
      System.out.println(ANSI_GREEN +  taskCompleted + ANSI_RESET);
    }
  }
}