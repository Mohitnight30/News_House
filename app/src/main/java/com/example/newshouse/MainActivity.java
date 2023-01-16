package com.example.newshouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ListItemClickListener{

    private static final String TAG = "MainActivity";
    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView textView;
    ArrayList<Articles> newsDataArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerview);

            RetofitInstance.getIntance().apiInterface.getNewsData().enqueue(new Callback<JSONRespond>() {
                @Override
                public void onResponse(Call<JSONRespond> call, Response<JSONRespond> response) {
                    Log.e("API", response.body().toString() );

                        JSONRespond jsonRespond=response.body();
                        newsDataArrayList = new ArrayList<>(Arrays.asList(jsonRespond.getArticles()));
                        PutDataIntoRecyclerView(newsDataArrayList);

                    newsAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                }

                @Override
                public void onFailure(Call<JSONRespond> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    textView.setText("Error: " + t.getMessage());
                    textView.setVisibility(View.VISIBLE);

                }
            });
    }

    private void PutDataIntoRecyclerView(ArrayList<Articles> newsDataList){

        newsAdapter = new NewsAdapter(getApplicationContext(), newsDataArrayList,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(newsAdapter);

    }

    @Override
    public void onListItemClickListener(int clickedItemIndex) {
        //Toast.makeText(getApplicationContext(), newsModelArrayList.get(clickedItemIndex).getNewsUrl(), Toast.LENGTH_SHORT).show();
        String url=newsDataArrayList.get(clickedItemIndex).getUrl();
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}