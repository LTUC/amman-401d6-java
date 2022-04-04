package com.example.shoppy.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Cart {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    private BigDecimal totalCost;

    @OneToMany(mappedBy = "cart")
    Set<Item> items;
}
