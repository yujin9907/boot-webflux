package co.book.reactivespringboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
    }
}
