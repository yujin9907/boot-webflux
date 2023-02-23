package co.book.reactivespringboot.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cart {
    private @Id String id;
    private List<CartItem> cartItems;

    public Cart(String id) {
        this.id = id;
    }
}
