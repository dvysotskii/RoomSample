package com.example.dmitriy.roomsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private List<Shop> shopList;
    private long start = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getShopsFromJson();

        createShopSample();
        queryShopSample();
    }

    private void getShopsFromJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        shopList = new ArrayList<>();

        try {
            JSONArray shopsJsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < shopsJsonArray.length(); i++) {
                JSONObject shopJsonObj = shopsJsonArray.getJSONObject(i);
                Shop shop = gson.fromJson(shopJsonObj.toString(), Shop.class);
                shopList.add(shop);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void queryShopSample() {
        Observable<Double> booleanObservable = Observable.fromCallable(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return App.getInstance().getRoomDb().shopDao().queryShopById(53618);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        booleanObservable.subscribe(new Observer<Double>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Double aBoolean) {
                Log.d("3452 11111", "onNext" + aBoolean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("3452 11111", "onError" + e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void createShopSample() {
        Observable<List<Long>> voidObservable = Observable.fromCallable(new Callable<List<Long>>() {
            @Override
            public List<Long> call() throws Exception {
                return App.getInstance().getRoomDb().shopDao().createAll(shopList);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        voidObservable.subscribe(new Observer<List<Long>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                start = System.currentTimeMillis();
                Log.d("3452", "onSubscribe " + start);
            }

            @Override
            public void onNext(@NonNull List<Long> longs) {
                Log.d("3452", "id " + longs.size());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("3452", "onError" + e);
            }

            @Override
            public void onComplete() {

                Log.d("3452", "onComplete" + (System.currentTimeMillis() - start));
            }
        });
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

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("shops.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
