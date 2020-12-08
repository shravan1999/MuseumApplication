package com.example.app;

/**
 * @author Tejas Sameera, Shravan Patel
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Sets up the first activity of the project
 * Ability to select from a list of museums, which would direct the user to a new activity
 */
public class MainActivity extends AppCompatActivity {
    ListView listView;

    String [] museumList = {"Museum of Modern Art","Princeton University Art Museum", "Metropolitan Museum of Art", "American Museum of Natural History"};

    /**
     * Sets up the main activity
     * Uses a list view with an array adapter to set the choices the user can select from
     * i.e. List of 4 different museums from NY and NJ
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = findViewById(R.id.listView);
        this.setTitle("Museum Selection");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, museumList){

            /**
             * Overiding this method to change the style of the list view
             * Chnages include modifications to the text view
             * @param position
             * @param convertView
             * @param parent
             * @return
             */
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        listView.setAdapter(adapter);

        listView.setSelector(R.color.teal_700);

        //Sets up the onclick listener for the list view
        //Sends the selection to the second activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, TicketPriceCalculator.class);
                intent.putExtra("clicked", museumList[i]);
                startActivity(intent);
            }
        });



    }
}