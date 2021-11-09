package com.example.Cat1Testing.controllers;
import com.example.Cat1Testing.model.Cart;
import com.example.Cat1Testing.model.Item;
import com.example.Cat1Testing.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/get-all")
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    @PostMapping("/add-cart")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.createCart(cart.getId(),cart.getName());
    }

    @GetMapping("/{CartId}/items")
    public List<Item> retrieveItemsInCart(@PathVariable Long cartId) {
        return cartService.retrieveItems(cartId);
    }

    @GetMapping("/{CardId}/items/{itemsId}")
    public Item retrieveDetailsForItems(@PathVariable Long cartId,
                                           @PathVariable Long itemsId) {
        return cartService.retrieveItem(cartId, itemsId);
    }

    @PostMapping("/cart/{cartId}/items")
    public Item addItemInCart(
            @PathVariable Long cartId, @RequestBody Item newItem) {

        return cartService.addItem(cartId, newItem);
    }

}
