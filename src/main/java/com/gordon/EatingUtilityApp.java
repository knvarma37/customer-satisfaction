package com.gordon;

import com.gordon.exceptions.InvalidInputException;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * EatingUtilityApp is an entry point to application. It create RestaurantMenu and asks to parse menu from a file.
 * Once the menu is loaded, it asks EatingUtilityService to find the max satisfaction with given time limit for a customer.
 */
public class EatingUtilityApp {

    public static void main(String[] args) throws URISyntaxException {
        EatingUtilityApp eatingUtilityApp = new EatingUtilityApp();

        try {
            EatingUtilityService eatingUtilityService = eatingUtilityApp.getUtilityService(args);
            int customerTimeLimit = 100;
            Integer maxSatisfaction = eatingUtilityService.findMaxSatisfaction(customerTimeLimit);
            System.out.println("Max satisfaction a customer can get with given time limit is  " + maxSatisfaction);
        } catch (FileNotFoundException e) {
            System.out.println("Given input file not found");
        } catch (InvalidInputException e) {
            System.out.println("Given input file has invalid content");
        }
    }

    protected EatingUtilityService getUtilityService(String[] args) throws FileNotFoundException, InvalidInputException,
            URISyntaxException{
        Restaurant restaurantMenu = new Restaurant();
        // uncomment and change this line to give absolute path.
        Path inputFileURL = Paths.get(this.getClass().getResource("/input1.txt").toURI());
        args = new String[]{inputFileURL.toAbsolutePath().toString()};

        if (args.length > 0) {
            restaurantMenu.readMenuFromFile(args[0]);
        } else {
            System.out.println("Required restaurant menu file path as an argument");           
        }

        return new EatingUtilityService(restaurantMenu);
    }

}
