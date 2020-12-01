package com.example.app;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class TicketPriceCalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView museumTitle;
    ImageButton image;
    Spinner adult_amount, child_amount, senior_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_calculation);
        this.setTitle("Ticket Price Calculator");
        museumTitle = (TextView) findViewById(R.id.museumTextView);
        String museumTitleTemp = this.getIntent().getStringExtra("clicked");
        this.museumTitle.setText(museumTitleTemp);
        this.createImages(museumTitleTemp);
        Toast.makeText(this, "Maximum of 5 tickets for each!!", Toast.LENGTH_SHORT).show();

        this.adult_amount = (Spinner) findViewById(R.id.adult_prices);
        this.child_amount = (Spinner) findViewById(R.id.child_prices);
        this.senior_amount = (Spinner) findViewById(R.id.senior_prices);
       String amount [] = {"0", "1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, R.layout.checked_text, amount);
        adapter.setDropDownViewResource(R.layout.spinner);
        this.adult_amount.setAdapter(adapter);
        this.senior_amount.setAdapter(adapter);
        this.child_amount.setAdapter(adapter);





    }


    private void createImages(String museumTitleTemp) {

        this.image = (ImageButton)findViewById(R.id.imageButton);

        switch(museumTitleTemp)
        {
            case "Museum of Modern Art":
                this.image.setImageResource(R.drawable.moma);
                this.image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.moma.org/"));
                        startActivity(viewIntent);
                    }
                });
                break;
            case "Princeton University Art Museum":
                this.image.setImageResource(R.drawable.princeton);
                this.image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://artmuseum.princeton.edu/"));
                        startActivity(viewIntent);
                    }
                });
                break;
            case "Metropolitan Museum of Art":
                this.image.setImageResource(R.drawable.metropolitan);
                this.image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.metmuseum.org/"));
                        startActivity(viewIntent);
                    }
                });
                break;
            case "American Museum of Natural History":
                this.image.setImageResource(R.drawable.natural_history);
                this.image.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.amnh.org/"));
                        startActivity(viewIntent);
                    }
                });
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)findViewById(R.id.adult_prices);
        String text = spinner.getSelectedItem().toString();
        int adult = Integer.valueOf(text);
        Spinner spinner2 = (Spinner)findViewById(R.id.child_prices);
        String text2 = spinner2.getSelectedItem().toString();
        int child =  Integer.valueOf(text2);
        Spinner spinner3 = (Spinner)findViewById(R.id.senior_prices);
        String text3 = spinner3.getSelectedItem().toString();
        int senior =  Integer.valueOf(text3);

        String temp = this.museumTitle.toString();

        switch(temp){

            case "Museum of Modern Art":
                TextView ticketPrice = (TextView) findViewById(R.id.ticketPrice);
                ticketPrice.setText( String.valueOf((adult  + child  + senior) * 25) );
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
