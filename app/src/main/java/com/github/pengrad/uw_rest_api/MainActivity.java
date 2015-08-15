package com.github.pengrad.uw_rest_api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditEndpoint;
    private EditText mEditEmail;
    private EditText mEditPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditEndpoint = (EditText) findViewById(R.id.editEndpoint);
        mEditEmail = (EditText) findViewById(R.id.editEmail);
        mEditPassword = (EditText) findViewById(R.id.editPassword);

        findViewById(R.id.buttonSubmit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String endpoint = mEditEndpoint.getText().toString();
        if (TextUtils.isEmpty(endpoint)) {
            Toast.makeText(this, "Endpoint should be as http://my-server.com/api", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();

        MyService myService = buildAPI(endpoint);

        myService.login(email, password, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Toast.makeText(getApplicationContext(), jsonElement.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private MyService buildAPI(String endpoint) {
        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(endpoint)
                .build()
                .create(MyService.class);
    }

}
