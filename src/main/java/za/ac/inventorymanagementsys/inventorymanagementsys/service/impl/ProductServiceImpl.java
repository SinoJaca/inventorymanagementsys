package za.ac.inventorymanagementsys.inventorymanagementsys.service.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import za.ac.inventorymanagementsys.inventorymanagementsys.Model.products.ProductDtoUpdate;
import za.ac.inventorymanagementsys.inventorymanagementsys.Model.products.ProductModel;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.products.Product;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.lookups.CategoryRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.repository.products.ProductRepository;
import za.ac.inventorymanagementsys.inventorymanagementsys.service.products.ProductService;
import za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static za.ac.inventorymanagementsys.inventorymanagementsys.util.Helper.declineCode;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserDetailsService userDetailsService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserDetailsService userDetailsService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public List<ProductModel> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map((product) -> domainToModelConverter(product)).collect(Collectors.toList());
    }

    @Override
    public Product findProductByCode(String productCode){
        return productRepository.findProductByProductCode(productCode);
    }

    @Override
    public String CreateProducts(ProductModel productModel) {
        Product newProduct = new Product();

        // global var
        String approveResponse;
        String declineResponse;

        // validate Entry Field Before Saving

        if(!Helper.IsNullOrEmptyField(productModel.getProductCode()) &&
                !Helper.IsNullOrEmptyField(productModel.getProductName()) &&
                productModel.getQuantity() > 0 && productModel.getCategoryId() > 0
                && productModel.getPrice() > 0
                ){

            newProduct.setProductCode(productModel.getProductCode());
            newProduct.setProductName(productModel.getProductName());
            newProduct.setProductName(productModel.getProductName());
            newProduct.setQuantity(productModel.getQuantity());
            newProduct.setPrice(productModel.getPrice());
            newProduct.setCategories(categoryRepository.getCategoryId(productModel.getCategoryId()));

            newProduct.setCreatedBy("SYSTEM"); //TODO: try to use session to get username
            java.util.Date dt = new java.util.Date();
            newProduct.setCreatedDate(dt);
            productRepository.save(newProduct);
            approveResponse = Helper.approvedCode();
            return approveResponse;
        }else{
            declineResponse = declineCode();
            return declineResponse;
        }
    }

    @Override
    public String maintainProduct(ProductDtoUpdate productDtoUpdate, Product product) {
        // global var
        String approveResponse;
        String declineResponse;

        if(!Helper.IsNullOrEmptyField(productDtoUpdate.getProductName()) ||
                productDtoUpdate.getPrice() <= 0 ||
                productDtoUpdate.getQuantity() <= 0||
                !Helper.IsNullOrEmptyField(productDtoUpdate.getProductCode())||
                productDtoUpdate.getCategoryId() <= 0

        ){
            Product isEmptyOrNot = productRepository.findProductByProductCode(productDtoUpdate.getProductCode());

            if(isEmptyOrNot.getProductCode().isEmpty()){
                declineResponse = declineCode();
                return declineResponse;
            }else{
                isEmptyOrNot.setProductName(productDtoUpdate.getProductName());
                isEmptyOrNot.setPrice(productDtoUpdate.getPrice());
                isEmptyOrNot.setQuantity(productDtoUpdate.getQuantity());
                isEmptyOrNot.setModifiedBy(productDtoUpdate.getModifiedBy());
                isEmptyOrNot.setModifiedDate(productDtoUpdate.getModifiedDate());

                productRepository.save(isEmptyOrNot);

                approveResponse = Helper.approvedCode();
                return approveResponse;

            }
        }else{
            declineResponse = declineCode();
            return declineResponse;
        }
    }


    public String deleteProductByProductCode(String productCode){
        Product myProductCode = findProductByCode(productCode);

        // global var
        String approveResponse;
        String declineResponse;

        if(myProductCode != null){
            productRepository.removeByProductCode(myProductCode.getProductCode());
            approveResponse = Helper.approvedCode();
            return approveResponse;
        }
        else{
            declineResponse = declineCode();
            return declineResponse;
        }

    }

    private ProductModel domainToModelConverter(Product product){

        ProductModel productModel = new ProductModel();

        productModel.setProductCode(product.getProductCode());
        productModel.setProductName(product.getProductName());
        productModel.setPrice(product.getPrice());
        productModel.setQuantity(product.getQuantity());
        productModel.setQuantity(product.getQuantity());
        productModel.setCreatedBy(product.getCreatedBy());
        productModel.setCreatedDate((Timestamp) product.getCreatedDate());
        productModel.setCategoryId(product.getCategories().getCategoryId());

        return productModel;
    }
}
