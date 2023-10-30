package edu.ntnu.stud;

import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchSystemTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setPositiveTrainNumberTest() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        trainDispatchSystem.setTrainNumber("123");
        assertEquals("123", trainDispatchSystem.getTrainNumber(), "Train number is correct");
    }

    @Test
    void setNegativeTestForTrainNumber() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        trainDispatchSystem.setTrainNumber("134");
        assertEquals("123", trainDispatchSystem.getTrainNumber(), "Train number is incorrect");
    }

    @Test
    void allocateTrainDispatchesWithTheSameTrainNumberNegativeTest() {
        TrainDispatchSystem trainDispatchSystem0 = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        TrainDispatchSystem trainDispatchSystem1 = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        trainDispatchSystem0.setTrainNumber("123");
        trainDispatchSystem1.setTrainNumber("123");
        assertNotEquals(trainDispatchSystem0.getTrainNumber(), trainDispatchSystem1.getTrainNumber());
    }
}