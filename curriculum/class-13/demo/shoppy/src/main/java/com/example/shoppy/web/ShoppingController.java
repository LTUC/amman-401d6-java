package com.example.shoppy.web;

import com.example.shoppy.domain.Cart;
import com.example.shoppy.domain.Item;
import com.example.shoppy.domain.Shopper;
import com.example.shoppy.infrastructure.CartRepository;
import com.example.shoppy.infrastructure.ItemRepository;
import com.example.shoppy.infrastructure.ShopperRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShoppingController {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ShopperRepository shopperRepository;

    public ShoppingController(CartRepository cartRepository,
                              ItemRepository itemRepository, ShopperRepository shopperRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.shopperRepository = shopperRepository;
    }

    /**
     * Creates a new cart
     *
     * @param cart
     * @return
     */
    @ResponseBody
    @PostMapping("/carts")
    Cart createNewCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }

    /**
     * Creates a new shopper
     *
     * @param shopper
     * @return
     */
    @ResponseBody
    @PostMapping("/shoppers")
    Shopper createNewShopper(@RequestBody Shopper shopper) {
        return shopperRepository.save(shopper);
    }

    @ResponseBody
    @GetMapping("/shoppers/{name}")
    Shopper findShopperByName(@PathVariable String name) {

        return shopperRepository.findShopperByName(name);
    }

    /**
     * Gets all the carts
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/carts")
    List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    /**
     *
     * @param item
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/carts/{id}")
    Item addNewItemToCart(@RequestBody Item item, @PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        item.setCart(cart);

        return itemRepository.save(item);
    }

    @ResponseBody
    @PutMapping("/shoppers/{shopperId}/items/{itemId}")
    Item likeAnItem(@PathVariable Long shopperId, @PathVariable Long itemId) {
        Shopper shopper = shopperRepository.findById(shopperId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        shopper.setItem(item);

        shopperRepository.save(shopper);
        return item;
    }

    @ResponseBody
    @GetMapping("/carts/{id}/items")
    List<Item> findAllItemsOfCart(@PathVariable Long id) {

        return itemRepository.findAllByCart_Id(id);
    }

    @ResponseBody
    @GetMapping("/items/cost/{cost}")
    List<Item> findAllItemsAboveCost(@PathVariable BigDecimal cost) {

        return itemRepository.findItemsGreatThanCost(cost);
    }
}


