package com.aqeel.johnwick.aggregatecalculator;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aqeel.johnwick.aggregatecalculator.models.AppDatabase;
import com.aqeel.johnwick.aggregatecalculator.models.Uni;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    List<String> uniNames = new LinkedList<>();
    List<String> entryWeightageList = new LinkedList<>();
    List<String> fscWeightageList = new LinkedList<>();
    List<String> matricWeightageList = new LinkedList<>();
    List<String> imgLinkList = new LinkedList<>();
    List<String> uidList = new LinkedList<>();
    ArrayList<Uni> convertInArray = new ArrayList<>();
    SwipeRefreshLayout swiper ;



    AppDatabase db;
    ArrayList<Uni> uniListForRoom = new ArrayList<>();

    RecyclerView recyclerView ;
    CardView cardView;
    ProgressBar pb ;
    TextView loading ;

    AdView adView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loadAdd();


        pb = findViewById(R.id.progressBar);
        loading = findViewById(R.id.main_text_loading);
        recyclerView = findViewById(R.id.main_recycler);
        swiper = findViewById(R.id.main_swipe_refresh);


        isShowProgress(true);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "uniList").allowMainThreadQueries().build();




        if(isNetworkAvaliable(MainActivity.this)){
            getDataFromFirestore() ;
        }
        else{
            getDataFromRoom() ;
            // textView.setText(" not available");

        }





    }

    public static boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }

    void getDataFromFirestore(){
        db.clearAllTables();
        final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Universities-List");



        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        uniNames.add(document.getId());
                        entryWeightageList.add(document.get("e").toString());
                        fscWeightageList.add(document.get("f").toString());
                        matricWeightageList.add(document.get("m").toString());
                        imgLinkList.add(document.get("img").toString());
                        uidList.add(document.get("uid").toString());
                    }
                    toUpdateRoom();
                } else {
                    Toast.makeText(MainActivity.this, "get data from firestore failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    void toUpdateRoom(){





        for(int i=0 ; i<uniNames.size(); i++){

            Uni uni =new Uni(uidList.get(i), uniNames.get(i), entryWeightageList.get(i), fscWeightageList.get(i), matricWeightageList.get(i));
            convertInArray.add(uni);
            db.uniDao().insertAll(uni);
        }

        recyclerView.setAdapter(new com.aqeel.johnwick.aggregatecalculator.Adapter(convertInArray, MainActivity.this, imgLinkList, true, swiper));
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        isShowProgress(false);







//        uniListForRoom = (ArrayList<Uni>) db.uniDao().getAll();
//
//        String s = "" ;
//        for(int i=0 ; i<uniListForRoom.size() ; i++){
//            Uni temp = uniListForRoom.get(i);
//            s = s + temp.getUid() + "  " + temp.getUniName() + temp.getEntryTestWeightage() + "From Room" + "\n";
//        }
//        textView.setText(s);
    }

    void getDataFromRoom(){
        List<Uni> list = db.uniDao().getAll();
        if(list != null && list.size() > 0)   {
            convertInArray = (ArrayList<Uni>) db.uniDao().getAll();
            recyclerView.setAdapter(new com.aqeel.johnwick.aggregatecalculator.Adapter(convertInArray, MainActivity.this, uniNames, false, swiper));
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            isShowProgress(false);
        }
        else{
            swiper.setRefreshing(false);
            Toast.makeText(MainActivity.this, "Internet unavailable \nConnect to the internet \nSwipe Down to refresh", Toast.LENGTH_LONG).show();
            swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refresh();
                }
            });

        }
    }
    void isShowProgress(boolean b){
        if(b==true){
            swiper.setRefreshing(true);
            recyclerView.setVisibility(View.GONE);
            pb.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
        }
        else{
            swiper.setRefreshing(false);

            recyclerView.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        }

    }

    private void refresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CookieSyncManager.createInstance(MainActivity.this);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.this.startActivity(intent);


                swiper.setRefreshing(false);
            }
        }, 2000);

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mShare:

                Intent  i = new Intent(

                        android.content.Intent.ACTION_SEND);

                i.setType("text/plain");

                i.putExtra(

                        android.content.Intent.EXTRA_TEXT, "The string you want to share, which can include URLs");

                startActivity(Intent.createChooser(

                        i,

                        "Title of your share dialog"));

                break;

        }

        return super.onOptionsItemSelected(item);

    }

    void loadAdd(){
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        adView = findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder().addTestDevice("F02E0203F895D0536030133C89C425DD").build();

        adView.loadAd(request);
    }

}























//        Uni uni0 = new Uni(0, "fast","50", "25", "25");
//        Uni uni1 = new Uni(1, "fast","50", "25", "25");
//        Uni uni2 = new Uni(2, "fast","50", "25", "25");
//        Uni uni3 = new Uni(3, "fast","50", "25", "25");
//        Uni uni4 = new Uni(4, "fast","50", "25", "25");
//
//        db.uniDao().insertAll(uni0, uni1, uni2, uni3, uni4);
//
//        uniLst = (ArrayList<Uni>) db.uniDao().getAll();
//
//        String s = "" ;
//        for(int i=0 ; i<uniLst.size() ; i++){
//            Uni temp = uniLst.get(i);
//            s = s + temp.getUid() + "  " + temp.getUniName() + "\n";
//        }
//        textView.setText(s);
