package com.api.v1.model.dto.request;

public class ClientDTO {
    private String username;
    private String nombre;
    private Integer edad;
    private String province;

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getProvince() {
        return province;
    }
}
