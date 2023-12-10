package edu.ntnu.stud.utility;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * The {@code InputHandler} class provides methods for handling user input.
 * It includes methods for reading strings and integers with input validation.
 *
 * <p>This class ensures that user inputs are validated based on specified criteria, such as time
 * format, integer format, and non-empty values.
 * It utilizes regular expressions for input validation
 * and includes methods to prompt the user until valid input is provided.
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * Create an instance of {@code InputHandler} and call the desired input methods,
 * such as {@code inputString} or {@code inputInteger}, providing the type of input expected.
 * The class will prompt the user until valid input is provided based on criteria.
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.0.3 (Version of this class)
 * @since 0.0.8 (Introduced in Version 0.0.8 of the Train Dispatch System application)
 */
public class InputHandler {

  private final Scanner inputReader;
  private final UserFeedback userFeedback = new UserFeedback();


  // Constants
  private static final String NON_EMPTY = "non-empty";    // To check for non-empty input (trimmed)
  private static final String ANSI_RED = "\u001B[31m";    // To print text in red
  private static final String ANSI_RESET = "\u001B[0m";   // To reset the text color



  /**
   * Constructs an instance of {@code InputHandler}.
   * Initializes the input reader.
   *
   * @since 0.0.1
   */
  public InputHandler() {
    this.inputReader = new Scanner(System.in);
  }




  /**
   * Reads a string input from the user with the specified type of input and performs validation.
   *
   * <p>The method prompts the user to input a string and validates it based on the specified type.
   * It ensures that the input is in the correct time format (HH:mm) or non-empty based on the
   * specified criteria. The user is prompted until valid input is provided.
   *
   * @param typeOfInput The type of input expected (e.g., "time" or "non-empty").
   * @return The validated string input from the user.
   * @since 0.0.1
   */
  public String inputValidString(String typeOfInput) {
    boolean flag = false;       // To check if the input is valid
    String inputString = "";    // To store the input string

    while (!flag) {
      System.out.println(typeOfInput + " :");

      if (inputReader.hasNextLine()) {
        inputString = inputReader.nextLine().trim();

        // Validation of the input
        if (isValidInput(inputString, NON_EMPTY)) {
          flag = true;

        } else {
          System.out
            .println(ANSI_RED + "Invalid input! (Please press 'enter' to try again)" + ANSI_RESET);
          inputReader.nextLine();  // Consume newline to prepare for the next input.
        }
      }
    }
    return inputString;   // Return the validated input string
  }




  /**
   * Reads an integer input from the user with the specified type of input and performs validation.
   *
   * <p>The method prompts the user to input an integer and
   * validates it based on the specified type.
   * It ensures that the input is a valid integer or non-empty based on the specified criteria.
   * The user is prompted until valid input is provided.</p>
   *
   * @param typeOfInput The type of input expected (e.g., "integer" or "non-empty").
   * @return The validated integer input from the user.
   * @since 0.0.1
   */
  public int inputValidInteger(String typeOfInput) {
    int inputInteger = 0;
    boolean flag = false;

    while (!flag) {
      System.out.println(typeOfInput + " :");

      if (inputReader.hasNextInt()) {
        inputInteger = inputReader.nextInt();

        // Validation of the input
        if (isValidInput(Integer.toString(inputInteger), "integer")
            || isValidInput(Integer.toString(inputInteger), NON_EMPTY)) {
          flag = true;
        }
      } else {
        System.out.println(ANSI_RED
            + "Please enter an integer.(Press 'enter' to try again)" + ANSI_RESET);
      }
      inputReader.nextLine();  // Consume newline to prepare for the next input.
    }
    return inputInteger;       // Return the validated input integer
  }

  /**
   * Reads a time input from the user with the specified label and performs validation.
   *
   * <p>The method prompts the user to input a time and validates it based on the defined criteria.
   * It ensures that the input is in the correct time format (HH:mm) and prompts the user until
   * a valid time input is provided. If the input does not match to the specified format,
   * the method logs a feedback message and prompts the user to try again.
   *
   * @param label The label or description for the time input.
   * @return The validated {@code LocalTime} input from the user.
   * @since 0.0.3
   */
  public LocalTime inputValidTime(String label) {
    LocalTime inputTime = null;
    boolean flag = false;

    while (!flag) {
      System.out.println(label + " :");
      if (inputReader.hasNextLine()) {
        String inputTimeStr = inputReader.nextLine().trim();

        // Validation of the input:
        if (isValidInput(inputTimeStr, "time")) {
          inputTime = LocalTime.parse(inputTimeStr);
          flag = true;
        } else {
          userFeedback.logFeedback("invalidTimeFormat");
          inputReader.nextLine();  // Consume newline to prepare for the next input.
        }
      }
    }
    return inputTime;
  }

  /**
   * Validates the input based on the specified type.
   *
   * <p>The method performs validation on the provided input based on the specified type.
   * It checks if the input adheres to the defined criteria,
   * such as being a valid time format (HH:mm),
   * a valid integer, or non-empty based on the specified criteria. The method returns a boolean
   * indicating whether the input is valid or not.
   *
   * @param input The input string to be validated.
   * @param type The type of validation to be applied (e.g., "time," "integer," or "non-empty").
   * @return True if the input is valid according to the specified type; otherwise, false.
   *
   * @since 0.0.2
   */
  private boolean isValidInput(String input, String type) {
    return switch (type) {

      case "time" ->
          // To check if time format is valid (HH:mm)
          input.matches("\\d{2}:\\d{2}");

      case "integer" ->
          // To check if the input integer is valid
          input.matches("\\d+");

      case NON_EMPTY ->
          // To check for non-empty input (trimmed)
          !input.trim().isEmpty();

      default -> true;
    };
  }
}