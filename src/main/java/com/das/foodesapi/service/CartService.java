package com.das.foodesapi.service;

import com.das.foodesapi.io.CartRequest;
import com.das.foodesapi.io.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);
    CartResponse getCart();
    void clearCart();
    CartResponse removeFromCart(CartRequest cartRequest);
}
