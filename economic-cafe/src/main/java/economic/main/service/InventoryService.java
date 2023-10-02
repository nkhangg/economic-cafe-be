package economic.main.service;

import economic.main.model.Product;
import economic.main.payload.respone.modal.InventoryResponce;
import economic.main.reponsitory.InventoryRepository;

public interface InventoryService {
    InventoryResponce buildInventoryByProduct(Product product);
}
