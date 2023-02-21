package co.book.reactivespringboot.entity;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Dish {

    private String description;
    private boolean delivered = false;

    public Dish(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Dish deliver(Dish dish) {
        Dish deliveredDish = new Dish(dish.description);
        deliveredDish.delivered = true;
        return deliveredDish;
    }

}
