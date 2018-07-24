package com.aqeel.johnwick.aggregatecalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.aqeel.johnwick.aggregatecalculator.models.Uni;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    ArrayList<Uni> uniListForAdapter ;
    Context ctx ;
    List<String> imgLinkList ;
    SwipeRefreshLayout swiper ;
    boolean flag ;

    Adapter(ArrayList<Uni> uniListForAdapter, Context ctx, List<String> imgLinkList, boolean flag, SwipeRefreshLayout swiper){
        this.uniListForAdapter = uniListForAdapter;
        this.ctx = ctx ;
        this.imgLinkList = imgLinkList;
        this.flag = flag;
        this.swiper = swiper;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Uni uni = uniListForAdapter.get(position);
        holder.uniName.setText(uni.getUniName());



        if(flag == true) {
            String url = imgLinkList.get(position);
            Glide.with(ctx).load(url).into(holder.uniImg);
        }
        if(uni.getUniName().equals("~Custom")){
            holder.parentLayout.setBackgroundColor(Color.parseColor("#F5DEB3"));

        }


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(uni.getUniName().equals("~Custom"))) {
                    Intent intent = new Intent(ctx, Uni_Info_Activity.class);
                    intent.putExtra("uniName", uni.getUniName());
                    intent.putExtra("entryTest%", Integer.parseInt(uni.getEntryTestWeightage()));
                    intent.putExtra("fsc%", Integer.parseInt(uni.getFscWeightage()));
                    intent.putExtra("matric%", Integer.parseInt(uni.getMatricWeightage()));
                    ctx.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(ctx, Custom.class);
                    intent.putExtra("uniName", uni.getUniName());
                    intent.putExtra("entryTest%", Integer.parseInt(uni.getEntryTestWeightage()));
                    intent.putExtra("fsc%", Integer.parseInt(uni.getFscWeightage()));
                    intent.putExtra("matric%", Integer.parseInt(uni.getMatricWeightage()));
                    ctx.startActivity(intent);
                }
            }
        });

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });





    }

    private void refresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CookieSyncManager.createInstance(ctx);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ctx.startActivity(intent);


                swiper.setRefreshing(false);
            }
        }, 2000);

    }

    @Override
    public int getItemCount() {
        return uniListForAdapter.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView uniName ;
        ImageView uniImg ;
        ConstraintLayout parentLayout ;

        public Holder(@NonNull View itemView) {
            super(itemView);

            uniName = itemView.findViewById(R.id.viewholder_text_uniName);
            uniImg = itemView.findViewById(R.id.viewholder_img);
            parentLayout = itemView.findViewById(R.id.parent_Layout);
        }
    }


}
