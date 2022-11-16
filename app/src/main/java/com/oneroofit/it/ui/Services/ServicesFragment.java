package com.oneroofit.it.ui.Services;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.oneroofit.it.Adapter.ServicesAdapter;
import com.oneroofit.it.Adapter.ServicesAdapterMain;
import com.oneroofit.it.Adapter.TeamAdapter;
import com.oneroofit.it.Helper.ServiceHelperClass;
import com.oneroofit.it.Helper.TeamHelperClass;
import com.oneroofit.it.R;
import com.oneroofit.it.verification.UrlProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicesFragment extends Fragment {
    RecyclerView servicesRecyclerMain;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    List<ServiceHelperClass> listServices;
    private static String SERVICE_JSON_URL = UrlProvider.webUrl+"add_services/getallservice.php";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Services");
            View rootview = inflater.inflate(R.layout.fragment_services, container, false);
            listServices = new ArrayList<>();
            servicesRecyclerMain = rootview.findViewById(R.id.services_main_Recycler);
            listServices = new ArrayList<>();
            servicesRecyclerMain();

            return rootview;
        }


    }
    private void servicesRecyclerMain(){

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        servicesRecyclerMain.setLayoutManager(new LinearLayoutManager(getContext()));
        //ArrayList<TeamHelperClass> teamLocations = new ArrayList<>();

        //test CODE
        request = new JsonArrayRequest(SERVICE_JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;
                for (int i = 0 ; i < response.length(); i++ ) {
                    try {
                        progressDialog.dismiss();
                        jsonObject = response.getJSONObject(i) ;
                        ServiceHelperClass service = new ServiceHelperClass() ;
                        service.setTitle(jsonObject.getString("service_name"));
                        service.setDescription(jsonObject.getString("service_description"));
                        service.setSmall_desc(jsonObject.getString("small_desc"));
                        //team.setRating(jsonObject.getString("Rating"));
                        //anime.setCategorie(jsonObject.getString("categorie"));
                        //anime.setNb_episode(jsonObject.getInt("episode"));
                        //anime.setStudio(jsonObject.getString("studio"));
                        service.setImage(jsonObject.getString("service_image"));
                        listServices.add(service);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setServicesRecyclerMain(listServices);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;

    }
    private void setServicesRecyclerMain(List<ServiceHelperClass> listServices) {


        ServicesAdapterMain myadapter = new ServicesAdapterMain(this.getContext(),listServices) ;
        servicesRecyclerMain.setLayoutManager(new LinearLayoutManager(this.getContext()));
        servicesRecyclerMain.setAdapter(myadapter);

    }
}