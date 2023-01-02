package sg.nus.iss.workshop24.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.nus.iss.workshop24.models.OrderDetails;

@Repository
public class OrderDetailsRepository {
    @Autowired
    private JdbcTemplate template;
    private Queries queries;

    public boolean insertOrderDetails(OrderDetails od){//create an update function for orders
        return template.update(queries.SQL_INSERT_PURCHASE_ORDER, od.getOrderId(), od.getName()) > 0;
    }
    
}
