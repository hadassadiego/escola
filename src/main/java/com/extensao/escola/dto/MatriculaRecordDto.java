package com.extensao.escola.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record MatriculaRecordDto(@NotBlank String name,
                                 @NotBlank String address,
                                 @NotBlank String phone,
                                 @NotBlank String birth,
                                 @NotBlank String email,
                                 @NotBlank String subscription) {
}
