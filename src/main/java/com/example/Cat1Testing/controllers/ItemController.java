package com.example.Cat1Testing.controllers;

import com.example.Cat1Testing.model.Item;
import com.example.Cat1Testing.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/get-all")
    public List<Item> getAll(){
        return itemService.getAll();
    }

    @PostMapping("/add-item")
    public Item addItem(@RequestBody Item item){
        return itemService.createItems(item.getId(),item.getQuantity(), item.getDescription());
    }

    @PostMapping("/edit-item/{itemId}")
    public Item editItem(@RequestBody Item item, @PathVariable Long itemId){
        return itemService.updateItem(itemId, item);
    }

}
