package za.ac.inventorymanagementsys.inventorymanagementsys.model.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups.OrderStatus;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.orders.Order;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.products.Product;

import javax.validation.constraints.NotEmpty;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//
//    Customer getCustomerByCustomerId(Long customerId);
//
//
    @Query(value= "SELECT  customerId,address," +
            "createdBy,createdDate," +
            "firstName,gender," +
            "mobileNumber,modifiedBy," +
            "modifiedDate,surname" +
            " FROM tcustomer c WHERE c.customerId = :customerId", nativeQuery = true)
    @NotEmpty
    Long getCustomerDetails(@Param("customerId") Customer customerId);
//
//    Product findProductByProductId(Long productId);
//
    @Query(value= "SELECT  productId,price," +
            "createdBy,createdDate," +
            "productCode,productName," +
            "quantity,categoryId," +
            "modifiedDate,modifiedBy" +
            " FROM tProduct p WHERE p.productId = :productId", nativeQuery = true)
    Long getProductDetails(@Param("productId") Product productId);


//    OrderStatus getOrderStatusByOrderStatusId(Long OrderStatusId);

    @Query(value= "SELECT  orderStatusId,description, code" +
            " FROM tOrderStatus od WHERE od.orderStatusId = :orderStatusId", nativeQuery = true)
    Long getOrderStatusDetails(@Param("orderStatusId") OrderStatus orderStatusId);



//    @Query(value= "SELECT c.mobileNumber, od.orderId " +
//            "FROM tCustomer c INNER JOIN tOrder od ON od.customerId = c.customerId" +
//            " WHERE c.customerId = od.customerId AND c.mobileNumber = :mobileNumber")
//    Order getOrderByCustomer(@Param("mobileNumber") String mobileNumber);

    @Query(value= "SELECT od.orderId FROM tOrder WHERE od.orderId = :orderId", nativeQuery = true)
    Order getOrderByOrderId(@Param("orderId") Long orderId);

}
