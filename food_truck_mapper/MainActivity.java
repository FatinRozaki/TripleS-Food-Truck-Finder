package dev.lab.food_truck_mapper;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Initialize Retrofit ApiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fetchFoodTrucks();
    }

    private void fetchFoodTrucks() {
        // Call the Retrofit API to fetch food trucks
        Call<List<FoodTruck>> call = apiInterface.getFoodTrucks();
        call.enqueue(new Callback<List<FoodTruck>>() {
            @Override
            public void onResponse(Call<List<FoodTruck>> call, Response<List<FoodTruck>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Iterate through the list of food trucks and add markers on the map
                    for (FoodTruck truck : response.body()) {
                        LatLng location = new LatLng(truck.getLatitude(), truck.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(location)
                                .title(truck.getName())
                                .snippet(truck.getMenu() + "\n" + truck.getSchedule()));
                        // Move the camera to the first food truck location
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FoodTruck>> call, Throwable t) {
                // Handle failure to fetch data from the server
                t.printStackTrace(); // Log the error
            }
        });
    }
}
