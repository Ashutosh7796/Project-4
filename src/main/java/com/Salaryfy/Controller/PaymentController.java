package com.Salaryfy.Controller;


import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

//    @PostMapping("/CreatePayment")
//    public Order createPaymnet () throws RazorpayException {
//
//        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_WPpftTTXDicNlm", "qqPydDxwTKRf2pLW1jpZlRzl");
//        JSONObject options = new JSONObject();
//        options.put("amount", 5000);
//        options.put("currency", "INR");
//        options.put("receipt", "txn_123456");
//        Order order = razorpayClient.Orders.create(options);
//         return order;
//    }
}
