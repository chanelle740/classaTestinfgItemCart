package com.example.Cat1Testing.service;

import com.example.Cat1Testing.model.Item;
import com.example.Cat1Testing.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll(){
        List<Item> items = itemRepository.findAll();

        for(Item item :items){
            item.setValue (item.getQuantity()*item.getPrice());
        }
        return items;
    }

    public Item createItems(Long price, int quantity, String description){
        Item item = new Item( description, price, quantity);

        return itemRepository.save(item);

    }


    public void deleteItem(Long id){
        itemRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("item not found with id"+ id));
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, Item item){
        itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item not found with id"+ id));

        item.setId(id);

        return itemRepository.save(item);

    }

    public Item detailItem(Long id){
        return itemRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Item with id "+id+ " not found!"));
    }

}
