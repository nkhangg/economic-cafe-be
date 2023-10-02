package economic.main.payload.respone;

import java.util.List;

import economic.main.model.Category;
import economic.main.model.Product;
import economic.main.payload.respone.modal.ProductResponce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomepageResponce {
    List<Category> categories;
    List<ProductResponce> products;
}
