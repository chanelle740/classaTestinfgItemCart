package com.example.Cat1Testing.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date created_at = new Date();
    private Date updated_at = new Date();

    @OneToMany(mappedBy="cart")
    private List<Item> items;


    public Cart(){

    }

//    public  Cart(Long  cartTotal) {
//        this. cartTotal =  cartTotal;
//    }

    public Cart(Long id, String name,Date created_at, Date updated_at, List<Item> items) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.items = items;
    }

    public Cart(String name, Date created_at, Date updated_at, List<Item> items) {
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.items = items;
    }

    public Cart(String name) {
    }

    public Cart(Long id, String name) {
    }


    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return  items;
    }

    public void setItems(List<Item> items) {
        this.items =  items;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setItem(Item items) {
        this.items.add(items);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Course [id=%s, name=%s, items=%s]", id,name, items);
    }
}
