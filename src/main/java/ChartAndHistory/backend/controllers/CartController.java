package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/api/cart/{ownerId}")
    @ResponseBody
    public ResponseEntity<Cart> getCart(@PathVariable String ownerId) {
        Cart cart = cartService.getCartByOwnerId(ownerId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/cart/{ownerId}/add")
    @ResponseBody
    public ResponseEntity<Cart> addProductToCart(@PathVariable String ownerId, @RequestBody Product product) {
        Cart cart = cartService.addProductToCart(ownerId, product);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/cart/{ownerId}/remove")
    @ResponseBody
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable String ownerId, @RequestBody Product product) {
        boolean cart = cartService.removeProductFromCart(ownerId, product.getProductId());
        if (cart == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/cart/{ownerId}/clear")
    @ResponseBody
    public ResponseEntity<Void> clearCart(@PathVariable String ownerId) {
        cartService.clearCart(ownerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}