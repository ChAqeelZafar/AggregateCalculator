package com.aqeel.johnwick.aggregatecalculator;

import android.content.Intent;
import android.os.Bundle;

import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class Custom extends AppCompatActivity {
    TextView uniNameText, aggregateText ;
    TextInputLayout obtainedMarksCard1Input, obtainedMarksCard2Input, obtainedMarksCard3Input ;
    TextInputLayout totalMarksCard1Input, totalMarksCard2Input, totalMarksCard3Input ;
    TextInputLayout percentageCard1Input, percentageCard2Input, percentageCard3Input ;
    TextInputLayout entryTestWeightageInput, fscWeightageInput, matricWeightageInput ;

    int entryTestWeightage = 0, fscWeightage = 0, matricWeightage = 0, allCardDone = 0 ;

    Button done ;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        //getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Aggregate Calculator</font>"));

        loadAdd();


        assign() ;
        obtainedMarksCard1Input.requestFocus();
        removeErrorsOnInputLayouts() ;

        aggregateText.setVisibility(View.GONE);




        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(obtainedMarksCard1Input.getEditText().getText().toString().equals("")) || !(totalMarksCard1Input.getEditText().getText().toString().equals(""))) {
                    setPercentagecard1();
                }
                if(!(obtainedMarksCard2Input.getEditText().getText().toString().equals("")) || !(totalMarksCard2Input.getEditText().getText().toString().equals(""))) {
                    setPercentagecard2();
                }
                if(!(obtainedMarksCard3Input.getEditText().getText().toString().equals("")) || !(totalMarksCard3Input.getEditText().getText().toString().equals(""))) {
                    setPercentagecard3();
                }



