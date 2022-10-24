/*
 * Capstone
 * Author: Emeka Thato Nwamadi (219404070)
 * Date: October 2022
 */
package za.ac.inventorymanagementsys.inventorymanagementsys.service.persons;


import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.persons.CustomerDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.persons.CustomerModel;

import java.util.List;

public interface CustomerService {

    String createCustomer(CustomerModel customer);
    String maintainCustomer(CustomerDtoUpdate customerDtoUpdate, Customer customer);
    Customer findBymobileNumber(String mobileNumber);
    List<CustomerModel> findAllCustomers();
}
