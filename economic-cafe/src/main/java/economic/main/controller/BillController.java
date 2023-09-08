package economic.main.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.model.Bill;
import economic.main.model.Discount;
import economic.main.model.Payment;
import economic.main.model.Receiver;
import economic.main.model.ShippingUnit;
import economic.main.model.User;
import economic.main.reponsitory.BillReponsitory;
import economic.main.reponsitory.DiscountsReponsitory;
import economic.main.reponsitory.PaymentReponsitory;
import economic.main.reponsitory.ReceiverReponsitory;
import economic.main.reponsitory.ShippingUnitReponsitory;
import economic.main.reponsitory.UserReponsitory;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillReponsitory billRep;

    @Autowired
    private UserReponsitory userRep;

    // @Autowired
    // private DetailBillReponsitory detailBillRep;

    @Autowired
    private ReceiverReponsitory receiverRep;

    @Autowired
    private PaymentReponsitory paymentRep;

    @Autowired
    private ShippingUnitReponsitory shippingUnitRep;

    // @Autowired
    // private ProductReponsitory productRes;

    @Autowired
    private DiscountsReponsitory discountRep;

    @GetMapping("")
    public List<Bill> selectAll(){
        return billRep.findAll();
    }

    @GetMapping("/add")
    public Object addNewBill(){
        User user = userRep.findById(2).orElse(null);

        Set<Discount> discounts = new HashSet<>();

        Receiver receiver = receiverRep.findById(1).orElse(null);

        Payment payment = paymentRep.findById(2).orElse(null);

        ShippingUnit shippingUnit = shippingUnitRep.findById(1).orElse(null);

        // Bill bill = billRep.findById(1).orElse(null);

        // Product product = productRes.findById("CF001").orElse(null);

        Discount discount1 = discountRep.findById("KM01").orElse(null);
        Discount discount2 = discountRep.findById("KM02").orElse(null);

        discounts.add(discount1);
        discounts.add(discount2);
        

        // DetailBill detailBill = new DetailBill();
        // detailBill.setBill(bill);
        // detailBill.setProduct(product);
        // detailBill.setQuantity(1);
        // detailBill.setPrice(product.getNewPrice() * detailBill.getQuantity());

        // Set<Bill> bills = new HashSet<>();

        Bill bill = new Bill();
        // bill.setUser(user);
        bill.setReceiver(receiver);
        bill.setStatus(0);
        bill.setPayment(payment);
        bill.setShippingUnit(shippingUnit);
        bill.setDiscounts(discounts);

        // bills.add(bill);




        return billRep.save(bill);
    }
}
