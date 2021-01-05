package com.example.coincountertb;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import lib.Utils;

public class MainActivity extends AppCompatActivity {
    private CoinCounter mCoinCounter = new CoinCounter();
    EditText pennyAmount, nickelAmount, dimeAmount, quarterAmount;
    TextView statusBar;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("COINCOUNTER", CoinCounter.getJSONStringFrom(mCoinCounter));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCoinCounter = CoinCounter.getCoinCounterObjectFromJSONString(savedInstanceState.getString("COINCOUNTER"));
        displayCalculation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupViews();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAmounts();
                displayCalculation();
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupViews() {
        statusBar = findViewById(R.id.status_bar);
        pennyAmount = findViewById(R.id.enter_pennies);
        nickelAmount = findViewById(R.id.enter_nickels);
        dimeAmount = findViewById(R.id.enter_dimes);
        quarterAmount = findViewById(R.id.enter_quarters);
    }

    private void setAmounts() {
        mCoinCounter.setCountOfPennies(pennyAmount.getText().toString());
        mCoinCounter.setCountOfNickels(nickelAmount.getText().toString());
        mCoinCounter.setCountOfDimes(dimeAmount.getText().toString());
        mCoinCounter.setCountOfQuarters(quarterAmount.getText().toString());
    }


    private void displayCalculation() {
        String result = "Calculation Results: " + mCoinCounter.getCentsValueTotal();
        statusBar.setText(result);
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
            pennyAmount.getText().clear();
            nickelAmount.getText().clear();
            dimeAmount.getText().clear();
            quarterAmount.getText().clear();
            statusBar.setText(R.string.name);

            mCoinCounter.setCountOfPennies(0);
            mCoinCounter.setCountOfNickels(0);
            mCoinCounter.setCountOfDimes(0);
            mCoinCounter.setCountOfQuarters(0);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}