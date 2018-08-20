package com.android.yanzhou.huang.yingji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.NumberPicker;

import com.android.yanzhou.huang.model.Cart;
import com.android.yanzhou.huang.model.Saleable;
import com.android.yanzhou.huang.util.CartHelper;
import com.android.yanzhou.huang.yingji.adapter.CartItemAdapter;
import com.android.yanzhou.huang.yingji.constant.Constant;
import com.android.yanzhou.huang.yingji.model.CartItem;
import com.android.yanzhou.huang.yingji.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.*;
/*
* Calculate total bill amount due.
 Set a tip percent by typing tip percent or dragging the thumb on the seek bar to the right
or left. Calculate and display the tip.
Be able to enter number of guests.
Split the final bill amount (including tips) among the number of guests and display
* */
public class CheckActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button pay;
    Button backcart;
    private double tipPercentValue;
    private double peoplenumber;
    private EditText precenttip;
    private EditText people;
    private SeekBar peoplebar;
private SeekBar tipbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        pay = (Button) findViewById(R.id.pay);
        backcart = (Button) findViewById(R.id.backcart);
        people = (EditText) findViewById(R.id.people);
        precenttip = (EditText) findViewById(R.id.precenttip);
        tipbar = (SeekBar) findViewById(R.id.tipbar);
        tipbar.setOnSeekBarChangeListener(this);
        peoplebar = (SeekBar) findViewById(R.id.peoplebar);
        peoplebar.setOnSeekBarChangeListener(this);
        final Cart cart = CartHelper.getCart();
        tipPercentValue = Double.parseDouble(precenttip.getText().toString());
        peoplenumber = Double.parseDouble(people.getText().toString());
        backcart.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(CheckActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView eachtotal = (TextView) findViewById(R.id.eachtotal);// calculate each bill
                eachtotal.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().multiply(BigDecimal.valueOf(1.06 + tipPercentValue)).divide(BigDecimal.valueOf(peoplenumber)).setScale(2, ROUND_HALF_DOWN)));
                final TextView tip = (TextView) findViewById(R.id.tip);// calculate the tips
                tip.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().multiply(BigDecimal.valueOf(tipPercentValue)).setScale(2, ROUND_HALF_UP)));
                final TextView Estimated_Total = (TextView) findViewById(R.id.Estimated_Total);// total include tip and tax
                Estimated_Total.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().multiply(BigDecimal.valueOf(1.06 + tipPercentValue)).setScale(2, ROUND_HALF_UP)));
            }
        });

        final TextView TotalPrice = (TextView) findViewById(R.id.Subtotal);// menu cost
        TotalPrice.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().setScale(2, ROUND_HALF_UP)));
        final TextView tax = (TextView) findViewById(R.id.tax);//tax
        tax.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().multiply(BigDecimal.valueOf(0.06)).setScale(2, ROUND_HALF_UP)));
        final TextView taxtotal = (TextView) findViewById(R.id.taxtotal);//tax plus menu
        taxtotal.setText(Constant.CURRENCY + String.valueOf(cart.getTotalPrice().multiply(BigDecimal.valueOf(1.06)).setScale(2, ROUND_HALF_UP)));
    }

    @Override
    //seekbar for tip and number people
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar == tipbar && b == true) {
            tipPercentValue = seekBar.getProgress();
            precenttip.setText(String.format("%.0f", tipPercentValue) + "%");
            tipPercentValue = tipPercentValue / 100;
        }
        if (seekBar == peoplebar && b == true) {
            peoplenumber = seekBar.getProgress();
            people.setText(String.format("%.0f", peoplenumber));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}






