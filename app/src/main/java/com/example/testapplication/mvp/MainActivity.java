package com.example.testapplication.mvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testapplication.DeltaApi.DeltaApi;
import com.example.testapplication.R;
import com.example.testapplication.models.Owner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter customAdapter;
    List<String> repos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvHome);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        repos = new ArrayList<>();

        customAdapter = new CustomAdapter(repos);

        recyclerView.setAdapter(customAdapter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DeltaApi deltaApi = (DeltaApi) retrofit.create(Owner.class);

        Observable<List<Owner>> observableRepo = deltaApi.ownerClass();
        observableRepo.subscribeOn(Schedulers.io());
        observableRepo.observeOn(AndroidSchedulers.mainThread());
        observableRepo.subscribe(new Observer<List<Owner>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Owner> owners) {

                for(Owner owner: owners){
                    repos.add(owner.getTitle());

                    customAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

//        repos.add("Pig Latin");
//        repos.add("is ever so");
//        repos.add("popular");


    }
}


/*
*
* https://jsonplaceholder.typicode.com/posts
*
* */