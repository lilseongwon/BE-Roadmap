package com.example.jpabook.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ORDERS") //order는 예약어라서 s 추가
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID") //todo 멤버 외래키
    private Long MemberId;

    @Temporal(TemporalType.TIMESTAMP)//todo 주문 날짜
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //todo 주문 상태
}
