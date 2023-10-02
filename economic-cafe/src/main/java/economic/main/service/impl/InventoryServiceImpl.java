package economic.main.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import economic.main.model.Inventory;
import economic.main.model.Product;
import economic.main.payload.respone.modal.InventoryResponce;
import economic.main.payload.respone.modal.detail.DetailInventoryResponce;
import economic.main.reponsitory.InventoryRepository;
import economic.main.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public InventoryResponce buildInventoryByProduct(Product product) {
        Set<String> weightNames = new HashSet<>();
        Set<String> typeNames = new HashSet<>();;
        Set<DetailInventoryResponce> detailInventoryResponces = new HashSet<>();
        List<Inventory> inventories = inventoryRepository.findMinByProduct(product);

        inventories.stream().forEach((inventory) -> {
            weightNames.add(inventory.getWeight());
            typeNames.add(StringUtils.capitalize(inventory.getNameType()));
            detailInventoryResponces.add(new DetailInventoryResponce(inventory));
        });

       

       return InventoryResponce.builder()
       .inventories(detailInventoryResponces)
       .weightNames(weightNames)
       .typeNames(typeNames)
       .build();
    }
    
}
