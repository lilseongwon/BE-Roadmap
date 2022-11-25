package com.example.jpabook.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name; //todo 이름
    private int price; //todo 가격
    private int stockQuantity; //todo 재고수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();
}
