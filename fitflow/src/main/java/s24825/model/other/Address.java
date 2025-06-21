package s24825.model.other;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @NotBlank(message = "Street is required")
    @Size(min = 2, max = 255, message = "Street must be between 2 and 255 characters")
    private String street;

    @NotBlank(message = "House number is required")
    @Size(min = 1, max = 10, message = "House number must be between 1 and 10 characters")
    private String houseNumber;

    @Size(max = 10, message = "Apartment number must be up to 10 characters")
    @Nullable
    private String apartmentNumber;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 255, message = "City must be between 2 and 255 characters")
    private String city;

    @NotBlank(message = "Postal code is required")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    private String postalCode;

}