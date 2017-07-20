package com.hmmelton.workouttracker;

import com.hmmelton.workouttracker.data.ExerciseContract;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by harrisonmelton on 7/18/17.
 * Local unit test used for database contract.
 */

public class ContractClassUnitTest {

    @Test
    public void inner_classes_count() throws Exception{
        Class[] innerClasses = ExerciseContract.class.getDeclaredClasses();
        assertEquals("There should be 5 inner classes in the contract class", 5,
                innerClasses.length);
    }
}
