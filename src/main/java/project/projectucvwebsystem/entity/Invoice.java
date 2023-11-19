package project.projectucvwebsystem.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private String category;
    private List<Map.Entry<String, Integer>> productos = new ArrayList<>(); //Name|Quantities of Product
    private String nameClient;
    private String contactNumberClient;
    private double totalPrice;
}
