/*
 * Capstone
 * Author: Emeka Thato Nwamadi (219404070)
 * Date: October 2022
 */
package za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.orders.Order;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "tCustomer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(nullable = false, updatable = false)
    private Long customerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String mobileNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;

    private java.util.Date modifiedDate;

    private String createdBy;

    private String modifiedBy;

    @OneToMany(mappedBy="orderId")
    private List<Order> orders;

    public Customer(){}

    public Customer(String mobileNumber) {
    }

    public Customer(Long customerId) {
    }
}
