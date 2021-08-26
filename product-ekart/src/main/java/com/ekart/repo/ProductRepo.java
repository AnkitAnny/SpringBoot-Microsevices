package com.ekart.repo;

import com.ekart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    //@Query(value= "select p from Product p where p.itemName like ?1" ,nativeQuery = true)
    @Query("SELECT m FROM Product m WHERE m.itemName LIKE %:name%")
    public List<Product> findByItemName(@Param("name") String itemName);
}
