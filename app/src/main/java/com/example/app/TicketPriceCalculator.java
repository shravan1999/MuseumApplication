package com.example.app;
/**
 * @author Tejas Sameera, Shravan Patel
 * This class defines the second activity that the user is directed to based on his/her selection from the first activity
 * i.e. Selected museum
 * Defined the ticker price calculation functionality
 */

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TicketPriceCalculator extends AppCompatActivity {
    TextView museumTitle;
    ImageButton image;
    Spinner adult_amount, child_amount, senior_amount;
    TextView ticketPrice, salesTax, ticketTotal, adult_text, senior_text, child_text;

    /**
     * Populates the second activity with the correct dollar values and picture associated with
     * the selected museum
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_calculation);
        this.setTitle("Ticket Price Calculator");
        museumTitle = (TextView) findViewById(R.id.museumTextView);

        //Received the intent (museum selection) from the first activity
        String museumTitleTemp = this.getIntent().getStringExtra("clicked");
        this.museumTitle.setText(museumTitleTemp);


        this.adult_text = (TextView) findViewById(R.id.adult_text);
        this.senior_text = (TextView) findViewById(R.id.senior_text);
        this.child_text = (TextView) findViewById(R.id.child_text);

        this.setInitialPrices(museumTitleTemp);
        this.createImages(museumTitleTemp);

        //Sets the toast message
        Toast.makeText(this, "Maximum of 5 tickets for each!!", Toast.LENGTH_SHORT).show();

        this.ticketPrice = (TextView) findViewById(R.id.ticketPrice);
        this.ticketPrice.setTextColor(Color.WHITE);
        this.ticketTotal  = (TextView)findViewById(R.id.ticket_total);
        this.ticketTotal.setTextColor(Color.WHITE);
        this.salesTax = (TextView)findViewById(R.id.sales_tax);
        this.salesTax.setTextColor(Color.WHITE);

        this.adult_amount = (Spinner) findViewById(R.id.adult_prices);
        this.child_amount = (Spinner) findViewById(R.id.child_prices);
        this.senior_amount = (Spinner) findViewById(R.id.senior_prices);

        AdapterView.OnItemSelectedListener itemSelect = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                changeTicketPrices();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        };
    //adding a listener to each spinner to allow for ticket price recalculation
    this.adult_amount.setOnItemSelectedListener(itemSelect);
    this.senior_amount.setOnItemSelectedListener(itemSelect);
    this.child_amount.setOnItemSelectedListener(itemSelect);

    //Creating the array adapter to allow the user to select the number of tickets for each category
    String amount [] = {"0", "1", "2", "3", "4", "5"};
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(
            this, R.layout.checked_text, amount);
    adapter.setDropDownViewResource(R.layout.spinner);
    this.adult_amount.setAdapter(adapter);
    this.senior_amount.setAdapter(adapter);
    this.child_amount.setAdapter(adapter);

    }

    /**
     *Based on the user select museum, the second activity's ticket prices text fields are set according the defined
     *ticket value for that particular museum
     * @param museumTitleTemp
     */
    private void setInitialPrices(String museumTitleTemp) {

        switch(museumTitleTemp){

            case "Museum of Modern Art":
                this.adult_text.append(String.valueOf(Prices.MOMA_ADULT));
                this.senior_text.append(String.valueOf(Prices.MOMA_SENIOR));
                this.child_text.append(String.valueOf(Prices.MOMA_STUDENT));
                break;
            case "Princeton University Art Museum":
                this.adult_text.append(String.valueOf(Prices.PRINCETON_ADULT));
                this.senior_text.append(String.valueOf(Prices.PRINCETON_SENIOR));
                this.child_text.append(String.valueOf(Prices.PRINCETON_CHILD));
                break;
            case  "Metropolitan Museum of Art":
                this.adult_text.append(String.valueOf(Prices.MET_ADULT));
                this.senior_text.append(String.valueOf(Prices.MET_SENIOR));
                this.child_text.append(String.valueOf(Prices.MET_CHILD));
                break;
            case "American Museum of Natural History":
                this.adult_text.append(String.valueOf(Prices.HISTORY_ADULT));
                this.senior_text.append(String.valueOf(Prices.HISTORY_SENIOR));
                this.child_text.append(String.valueOf(Prices.HISTORY_CHILD));
                break;

        }
    }

    /**
     * Based on the selected number of tickets, calculates the total ticket price
     * Due to the listener created earlier, each time a user changes a single ticket selection value,
     * the total price is updated.
     */
    private void changeTicketPrices() {

        String text = this.adult_amount.getSelectedItem().toString();
        int adult = Integer.valueOf(text);

        String text2 = this.child_amount.getSelectedItem().toString();
        int child =  Integer.valueOf(text2);

        String text3 = this.senior_amount.getSelectedItem().toString();
        int senior =  Integer.valueOf(text3);

        String temp = this.museumTitle.getText().toString();


        int price_tickets;
        double total_price, tax_amount;

        /**
         * Using the scraped spinner values, the total ticket price is formally calculated
         */
        switch(temp){

            case "Museum of Modern Art":
                this.ticketPrice.setTextColor(Color.WHITE);
                price_tickets = (Prices.MOMA_ADULT *adult)  + (Prices.MOMA_SENIOR * senior)  + (Prices.MOMA_STUDENT *child);
                this.ticketPrice.setText(String.valueOf((price_tickets)));
                tax_amount = (Prices.NY_TAX * price_tickets);
                this.salesTax.setText(String.format("%.2f", tax_amount));
                total_price = price_tickets  + Double.valueOf(this.salesTax.getText().toString());
                this.ticketTotal.setText(String.format("%.2f", total_price));
                break;
            case "Princeton University Art Museum":
                this.ticketPrice.setTextColor(Color.WHITE);
                price_tickets = (Prices.PRINCETON_ADULT *adult) + (Prices.PRINCETON_SENIOR * senior)  + (Prices.PRINCETON_CHILD * child);
                this.ticketPrice.setText(String.valueOf((price_tickets)));
                tax_amount = (Prices.NJ_TAX * price_tickets);
                this.salesTax.setText(String.format("%.2f", tax_amount));
                total_price = price_tickets  + Double.valueOf(this.salesTax.getText().toString());
                this.ticketTotal.setText(String.format("%.2f", total_price));
                break;
            case "Metropolitan Museum of Art":
                price_tickets = (Prices.MET_ADULT *adult)  + (Prices.MET_SENIOR *senior)  + (Prices.MET_CHILD * child);
                this.ticketPrice.setText(String.valueOf((price_tickets)));
                tax_amount = (Prices.NY_TAX * price_tickets);
                this.salesTax.setText(String.format("%.2f", tax_amount));
                total_price = price_tickets  + Double.valueOf(this.salesTax.getText().toString());
                this.ticketTotal.setText(String.format("%.2f", total_price));
                break;
            case "American Museum of Natural History":
                price_tickets = (Prices.HISTORY_ADULT *adult) + (Prices.HISTORY_SENIOR * senior) + (Prices.HISTORY_CHILD *child);
                this.ticketPrice.setText(String.valueOf((price_tickets)));
                tax_amount = (Prices.NY_TAX * price_tickets);
                this.salesTax.setText(String.format("%.2f", tax_amount));
                total_price = price_tickets  + Double.valueOf(this.salesTax.getText().toString());
                this.ticketTotal.setText(String.format("%.2f", total_price));
                break;

        }
    }

    /**
     * Based on the selected museum, the image on the second activity is set to represent a picture of the actual museum
     * an on click listener is also set up to allow the user to select the picture and be redirected to the selected museum's website
     * @param museumTitleTemp
     */
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