//                if((obtainedMarksCard1Input.getEditText().getText().toString().equals("")) && (totalMarksCard1Input.getEditText().getText().toString().equals("")) && (percentageCard1Input.getEditText().getText().equals("") )) {
//                    allCardDone++;
//                    Toast.makeText(Uni_Info_Activity.this, "increment done" , Toast.LENGTH_LONG).show();
//                }
//
//                if((obtainedMarksCard2Input.getEditText().getText().toString().equals("")) && (totalMarksCard2Input.getEditText().getText().toString().equals("")) && (percentageCard2Input.getEditText().getText().equals("") )) {
//                    allCardDone++;
//                }
//
//                if((obtainedMarksCard3Input.getEditText().getText().toString().equals("")) && (totalMarksCard3Input.getEditText().getText().toString().equals("")) && (percentageCard3Input.getEditText().getText().equals("") )) {
//                    allCardDone++;
//
//                }





                setAggregateWithPercentage();
            }

        });


    }

    void setPercentagecard1(){

        if(!(obtainedMarksCard1Input.getEditText().getText().toString().equals("")) || !(totalMarksCard1Input.getEditText().getText().toString().equals(""))){
            if(obtainedMarksCard1Input.getEditText().getText().toString().equals("")){
                obtainedMarksCard1Input.setError("Enter Obtained Marks Plz");
                return ;
            }
            if(totalMarksCard1Input.getEditText().getText().toString().equals("")){
                totalMarksCard1Input.setError("Enter Total Marks Plz");
                return ;
            }
            allCardDone++ ;

            //Toast.makeText(Uni_Info_Activity.this, "In If" , Toast.LENGTH_LONG).show();
            DecimalFormat df2 = new DecimalFormat(".##");
            double per = 0.0, obt = 0.0 , total = 0.0;
            //obt = obtainedMarksCard1Input.getEditText().getText();
            per = ((Double.parseDouble(obtainedMarksCard1Input.getEditText().getText().toString()) * 100) /  Double.parseDouble(totalMarksCard1Input.getEditText().getText().toString()));
            //per = 65.65242 ;
            percentageCard1Input.getEditText().setText(df2.format(per).toString());

        }



    }


    void setPercentagecard2(){

        if(!(obtainedMarksCard2Input.getEditText().getText().toString().equals("")) || !(totalMarksCard2Input.getEditText().getText().toString().equals(""))){
            if(obtainedMarksCard2Input.getEditText().getText().toString().equals("")){
                obtainedMarksCard2Input.setError("Enter Obtained Marks Plz");
                return ;
            }
            if(totalMarksCard2Input.getEditText().getText().toString().equals("")){
                totalMarksCard2Input.setError("Enter Total Marks Plz");
                return ;
            }

            allCardDone++ ;
            //Toast.makeText(Uni_Info_Activity.this, "In If" , Toast.LENGTH_LONG).show();
            DecimalFormat df2 = new DecimalFormat(".##");
            double per = 0.0, obt = 0.0 , total = 0.0;
            //obt = obtainedMarksCard1Input.getEditText().getText();
            per = ((Double.parseDouble(obtainedMarksCard2Input.getEditText().getText().toString()) * 100) /  Double.parseDouble(totalMarksCard2Input.getEditText().getText().toString()));
            //per = 65.65242 ;
            percentageCard2Input.getEditText().setText(df2.format(per).toString());

        }



    }



    void setPercentagecard3(){

        if(!(obtainedMarksCard3Input.getEditText().getText().toString().equals("")) || !(totalMarksCard3Input.getEditText().getText().toString().equals(""))){
            if(obtainedMarksCard3Input.getEditText().getText().toString().equals("")){
                obtainedMarksCard3Input.setError("Enter Obtained Marks Plz");
                return ;
            }
            if(totalMarksCard3Input.getEditText().getText().toString().equals("")){
                totalMarksCard3Input.setError("Enter Total Marks Plz");
                return ;
            }

            allCardDone++ ;
            //Toast.makeText(Uni_Info_Activity.this, "In If" , Toast.LENGTH_LONG).show();
            DecimalFormat df2 = new DecimalFormat(".##");
            double per = 0.0, obt = 0.0 , total = 0.0;
            //obt = obtainedMarksCard1Input.getEditText().getText();
            per = ((Double.parseDouble(obtainedMarksCard3Input.getEditText().getText().toString()) * 100) /  Double.parseDouble(totalMarksCard3Input.getEditText().getText().toString()));
            //per = 65.65242 ;
            percentageCard3Input.getEditText().setText(df2.format(per).toString());

        }



    }

    void assign(){
        if(getIntent().hasExtra("uniName")){
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + getIntent().getExtras().getString("uniName") +"</font>"));
        }

        entryTestWeightageInput = findViewById(R.id.custom_card1_weightage);
        fscWeightageInput = findViewById(R.id.custom_card2_weightage);
        matricWeightageInput = findViewById(R.id.custom_card3_weightage);



        aggregateText = findViewById(R.id.custom_text_aggregate);

        obtainedMarksCard1Input = findViewById(R.id.custom_card1_obtained);
        obtainedMarksCard2Input = findViewById(R.id.custom_card2_obtained);
        obtainedMarksCard3Input = findViewById(R.id.custom_card3_obtained);

        totalMarksCard1Input = findViewById(R.id.custom_card1_total);
        totalMarksCard2Input = findViewById(R.id.custom_card2_total);
        totalMarksCard3Input = findViewById(R.id.custom_card3_total);

        percentageCard1Input = findViewById(R.id.custom_card1_percentage);
        percentageCard2Input = findViewById(R.id.custom_card2_percentage);
        percentageCard3Input = findViewById(R.id.custom_card3_percentage);

        done = findViewById(R.id.custom_btn_done);
    }

    void removeErrorsOnInputLayouts(){
        totalMarksCard1Input.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalMarksCard1Input.setErrorEnabled(false);
            }
        });

        totalMarksCard1Input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalMarksCard1Input.setErrorEnabled(false);
            }
        });

        obtainedMarksCard1Input.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtainedMarksCard1Input.setErrorEnabled(false);
            }
        });




        totalMarksCard2Input.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalMarksCard2Input.setErrorEnabled(false);
            }
        });

        obtainedMarksCard2Input.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtainedMarksCard2Input.setErrorEnabled(false);
            }
        });




        totalMarksCard3Input.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalMarksCard3Input.setErrorEnabled(false);
            }
        });

        obtainedMarksCard3Input.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtainedMarksCard3Input.setErrorEnabled(false);
            }
        });
    }

    void setAggregateWithNumbers(){
        DecimalFormat df2 = new DecimalFormat(".##");
        double aggregate = 0.0, temp ;
        double e = (Double.parseDouble(obtainedMarksCard1Input.getEditText().getText().toString()) / Double.parseDouble(totalMarksCard1Input.getEditText().getText().toString())) ;
        double f = (Double.parseDouble(obtainedMarksCard2Input.getEditText().getText().toString()) / Double.parseDouble(totalMarksCard2Input.getEditText().getText().toString()));
        double m = (Double.parseDouble(obtainedMarksCard3Input.getEditText().getText().toString()) / Double.parseDouble(totalMarksCard3Input.getEditText().getText().toString()));

        aggregate =((e * entryTestWeightage) + (f * fscWeightage)  +  (m * matricWeightage));


        aggregateText.setVisibility(View.VISIBLE);
        aggregateText.setText("Your Aggregate : " + df2.format(aggregate).toString());
    }

    void setAggregateWithPercentage(){
        double entryTestPercentage =  0 ;
        double fscPercentage = 0;
        double matricPercentage = 0 ;
        if(!(percentageCard1Input.getEditText().getText().toString().equals(""))){
            entryTestPercentage = ((Double.parseDouble(percentageCard1Input.getEditText().getText().toString())) / 100) ;
        }

        if(!(percentageCard2Input.getEditText().getText().toString().equals(""))){
            fscPercentage = ((Double.parseDouble(percentageCard2Input.getEditText().getText().toString())) / 100) ;

        }

        if(!(percentageCard3Input.getEditText().getText().toString().equals(""))){
            matricPercentage = ((Double.parseDouble(percentageCard3Input.getEditText().getText().toString())) / 100);

        }


        if(entryTestWeightageInput.getEditText().getText().toString().equals("")){
            entryTestWeightage = 0 ;
        }
        else {
            entryTestWeightage = Integer.parseInt(entryTestWeightageInput.getEditText().getText().toString());
        }

        if(fscWeightageInput.getEditText().getText().toString().equals("")){
            fscWeightage = 0 ;
        }
        else {
            fscWeightage = Integer.parseInt(fscWeightageInput.getEditText().getText().toString());
        }
        if(matricWeightageInput.getEditText().getText().toString().equals("")){
            matricWeightage = 0 ;
        }
        else {
            matricWeightage = Integer.parseInt(matricWeightageInput.getEditText().getText().toString());
        }


        DecimalFormat df2 = new DecimalFormat(".##");
        double aggregate = 0.0 ;
        aggregate =((entryTestPercentage * entryTestWeightage) + (fscPercentage * fscWeightage)  +  (matricPercentage * matricWeightage));
        aggregateText.setVisibility(View.VISIBLE);
        aggregateText.setText("Your Aggregate : " + df2.format(aggregate).toString());


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

                Intent i = new Intent(

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

