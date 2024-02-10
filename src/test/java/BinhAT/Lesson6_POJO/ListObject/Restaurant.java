package BinhAT.Lesson6_POJO.ListObject;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Restaurant {
    private String name;
    private List<MenuItem> menu;

    @Data
    @AllArgsConstructor
    static class MenuItem {
        private String description;
        private float price;
    }

}

