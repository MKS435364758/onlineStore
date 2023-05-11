package com.onlineShopping.Web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "card_holder")
    private String CardHolder;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "date")
    private String date;
//    private Timestamp date;

    @Column(name = "secure_code")
    private Long secureCode;

    @Column(name = "transaction_id")
    private String transaction_id;

    @Column(name="status")
    private String status;

    @CreationTimestamp
    @Column(name = "made_on")
    //private Date madeOn;
    private Timestamp madeOn;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Orders order;

}
