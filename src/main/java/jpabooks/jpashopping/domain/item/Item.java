package jpabooks.jpashopping.domain.item;
import jpabooks.jpashopping.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories= new ArrayList<Category>();

//비즈니스 로직
public void addStock(int quantity){
    this.stockQuantity+=quantity;
}

public void removeStock(int quantity){
    int restStock=this.stockQuantity-quantity;
    if(restStock<0){
        throw new NotEnoughStockException("need stock");
    }
    this.stockQuantity=restStock;

}


}
