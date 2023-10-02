package economic.main.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import economic.main.constants.MessageResponse;
import economic.main.constants.TypeFileImage;
import economic.main.model.Comment;
import economic.main.model.Discount;
import economic.main.model.Inventory;
import economic.main.model.LikesComments;
import economic.main.model.Product;
import economic.main.model.User;
import economic.main.payload.respone.ApiResponce;
import economic.main.payload.respone.DetailProductResponce;
import economic.main.payload.respone.modal.CommentResponce;
import economic.main.payload.respone.modal.InventoryResponce;
import economic.main.payload.respone.modal.ProductResponce;
import economic.main.reponsitory.CommentReponsitory;
import economic.main.reponsitory.DetailBillReponsitory;
import economic.main.reponsitory.DiscountsReponsitory;
import economic.main.reponsitory.ImagesReponsitory;
import economic.main.reponsitory.InventoryRepository;
import economic.main.reponsitory.LikesCommentsReponsitory;
import economic.main.reponsitory.ProductReponsitory;
import economic.main.reponsitory.UserReponsitory;
import economic.main.service.CommentsService;
import economic.main.service.InventoryService;
import economic.main.service.ProductService;
import economic.main.ultils.AppUltil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private AppUltil appUltil;

    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private LikesCommentsReponsitory likesCommentsReponsitory;

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Autowired
    private DetailBillReponsitory detailBillReponsitory;

    @Autowired
    private ImagesReponsitory imagesReponsitory;

    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired 
    private CommentsService commentsService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired DiscountsReponsitory discountsReponsitory;
    

    

    @Override
    public List<ProductResponce> getProductsHome(HttpServletRequest http) {
         List<ProductResponce> products = productReponsitory.findProductsHotHomepage().stream().map((porudct) -> {
            return this.buildProduct(porudct, false);
         }).toList();

        return products;
        
    }

    @Override
    public ApiResponce getDetailProduct(@NotBlank(message = "Id isn't empty")String id,@NotBlank(message = "Name isn't empty") String name, String username) {

        //conver url to name
        String named = name.replaceAll("-", " ");

        // get user from username
        User curUser = this.checkingUser(username);

        // check exis name or id
        if(!productReponsitory.existsById(id) || !productReponsitory.existsByName(named)){
            return ApiResponce.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .message(MessageResponse.FAILURE.getValue())
            .errors(true)
            .data("This is 1")
            .build();
        }

        Product product = productReponsitory.findProductIsUsing(id, named);

        // check product is null
        if(product == null) {
            return ApiResponce.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .message(MessageResponse.FAILURE.getValue())
            .errors(true)
            .data("This is 2")
            .build();
        }
        // product not empty (1)
        ProductResponce productResponce = this.buildProduct(product, true);

        // get comments(2)
        List<CommentResponce> comments = commentReponsitory.findByProduct(product).stream().map((cm) -> {
            return commentsService.buldCommnets(cm, curUser);
        }).toList();

        // get Evaluate (3)
        int evaluate = comments.size();

        // get sold (4)
        int sold = detailBillReponsitory.findByProduct(product).size();

        // get images (5)
        List<String> images = imagesReponsitory.findByProduct(product).stream().map((image) -> {
            return appUltil.getUrlImage(image.getImage(), TypeFileImage.PRODUCT);
        }).toList();

        // get inventories (6)
        InventoryResponce inventoryResponce = inventoryService.buildInventoryByProduct(product);

        // get discouts (7)
        // List<Discount> discounts = discountsReponsitory.findDiscountValid();


        // all good 
        return ApiResponce.builder()
        .status(HttpStatus.OK.value())
        .message(MessageResponse.SUCCESS.getValue())
        .errors(false)
        .data(DetailProductResponce.builder()
                .product(productResponce)
                .evaluate(evaluate)
                .sold(sold)
                .images(images)
                .comments(comments)
                .remaining(inventoryResponce)
                // .discounts(discounts)
                .build())
        .build();
    }

    // check exis user and user is login on system
    private User checkingUser(String username){

        if(username == null){
            return null;
        }

        User user = userReponsitory.findByUsernameAndCheckLogin(username).orElse(null);

        return user == null ? null : user;
    }

    @Override
    public ProductResponce buildProduct(Product product,boolean buildImageCategory) {

        ProductResponce productResponce = new ProductResponce(product);

        List<Inventory> inventories = inventoryRepository.findMinByProduct(product);


        // create old price and new price
        double oldPrice = inventories.get(0).getPrice();
        double newPrice = oldPrice - (oldPrice * (product.getDiscount() / 100.0));

        // set old price and new price for productResponce
        productResponce.setOldPrice(oldPrice);
        productResponce.setNewPrice(newPrice);


        // set image link for product and category
        productResponce.setImage(appUltil.getUrlImage(product.getImage(), TypeFileImage.PRODUCT));

        if(buildImageCategory){
            productResponce.getCategory().setThumbnail(appUltil.getUrlImage(product.getCategory().getThumbnail(), TypeFileImage.CATEGORIES));
        }

        return productResponce;
    }
    
}
