package com.aladar.pushnotifications.RestAPI.model;

import java.util.List;

public class UsuarioResponse {
    private String id;
    private String token;
    //private String data;




    public UsuarioResponse(String id, String token){
        this.id = id;
        this.token = token;
        //this.data = data;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
