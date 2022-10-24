package za.ac.inventorymanagementsys.inventorymanagementsys.domain.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups.OrderStatus;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.products.Product;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(nullable = false, updatable = false)
    private Long orderId;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date modifiedDate;

    private String createdBy;
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName="customerId")
    private Customer customers;

    @ManyToOne
    @JoinColumn(name="productId", referencedColumnName="productId")
    private Product products;

    @ManyToOne
    @JoinColumn(name="orderStatusId", referencedColumnName="orderStatusId")
    private OrderStatus orderStatus;

    public Order(){}
}
