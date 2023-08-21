package com.Salaryfy.Controller;

import com.Salaryfy.Entity.Payment;
import com.Salaryfy.Entity.Plan;
import com.Salaryfy.Entity.User;
import com.Salaryfy.Exception.UserNotFoundException;
import com.Salaryfy.Repository.PaymentRepo;
import com.Salaryfy.Repository.PlanRepository;
import com.Salaryfy.Repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final PaymentRepo paymentRepo;

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException, ParseException {
        System.out.println(data);
        Integer rupeeAmount = Integer.parseInt(data.get("amount").toString());

        int amt= Integer.parseInt(data.get("amount").toString());

        var client =  new RazorpayClient("rzp_test_ESZw3ItBQDQ2YV", "s2RQz6LkHw5WKus9YHKxQOcf");

        JSONObject ob = new JSONObject();
        ob.put("amount", amt*100);
        ob.put("currency", "INR");
        String transactionId = "txn_" + UUID.randomUUID().toString();
        ob.put("receipt",transactionId);

        Order order = client.Orders.create(ob);
        System.out.println(order);

        Integer userId = Integer.parseInt(data.get("userId").toString());

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

        Integer planId = Integer.parseInt(data.get("planId").toString());

        Plan plan = planRepository.findById(planId).orElse(null);

        String planName = plan.getPlan();

        Payment myPayment = new Payment();
        myPayment.setOrderId(order.get("id"));
        myPayment.setReceipt(order.get("receipt"));
        myPayment.setPaymentId(null);
        myPayment.setStatus("created");
        myPayment.setUser(user);
        myPayment.setPlan(plan);
        myPayment.setPlanName(planName);
        myPayment.setDate(order.get("date"));
        String isoDate = data.get("date").toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date parsedDate = dateFormat.parse(isoDate);

        java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        myPayment.setDate(timestamp);
        myPayment.setAmount(order.get("amount"));

        myPayment.setAmount(rupeeAmount);

       this.paymentRepo.save(myPayment);
        return order.toString();
    }


     @PostMapping("/updatePayment")
    public ResponseEntity<?> updateOrder (@RequestBody Map <String, Object> data) {

        Payment payment =this.paymentRepo.findByOrderId(data.get("order_id").toString());
        payment.setPaymentId(data.get("payment_id").toString());
        payment.setStatus(data.get("status").toString());

        this.paymentRepo.save(payment);
        return ResponseEntity.ok(Map.of("msg","updated"));
    }
}
