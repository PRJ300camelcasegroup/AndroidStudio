package com.example.user.shanesandapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Size;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.shanesandapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    private double v;
    ActivityMainBinding bind;
    People[] player;
    Match[] matches;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Write a message to the database
        super.onCreate(savedInstanceState);

        GetParticipantData();
        GetMatchData();
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
    public void ViewData(View view) {
      //  boolean check = false;
        List PlayerIDs = new ArrayList();
        Arrays.sort(player);
        for (People t : player) {
           PlayerIDs.add(t.PlayerID);
           System.out.println(t.PlayerID+t.Name);
        }
        for (int m=0;m<matches.length;m++){
            if (matches[m] != null) {
                int indexp1 = Collections.binarySearch(PlayerIDs, matches[m].Player1ID);
                int indexp2 = Collections.binarySearch(PlayerIDs, matches[m].Player2ID);
                bind.output.append( player[indexp1].Name +
                        " vs " + player[indexp2].Name + "\n");
                if (matches[m].Round==5){
                    bind.output.append("\t Quarter-Finals");
                }
            }
        }
       /*int index= Collections.binarySearch(PlayerIDs,matches[0].Player1ID);
        String name = player[index].Name;
        bind.output.setText(player[index].PlayerID+" "+name);*/
    }

    private void GetParticipantData(){
        RequestQueue jkj;
        jkj= Volley.newRequestQueue(getApplicationContext()); // getApplicationContext ==> It lets newly-created objects understand what has been going on in the app so far

        String url = "https://api.challonge.com/v1/tournaments/2019_WDC/participants.json?api_key=wVd60AxUdFQJKZWc0DTFVTfWlZulTUkZyigOaLnW";
        // TO ADD SECOND PARAMETER TO THIS URL SIMPILY ADD '&' PLUS THE PARAM TO PASS...
        JsonArrayRequest ChallongeApi = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                         player = new People[response.length()];
                        try {

                            // JSONArray PLAYERS = response.names();
                            //JSONObject K = response.getJSONObject("participant");
//                            JSONObject TOURNEY = response.getJSONObject("tournament");
                            for (int i = 0;i<response.length();i++){
                                player[i] = new People(response.getJSONObject(i).getJSONObject("participant").getString("name"),
                                                       response.getJSONObject(i).getJSONObject("participant").getInt("id"));
                            }
                           // System.out.println(players.get(0).Name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }

        });

        //System.out.println(player[0].Name);
        jkj.add(ChallongeApi);

    }
    private  void GetMatchData(){
        RequestQueue matchtime;
        matchtime = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.challonge.com/v1/tournaments/2019_WDC/matches.json?api_key=wVd60AxUdFQJKZWc0DTFVTfWlZulTUkZyigOaLnW";
        JsonArrayRequest ChallongeApi = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        matches = new Match[response.length()];
                        System.out.println(response.toString());

                        try {

                            // JSONArray PLAYERS = response.names();
                            //JSONObject K = response.getJSONObject("participant");
//                            JSONObject TOURNEY = response.getJSONObject("tournament");
                            for (int i = 0;i<response.length();i++){
                                matches[i] = new Match(response.getJSONObject(i).getJSONObject("match").getInt("player1_id"),
                                        response.getJSONObject(i).getJSONObject("match").getInt("player2_id"),
                                        response.getJSONObject(i).getJSONObject("match").getInt("round"));
                               // System.out.println(matches[i].Player1ID);

                            }
                           // System.out.println(matches[0].Player1ID);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }

        });
        matchtime.add(ChallongeApi);
    }
}


