package za.ac.inventorymanagementsys.inventorymanagementsys.model.orders;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@AllArgsConstructor
public class OrderModel {

    @NotEmpty
    Long customerId;

    @NotEmpty
    Long productId;

    @NotEmpty
    Long orderStatusId;

    private java.util.Date createdDate;

    private String createdBy;

    public OrderModel() {

    }
}
