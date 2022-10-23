/*
 * Capstone
 * Author: Emeka Thato Nwamadi (219404070)
 * Date: October 2022
 */
package za.ac.inventorymanagementsys.inventorymanagementsys.model.persons;

import lombok.*;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {


    @NotEmpty
    private String firstName;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String mobileNumber;

    @NotEmpty
    private String address;

    @NotEmpty
    private String gender;

    @NotEmpty
    private java.util.Date createdDate;

    @NotEmpty
    private String createdBy;

}
