package com.gordon;

import com.gordon.exceptions.InvalidInputException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

/**
 * Created by knvarma
 */
public class RestaurantTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    Restaurant restaurant;

    @Before
    public void setup() {
        restaurant = new Restaurant();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFilePathIsNull() throws FileNotFoundException, InvalidInputException {
        restaurant.readMenuFromFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfInvalidFilePathPassed() throws FileNotFoundException, InvalidInputException {
        restaurant.readMenuFromFile("");
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionIfFileNotFound() throws FileNotFoundException, InvalidInputException {
        restaurant.readMenuFromFile("input.txt");
    }

    @Test
    public void shouldParseAllTheMenuFromValidFile() throws IOException, URISyntaxException, InvalidInputException {
        Path inputFileURL = Paths.get(this.getClass().getResource("/input1.txt").toURI());
        restaurant.readMenuFromFile(inputFileURL.toAbsolutePath().toString());
        assertEquals(restaurant.getMenuCount(), 5);

        List<MenuItem> menuItemList = new ArrayList<MenuItem>();
        menuItemList.add(new MenuItem(101, 5));
        menuItemList.add(new MenuItem(51, 2));
        menuItemList.add(new MenuItem(15, 3));
        menuItemList.add(new MenuItem(22, 1));
        menuItemList.add(new MenuItem(89, 4));
        assertThat(restaurant.getMenu(), equalTo(menuItemList));
    }

    @Test
    public void shouldReturnMenuItemForAGivenIndex() throws URISyntaxException, FileNotFoundException, InvalidInputException {
        Path inputFileURL = Paths.get(this.getClass().getResource("/input1.txt").toURI());
        restaurant.readMenuFromFile(inputFileURL.toAbsolutePath().toString());
        assertThat(restaurant.getMenuAtIndex(4), equalTo(new MenuItem(89, 4)));
    }
}
