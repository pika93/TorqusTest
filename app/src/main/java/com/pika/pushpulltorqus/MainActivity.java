package com.pika.pushpulltorqus;
/**
 * Created by Pika on 5/15/2015.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    //--Define variables with Global Scope--//
    Button push, pull;
    TextView statusTv;
    ListView stackLv;
    //--A custom adapter for our stack--//
    CustomArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //--Inflate layout containing stack and push,pull buttons --//
        setContentView(R.layout.activity_main);

        //--Obtain a ref to our views --//
        statusTv = (TextView) findViewById(R.id.statusTv);
        stackLv = (ListView) findViewById(R.id.stackListView);
        push = (Button) findViewById(R.id.push);
        pull = (Button) findViewById(R.id.pull);

        //-- A list Collection to hold Entries to our Stack--//
        final List<String> objects = new ArrayList<>();

        //--Initialize our Custom Adapter @CustomArrayAdapter --//
        adapter = new CustomArrayAdapter(this, R.layout.list_view_row, R.id.stackTextView, objects);
        stackLv.setAdapter(adapter);

        //--Set default values--//
        statusTv.setText("Press the push! button to get started.");

        //--Code for push--//
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objects.add("" + getRandomVal());
                adapter.notifyDataSetChanged();
                statusTv.setText("Pushed " + objects.get(objects.size() - 1) + " to stack.");
            }
        });

        //--Code for Pull--//
        pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //--Check stack status--//
                if (isStackEmpty()) {
                    Toast.makeText(MainActivity.this, "Sorry! Stack is Empty!", Toast.LENGTH_SHORT).show();
                    statusTv.setText("Empty Stack.");
                    return;
                }
                statusTv.setText("Pulled " + objects.get(objects.size() - 1) + " from stack.");
                objects.remove(objects.size() - 1);
                adapter.notifyDataSetChanged();

            }
        });


    }

    /**
     * Method invoked by Pull,to check if stack is empty before popping stack.
     *
     * @return true if empty
     */
    private boolean isStackEmpty() {
        return stackLv.getAdapter().isEmpty();
    }

    /**
     * A static method to generate random values to populate the ListView Adapter with data
     *
     * @return random integer between 0-100
     */
    private static int getRandomVal() {

        Random random = new Random();
        return random.nextInt(100);

    }

}
