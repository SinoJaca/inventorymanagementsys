/*
 * Capstone
 * Author: Emeka Thato Nwamadi (219404070)
 * Date: October 2022
 */
package za.ac.inventorymanagementsys.inventorymanagementsys.service.persons.impl;

import org.springframework.stereotype.Service;

import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.persons.CustomerDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.model.persons.CustomerModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.persons.CustomerRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.service.persons.CustomerService;
import za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper;

import java.util.List;
import java.util.stream.Collectors;

import static za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper.declineCode;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public String createCustomer(CustomerModel customer) {
        Customer newCustomer = new Customer();
        // global variable
        String approveResponse;
        String declineResponse;
        // validate Entry Field Before Saving

        if (!Helper.IsNullOrEmptyField(customer.getFirstName()) ||
                !Helper.IsNullOrEmptyField(customer.getSurname()) ||
                !Helper.IsNullOrEmptyField(customer.getMobileNumber()) ||
                !Helper.IsNullOrEmptyField(customer.getAddress()) ||
                !Helper.IsNullOrEmptyField(customer.getGender())) {


            // check specific for mobile number
            if (!Helper.isValidPhoneNumber(customer.getMobileNumber())) {
                declineResponse = Helper.declineCode();
                return declineResponse;
            }

            newCustomer.setFirstName(customer.getFirstName());
            newCustomer.setSurname(customer.getSurname());
            newCustomer.setMobileNumber(customer.getMobileNumber());
            newCustomer.setAddress(customer.getAddress());
            newCustomer.setGender(customer.getGender());
            newCustomer.setCreatedBy(customer.getCreatedBy()); // set default creation as system
            // set current creation time
            java.util.Date dt = new java.util.Date();
            newCustomer.setCreatedDate(dt);

            customerRepository.save(newCustomer);
            approveResponse = Helper.approvedCode();
            return approveResponse;

        } else {
            declineResponse = Helper.declineCode();
            return declineResponse;
        }
    }

    @Override
    public String maintainCustomer(CustomerDtoUpdate customerDtoUpdate, Customer customer) {
        // global variable
        String approveResponse;
        String declineResponse;

        if(!Helper.IsNullOrEmptyField(customerDtoUpdate.getFirstName()) ||
                !Helper.IsNullOrEmptyField(customerDtoUpdate.getFirstName())||
                !Helper.IsNullOrEmptyField(customerDtoUpdate.getMobileNumber())||
                !Helper.IsNullOrEmptyField(customerDtoUpdate.getAddress())||
                !Helper.IsNullOrEmptyField(customerDtoUpdate.getGender())

        ){
            // check valid phone number
            if (!Helper.isValidPhoneNumber(customerDtoUpdate.getMobileNumber())) {
                declineResponse = Helper.declineCode();
                return declineResponse;
            }

            Customer updateCustomer = customerRepository.findByMobileNumber(customerDtoUpdate.getMobileNumber());

            if(updateCustomer.getMobileNumber().isEmpty()){
                declineResponse = declineCode();
                return declineResponse;
            }else{

            updateCustomer.setFirstName(customerDtoUpdate.getFirstName());
            updateCustomer.setSurname(customerDtoUpdate.getSurname());
            updateCustomer.setMobileNumber(customerDtoUpdate.getMobileNumber());
            updateCustomer.setAddress(customerDtoUpdate.getAddress());
            updateCustomer.setGender(customerDtoUpdate.getGender());
            updateCustomer.setModifiedBy(customerDtoUpdate.getModifiedBy());
            updateCustomer.setModifiedDate(customerDtoUpdate.getModifiedDate());

            customerRepository.save(updateCustomer);

            approveResponse = Helper.approvedCode();
            return approveResponse;

            }
        }else{
            declineResponse = Helper.declineCode();
            return declineResponse;
        }

    }

    @Override
    public Customer findBymobileNumber(String mobileNumber) {
        return customerRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public List<CustomerModel> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map(this::domainToModelConverter).collect(Collectors.toList()); // use reference instead of lambda func
    }


    public String deleteCustomer(String mobileNumber){
        Customer customerMobileNumber = findBymobileNumber(mobileNumber);

        // global variable
        String approveResponse;
        String declineResponse;

        if(customerMobileNumber != null){
            customerRepository.removeByMobileNumber(customerMobileNumber.getMobileNumber());
           approveResponse = Helper.approvedCode();
           return approveResponse;
        }
        else{
           declineResponse = Helper.declineCode();
           return declineResponse;
        }
    }

    private CustomerModel domainToModelConverter(Customer customer){

        CustomerModel customerModel = new CustomerModel();

        customerModel.setFirstName(customer.getFirstName());
        customerModel.setSurname(customer.getSurname());
        customerModel.setMobileNumber(customer.getMobileNumber());
        customerModel.setAddress(customer.getAddress());
        customerModel.setGender(customer.getGender());
        customerModel.setCreatedBy(customer.getCreatedBy());
        customerModel.setCreatedDate(customer.getCreatedDate());

        return customerModel;
    }

}
