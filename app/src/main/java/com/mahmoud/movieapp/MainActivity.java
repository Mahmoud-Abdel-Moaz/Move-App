package com.mahmoud.movieapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import java.util.List;

import static com.mahmoud.movieapp.R.id.cont;

public class MainActivity extends AppCompatActivity implements commication {

    RecyclerView recyclerView ;
    ArrayList<Move> LM=new ArrayList<>();;
    recycledapter recycledapter;
    RecyclerView.LayoutManager layoutManager ;

    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        con =this;
    FragmentManager manager=getFragmentManager();
    FragmentTransaction transaction =manager.beginTransaction();
    MainFragment firstFragment=new MainFragment();
    transaction.add(R.id.cont,firstFragment);
    transaction.commit();
      //  getjson();
    }

    public void getjson(){
        JsonObjectRequest requst =new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/popular?api_key=777660159186d81259c9dcfa910ad0f1&page=", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volly.getinsance(getApplicationContext()).add_request(requst);
    }

    public void downlaodimage(View view ){
//        //ImageView imag = (ImageView) findViewById(R.id.imageView2);
//        Picasso.with(getApplicationContext()).load("  http://image.tmdb.org/t/p/w185/cbRQVCia0urtv5UGsVFTdqLDIRv.jpg"
//        ).placeholder(R.drawable.f).into(imag);
    }

    @Override
    public void sendData(Move m) {
        Bundle bundle=new Bundle();

        bundle.putString("id",m.getId());
        bundle.putString("dat",m.getDat());
        bundle.putString("det",m.getDetails());
        bundle.putString("post",m.getPoster());
        bundle.putString("vot",m.getVote());
        bundle.putString("titel",m.getTitel());
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        DetailFragment detailFragment=new DetailFragment();
        transaction.add(R.id.cont,detailFragment);
        detailFragment.setArguments(bundle);
        transaction.commit();

    }
   /* public void replacefrag (View view)
    {
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        DetailFragment detailFragment=new DetailFragment();
        transaction.replace(R.id.cont,detailFragment);
        transaction.commit();
    }*/




}
