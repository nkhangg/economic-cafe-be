package economic.main.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.model.Comment;
import economic.main.model.Discount;
import economic.main.model.Image;
import economic.main.model.Inventory;
import economic.main.model.LikesComments;
import economic.main.model.Payment;
import economic.main.model.Product;
import economic.main.model.Receiver;
import economic.main.model.ShippingUnit;
import economic.main.model.TransportFee;
import economic.main.model.User;
import economic.main.payload.request.CommentsGetLikeResquest;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.CommentReponsitory;
import economic.main.reponsitory.DiscountsReponsitory;
import economic.main.reponsitory.ImagesReponsitory;
import economic.main.reponsitory.InventoryRepository;
import economic.main.reponsitory.LikesCommentsReponsitory;
import economic.main.reponsitory.PaymentReponsitory;
import economic.main.reponsitory.ProductReponsitory;
import economic.main.reponsitory.ReceiverReponsitory;
import economic.main.reponsitory.ShippingUnitReponsitory;
import economic.main.reponsitory.TransoprtFeeReponsitory;
import economic.main.reponsitory.UserReponsitory;
import economic.main.service.CommentsService;
import economic.main.service.InventoryService;
import economic.main.service.ProductService;

@RestController
@RequestMapping("/sub")
public class SubController {
    

    @Autowired
    PaymentReponsitory paymentRep;

    @Autowired
    ShippingUnitReponsitory shippingUnitRep;

    @Autowired
    TransoprtFeeReponsitory transoprtFeeRep;

    @Autowired
    ReceiverReponsitory receiverRep;

    @Autowired
    CommentReponsitory commentRep;

    @Autowired
    UserReponsitory userRep;

    @Autowired
    ProductReponsitory productRep;

    @Autowired
    DiscountsReponsitory discountRep;

    @Autowired
    LikesCommentsReponsitory likesCommentsReponsitory;

    @Autowired
    ImagesReponsitory imagesReponsitory;

    @Autowired
    CommentsService commentsService;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductService productService;

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/payment")
    public List<Payment> getPayments(){
        return paymentRep.findAll();
    }

    @GetMapping("/shipping-unit")
    public List<ShippingUnit> getShipList(){
        return shippingUnitRep.findAll();
    }

    @GetMapping("/shipping-unit/add")
    public ShippingUnit addShip(){

        ShippingUnit shippingUnit = shippingUnitRep.findById(1).orElse(null);

        Set<TransportFee> lisTransportFees = new HashSet<>();

        TransportFee fee1 = new TransportFee();
        TransportFee fee2 = new TransportFee();
        fee1.setShippingUnit(shippingUnit);
        fee1.setKmMax(30);
        fee1.setWeightMax(20);
        fee1.setPrice(80000);
        lisTransportFees.add(fee1);

        fee2.setShippingUnit(shippingUnit);
        fee2.setKmMax(10);
        fee2.setWeightMax(40);
        fee2.setPrice(80000);
        lisTransportFees.add(fee2);


        // shippingUnit.setTransportFees(lisTransportFees);
        

        return shippingUnit;
    }

    @GetMapping("/tsf")
    public List<TransportFee> getTransprotFeeList(){
        return transoprtFeeRep.findAll();
    }

    @GetMapping("/receiver")
    public List<Receiver> getReceivers(){
        return receiverRep.findAll();
    }

    @GetMapping("/comments")
    public List<Comment> gComments(){
        Product pr = productRep.findById("CF001").orElse(null);
        return commentRep.findByProduct(pr);
    }

    @GetMapping("/comments/add")
    public Comment addComment(){

        User user = userRep.findById(1).orElse(null);


        Product product = productRep.findById("CF001").orElse(null);

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setProduct(product);
        comment.setContent("Cafe này ngon lắm :))");



        return commentRep.save(comment);
    }

    @GetMapping("/discounts")
    public List<Discount> getDiscounts(){
        return discountRep.findDiscountValid();
    }

    @GetMapping("/detail-comment")
    public List<LikesComments> detailComment(){
        return likesCommentsReponsitory.findAll();
    }

    @GetMapping("/detail-comment/add")
    public LikesComments detailCommentAdd(){
        User user = userRep.findById(1).orElse(null);
        Comment comment = commentRep.findById(1).orElse(null);


        LikesComments likesComments = new LikesComments();

        likesComments.setComment(comment);
        likesComments.setUser(user);

        return likesCommentsReponsitory.save(likesComments);
    }

    @GetMapping("/image/{id}")
    public List<Image> images(@PathVariable String id){

        Product product = productRep.findById(id).orElse(null);

        return imagesReponsitory.findByProduct(product);
    }

    @GetMapping("/inventory")
    public Object inventories(){

       Product product = productRep.findById("CF001").orElse(null);

        return inventoryService.buildInventoryByProduct(product);
    }

    
    @GetMapping("/products")
    public Object getAllProducts () {
        return productService.getDetailProduct("CF001", "Cà phê chồn", null);
    }

   
}
