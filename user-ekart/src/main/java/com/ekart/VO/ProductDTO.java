package com.ekart.VO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    private List<Product> productList;

    public ProductDTO(){
        productList = new ArrayList<>();
    }
}
