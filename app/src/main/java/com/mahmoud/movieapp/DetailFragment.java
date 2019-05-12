package com.mahmoud.movieapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment  {

    ArrayList<rev> L = new ArrayList<>();
    revadapter revadapter;
    RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutManager;
    TextView  txt_ditael,txt_name;
    String id,poster,name,dit;
    ImageView img_back;
    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View row = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle=this.getArguments();
        id=bundle.getString("id","text");
        poster=bundle.getString("post","text");
        name=bundle.getString("titel","text");
        dit=bundle.getString("det","text");

        txt_ditael=(TextView)row.findViewById(R.id.revdetile);
        txt_name=(TextView)row.findViewById(R.id.revname);
        recyclerView = (RecyclerView)row.findViewById(R.id.recyclerev);
        img_back=(ImageView) row.findViewById(R.id.revimg);

        //Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185"+ poster).into(img_back);
        Picasso.get().load("http://image.tmdb.org/t/p/w185"+ poster).into(img_back);
        txt_ditael.setText(dit);
        txt_name.setText(name);

        getjson();
        revadapter=new revadapter(L,getActivity());
        recyclerView.setAdapter(revadapter);
        layoutManager =new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        return row;
    }

    public void getjson() {
        //Toast.makeText(getActivity(), "getjson", Toast.LENGTH_SHORT).show();

        final JsonObjectRequest requst = new JsonObjectRequest(Request.Method.GET, "http://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=777660159186d81259c9dcfa910ad0f1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr= response.getJSONArray("results");
                            for (int i=0;i<arr.length();i++) {
                                JSONObject move = arr.getJSONObject(i);
                                rev r = new rev();
                                r.setName(move.getString("author"));
                                r.setRev(move.getString("content"));
                                L.add(r);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getActivity(), response + "", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
     //           Toast.makeText(getActivity(), error.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });
      //  Toast.makeText(getActivity(), "after request", Toast.LENGTH_SHORT).show();


        volly.getinsance(getActivity()).add_request(requst);
    }
}


