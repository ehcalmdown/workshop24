package sg.nus.iss.workshop24.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderDetails {
    private String orderId;
    private String name;
    private Date orderDate;
    private List<LineItems> lineItems = new LinkedList<>();//uses the lineitems model as reference to form a mini list, added on in the purchase order details

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public List<LineItems> getLineItems() {
        return lineItems;
    }
    public void setLineItems(List<LineItems> lineItems) {
        this.lineItems = lineItems;
    }
    
    public void addLineItem(LineItems lineItem) { //to create the add function for add line item
        this.lineItems.add(lineItem);
    }
    
}
