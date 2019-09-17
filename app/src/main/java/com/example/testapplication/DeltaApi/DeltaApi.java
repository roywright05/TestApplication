package com.example.testapplication.DeltaApi;

import com.example.testapplication.models.Owner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DeltaApi {

    @GET("posts#")
    Observable<List<Owner>> ownerClass();
}
