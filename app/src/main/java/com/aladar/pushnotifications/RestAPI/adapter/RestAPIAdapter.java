package com.aladar.pushnotifications.RestAPI.adapter;

import com.aladar.pushnotifications.RestAPI.Constantes;
import com.aladar.pushnotifications.RestAPI.Endospoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIAdapter {

    public Endospoints establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(Endospoints.class);
        }

    }

