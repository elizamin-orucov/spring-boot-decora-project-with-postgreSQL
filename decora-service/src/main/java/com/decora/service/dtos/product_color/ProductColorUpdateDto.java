package com.decora.service.dtos.product_color;

import com.decora.service.base.BaseIdDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductColorUpdateDto extends BaseIdDto {
    @NotBlank(message = "Color name cannot be empty")
    @Size(min = 2, max = 50, message = "Color name must be between 2 and 50 characters")
    private String colorName;

    @NotBlank(message = "Color code cannot be empty")
    @Size(min = 2, max = 50, message = "Color code must be between 2 and 50 characters")
    private String colorCode;
}
