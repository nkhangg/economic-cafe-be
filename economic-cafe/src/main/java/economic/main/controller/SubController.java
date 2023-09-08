package economic.main.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.model.Comment;
import economic.main.model.Discount;
import economic.main.model.LikesComments;
import economic.main.model.Payment;
import economic.main.model.Product;
import economic.main.model.Receiver;
import economic.main.model.ShippingUnit;
import economic.main.model.TransportFee;
import economic.main.model.User;
import economic.main.reponsitory.CommentReponsitory;
import economic.main.reponsitory.DiscountsReponsitory;
import economic.main.reponsitory.LikesCommentsReponsitory;
import economic.main.reponsitory.PaymentReponsitory;
import economic.main.reponsitory.ProductReponsitory;
import economic.main.reponsitory.ReceiverReponsitory;
import economic.main.reponsitory.ShippingUnitReponsitory;
import economic.main.reponsitory.TransoprtFeeReponsitory;
import economic.main.reponsitory.UserReponsitory;

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
        return commentRep.findAll();
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
        return discountRep.findAll();
    }

    @GetMapping("/detail-comment")
    public List<LikesComments> detailComment(){
        return likesCommentsReponsitory.findAll();
    }
}
