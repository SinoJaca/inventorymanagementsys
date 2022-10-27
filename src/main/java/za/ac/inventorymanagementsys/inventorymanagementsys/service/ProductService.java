package za.ac.inventorymanagementsys.inventorymanagementsys.service;

import za.ac.inventorymanagementsys.inventorymanagementsys.Model.persons.CustomerDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.Model.products.ProductDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.Model.products.ProductModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.persons.Customer;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.products.Product;

import java.util.List;

public interface ProductService {
    List<ProductModel> findAllProducts();
    Product findProductByCode(String productCode);
    String CreateProducts(ProductModel productModel);
    String maintainProduct(ProductDtoUpdate productDtoUpdate, Product product);
}
