package com.example.shoppy.infrastructure;

import com.example.shoppy.domain.Cart;
import com.example.shoppy.domain.Item;
import com.example.shoppy.domain.Shopper;
import com.example.shoppy.web.CartNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;

@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService {

    private final CartRepository cartRepository;
    private final ShopperRepository shopperRepository;
    private final ItemRepository itemRepository;

    public ShoppingServiceImpl(CartRepository cartRepository,
                               ShopperRepository shopperRepository,
                               ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.shopperRepository = shopperRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Cart createNewCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Shopper createNewShopper(Shopper shopper) {
        return shopperRepository.save(shopper);
    }

    @Override
    public Shopper findShopperByName(String shopperName) {
        return shopperRepository.findShopperByName(shopperName);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Item addNewItemToCart(Item item, Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart Not Found"));

        //        Optional<Cart> cart1 = cartRepository.findById(id);
//        if (cart1.get() != null) {
//            // your logic
//        } else {
//            throw new CartNotFoundException("Cart not found exception message");
//        }

        item.setCart(cart);
        return itemRepository.save(item);
    }

    @Override
    public Item likeAnItem(Long shopperId, Long itemId) {
        Shopper shopper = shopperRepository.findById(shopperId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        shopper.setItem(item);

        shopperRepository.save(shopper);
        return item;
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public Item saveAnItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAllItemsOfCart(Long id) {
        return itemRepository.findAllByCart_Id(id);
    }

    @Override
    public List<Item> findAllItemsAboveCost(BigDecimal cost) {
        return itemRepository.findItemsGreatThanCost(cost);
    }

    @Override
    public String getSingleItem(Long id, Model model) {
        Item item = itemRepository.findById(id).orElseThrow();
        List<Cart> carts = cartRepository.findAll();

        model.addAttribute("item", item);
        model.addAttribute("carts", carts);

        return "item";
    }

    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }
}
