package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;

public class TicketPriceCalculator extends AppCompatActivity {
    TextView museumTitle;
    ImageButton image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_calculation);
        this.setTitle("Ticket Price Calculator");
        museumTitle = (TextView) findViewById(R.id.museumTextView);
        String museumTitleTemp = this.getIntent().getStringExtra("clicked");
        this.museumTitle.setText(museumTitleTemp);
        this.createImages(museumTitleTemp);

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
}
