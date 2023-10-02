package economic.main.payload.respone.modal.detail;

import org.springframework.util.StringUtils;

import economic.main.model.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailInventoryResponce {


    public DetailInventoryResponce(Inventory inventory){
        this.weightName = inventory.getWeight();
        this.nameType = StringUtils.capitalize(inventory.getNameType());
        this.inventory = inventory.getInventory();

        this.oldPrice = inventory.getPrice();

        this.newPrice = inventory.getPrice() - (inventory.getPrice() * inventory.getProduct().getDiscount() / 100.0);
    }

    private String weightName;
    private String nameType;
    private int inventory;
    private double newPrice;
    private double oldPrice;
}
