package com.das.foodesapi.service;

import com.das.foodesapi.entity.CartEntity;
import com.das.foodesapi.io.CartRequest;
import com.das.foodesapi.io.CartResponse;
import com.das.foodesapi.repositry.CartRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepositry cartRepositry;
    private final UserService userService;

    @Override
    public CartResponse addToCart(CartRequest request) {
        String loggedInUserId = userService.findByUserId();
        Optional<CartEntity> cartOptional =  cartRepositry.findByUserId(loggedInUserId);
        CartEntity cart = cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));
        Map<String,Integer> cartItems = cart.getItems();
        cartItems.put(request.getFoodId(),cartItems.getOrDefault(request.getFoodId(),0) + 1);
        cart.setItems(cartItems);
        cart = cartRepositry.save(cart);
        return convertToResponse(cart);
    }

    @Override
    public CartResponse getCart() {
        String loggedInUserId = userService.findByUserId();
        CartEntity entity = cartRepositry.findByUserId(loggedInUserId)
                .orElse(new CartEntity(null,new HashMap<>()));
        return convertToResponse(entity);
    }

    @Override
    public void clearCart() {
        String loggedInUserId = userService.findByUserId();
        cartRepositry.deleteByUserId(loggedInUserId);
    }

    @Override
    public CartResponse removeFromCart(CartRequest cartRequest) {
        String loggedInUserId = userService.findByUserId();
        CartEntity entity = cartRepositry.findByUserId(loggedInUserId)
                .orElseThrow(() -> new RuntimeException("Cart is Not Found"));
        Map<String,Integer> cartItems = entity.getItems();
        if(cartItems.containsKey(cartRequest.getFoodId())){
            int currentQty = cartItems.get(cartRequest.getFoodId());
            if(currentQty > 0) {
                cartItems.put(cartRequest.getFoodId(),currentQty-1);
            }else {
                cartItems.remove(cartRequest.getFoodId());
            }
            entity = cartRepositry.save(entity);
        }
        return convertToResponse(entity);
    }

    private CartResponse convertToResponse(CartEntity cartEntity) {
        return  CartResponse.builder()
                .id(cartEntity.getId())
                .userId(cartEntity.getUserId())
                .items(cartEntity.getItems())
                .build();
    }
}
