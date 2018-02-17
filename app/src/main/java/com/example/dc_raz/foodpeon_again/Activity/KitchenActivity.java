package com.example.dc_raz.foodpeon_again.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


//import com.example.dc_raz.foodpeon_again.API.RetroFIT;

import com.example.dc_raz.foodpeon_again.API.RetroFIT;
import com.example.dc_raz.foodpeon_again.API.Retrofit2;
import com.example.dc_raz.foodpeon_again.Adapter.MyAdapter;
import com.example.dc_raz.foodpeon_again.Model.Kitchens;
import com.example.dc_raz.foodpeon_again.Model.Resturent;
import com.example.dc_raz.foodpeon_again.Model.Resturent;
import com.example.dc_raz.foodpeon_again.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class KitchenActivity extends AppCompatActivity {
    ListView listView;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_activity);
        recyclerView=findViewById(R.id.recyclerView);
        //every item of the recycle view will have same size
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //listView=findViewById(R.id.listView);
        //here we build the retrofit
        Gson gson = new GsonBuilder() .setLenient() .create();
       Log.d(""+gson,"JSON");
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        Log.i("JSON","yahooo  "+gson);
//now we have retofit object.
//then we need to build our api.
        Retrofit2   retroFIT=retrofit.create(Retrofit2.class);

        // in here RetroFIT is our our api interface... and by the the retrofit object we call
        //create and we give the RetroFIT class into it.{crtl+left click}

        //now we need to call the movies froom retrofitapi.

        Call<List<Resturent>> call =  retroFIT.getRes();

        //calling the api. it need two callback one is response and the other is failure

        call.enqueue(new Callback<List<Resturent>>() {
            @Override
            public void onResponse(Call<List<Resturent>> call, final Response<List<Resturent>> response) {
                Log.i("JSON","yahoooaa  "+response);

               // Log.i(""+response,"JSON");
                final List<Resturent> resturents=response.body();

                adapter=new MyAdapter(resturents,getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position, boolean isLongClick) {
                        Intent foodlist =new Intent(KitchenActivity.this,FoodActivity.class);
                        foodlist.putExtra("KitchenID",adapter.getItemId(position) );
                        startActivity(foodlist);
                    }


                });

              /*  List<Resturent> Resturent=response.body();
                MyAdapter adapter;
                adapter=new MyAdapter(Resturent,getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(KitchenActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int itemPosition = recyclerView.getChildLayoutPosition(view);
                                String item = mList.get(itemPosition);
                                Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(KitchenActivity.this,"Press More",Toast.LENGTH_SHORT).show();

                            }
                        })
                );
                in response we can get the list of movies..or anything that we need or we have in our class
                and we get it from above response object

                List<Resturent> kitchexns222 = new ArrayList<>();
                 now we can do whatever we want to with this Resturent object

                lets create an array {after the equal we define the size of the array :p}
                String [] moviesNAME= new String[Resturent.size()];
                String [] moviesBio= new String[Resturent.size()];

                for (int i=0; i< Resturent.size();i++){
                  moviesNAME[i]=Resturent.get(i).getName();
                   moviesBio[i+1]=Resturent.get(i+1).getBio();
                    Resturent Resturent1=new Resturent(moviesNAME,,moviesBio);
                    Resturent Resturent1=new Resturent("raz"+i+1,"asdasdasdjhasgdhas");

                    kitchexns222.add(Resturent1);


                   // moviesNAME[i]=Resturent.get(i).getBio();
                }
                adapter=new MyAdapter(kitchexns222,getApplicationContext());
                recyclerView.setAdapter(adapter);

                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,moviesNAME));
*/

            }

            @Override
            public void onFailure(Call<List<Resturent>> call, Throwable t) {
                Log.i("JSON","yahooobb  "+call);

                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG)
                        .show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        //When User Press a Back button.. it creates a new of activity of this..the code goes here.we can do with it fragment too.
        super.onBackPressed();
        startActivity(new Intent(KitchenActivity.this,MapsActivity.class));
        finish();
    }
}
