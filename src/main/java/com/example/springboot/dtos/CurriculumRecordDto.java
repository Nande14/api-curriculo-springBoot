package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record CurriculumRecordDto(@NotBlank String name, @NotBlank String description, @NotBlank String skills) {
}
