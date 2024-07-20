package dev.lab.food_truck_mapper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Vector;

public class NearMeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng centerLocation;
    private Vector<MarkerOptions> markerOptions;

    private static final String URL = "http://192.168.56.1/informatios/all.php";
    private RequestQueue requestQueue;
    private Gson gson;
    private Information[] informations;

    public NearMeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.near_me_fragment, container, false);

        gson = new GsonBuilder().create();
        requestQueue = Volley.newRequestQueue(getContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Check for location permissions
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Define a center location
        double latitude = 3.0; // Replace with actual value
        double longitude = 101.0; // Replace with actual value
        centerLocation = new LatLng(latitude, longitude);

        // Add a marker at the center location
        MarkerOptions markerOptions = new MarkerOptions()
                .position(centerLocation)
                .title("Your Location");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation, 12));

        // Load markers from server
        loadMarkersFromServer();
    }

    private void loadMarkersFromServer() {
        // Send a GET request to the XAMPP server
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Parse the response JSON here
                informations = gson.fromJson(response, Information[].class);

                // Add markers to the map based on the retrieved data
                for (Information information : informations) {
                    LatLng location = new LatLng(information.getLatitude(), information.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(location)
                            .title(information.getName());
                    mMap.addMarker(markerOptions);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error here
                Log.e("Error", error.getMessage());
                Toast.makeText(getContext(), "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}

// Define the Information class
class Information {
    private double latitude;
    private double longitude;
    private String name;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
