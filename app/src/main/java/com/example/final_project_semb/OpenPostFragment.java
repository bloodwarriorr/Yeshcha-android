package com.example.final_project_semb;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.type.LatLng;

public class OpenPostFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {
    MapView mapView;
    ViewGroup root;
    Button close_btn;
    OpenPostCallback openPostCallback;
    private GoogleMap mMap;
    @Override

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        openPostCallback = (OpenPostCallback) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup)inflater.inflate(R.layout.fragment_open_post, container, false);
        initViews();
        close_btn.setOnClickListener(this);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);
        return root;
    }

    private void initViews() {
        mapView = root.findViewById(R.id.mapView);
        close_btn = root.findViewById(R.id.btn_closePostFrmnt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_closePostFrmnt:
                openPostCallback.closeOpenPostFragment();
                break;
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng telAviv = new LatLng(32.0853,34.7818);
        mMap.addMarker(new MarkerOptions()
                .position(telAviv)
                .title("אני פה"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(telAviv,16f));
    }


    public interface OpenPostCallback{
        public void closeOpenPostFragment();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}