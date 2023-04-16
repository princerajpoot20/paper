package com.example.paper.Listeners;

import com.example.paper.Models.CuratedAPIResponse;

public interface CuratedResponseListener {
    void onFetch(CuratedAPIResponse response , String message);
    void onError(String message);
}
//Added Curated Response Listener