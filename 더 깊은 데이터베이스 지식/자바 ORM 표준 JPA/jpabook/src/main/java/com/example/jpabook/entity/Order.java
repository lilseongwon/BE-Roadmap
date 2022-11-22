package com.example.jpabook.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS") //todo order는 예약어라서 s 추가
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

   @ManyToOne
   @JoinColumn(name = "MEMBER_ID")
   private Member member; //todo 주문 화원

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)//todo 주문 날짜
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //todo 주문 상태

    //==연관관계 메소드==//
    public void setMember(Member member) {
        //todo 기존 관계 제거
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}