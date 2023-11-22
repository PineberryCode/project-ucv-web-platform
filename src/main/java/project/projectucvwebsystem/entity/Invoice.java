package project.projectucvwebsystem.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    protected String category;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected Map<String, Integer> productCharacteristicsList = new HashMap<>(); //Name|Quantities of Product
    protected String nameClient;
    protected String contactNumberClient;
    protected double totalPrice;
}
