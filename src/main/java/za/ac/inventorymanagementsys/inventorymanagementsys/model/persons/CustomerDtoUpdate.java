/*
 * Capstone
 * Author: Emeka Thato Nwamadi (219404070)
 * Date: October 2022
 */
package za.ac.inventorymanagementsys.inventorymanagementsys.model.persons;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoUpdate {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String surname;

    private String mobileNumber;
    @NotEmpty
    private String address;
    @NotEmpty
    private String gender;

    private java.util.Date modifiedDate;

    private String modifiedBy;
}
