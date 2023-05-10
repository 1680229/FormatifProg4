package com.example.formatif4n6.http;

import com.example.formatif4n6.transfer.reponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    @POST("exemple/réponseAuJeu")
    Call<reponse> listRepos(@Body int reponse);

    @GET("exam2022/motdepasse/{passe}")
    Call<String> motDePasse(@Path("passe") String mdp);
}