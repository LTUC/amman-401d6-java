package com.example.shoppy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"likedItems"})
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Shopper {
    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "liked_items",
            joinColumns = @JoinColumn(name = "shopper_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    Set<Item> likedItems;

    public void setItem(Item item) {
        likedItems.add(item);
    }
}
