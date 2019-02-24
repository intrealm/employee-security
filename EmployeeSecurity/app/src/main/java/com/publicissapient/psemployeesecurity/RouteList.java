package com.publicissapient.psemployeesecurity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class RouteList extends AppCompatActivity {

    ListView listView ;

    List<RouteData> routes;
    private PopulateRouteDetailTask populateTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.routelistview);



        //start fetch data



        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                RouteData data = RouteList.this.routes.get(itemPosition);

                // Show Alert
                //Toast.makeText(RouteList.this, "", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),
                        "Route is delayed by"+data.getDelayedBy() , Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(RouteList.this, TrackRouteActivity
                        .class);
                intent.putExtra("routeNumber",data.getRouteNumber());

                startActivity(intent);

            }

        });



        populateTask = new PopulateRouteDetailTask();
        populateTask.execute((Void) null);
    }


    public class PopulateRouteDetailTask extends AsyncTask<Void, Void, List<RouteData>> {

        PopulateRouteDetailTask() {
            //mEmail = email;
            //mPassword = password;
        }

        @Override
        protected List<RouteData> doInBackground(Void... params) {

            //get sessoinid and send along
            //storeToSharedStorage("","");

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(
                    "http://192.168.1.8:9091/allActiveRoutes/populatesessionId");// replace with your url
            httpPost.addHeader("Content-type",
                    "application/x-www-form-urlencoded");

            // You can add more parameters like above



            try {
                try {
                    HttpResponse httpResponse = httpClient
                            .execute(httpPost);
                    InputStream inputStream = httpResponse.getEntity()
                            .getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(
                            inputStream);
                    BufferedReader bufferedReader = new BufferedReader(
                            inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String bufferedStrChunk = null;
                    while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                        stringBuilder.append(bufferedStrChunk);
                    }

                    String responseStrin = stringBuilder.toString();

                    return jsonToMap(responseStrin);

                } catch (ClientProtocolException cpe) {
                    System.out
                            .println("First Exception coz of HttpResponese :"
                                    + cpe);
                    cpe.printStackTrace();
                } catch (IOException ioe) {
                    System.out
                            .println("Second Exception coz of HttpResponse :"
                                    + ioe);
                    ioe.printStackTrace();
                }
                catch (Exception ioe) {
                    System.out
                            .println("Exception coz of json parsing:"
                                    + ioe);
                    ioe.printStackTrace();
                }

            } catch (Exception uee) {
                System.out
                        .println("An Exception given because of UrlEncodedFormEntity argument :"
                                + uee);
                uee.printStackTrace();
            }

            return Collections.emptyList()
                    ;
        }

        @Override
        protected void onPostExecute(final List<RouteData> returnedData) {

            //ArrayList<String> lits = new ArrayList<>(returnedData.size());


            int size = returnedData.size();
            String[] arrayOfRoute = new String[size];
            int i = 0;
            for(RouteData route : returnedData)
            {
                arrayOfRoute[i++]=("route:"+route.getRouteNumber()+",delayedBy:"+route.getDelayedBy());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(RouteList.this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, arrayOfRoute);

            listView.setAdapter(adapter);


        }

        @Override
        protected void onCancelled() {
        }
    }

    private List<RouteData> jsonToMap(String t) throws JSONException {

        List<RouteData> set = new ArrayList<>();

        JSONArray array = new JSONArray(t);
        for(int i=0;i<array.length();i++)
        {
            Map<String, String> map = new HashMap<>();
            RouteData route = new RouteData();
            JSONObject obj = (JSONObject) array.get(i);
            Iterator<String> keys = obj.keys();

            while( keys.hasNext() ){
                String key = keys.next();
                String value = obj.getString(key);
                map.put(key, value);
            }
            route.setCompleted(map.get("completed"));
            route.setRouteNumber(map.get("routeNumber"));
            route.setVehicleNumber(map.get("vehicleNumber"));
            route.setStartTime(map.get("startTime"));
            route.setDelayedBy(map.get("delayedBy"));

            set.add(route);
        }
        this.routes=set;
        return set;
    }
}
