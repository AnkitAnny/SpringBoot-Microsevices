package com.ekart.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int pid;

    private String itemName;

    private String desc;

    private double itemPrice;

    private float rating;

}
