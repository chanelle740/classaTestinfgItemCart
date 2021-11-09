package com.example.Cat1Testing.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private long price;

    @NotNull
    private int quantity;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;


    @Transient
    private int value;


    public Item() {

    }

    public Item(String description, Long price, int quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(Long id, String description, long price, Cart cart,int quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.cart = cart;
        this.quantity = quantity;
    }

    public Item(String description, long price, Cart cart, int quantity) {
        this.description = description;
        this.price = price;
        this.cart = cart;
        this.quantity = quantity;
    }

    public Item(Long id , String description, long price,  int quantity) {
        this.id  = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public int getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = (int) value;
    }

    @Override
    public String toString() {
        return String.format(
                "Item [id=%s, description=%s, price=%s]", id, description, price,
                description);
    }


}
