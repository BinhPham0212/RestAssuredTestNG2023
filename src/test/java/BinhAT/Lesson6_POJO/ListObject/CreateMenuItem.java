package BinhAT.Lesson6_POJO.ListObject;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateMenuItem {
    @Test
    public static void createMenuItems() {
        List<Restaurant.MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new Restaurant.MenuItem("egg", 12.5f));
        menuItems.add(new Restaurant.MenuItem("chicken", 2.5f));
        menuItems.add(new Restaurant.MenuItem("vegetable", 7.5f));
        menuItems.add(new Restaurant.MenuItem("pig", 11.55f));

        Restaurant menu = new Restaurant("VN Food", menuItems);
        String json = new Gson().toJson(menu, Restaurant.class);
        System.out.println(json);
        try (FileWriter fileWriter = new FileWriter("src/test/resources/testdata/CreateMenuItem.json")) {
            new Gson().toJson(menu, Restaurant.class);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
