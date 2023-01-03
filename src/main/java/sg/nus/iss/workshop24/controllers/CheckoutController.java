package sg.nus.iss.workshop24.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.workshop24.exceptions.OrderException;
import sg.nus.iss.workshop24.models.LineItems;
import sg.nus.iss.workshop24.models.OrderDetails;
import sg.nus.iss.workshop24.services.OrderService;

@Controller
@RequestMapping(path = "/checkout")
public class CheckoutController {
    @Autowired
    private OrderService orderSvc;

    @PostMapping
    public String postCheckout(Model model, HttpSession sess) throws OrderException{
        List<LineItems>lineItems = (List<LineItems>)sess.getAttribute("cart");
        OrderDetails od = (OrderDetails)sess.getAttribute("checkoutCart");
        //invalidate session to prevent backlog
        sess.invalidate();
        orderSvc.createNewOrder(od);
        model.addAttribute("total", lineItems.size());
        return "checkout";
    }
    
}
