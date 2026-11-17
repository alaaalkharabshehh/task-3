package com.infomines.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Author is required")
        String author,

        @Min(value = 1000, message = "Year must be 1000 or later")
        Integer year,

        Boolean available
) {
}
