package sg.nus.iss.workshop24.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.workshop24.exceptions.OrderException;
import sg.nus.iss.workshop24.models.OrderDetails;
import sg.nus.iss.workshop24.repositories.LineItemRepository;
import sg.nus.iss.workshop24.repositories.OrderDetailsRepository;

@Service
public class OrderService {
    @Autowired
    private OrderDetailsRepository odRepo;

    @Autowired
    private LineItemRepository lineRepo;

    @Transactional(rollbackFor = OrderException.class) //to wrap a method in a database transaction. It allows us to set propagation, isolation, timeout, read-only, and rollback conditions for our transaction.
    public void createNewOrder(OrderDetails od) throws OrderException {

        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8); //to create a random id for the order
        System.out.printf(">>>> OrderId: %s\n", orderId);

        od.setOrderId(orderId);

        // Create the purchaseOrder
        odRepo.insertOrderDetails(od);
        System.out.printf(">>>> order quantity: %s\n", od.getLineItems().size());
        if (od.getLineItems().size() > 5)
            throw new OrderException("Cannot order more than 5 items");
        // Create the associated line items
        lineRepo.addLineItems(od.getLineItems(), orderId);

    }
}
