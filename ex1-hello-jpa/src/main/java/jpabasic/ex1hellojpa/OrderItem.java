package jpabasic.ex1hellojpa;

import jakarta.persistence.*;

@Entity
public class OrderItem extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    //    @Column(name = "ORDER_ID")
//    private Long orderId;

//    @Column(name = "ITEM_ID")
//    private Long itemId;

}
