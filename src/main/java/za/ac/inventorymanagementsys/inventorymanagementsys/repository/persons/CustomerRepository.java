/*
 * Capstone
 * Author: Emeka Thato Nwamadi (219404070)
 * Date: October 2022
 */
package za.ac.inventorymanagementsys.inventorymanagementsys.repository.persons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;


import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    Customer findByMobileNumber(String mobileNumber);
    @Transactional
    List<Customer> removeByMobileNumber(String mobileNumber);

    Customer findByCustomerId(Long customerId);

}
