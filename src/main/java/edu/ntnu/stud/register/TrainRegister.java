package edu.ntnu.stud.register;

import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
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
 * The {@code TrainRegister} class is responsible for initializing train trips
 * and creating instances of {@code TrainDispatchSystem}.
 * It populates a collection of train dispatch systems and allocates train numbers for each trip.
 * This class serves as a crucial step in setting up the Train Dispatch System for user interaction.
 *
 * <p>Usage:
 *
 * <blockquote><pre>
 * To use this class, create an instance of {@code TrainRegister} and call the
 * {@code init} method to set up train trips.
 * The created {@code TrainDispatchSystem} instances are added to a collection,
 * and train numbers are allocated manually.
 * </pre></blockquote>
 *
 * @author Karwan Shekhe
 * @version 0.0.6 (Version of this class)
 * @since 0.0.5 (Introduced in Version 0.0.5 of the Train Dispatch System application)
 */
public class TrainRegister {
  private static TrainRegister instance;
  private HashMap<String, TrainDispatchSystem> trainDispatchRegister;
  private ArrayList<TrainDispatchSystem> dispatchSearchResults;
  private TrainManager trainManager;

  /**
   * Constructs an instance of {@code TrainRegister}.
   * Initializes the train dispatch register and the search results collection.
   *
   * @since 0.0.6
   */
  public TrainRegister() {
    trainDispatchRegister = new HashMap<>();
    dispatchSearchResults = new ArrayList<>();
    trainManager = new TrainManager();
  }

  /**
   * Gets the instance of {@code TrainRegister}.
   *
   * @return The instance of {@code TrainRegister}.
   */
  // Method to get the instance of TrainRegister
  public static TrainRegister getInstance() {
    if (instance == null) {
      instance = new TrainRegister();
    }
    return instance;
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
        LocalTime.of(8, 55), "F1", 1, "101");
    trainDispatchRegister.put(trainDispatch0.getTrainNumber() ,trainDispatch0);

    TrainDispatchSystem trainDispatch1 =
        new TrainDispatchSystem(departure_station, "Bergen",
        LocalTime.of(8, 55), "F1", 2, "102");
    trainDispatchRegister.put(trainDispatch1.getTrainNumber() ,trainDispatch1);

    TrainDispatchSystem trainDispatch2 =
        new TrainDispatchSystem(departure_station, "Lillehammer",
        LocalTime.of(8, 55), "F1", 3, "103");
    trainDispatchRegister.put(trainDispatch2.getTrainNumber() ,trainDispatch2);

    TrainDispatchSystem trainDispatch3 =
        new TrainDispatchSystem(departure_station, "Tromsø",
        LocalTime.of(15, 0), "F2", 4, "104");
    trainDispatchRegister.put(trainDispatch3.getTrainNumber() ,trainDispatch3);

    TrainDispatchSystem trainDispatch4 =
        new TrainDispatchSystem(departure_station, "Gardermoen",
        LocalTime.of(13, 15), "F13", 5, "105");
    trainDispatchRegister.put(trainDispatch4.getTrainNumber() ,trainDispatch4);
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
   * Adds a {@code TrainDispatchSystem} instance to the collection of initialized train trips.
   *
   * @param trainDispatchSystem The {@code TrainDispatchSystem} instance to be added.
   * @since 0.0.6
   */
  public void addTrainDeparture(TrainDispatchSystem trainDispatchSystem) {
    if (trainDispatchSystem != null &&
        trainManager.isTrainNumberAvailable(trainDispatchSystem.getTrainNumber())) {

      trainDispatchRegister
          .put(trainDispatchSystem.getTrainNumber(), trainDispatchSystem);
      trainManager
          .markTrainNumberAsAllocated(trainDispatchSystem.getTrainNumber(), trainDispatchSystem);
    }
  }

  /**
   * Sorts the collection of initialized train trips by departure time.
   * The collection is sorted in ascending order.
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
   * Removes train trips from the collection of initialized train trips if their departure time
   * has passed.
   * The method iterates over the collection and removes the train trips whose departure time
   * is before the current time.
   *
   * @since 0.0.6
   */
  public void removeTrainsIfDepartureTimePassed() {
    Iterator<Entry<String, TrainDispatchSystem>> iterator = trainDispatchRegister.entrySet().iterator();
    LocalTime currentTime = LocalTime.now();

    while (iterator.hasNext()) {
      Entry<String, TrainDispatchSystem> entry = iterator.next();
      TrainDispatchSystem trainDispatchSystem = entry.getValue();

      if (trainDispatchSystem.getDepartureTime().isBefore(currentTime)) {
        iterator.remove();
        System.out.println("Train " + entry.getKey() + " removed as departure time has passed.");
      }
    }
  }

  /**
   * Searches the collection of initialized train trips for train trips with a specific
   * attribute value.
   * The method iterates over the collection and filters the train trips based on the
   * attribute name and value.
   * The method returns an iterator for the filtered collection.
   *
   * @param attributeName The name of the attribute to search for.
   * @param attributeValue The value of the attribute to search for.
   * @return An iterator for the filtered collection of train trips.
   * @since 0.0.6
   */
  /*
  Legge i raporten at jeg brukte GPT til å spørre om jeg kunne gjøre om den gamle
  searchTrainDispatchByAttribute metoden til å bruke Iterator og stream.
   */
  public Iterator<TrainDispatchSystem> searchByAttributeAndValue(String attributeName, String attributeValue) {
    dispatchSearchResults = trainDispatchRegister.values().stream()
        .filter(trainDispatchSystem -> {
          switch (attributeName) {

            case "trainNumber":
              return trainDispatchSystem.getTrainNumber().equals(attributeValue);

            case "destination":
              return trainDispatchSystem.getDestination().equals(attributeValue);

            case "departureTime":
              return trainDispatchSystem.getDepartureTime().toString().equals(attributeValue);

            default:
              return false;
          }
        })
        .collect(Collectors.toCollection(ArrayList::new));

    return dispatchSearchResults.iterator();
  }

}
