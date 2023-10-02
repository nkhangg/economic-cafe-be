package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import economic.main.model.Discount;

public interface DiscountsReponsitory extends JpaRepository<Discount, String>{
    @Query(value = "select * from Discounts d\r\n" + //
            "WHERE close_date <= FORMAT (getdate(), 'dd-MM-yy HH:ss:mm')\r\n" + //
            "and d.use_num > 0 and deleted = 0", nativeQuery = true)
    List<Discount> findDiscountValid();
}
