package com.bibliotech.bibliotech.dtos.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDTO {

    private String to;
    private String subject;
    private String text;
}
