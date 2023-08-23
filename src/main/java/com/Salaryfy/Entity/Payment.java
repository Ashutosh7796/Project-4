package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payId", nullable = false)
    private Integer PayId;

    @Column(name = "receipt", length = 250)
    private String receipt;

    @Column(name = "Status", length = 45)
    private String status;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "amount", length = 45)
    private Integer amount;

    @Column(name = "PaymentType", length = 45)
    private String paymentType;

    @Column(name = "planName", length = 250)
    private String planName;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "paymentId")
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Plan plan;
}