package com.example.rohitsharma.retrofitdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rohitsharma.retrofitdemo.model.state.StateModel;
import com.example.rohitsharma.retrofitdemo.retrofit.ApiClient;
import com.example.rohitsharma.retrofitdemo.retrofit.ApiInterface;
import com.example.rohitsharma.retrofitdemo.R;
import com.example.rohitsharma.retrofitdemo.model.country.CountryModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnGetCountries;

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private Button btnGetCountryInfo;
    private EditText etAlphaCode;
    private Button btnStateList;
    private Button btnStateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnGetCountries = (Button) findViewById(R.id.btn_countries);
        btnGetCountryInfo = (Button) findViewById(R.id.btn_country_info);
        btnStateList = (Button) findViewById(R.id.btn_state_list);
        btnStateInfo = (Button) findViewById(R.id.btn_state_info);
        etAlphaCode = (EditText) findViewById(R.id.et_alpha_code);

        btnGetCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<CountryModel> call = apiInterface.getCountryList();
                call.enqueue(new Callback<CountryModel>() {
                    @Override
                    public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {

                        CountryModel countryModel = response.body();
                        Log.i("Country response", "" + response.body().getRestResponse().getResult());
                        Toast.makeText(MainActivity.this, "" + response.body().getRestResponse().getResult(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CountryModel> call, Throwable t) {
                        Log.i("Exception", "" + t);
                        Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        btnGetCountryInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = apiInterface.getCountyInfo(etAlphaCode.getText().toString().trim());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {

                            String bodyString = new String(response.body().bytes());

                            Log.i("Country Info", "" + bodyString);
                            Toast.makeText(MainActivity.this, "" + bodyString, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("Exception", "" + t);
                        Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnStateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<StateModel> call = apiInterface.getStateList(etAlphaCode.getText().toString().trim());
                call.enqueue(new Callback<StateModel>() {
                    @Override
                    public void onResponse(Call<StateModel> call, Response<StateModel> response) {

                        StateModel stateModel = response.body();
                        Log.i("State list", "" + response.body().getRestResponse().getResult());
                        Toast.makeText(MainActivity.this, "" + response.body().getRestResponse().getResult(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StateModel> call, Throwable t) {

                        Log.i("Exception", "" + t);
                        Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnStateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ResponseBody> call = apiInterface.getStateInfo("IND", etAlphaCode.getText().toString().trim());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {
                            try {
                                String bodyString = new String(response.body().bytes());

                                Log.i("Country Info", "" + bodyString);
                                Toast.makeText(MainActivity.this, "" + bodyString, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("Exception", "" + t);
                        Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
