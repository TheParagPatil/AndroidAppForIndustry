package com.oneroofit.it.ui.Team;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.oneroofit.it.Adapter.ServicesAdapter;
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

public class TeamFragment extends Fragment {

    RecyclerView.Adapter adapter;
    RecyclerView teamRecycler;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    ProgressDialog progressDialog;
    List<TeamHelperClass> listTeam;
    private static String JSON_URL = UrlProvider.webUrl+"add_team_member/getall.php" ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_team, container, false);
        teamRecycler = rootview.findViewById(R.id.teamRecycler);
        dest();
        listTeam = new ArrayList<>();
        teamRecycler();
        return rootview;

    }

    private void dest() {

    }

    private void teamRecycler() {


        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        teamRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //ArrayList<TeamHelperClass> teamLocations = new ArrayList<>();

        //test CODE
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;
                for (int i = 0 ; i < response.length(); i++ ) {
                    try {
                        progressDialog.dismiss();
                        jsonObject = response.getJSONObject(i) ;
                        TeamHelperClass team = new TeamHelperClass() ;
                        team.setTitle(jsonObject.getString("name"));
                        team.setDescription(jsonObject.getString("designation"));
                        team.setImage(jsonObject.getString("image_url"));
                        listTeam.add(team);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setTeamRecycler(listTeam);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;
    }
    private void setTeamRecycler(List<TeamHelperClass> listTeam) {


        TeamAdapter myadapter = new TeamAdapter(this.getContext(),listTeam) ;
        teamRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        teamRecycler.setAdapter(myadapter);

    }
}