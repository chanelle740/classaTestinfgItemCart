package com.example.Cat1Testing.services;

import com.example.Cat1Testing.model.Item;
import com.example.Cat1Testing.repository.ItemRepository;
import com.example.Cat1Testing.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServicesTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    public void returnItems(){
        when(itemRepository.findAll()).thenReturn(Arrays.asList(
                new Item("an item",200L,13),
                new Item("an itemyy",200L,13),
                new Item("an itemaaa",200L,13)));
        assertEquals("an itemyy",itemService.getAll().get(1).getDescription());
    }

    @Test
    public  void createItem(){
        when(itemRepository.save(ArgumentMatchers.any(Item.class))).thenReturn(new Item("an item",200L,13));
        assertEquals("item",itemService.createItems(200L,200,"an item").getDescription());
    }

    @Test
    public void deleteItems(){
        Item cr = new Item("an item",200L,13);
        when(itemRepository.findById(cr.getId())).thenReturn(Optional.of(cr));

        itemService.deleteItem(cr.getId());

        verify(itemRepository).deleteById(cr.getId());

    }

    @Test
    public void returnItem(){
        Item item = new Item();
        item.setId(2L);

        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        Item expected = itemService.detailItem(item.getId());

        assertThat(expected).isSameAs(item);

        verify(itemRepository).findById(item.getId());
    }
}
