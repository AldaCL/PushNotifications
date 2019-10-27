package com.aladar.pushnotifications.RestAPI;

import com.aladar.pushnotifications.RestAPI.model.UsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Endospoints {
        @FormUrlEncoded
        @POST(Constantes.KEY_POST_ID_TOKEN )
        Call<UsuarioResponse> registrarTokenID(@Field("token") String token);


}
