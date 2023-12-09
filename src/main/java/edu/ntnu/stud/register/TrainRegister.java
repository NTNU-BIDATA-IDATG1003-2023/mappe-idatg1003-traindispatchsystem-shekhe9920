package edu.ntnu.stud.register;

import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * <p>The {@code TrainRegister} class is responsible for initializing train trips
 * and creating instances of {@code TrainDispatchSystem}.
 * It populates a collection of train dispatch systems and
 * allocates train numbers for each trip.</p>
 *
 * <p>This class also includes methods for handling the data of the initialized instances.
 * For example, it includes methods for sorting the collection of train trips by
 * departure time and removing train trips if their departure time has passed.
 * It also includes methods for
 * searching the collection of train trips based on specific attributes.
 * The class utilizes the {@code TrainManager} class to manage the allocation of train numbers.</p>
 *
 * <p>For real-time accuracy, the class ensures that the station time is updated before presenting
 * train dispatch information.
 * It provides methods for both retrieving and updating the station time,
 * ensuring consistency in the displayed information.</p>
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * To use this class, create an instance of {@code TrainRegister} and call the
 * {@code initializeDepartureRegister} method to set up train trips.
 * The created {@code TrainDispatchSystem} instances are added to a collection,
 * and train numbers are allocated manually.
 * </pre></blockquote>
 *
 * <blockquote><pre>
 * <p><strong>Example Usage:</strong></p>
 *
 * {@code
 *    private final TrainRegister trainRegister;
 *
 *    public void addNewTrainDeparture(String destination, LocalTime departureTime,
 *                    String line, int track, String trainNumber) {
 *
 *       trainRegister.addTrainDeparture(new TrainDispatchSystem("Gjøvik", destination,
 *           departureTime, line, track, trainNumber));
 *
 *     }
 * }
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.0.8 (Version of this class)
 * @since 0.0.5 (Introduced in Version 0.0.5 of the Train Dispatch System application)
 */
public class TrainRegister {
  private HashMap<String, TrainDispatchSystem> trainDispatchRegister;
  private ArrayList<TrainDispatchSystem> dispatchSearchResults;
  private final TrainManager trainManager;
  private LocalTime stationTime;               // The current station time.

  /**
   * Constructs an instance of {@code TrainRegister}.
   * Initializes the train dispatch register, search results collection,
   * and a {@code TrainManager} for managing train number allocations.
   * The default station time is set to the current time.
   *
   * @since 0.0.6
   */
  public TrainRegister() {
    this.stationTime = LocalTime.now(); // Default station time, the user is able to change this
    trainDispatchRegister = new HashMap<>();
    dispatchSearchResults = new ArrayList<>();
    trainManager = new TrainManager();
  }

  /**
   * Initializes train trips and populates the trainDispatchList collection with
   * {@code TrainDispatchSystem} instances.
   * It also manually allocates train numbers for each trip and associates them with the
   * corresponding systems.
   *
   * @since 0.0.1
   */
  public void initializeDepartureRegister() {
    final String departure_station = "Gjøvik";

    trainDispatchRegister = new HashMap<>();

    TrainDispatchSystem trainDispatch0 =
        new TrainDispatchSystem(departure_station, "Oslo",
        LocalTime.of(20, 25), "F1", 1, "101");
    trainDispatchRegister.put(trainDispatch0.getTrainNumber(), trainDispatch0);
    addTrainDeparture(trainDispatch0);

    TrainDispatchSystem trainDispatch1 =
        new TrainDispatchSystem(departure_station, "Bergen",
        LocalTime.of(16, 0), "F1", 2, "102");
    trainDispatchRegister.put(trainDispatch1.getTrainNumber(), trainDispatch1);
    addTrainDeparture(trainDispatch1);

    TrainDispatchSystem trainDispatch2 =
        new TrainDispatchSystem(departure_station, "Lillehammer",
        LocalTime.of(8, 55), "F1", 3, "103");
    trainDispatchRegister.put(trainDispatch2.getTrainNumber(), trainDispatch2);
    addTrainDeparture(trainDispatch2);

    TrainDispatchSystem trainDispatch3 =
        new TrainDispatchSystem(departure_station, "Tromsø",
        LocalTime.of(15, 55), "F2", 4, "104");
    trainDispatchRegister.put(trainDispatch3.getTrainNumber(), trainDispatch3);
    addTrainDeparture(trainDispatch3);

    TrainDispatchSystem trainDispatch4 =
        new TrainDispatchSystem(departure_station, "Gardermoen",
        LocalTime.of(13, 15), "F13", 5, "105");
    trainDispatchRegister.put(trainDispatch4.getTrainNumber(), trainDispatch4);
    addTrainDeparture(trainDispatch4);
  }

  /**
   * Retrieves an iterator for the collection of initialized TrainDispatchSystem instances.
   *
   * @return An iterator for the collection of initialized TrainDispatchSystem instances.
   * @since 0.0.4
   */
  public Iterator<TrainDispatchSystem> getTrainDispatchListIterator() {
    return trainDispatchRegister.values().iterator();
  }

