package com.myomi.order.entity;

import com.myomi.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "orders")
@SequenceGenerator(name = "ORDERS_SEQ_GENERATOR", sequenceName = "ORDERS_SEQ"
        , initialValue = 1, allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GENERATOR"
    )
    @Column(name = "num")
    private Long oNum;

    @ManyToOne // 양방향
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    private String msg;

    @Column(name = "coupon_num")
    private Long couponNum;

    @Column(name = "used_point")
    private Long usedPoint;

    @Column(name = "pay_created_date")
    private Date payCreatedDate;

    @Column(name = "canceled_date")
    private Date canceledDate;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "save_point")
    private Long savePoint;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetail; // 양방향

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;

}
