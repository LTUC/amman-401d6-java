package com.example.shoppy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@JsonIgnoreProperties({"cart"})
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Item {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private BigDecimal cost;

    @ManyToOne
    Cart cart;

    @ManyToMany(mappedBy = "likedItems")
    private Set<Shopper> shoppers;
}
