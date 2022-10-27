package za.ac.inventorymanagementsys.inventorymanagementsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.inventorymanagementsys.inventorymanagementsys.domain.products.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductCode(String productCode);
    Product findProductByProductId(Long productId);

    @Transactional
    List<Product> removeByProductCode(String productCode);

}
