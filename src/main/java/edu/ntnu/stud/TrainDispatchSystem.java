package edu.ntnu.stud;

import java.time.LocalTime;


/**
 * This is the main class for the train dispatch application.
 *
 * @author Karwan Shekhe
 * @version 0.0.1
 *
 */
public class TrainDispatchSystem {
    private String departureStation;
    private String destination;
    private LocalTime departureTime;
    private String line;
    private int track;
    private int trainNumber; // Store the train number directly
    private boolean trainNumberSet = false; // Boolean flag to track if train number is set

    /**
     * Constructor to initialize a TrainDispatchSystem object.
     *
     * @param departureStation The departure station name.
     * @param destination      The destination station name.
     * @param departureTime    The departure time.
     * @param line             The train line name.
     * @param track            The track number.
     * @param trainNumber      The train number.
     */

    public TrainDispatchSystem(String departureStation, String destination,
                               LocalTime departureTime, String line, int track, int trainNumber) {
        this.departureStation = departureStation;
        this.departureTime = departureTime;
        this.destination = destination;
        setLine(line);
        setTrack(track);
        setTrainNumber(trainNumber);
    }

    // Getter methods
    public String getDepartureStation() {
        return departureStation;
    }

    public String getDestination() {
        return destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getLine() {
        return line;
    }

    public int getTrack() {
        return track;
    }
    // Setters methods
    /**
     * Sets the train number if it hasn't been set already.
     *
     * @param trainNumber                 The train number to set.
     * @throws IllegalArgumentException   If the train number has already been set.
     */
    public void setTrainNumber(int trainNumber) {
        if (!trainNumberSet) {
            this.trainNumber = trainNumber;
            trainNumberSet = true;
        } else {
            throw new IllegalStateException("Train number has already been set.");
        }
    }

    /**
     * Sets the track number within the range [1, 10].
     *
     * @param track                       The number to set
     * @throws IllegalArgumentException   If the track number is outside the valid range.
     */
    public void setTrack(int track) {
        if (track >= 1 && track <= 10) {
            this.track = track;
        } else {
            throw new IllegalArgumentException("Track must be between 1 and 10 inclusive.");
        }
    }

    /**
     * Sets the train line name. If line is null, it sets a default value.
     *
     * @param line The train line name to set.
     */
    public void setLine(String line) {
        this.line = (line != null) ? line : "Default"; // Sets default value if line is null
    }

    /**
     * Generates a string representation of the TrainDispatchSystem object.
     *
     * @return A string with details about the train dispatch.
     */
    @Override
    public String toString() {
        return "Departure from " + departureStation + " to "
                + destination + " at " + departureTime + "|| Line: "
                + line + " ||" + "|| Track: " + track + " ||" + "Train number: " + trainNumber;
    }
}