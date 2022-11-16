package com.oneroofit.it.ui.Home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.oneroofit.it.Adapter.ClientAdapter;
import com.oneroofit.it.Adapter.TeamAdapter;
import com.oneroofit.it.Helper.ClientHelperClass;
import com.oneroofit.it.Helper.ServiceHelperClass;
import com.oneroofit.it.Adapter.ServicesAdapter;
import com.oneroofit.it.Helper.TeamHelperClass;
import com.oneroofit.it.R;
import com.oneroofit.it.verification.LoginActivity;
import com.oneroofit.it.verification.UrlProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    //ViewPager viewPager;
    RecyclerView servicesRecycler, clientRecycler;
    RecyclerView.Adapter adapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    SwipeRefreshLayout swipeRefreshLayout;
    List<ServiceHelperClass> listServices;
    List<ClientHelperClass> listClients;
    private static String CLIENT_JSON_URL = UrlProvider.webUrl+"add_services/getallservice.php";
    private static String SERVICE_JSON_URL = UrlProvider.webUrl+"add_services/getallservice.php";
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        listServices = new ArrayList<>();
        servicesRecycler = rootview.findViewById(R.id.serviceRecycler);
        servicesRecycler();


        listClients = new ArrayList<>();
        clientRecycler = rootview.findViewById(R.id.clientRecycler);
        clientRecycler();

        ImageSlider imageSlider = rootview.findViewById(R.id.slider);

        List<SlideModel> slideModelList = new ArrayList<>();
        slideModelList.add(new SlideModel("https://img.freepik.com/free-vector/website-development-banner_33099-1687.jpg?size=626&ext=jpg"));
        slideModelList.add(new SlideModel("https://www.vtuelearning.com/wp-content/uploads/2020/03/android-development-course-vtuelearning.jpg"));
        slideModelList.add(new SlideModel("https://static.vecteezy.com/system/resources/previews/000/523/378/non_2x/web-development-application-design-coding-and-programming-on-laptop-and-smartphone-concept-with-programming-language-and-program-code-and-layout-on-screen-vector.jpg "));
        slideModelList.add(new SlideModel("https://legiit-service.s3.amazonaws.com/d94fd74dcde1aa553be72c1006578b23/bc8d3a391c72b1cafeefcfa086dbbd3c.jpg"));
        slideModelList.add(new SlideModel("https://image.freepik.com/free-vector/seo-optimization-banner_33099-1690.jpg"));

        imageSlider.setImageList(slideModelList, true);


        return rootview;

    }

    private void servicesRecycler() {

        if(!isConnectedToInternet(getContext())){
            showCustomDialog();
        }

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //servicesRecycler.setHasFixedSize(true);
        //servicesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));//, LinearLayoutManager.HORIZONTAL, false));

        //ArrayList<ServiceHelperClass> serviceLocations = new ArrayList<>();

        /*serviceLocations.add(new ServiceHelperClass(R.drawable.webdev, "Web Development", "HBHSXBHBSUXBUbux xusyxus..."));
        serviceLocations.add(new ServiceHelperClass(R.drawable.android, "Android Development", "HBHSXBHBSUXBUbux xusyxus..."));
        serviceLocations.add(new ServiceHelperClass(R.drawable.wordpressmain, "Wordpress Development", "HBHSXBHBSUXBUbux xusyxus..."));
        serviceLocations.add(new ServiceHelperClass(R.drawable.seo, "Search Engine Optimization", "HBHSXBHBSUXBUbux xusyxus..."));
        serviceLocations.add(new ServiceHelperClass(R.drawable.digital, "Digital Marketing", "HBHSXBHBSUXBUbux xusyxus..."));
        serviceLocations.add(new ServiceHelperClass(R.drawable.webdesign, "Website Designing", "HBHSXBHBSUXBUbux xusyxus..."));*/

        //servicesRecycler.setHasFixedSize(true);
        //servicesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        request = new JsonArrayRequest(SERVICE_JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        progressDialog.dismiss();
                        jsonObject = response.getJSONObject(i);
                        ServiceHelperClass services = new ServiceHelperClass();
                        services.setTitle(jsonObject.getString("service_name"));
                        services.setDescription(jsonObject.getString("service_description"));
                        services.setSmall_desc(jsonObject.getString("small_desc"));
                        services.setImage(jsonObject.getString("service_image"));
                        listServices.add(services);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setServiceRecycler(listServices);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Please connect to the internet")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity( new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                });
    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConn != null && wifiConn.isConnected() || (mobileConn != null && mobileConn.isConnected()))){
            return true;
        }
        else {
            return false;
        }
    }


    private void clientRecycler() {

        request = new JsonArrayRequest(SERVICE_JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        ClientHelperClass clients = new ClientHelperClass();
                        clients.setName(jsonObject.getString("service_name"));
                        clients.setDescription(jsonObject.getString("service_description"));
                        clients.setImage(jsonObject.getString("service_image"));
                        listClients.add(clients);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setClientRecycler(listClients);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private void setServiceRecycler(List<ServiceHelperClass> listServices) {


        ServicesAdapter maaadapter = new ServicesAdapter(this.getContext(), listServices);
        servicesRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        servicesRecycler.setAdapter(maaadapter);

    }

    private void setClientRecycler(List<ClientHelperClass> listClients) {

        ClientAdapter maaadapter = new ClientAdapter(this.getContext(), listClients);
        clientRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        clientRecycler.setAdapter(maaadapter);

    }


}