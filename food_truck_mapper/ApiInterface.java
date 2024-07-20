package dev.lab.food_truck_mapper;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Body;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/food trucks")
    Call<List<FoodTruck>> getFoodTrucks();

    @POST("api/food trucks")
    Call<Void> addFoodTruck(@Body FoodTruck foodTruck);

    @PUT("api/food trucks/{id}")
    Call<Void> updateFoodTruck(@Path("id") int id, @Body FoodTruck foodTruck);

    @DELETE("api/food trucks/{id}")
    Call<Void> deleteFoodTruck(@Path("id") int id);
}