  /**
   * Retrieves the current station time.
   *
   * @return The current station time.
   * @since 0.0.8
   */
  public LocalTime getStationTime() {
    return stationTime;
  }

  /**
   * <p>Updates the station time to the specified time, ensuring it matches the "HH:mm" format.
   * Throws an {@code IllegalArgumentException} for an invalid time format.</p>
   *
   * <p>The station time is set to the current time of the device by default, but this method
   * provides the user with the option to update it.</p>
   *
   * @param newStationTime The new station time to be set, in "HH:mm" format.
   * @throws IllegalArgumentException if the time format is invalid.
   * @since 0.0.8
   */
  public void updateStationTime(LocalTime newStationTime) {
    String timeString = newStationTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    if (timeString.matches("\\d{2}:\\d{2}")) {
      this.stationTime = newStationTime;
    } else {
      throw new IllegalArgumentException("Invalid time format. Please enter time in HH:mm format.");
    }
  }

  /**
   * <p>Adds a {@code TrainDispatchSystem} instance to the collection of initialized train trips.
   * Ensures the train number is available and marks it as allocated using the
   * {@code TrainManager} class.</p>
   *
   * <p>It checks that the provided {@code TrainDispatchSystem} instance is not null,
   * has a valid train number, and ensures that the train number is available for allocation.
   * If these conditions are met, the train trip is added to the collection,
   * and its train number is marked as allocated.</p>
   *
   * @param trainDispatch The {@code TrainDispatchSystem} instance to be added.
   * @return {@code true} if the addition is successful; otherwise, {@code false}.
   * @since 0.0.6
   */
  public boolean addTrainDeparture(TrainDispatchSystem trainDispatch) {
    if (trainDispatch != null && trainDispatch.getTrainNumber() != null
        && trainManager.isTrainNumberAvailable(trainDispatch.getTrainNumber())) {

      trainDispatchRegister
          .put(trainDispatch.getTrainNumber(), trainDispatch);
      trainManager.markTrainNumberAsAllocated(trainDispatch.getTrainNumber(), trainDispatch);
      addTrainDeparture(trainDispatch);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Sorts the collection of initialized train trips by departure time in ascending order.
   * This method provides a mechanism to organize train trips based on their departure times.
   * The collection is sorted in ascending order, ensuring a chronological arrangement.
   *
   * @since 0.0.6
   */
  public void sortListByDepartureTime() {
    // Convert HashMap entries to a List
    List<Entry<String, TrainDispatchSystem>> entryList =
        new ArrayList<>(trainDispatchRegister.entrySet());

    // Sort the List based on LocalTime (assuming departure time is stored in TrainDispatchSystem)
    entryList.sort(Comparator.comparing(entry -> entry.getValue().getDepartureTime()));

    // Create a new LinkedHashMap to store the sorted entries
    LinkedHashMap<String, TrainDispatchSystem> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, TrainDispatchSystem> entry : entryList) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }

    // Update the trainDispatchRegister with the sorted entries
    trainDispatchRegister = sortedMap;
  }

  /**
   * <p>Removes train trips from the collection if their departure time has passed,
   * ensuring up-to-date scheduling information.</p>
   *
   * <p>This method iterates through the collection, removing train trips whose departure time is
   * earlier than the station time, accounting for any delays.</p>
   *
   * @since 0.0.6
   */
  public void removeTrainsIfDepartureTimePassed() {

    Iterator<Entry<String, TrainDispatchSystem>> iterator =
        trainDispatchRegister.entrySet().iterator();


    while (iterator.hasNext()) {
      Entry<String, TrainDispatchSystem> entry = iterator.next();
      TrainDispatchSystem trainDispatchSystem = entry.getValue();
      LocalTime currentTime = getStationTime();

      if ((trainDispatchSystem.getDepartureTime()
          .plusMinutes(trainDispatchSystem.getDelay())).isBefore(currentTime)) {

        iterator.remove();
      }
    }
  }

  /**
   * <p>Searches the collection of initialized train trips based on specific attribute values,
   * providing an iterator for the filtered results.
   * Supported attributes include "trainNumber," "destination," and "departureTime".</p>
   *
   * <p>The method filters train trips based on the attribute name and value, returning an iterator
   * for the resulting collection.</p>
   *
   * @param attributeName The name of the attribute to search for.
   * @param attributeValue The value of the attribute to search for.
   * @return An iterator for the filtered collection of train trips.
   * @since 0.0.6
   */
  public Iterator<TrainDispatchSystem> searchByAttributeAndValue(String attributeName,
      String attributeValue) {

    dispatchSearchResults = trainDispatchRegister.values().stream()
        .filter(trainDispatchSystem -> switch (attributeName) {
          case "trainNumber" -> trainDispatchSystem.getTrainNumber().equals(attributeValue);
          case "destination" -> trainDispatchSystem.getDestination().equals(attributeValue);
          case "departureTime" ->
              trainDispatchSystem.getDepartureTime().toString().equals(attributeValue);
          default -> false;
        })
        .collect(Collectors.toCollection(ArrayList::new));

    return dispatchSearchResults.iterator();
  }
}
