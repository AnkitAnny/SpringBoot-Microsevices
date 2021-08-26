package com.ekart.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="tbl_product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name="item_name",length = 50)
    private String itemName;

    @Column(name="description")
    private String desc;

    @Column(name="item_price")
    private double itemPrice;

    @Column(name="rating")
    private float rating;



}
