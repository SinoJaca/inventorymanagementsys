package za.ac.inventorymanagementsys.inventorymanagementsys.service.orders.impl;

import org.springframework.stereotype.Service;
import za.ac.inventorymanagementsys.inventorymanagementsys.Model.orders.OrderModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.lookups.OrderStatus;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.orders.Order;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.products.Product;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.lookups.OrderStatusRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.orders.OrderRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.persons.CustomerRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.products.ProductRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.service.orders.OrderService;
import za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private  final ProductRepository productRepository;
    private final OrderStatusRepository orderStatusRepository;


    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public String createOrder(OrderModel order) {
        Order newOrder = new Order();
        // global var
        String approveResponse;
        String declineResponse;
        // validate Entry Field Before Saving

        if(order.getCustomerId() <= 0){
            declineResponse = Helper.declineCode();
            return declineResponse;
        }else if(order.getProductId() <= 0){
            declineResponse = Helper.declineCode();
            return declineResponse;
        }else if(order.getOrderStatusId() <= 0){
            declineResponse = Helper.declineCode();
            return declineResponse;
        }else{
            // check if customer id exist

            Customer customer = customerRepository.findByCustomerId(order.getCustomerId());
            Product product = productRepository.findProductByProductId(order.getProductId());
            OrderStatus status = orderStatusRepository.getOrderStatusId(order.getOrderStatusId());

            if(customer.getCustomerId() == null){
                declineResponse = Helper.declineCode();
                return declineResponse;
            }else if(product.getProductId() == null){
                declineResponse = Helper.declineCode();
                return declineResponse;
            }else if(status.getOrderStatusId() == null){
                declineResponse = Helper.declineCode();
                return declineResponse;
            }else{
                newOrder.setCustomers(customer);
                newOrder.setOrderStatus(status);
                newOrder.setProducts(product);

                newOrder.setCreatedBy("SYSTEM"); // set default creation as system

                // set current creation time
                java.util.Date dt = new java.util.Date();
                newOrder.setCreatedDate(dt);

                orderRepository.save(newOrder);
                approveResponse = Helper.approvedCode();
                return approveResponse;

            }

        }
    }

    @Override
    public Order findOrderByMobileNumber(String mobileNumber) {

       // Order isExist = orderRepository.getOrderByCustomer(mobileNumber);
        //TODO: check the join on this method and also check the creation order why it loading all the
        // entity for customer, product, and order status


        Order isExist = new Order();

        if(isExist == null)
            return null;
        else
            return isExist;
    }

    @Override
    public List<OrderModel> findAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::domainToModelConverter).collect(Collectors.toList());
    }

    @Override
    public String deleteOrder(Long orderId) {
        // global var
        String approveResponse;
        String declineResponse;

        Order order = orderRepository.getOrderByOrderId(orderId);

        if(order == null){
            declineResponse = Helper.declineCode();
            return declineResponse;
        }else{
            approveResponse = Helper.approvedCode();
            return approveResponse;
        }
    }


    private OrderModel domainToModelConverter(Order order){

        OrderModel orderModel = new OrderModel();

        orderModel.setCustomerId(orderRepository.getCustomerDetails(order.getCustomers()));
        orderModel.setProductId(orderRepository.getProductDetails(order.getProducts()));
        orderModel.setOrderStatusId(orderRepository.getOrderStatusDetails(order.getOrderStatus()));
        orderModel.setCreatedBy(order.getCreatedBy());
        orderModel.setCreatedDate(order.getCreatedDate());

        return orderModel;
    }


}
