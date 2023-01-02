package sg.nus.iss.workshop24.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.iss.workshop24.models.LineItems;
import sg.nus.iss.workshop24.models.OrderDetails;

@Repository
public class LineItemRepository {
        @Autowired
        private JdbcTemplate template;

       public void addLineItems(OrderDetails orderDetails){
        addLineItems(orderDetails.getLineItems(), orderDetails.getOrderId());
       }

       public void addLineItems(List<LineItems> lineItems, String orderId) {
        List<Object[]> data = lineItems.stream() //using stream to map out the purchase order details
                .map(li -> {
                    Object[] l = new Object[3];
                    l[0] = li.getDescription();//to set up in form of array for mysql
                    l[1] = li.getQuantity();
                    l[2] = orderId;
                    return l;
                })
                .toList();

        // Batch update
        template.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }
}

