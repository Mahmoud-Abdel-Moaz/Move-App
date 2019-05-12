package com.mahmoud.movieapp;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.solver.Cache;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements commication {

    int page =1;
    String URL ="https://api.themoviedb.org/3/movie/popular?api_key=777660159186d81259c9dcfa910ad0f1&page=" ;
    int itemVisible=0;
    int i =0;
    LinearLayout linearLayout;
    RecyclerView recyclerView ;
    ArrayList<Move> LM=new ArrayList<>();;
    recycledapter recycledapter;
    RecyclerView.LayoutManager layoutManager ;
    commication commication;
    Context con;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main, container, false);
        commication= (commication) getActivity();
        recyclerView = (RecyclerView)v.findViewById(R.id.RecyclerView);
        linearLayout=(LinearLayout)v.findViewById(R.id.move_item);
    /*    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                if (lastVisiblePosition + 1 ==itemVisible+20) {
                    Toast.makeText(getContext(), "last item in recycle ", Toast.LENGTH_SHORT).show();
                    initializeData();
                    itemVisible += 20;
                }
            }

        });
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray result=reader.getJSONArray("results");
            for (int i=0;i<result.length();i++)
            {
                JSONObject move=result.getJSONObject(i);
                Move m = new Move();
                m.setTitel(move.getString("title"));
                m.setVote(move.getString("vote_average"));
                m.setDetails(move.getString("overview"));
                m.setPoster(move.getString("poster_path"));
                m.setDat(move.getString("release_date"));
                m.setId(move.getString("id"));
                LM.add(m);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }*/
        con=getActivity();
        getjson();
        recycledapter =new recycledapter(LM,con);
        recyclerView.setAdapter(recycledapter);
        layoutManager =new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        return v;
    }
    /*
    public void initializeData()   {

        Cache cache = VolleySingleton.getInstance(getContext()).getRequestQueue().getCache(); // We first check for cached request
        Cache.Entry entry = cache.get(URL);
        if (entry != null)
        {
            String data = new String(entry.data);
            Parsing_Json parsing_json =new Parsing_Json(data);
            movies=parsing_json.getListOfMoves();
            Log.d("bakro",""+movies.size());
            Toast.makeText(getContext(), "entry"+movies.size(), Toast.LENGTH_SHORT).show();
            itemVisible += 20;
        }
        else
        {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (
                            Request.Method.POST,
                            URL+String.valueOf(page),
                            null,
                            new Response.Listener<JSONObject>()
                            {
                                @Override
                                public void onResponse(JSONObject response)
                                {

                                    Log.d("bakro",String.valueOf(response) );
                                    Parsing_Json parsing_json =new Parsing_Json(String.valueOf(response));
                                    temp_movies=parsing_json.getListOfMoves();
                                    movies.addAll(temp_movies);
                                    // adapter.notifyItemInserted(movies.size() - 1);
                                    adapter.notifyDataSetChanged();
                                    ++page ;

                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError volleyError)
                                {
                                    Log.d(TAG, "onErrorResponse: "+volleyError);
                                    VolleyLog.d(TAG, "Error: " + volleyError.getLocalizedMessage());
                                }
                            }
                    );
            VolleySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
        }
*/


    public void getjson() {
     //   Toast.makeText(getActivity(), "getjson", Toast.LENGTH_SHORT).show();

        final JsonObjectRequest requst = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/popular?api_key=777660159186d81259c9dcfa910ad0f1&page=", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr= response.getJSONArray("results");
                            for (int i=0;i<arr.length();i++) {
                                JSONObject move = arr.getJSONObject(i);
                                Move m = new Move();
                                m.setTitel(move.getString("title"));
                                m.setVote(move.getString("vote_average"));
                                m.setId(move.getString("id"));
                                LM.add(m);
                                m.setDetails(move.getString("overview"));
                                m.setPoster(move.getString("poster_path"));
                                m.setDat(move.getString("release_date"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
              //          Toast.makeText(getActivity(), response + "", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           //     Toast.makeText(getActivity(), error.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });
     //   Toast.makeText(getActivity(), "after request", Toast.LENGTH_SHORT).show();


        volly.getinsance(getActivity()).add_request(requst);
    }

    @Override
    public void sendData(Move m) {

    }
}


