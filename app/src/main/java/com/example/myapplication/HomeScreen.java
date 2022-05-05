package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.myapplication.DynamicRV.LoadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaticRecyclerAdapter staticRecyclerAdapter;

    List<DynamicRecyclerViewModel> itemss = new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ArrayList<StaticRecyclerViewModel> items = new ArrayList<>();
        items.add(new StaticRecyclerViewModel(R.drawable.land,"Land Registration"));
        items.add(new StaticRecyclerViewModel(R.drawable.land,"Land Registration"));
        items.add(new StaticRecyclerViewModel(R.drawable.land,"Land Registration"));
        items.add(new StaticRecyclerViewModel(R.drawable.land,"Land Registration"));
        items.add(new StaticRecyclerViewModel(R.drawable.land,"Land Registration"));
        items.add(new StaticRecyclerViewModel(R.drawable.land,"Land Registration"));
        recyclerView = findViewById(R.id.rv_1);
        staticRecyclerAdapter = new StaticRecyclerAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRecyclerAdapter);


        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));
        itemss.add(new DynamicRecyclerViewModel("Land Registrtaion"));

        RecyclerView  drv = findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv,this,itemss);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(new LoadMode() {
            @Override
            public void onLoadMode() {
                if (itemss.size()<=10){
                    itemss.add(null);
                    dynamicRVAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            itemss.remove(itemss.size()-1);
                            dynamicRVAdapter.notifyItemRemoved(itemss.size());
                            int index = itemss.size();
                            int end = index+10;
                            for (int i = index;i<end; i++){
                                String name = UUID.randomUUID().toString();
                                DynamicRecyclerViewModel item = new DynamicRecyclerViewModel(name);
                                itemss.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoaded();
                        }
                    },4000);

                }
                else
                    Toast.makeText(HomeScreen.this, "Data Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}