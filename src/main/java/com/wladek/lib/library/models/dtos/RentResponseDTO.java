package com.wladek.lib.library.models.dtos;

import java.io.Serializable;

public class RentResponseDTO implements Serializable {
    private String response;
    private String description;

    public RentResponseDTO(){}

    public RentResponseDTO(String response, String description) {
        this.response = response;
        this.description = description;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
