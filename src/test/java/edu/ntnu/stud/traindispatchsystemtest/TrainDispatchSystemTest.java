package edu.ntnu.stud.traindispatchsystemtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.traindispatchsystem.TrainDispatchSystem;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDispatchSystemTest {

    TrainDispatchSystem trainDispatchSystemTest;
    @BeforeEach
    void setUp() {

        trainDispatchSystemTest =
            new TrainDispatchSystem("Gjøvik", "Oslo",
                LocalTime.of(8, 55), "F1", 1, "706");

    }

    @AfterEach
    void tearDown() {
        trainDispatchSystemTest = null;
    }


    /**
     * Tests the positive case of setting a train number.
     * Verifies that the train number is successfully set to the expected value.
     */
    @Test
    void setPositiveTestForTrainNumber() {

        // Arrange
        setUp();

        // Act
        trainDispatchSystemTest.setTrainNumber("706");

        // Assert
        assertEquals("706", trainDispatchSystemTest.getTrainNumber(),
            "Train wasn't set");
        System.out.println("Train was set successfully");
        tearDown();
    }


    /**
     * Tests the negative case of setting a null value for the train number.
     * Verifies that an IllegalArgumentException is thrown when attempting
     * to set the train number to null.
     */
    @Test
    void negativeTestForEmptyValueTrainNumber() {
        // Arrange
        TrainDispatchSystem train1 = new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "");

        // Trying to set the train number to null, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> train1.setTrainNumber(null));

        tearDown();

    }

    /**
     * Tests the negative cases of setting a destination with non-numeric characters and null value.
     * Verifies that an IllegalArgumentException is thrown when attempting
     * to set the destination with invalid data.
     */
    @Test
    void settingInvalidDepartureStationNegativeTest() {
        // Arrange
        TrainDispatchSystem train1 = new TrainDispatchSystem("Gjøvik", "Oslo",
            LocalTime.of(8, 55), "F1", 1, "TEST");

        // Trying to set the destination to null, and expecting an exception
        assertThrows(IllegalArgumentException.class, () ->
            train1.setDestination(null), "The destination is null, the destination should not be set");

        // Trying to set the destination with non-alphabetic characters, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> train1.setDestination("123")
            , "The destination contains numbers, the destination should not be set");

        tearDown();
    }

    /**
     * Tests the positive case of setting a valid delay in minutes(0 < delay =< 60).
     * Verifies that the delay is successfully set to the expected value.
     */
    @Test
    void settingDelayPositiveTest() {
        // Arrange
        setUp();

        // Act
        trainDispatchSystemTest.setDelay(10);

        // Assert
        assertEquals(10, trainDispatchSystemTest.getDelay(), "The delay was not set correctly");
        System.out.println("The delay was set successfully");

        tearDown();

    }

    /**
     * Tests the positive case of setting a valid delay in minutes(0 < delay <= 60).
     * Verifies that an IllegalArgumentException is thrown when attempting
     * to set the delay with invalid data.
     */
    @Test
    void settingNegativeValueForTheDelayTest() {
        // Arrange
        setUp();

        // Trying to set the delay to negative value, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystemTest.setDelay(-1)
            , "The given delay is a negative integer, the delay should not be set.");

        // Trying to set the delay to a value greater than 60, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystemTest.setDelay(65)
            , "The delay is negative, the delay should not be set");

        tearDown();

    }


    /**
     * Tests the positive case of setting a valid number for the track (1 =< track <= 10).
     * Verifies that the track is successfully set to the expected value.
     */
    @Test
    void settingPositiveValueForTheTrackTest() {
        // Arrange
        setUp();

        // Act
        trainDispatchSystemTest.setTrack(4);

        // Assert
        assertEquals(4, trainDispatchSystemTest.getTrack(), "The delay was not set correctly");
        System.out.println("The delay was set successfully");

        tearDown();

    }

    /**
     * Tests the negative cases of setting a valid number for the track.
     * Verifies that an IllegalArgumentException is thrown when attempting
     * to set the track with invalid data.
     */
    @Test
    void settingNegativeValueForTheTrackTest() {
        // Arrange
        setUp();

        // Trying to set the track to negative value, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystemTest.setTrack(-1)
            , "The given track number is a negative integer, the delay should not be set.");

        // Trying to set the delay to 0, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystemTest.setTrack(0)
            , "The delay is 0, the delay should not be set");


        // Trying to set the delay to a value greater than 60, and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystemTest.setTrack(15)
            , "The delay is negative, the delay should not be set");

        tearDown();

    }

}