package ChartAndHistory.backend.controllers;

import ChartAndHistory.backend.models.Cart;
import ChartAndHistory.backend.models.Product;
import ChartAndHistory.backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.saveCart(cart);
        return ResponseEntity.ok(savedCart);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable long id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<Void> addProductToCart(@PathVariable long id, @RequestBody Product product) {
        cartService.addProductToCart(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/products")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable long id, @RequestBody Product product) {
        cartService.removeProductFromCart(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartById(@PathVariable long id) {
        cartService.deleteCartById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reset")
    public ResponseEntity<Void> resetCart(@PathVariable long id) {
        cartService.resetCart(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Product> getProductFromCartById(@PathVariable long cartId, @PathVariable UUID productId) {
        Product product = cartService.getProductFromCartById(cartId, productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/products/{productId}")
    public ResponseEntity<Void> removeProductFromCartByProductId(@PathVariable long id, @PathVariable UUID productId) {
        cartService.removeProductFromCartByProductId(id, productId);
        return ResponseEntity.ok().build();
    }
}