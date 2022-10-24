package za.ac.inventorymanagementsys.inventorymanagementsys.service.orders;


import za.ac.inventorymanagementsys.inventorymanagementsys.Model.orders.OrderModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.orders.Order;
import java.util.List;

public interface OrderService {

    String createOrder(OrderModel order);
    Order findOrderByMobileNumber(String mobileNumber);
    List<OrderModel> findAllOrders();
    String deleteOrder(Long orderId);
}
