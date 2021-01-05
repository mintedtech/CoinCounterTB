package com.example.coincountertb;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import lib.Utils;

public class MainActivity extends AppCompatActivity {
    private CoinCounter mCoinCounter;
    EditText pennyAmount, nickelAmount, dimeAmount, quarterAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView statusBar = findViewById(R.id.status_bar);
        pennyAmount = findViewById(R.id.enter_pennies);
        nickelAmount = findViewById(R.id.enter_nickels);
        dimeAmount = findViewById(R.id.enter_dimes);
        quarterAmount = findViewById(R.id.enter_quarters);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = calculateTotal();
                statusBar.setText("Calculation Results: " + total);
            }
        });
    }

    private int calculateTotal() {
        int pennyVal = Integer.parseInt(pennyAmount.getText().toString());
        int nickelVal = Integer.parseInt(nickelAmount.getText().toString()) * 5;
        int dimeVal = Integer.parseInt(dimeAmount.getText().toString()) * 10;
        int quarterVal = Integer.parseInt(quarterAmount.getText().toString()) * 25;
        return pennyVal + nickelVal + dimeVal + quarterVal;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Utils.showInfoDialog (MainActivity.this,
                R.string.action_about, R.string.about_info);
            return true;
        }
        if (id == R.id.action_clear) {
            pennyAmount.setText("0");
            nickelAmount.setText("0");
            dimeAmount.setText("0");
            quarterAmount.setText("0");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}