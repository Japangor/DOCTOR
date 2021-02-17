package com.doctorjiyo.app.Attendent.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doctorjiyo.app.API.Model.ResponseAttendentProfile;
import com.doctorjiyo.app.API.Model.ResponseDoctorProfile;
import com.doctorjiyo.app.API.RetrofitURL1;
import com.doctorjiyo.app.API.Service.APIService;
import com.doctorjiyo.app.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Attendent_Profile_Fragment extends Fragment {

    Context context;

    CircleImageView profilepic;
    TextView name,specialization,experience,qualification,knowledge,location;

    GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attendent__profile, container, false);

        context=view.getContext();

        profilepic=(CircleImageView) view.findViewById(R.id.profilepic);
        name=view.findViewById(R.id.doc_name);
        specialization=view.findViewById(R.id.doc_speal);
        experience=view.findViewById(R.id.doc_exp);
        qualification=view.findViewById(R.id.doc_qualification);
        knowledge=view.findViewById(R.id.doc_know);
        location=view.findViewById(R.id.doc_location);
        readProfileData();


        SupportMapFragment mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.frg);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.clear();
                /*
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(19.207871, 72.835975))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 5000, null);
*/
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(19.207871, 72.835975))
                        .title("Apollo Clinic, Kandivali West ")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_place)));


            }
        });



        return view;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void readProfileData(){

        APIService service= RetrofitURL1.getClient().create(APIService.class);
        Call<ResponseAttendentProfile> call=service.getAttedentProfile(
                RetrofitURL1.AttendentID
        );
        call.enqueue(new Callback<ResponseAttendentProfile>() {
            @Override
            public void onResponse(Call<ResponseAttendentProfile> call, Response<ResponseAttendentProfile> response) {
                if ((response.code() == 200) || (response.code() == 201)){
                    if (!response.equals(null)){

                        //profilepic.setImageBitmap(getBitmapFromURL(response.body().getImgPath()));

                        if (response.body().getImgPath().equals(null)||response.body().getImgPath().equals("")||response.body().getImgPath()==null||response.body().getImgPath()==""){
                            Glide.with(context).load(response.body().getImgPath()).placeholder(R.drawable.photo_female_6).into(profilepic);
                        }else {
                            Glide.with(context).load(response.body().getImgPath()).into(profilepic);
                        }


                        name.setText(response.body().getAttendantName().trim());
                        specialization.setText(response.body().getSpecaility().trim());
                        experience.setText(response.body().getExperience().trim());
                        qualification.setText(response.body().getQualification().trim());
                        knowledge.setText(response.body().getKnowledge().trim());
                        location.setText(response.body().getLocation().trim());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAttendentProfile> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}