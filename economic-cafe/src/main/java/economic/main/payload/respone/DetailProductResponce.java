package economic.main.payload.respone;

import java.util.List;

import economic.main.model.Comment;
import economic.main.model.Discount;
import economic.main.payload.respone.modal.CommentResponce;
import economic.main.payload.respone.modal.InventoryResponce;
import economic.main.payload.respone.modal.ProductResponce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductResponce {
    private ProductResponce product;
    private int evaluate;
    private int sold;
    private List<Discount> discounts;
    private InventoryResponce remaining;
    private List<String> images;
    private List<CommentResponce> comments;
}
