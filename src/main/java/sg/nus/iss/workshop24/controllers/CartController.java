package sg.nus.iss.workshop24.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.workshop24.exceptions.OrderException;
import sg.nus.iss.workshop24.models.LineItems;
import sg.nus.iss.workshop24.models.OrderDetails;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession sess)
        throws OrderException{
            List<LineItems> lineItems = (List<LineItems>) sess.getAttribute("cart"); //creating the lineitem list from input
            if(null == lineItems){
                System.out.println("new session");
                System.out.println("session id = " + sess.getId());//check for session id
                lineItems = new LinkedList<>();
                sess.setAttribute("cart", lineItems);

            }
            
            String item = form.getFirst("item");
            Integer qty = Integer.parseInt(form.getFirst("quantity"));
            lineItems.add(new LineItems(item, qty));
            OrderDetails od = new OrderDetails();
            od.setName(form.getFirst("name"));
            for(LineItems line : lineItems)
                System.out.println("Description: "+line.getDescription() + "Quantity: "+ line.getQuantity());
                od.setLineItems(lineItems);

                sess.setAttribute("checkoutCart", od);
                model.addAttribute("lineItems", lineItems);

            return "cart_template";
            
        }
    
}
