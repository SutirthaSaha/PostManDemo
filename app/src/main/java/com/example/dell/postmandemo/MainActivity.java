package com.example.dell.postmandemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv;
    String url="https://www.nfly.in/api/user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.btn);
        tv=(TextView)findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String finalJson=response.toString();
                        tv.setText(finalJson);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("X-api-key", "59671596837f42d974c7e9dcf38d17e8");
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String > params = new HashMap<String, String>();
                        params.put("user_id", "12");

                        return params;
                    }
                };



                MySingleton.getmInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }
        });
    }
}
