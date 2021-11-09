package com.example.Cat1Testing.service;

import com.example.Cat1Testing.model.Cart;
import com.example.Cat1Testing.model.Item;
import com.example.Cat1Testing.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private static CartRepository cartRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public Cart createCart(Long id,String name){
        Cart cart = new Cart(id,name);
        return  cartRepository.save(cart);
    }

    public void deleteCart(Long id){
        cartRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Cart not found with id"+ id));
        cartRepository.deleteById(id);
    }

    public static Cart updateCart(Long id, Cart cart){
        cartRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Cart not found with id"+ id));

        cart.setId(id);

        return cartRepository.save(cart);

    }


    public Cart detailCart(Long id){
        return cartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart with id "+id+ " not found!"));
    }

    public List<Item> retrieveItems(Long cartId) {
        Cart cart = detailCart(cartId);
        return  cart.getItems();
    }

    public Item retrieveItem(Long cartId, Long itemId) {
        Cart cart = detailCart(cartId);

        for (Item item : cart.getItems()) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }

        return null;
    }

    public Item addItem(Long cartId, Item item) {
        Cart cart = detailCart(cartId);

        if (cart == null) {
            return null;
        }

        cart.setItem(item);
        cartRepository.save(cart);

        return item;
    }



}
