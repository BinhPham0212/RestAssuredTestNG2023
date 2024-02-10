package BinhAT.model;

import lombok.Data;

import java.util.ArrayList;
@Data
public class BookingPOJO {

    private String name;
    private int category_id;
    private int price;
    private String release_date;
    private ArrayList<Integer> image_ids;
    private Boolean status;


}
