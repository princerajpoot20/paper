package com.example.paper;

import android.content.Context;
import android.widget.Toast;

import com.example.paper.Listeners.CuratedResponseListener;
import com.example.paper.Listeners.SearchResponseListener;
import com.example.paper.Models.CuratedAPIResponse;
import com.example.paper.Models.SearchAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public  void getCuratedWallpapers(CuratedResponseListener listener,String page){
        CallWallpaperList callWallpaperList = retrofit.create(CallWallpaperList.class);
        Call<CuratedAPIResponse> call = callWallpaperList.getWallpapers(page,"24");

        call.enqueue(new Callback<CuratedAPIResponse>() {
            @Override
            public void onResponse(Call<CuratedAPIResponse> call, Response<CuratedAPIResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context,"An error occurred",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<CuratedAPIResponse> call, Throwable t) {
                listener.onError(t.getMessage());

            }
        });
    }
    //to search curated wallpapers


    

    public  void searchCuratedWallpapers(SearchResponseListener listener, String page,String query){
        CallWallpaperListSearch callWallpaperListSearch = retrofit.create(CallWallpaperListSearch.class);
        Call<SearchAPIResponse> call = callWallpaperListSearch.searchWallpapers(query,page,"24");

        call.enqueue(new Callback<SearchAPIResponse>() {
            @Override
            public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context,"An error occurred",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetch(response.body(), response.message());
            }
            @Override
            public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
                listener.onError(t.getMessage());

            }
            
        });
    }


    private interface CallWallpaperList{
        @Headers({
                "Accept: application/json",
                "Authorization: GKIlGYmipdZPl4umGNXANTGJoKosZrBw4tGeleA2Hwd2rdo0xIRqpEVQ"
        })
        @GET("curated/")
        Call<CuratedAPIResponse> getWallpapers(
                @Query("page") String page,
                @Query("per_page") String per_page
        );
    }

    private interface CallWallpaperListSearch{
        @Headers({
                "Accept: application/json",
                "Authorization: GKIlGYmipdZPl4umGNXANTGJoKosZrBw4tGeleA2Hwd2rdo0xIRqpEVQ"
        })
        @GET("search")
        Call<SearchAPIResponse> searchWallpapers(
                @Query("query") String query,
                @Query("page") String page,
                @Query("per_page") String per_page
        );
    }
}
