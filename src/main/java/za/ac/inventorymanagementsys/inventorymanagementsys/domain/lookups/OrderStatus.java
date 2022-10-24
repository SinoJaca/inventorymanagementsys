package za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.orders.Order;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Users;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tOrderStatus")
@NoArgsConstructor
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(nullable = false, updatable = false)
    private Long orderStatusId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String Code;

    @OneToMany(mappedBy="orderId")
    private List<Order> orders;

    public OrderStatus(Long orderStatusId) {
    }
}
