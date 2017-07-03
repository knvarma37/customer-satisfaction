package com.gordon;

import com.gordon.exceptions.InvalidInputException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by knvarma
 */
public class EatingUtilityServiceTest {

    @Test
    public void shouldFindMaxSatisfactionWithGivenRestaurantMenu() throws FileNotFoundException,
            InvalidInputException, URISyntaxException {
        Restaurant restaurantMenu = new Restaurant();
        Path inputFileURL = Paths.get(this.getClass().getResource("/input1.txt").toURI());
        restaurantMenu.readMenuFromFile(inputFileURL.toAbsolutePath().toString());
        EatingUtilityService eatingUtilityService = new EatingUtilityService(restaurantMenu);
        int maxSatisfaction = eatingUtilityService.findMaxSatisfaction(100);
        assertEquals(maxSatisfaction, 278);
    }
}
