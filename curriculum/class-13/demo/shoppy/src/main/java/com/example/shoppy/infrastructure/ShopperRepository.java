package com.example.shoppy.infrastructure;

import com.example.shoppy.domain.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    Shopper findShopperByName(String name); // spring JPA/Hibernate generates
}
