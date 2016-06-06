package com.example.contactdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.contactdemo.Adapter.ContactAdapter;
import com.example.contactdemo.Model.Contacts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacts> ContactList = new ArrayList<>();
    private ContactAdapter adapter;
    private ListView listView;
    private final String URL = "http://www.mocky.io/v2/574ff05b100000071575e9a0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.ContactList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String FirstName = ContactList.get(position).getFirstName();
                String LastName = ContactList.get(position).getLastName();
                int Mobile = ContactList.get(position).getMobileNumber();
                String Email = ContactList.get(position).getEmail();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Info");
                builder.setMessage(FirstName+" "+ LastName+"\n"+Mobile+"\n"+Email);
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.create().show();
            }
        });
        adapter = new ContactAdapter(this,ContactList);
        listView.setAdapter(adapter);

        JsonObjectRequest request = new JsonObjectRequest(URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("employees");
                    for (int i=0; i< array.length(); i++)
                    {
                        Contacts contacts = new Contacts();
                        JSONObject object = array.getJSONObject(i);
                        contacts.setFirstName(object.getString("first_name"));
                        contacts.setLastName(object.getString("last_name"));

                        //Contact Array
                        JSONObject ContactObject = object.getJSONObject("contact");
                        contacts.setMobileNumber(ContactObject.getInt("mobile"));
                        contacts.setEmail(ContactObject.getString("email"));
                        ContactList.add(contacts);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(4000, 2, 2f));
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
