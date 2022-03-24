package com.example.shoppy.web;

import com.example.shoppy.domain.Cart;
import com.example.shoppy.domain.Item;
import com.example.shoppy.domain.Shopper;
import com.example.shoppy.infrastructure.ShoppingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
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
        return shoppingService.createNewCart(cart);
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
        return shoppingService.createNewShopper(shopper);
    }

    @ResponseBody
    @GetMapping("/shoppers/{name}")
    Shopper findShopperByName(@PathVariable String name) {
        return shoppingService.findShopperByName(name);
    }

    /**
     * Gets all the carts
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/carts")
    List<Cart> getAllCarts() {
        return shoppingService.getAllCarts();
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
        return shoppingService.addNewItemToCart(item, id);
    }

    @ResponseBody
    @PutMapping("/shoppers/{shopperId}/items/{itemId}")
    Item likeAnItem(@PathVariable Long shopperId, @PathVariable Long itemId) {
        return shoppingService.likeAnItem(shopperId, itemId);
    }

    @ResponseBody
    @GetMapping("/carts/{id}/items")
    List<Item> findAllItemsOfCart(@PathVariable Long id) {
        return shoppingService.findAllItemsOfCart(id);
    }

    @ResponseBody
    @GetMapping("/items/cost/{cost}")
    List<Item> findAllItemsAboveCost(@PathVariable BigDecimal cost) {
        return shoppingService.findAllItemsAboveCost(cost);
    }

    @GetMapping("/items/{id}")
    String getSingleItem(@PathVariable Long id, Model model) {
        return shoppingService.getSingleItem(id, model);
    }

    @PostMapping("/items/update")
    public RedirectView updateItem(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam int cost,
            @RequestParam long cart
    ) {

        // we have to get the itemObj from the passed in id
        Item item = shoppingService.findItemById(id);

        // _and_ the location we want to connect it to from the passed in location id
        Cart foundCart = shoppingService.findCartById(cart);

        Item itemObj = item;
        Cart cartObj = foundCart;
        itemObj.setName(name);
        itemObj.setCost(BigDecimal.valueOf(cost));
        // we assign the itemObj location simply by setting the location
        itemObj.setCart(foundCart);
        // and saving it out
        itemObj = shoppingService.saveAnItem(itemObj);

        return new RedirectView("/");
    }

    @PostMapping("/items/create")
    public RedirectView createItem(
            @RequestParam String name,
            @RequestParam int cost,
            @RequestParam long cart
    ) {

        Cart foundCart = shoppingService.findCartById(cart);
        Item item = new Item();
        item.setName(name);
        item.setCost(BigDecimal.valueOf(cost));
        item.setCart(foundCart);

        shoppingService.saveAnItem(item);

        return new RedirectView("/");
    }


    @GetMapping("/")
    public String getItems(Model model) {
        List<Item> items = shoppingService.findAllItems();
        List<Cart> carts = shoppingService.findAllCarts();

        model.addAttribute("items", items);
        model.addAttribute("carts", carts);

        return "items";
    }
}


