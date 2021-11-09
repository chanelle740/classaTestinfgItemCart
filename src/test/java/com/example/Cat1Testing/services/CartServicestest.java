package com.example.Cat1Testing.services;
import com.example.Cat1Testing.model.Cart;
import com.example.Cat1Testing.model.Item;
import com.example.Cat1Testing.repository.CartRepository;
import com.example.Cat1Testing.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServicestest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartService cartService;

    @Test
    public void returnCarts() {
        when(cartRepository.findAll()).thenReturn(Arrays.asList(
                new Cart(1L, "cartOne"),
                new Cart(2L, "cartTwo"),
                new Cart(3L, "cart Three")));
        assertEquals(null, cartService.getAll().get(1).getName());
    }


    @Test
    public void createCart() {
        when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(new Cart(1L, "Carttt"));
        assertEquals(null, cartService.createCart(4L, "Car").getName());
//        assertEquals("Male",studentService.createStudent("Didier","Male",4).getGender());
    }

    @Test
    public void deleteCart() {
        Cart cart = new Cart(5L, "Carta");
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        cartService.deleteCart(cart.getId());

        verify(cartRepository).deleteById(cart.getId());
    }


    @Test
    public void updateCart(){
        Cart cart = new Cart(9L,"Cart9");
        Cart newCart = new Cart(9L, "Cart 19");
        given(cartRepository.findById(cart.getId())).willReturn(Optional.of(cart));

        CartService.updateCart(cart.getId(),newCart);
        verify(cartRepository).save(newCart);
        verify(cartRepository).findById(cart.getId());
    }

    @Test
    public void returnCart(){
        Cart cart = new Cart();
        cart.setId(2L);

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        Cart expected = cartService.detailCart(cart.getId());

        assertThat(expected).isSameAs(cart);

        verify(cartRepository).findById(cart.getId());
    }

    @Test
    public void addItemToCart(){
        Item item = new Item("an item",200L,13);
        Cart st = new Cart(9L, "Cart 19");
        st.setItems(new ArrayList<Item>());

        when(cartRepository.findById(st.getId())).thenReturn(Optional.of(st));
        cartService.addItem(st.getId(), item);


        verify(cartRepository).save(st);
        verify(cartRepository).findById(st.getId());

        List<Item> expected = cartService.detailCart(st.getId()).getItems();
        assertThat(expected).isSameAs(st.getItems());
        verify(cartRepository).save(st);
    }

    @Test
    public void retrieveCartItems() {
        Cart st = new Cart(9L, "Cart 19");
        st.setItems(new ArrayList<Item>(Arrays.asList(
                new Item("an item",200L,13),
                new Item("an itema",200L,13),
                new Item("an itemaa",200L,13))));

        when(cartRepository.findById(st.getId())).thenReturn(Optional.of(st));
        cartService.retrieveItems(st.getId());

        assertEquals("itema",cartService.retrieveItems(st.getId()).get(1).getDescription());
    }

    @Test
    public void retrieveDetailsForItems() {
        Item item = new Item("an itemaa",200L,13);
        Cart st = new Cart(9L, "Cart 19");


        when(cartRepository.findById(st.getId())).thenReturn(Optional.of(st));
        when(cartService.retrieveItem(st.getId(), item.getId())).thenReturn(item);

        String expected = "[an itema,200L,13]";

        assertEquals(expected, st.getItems());
        verify(cartRepository).findById(st.getId());
    }


}
