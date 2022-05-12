package com.example.xquotes;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class requestManager {
    Context context;
    //Instance of Retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public requestManager(Context context) {
        this.context = context;
    }


    public void getAllQuotes(Listener listener){
        CallQuotes callQuotes = retrofit.create(CallQuotes.class);
        Call<List<Model>> call = callQuotes.callQuotes();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,"Request Failed", Toast.LENGTH_SHORT).show();
                }
                listener.fetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                listener.Error(t.getMessage());

            }
        });


    }

    private interface CallQuotes {
        @GET("api/quotes")
        Call<List<Model>> callQuotes();
    }
}
