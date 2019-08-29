package br.edu.iftm.spinner.services;

import java.util.List;

import br.edu.iftm.spinner.entities.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {

    @GET("users")
    Call<List<User>> getUser();
}
