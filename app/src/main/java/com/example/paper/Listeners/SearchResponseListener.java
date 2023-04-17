package com.example.paper.Listeners;

import com.example.paper.Models.SearchAPIResponse;

public interface SearchResponseListener {
    void onFetch(SearchAPIResponse response, String message);
    void onError(String message);
}
//Added SearchResponseListener