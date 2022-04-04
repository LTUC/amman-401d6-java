package com.example.shoppy.infrastructure;

import com.example.shoppy.domain.Cart;
import com.example.shoppy.domain.Item;
import com.example.shoppy.domain.Shopper;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingService {

    Cart createNewCart(Cart cart);
    Shopper createNewShopper(Shopper shopper);
    Shopper findShopperByName(String shopperName);

    List<Cart> getAllCarts();
    Item addNewItemToCart(Item item, Long id);
    Item likeAnItem(Long shopperId, Long itemId);
    Cart findCartById(Long id);
    Item findItemById(Long id);
    Item saveAnItem(Item item);

    List<Item> findAllItemsOfCart(Long id);
    List<Item> findAllItemsAboveCost(BigDecimal cost);
    String getSingleItem(Long id, Model model);

    List<Cart> findAllCarts();
    List<Item> findAllItems();
}
