package co.book.reactivespringboot.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "cart")
public class Cart {
    private @Id String id;
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(String id) {
        this.id = id;
    }
}
