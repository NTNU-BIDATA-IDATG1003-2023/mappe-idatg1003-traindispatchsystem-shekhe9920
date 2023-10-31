package edu.ntnu.stud;

import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchSystemTest {
    TrainDispatchSystem trainDispatchSystem;

    @BeforeEach
    void setUp() {
         trainDispatchSystem = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
            55),"F1", 4);
    }

    @AfterEach
    void tearDown() {
    }
    /*
    @Test
    void setPositiveTrainNumberTest() {
        trainDispatchSystem.setTrainNumber("123");
        assertEquals("123", trainDispatchSystem.getTrainNumber(), "Train number is correct");
    }

    */

    @Test
    void setNegativeTestForTrainNumber() {
        trainDispatchSystem = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        trainDispatchSystem.setTrainNumber("134");
        assertNotEquals("true", trainDispatchSystem.getTrainNumber(), "Train number is incorrect");
    }

    @Test
    void allocateTrainDispatchesWithTheSameTrainNumberNegativeTest() {
        TrainDispatchSystem trainDispatchSystem0 = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        TrainDispatchSystem trainDispatchSystem1 = new TrainDispatchSystem("Gjøvik", "Oslo", LocalTime.of(8,
                55),"F1", 4);
        trainDispatchSystem0.setTrainNumber("987");
        trainDispatchSystem1.setTrainNumber("789");
        assertNotEquals(trainDispatchSystem0.getTrainNumber(), trainDispatchSystem1.getTrainNumber());
    }
    // commit test
}