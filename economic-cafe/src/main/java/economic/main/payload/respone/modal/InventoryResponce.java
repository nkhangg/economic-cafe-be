package economic.main.payload.respone.modal;

import java.util.Set;

import economic.main.payload.respone.modal.detail.DetailInventoryResponce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponce {
    private Set<String> weightNames;
    private Set<String> typeNames;
    private Set<DetailInventoryResponce> inventories;
}
