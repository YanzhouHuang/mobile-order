package com.android.yanzhou.huang.yingji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.yanzhou.huang.yingji.adapter.CartItemAdapter;
import com.android.yanzhou.huang.yingji.constant.Constant;
import com.android.yanzhou.huang.yingji.model.CartItem;
import com.android.yanzhou.huang.yingji.model.Product;
import com.android.yanzhou.huang.model.Cart;
import com.android.yanzhou.huang.model.Saleable;
import com.android.yanzhou.huang.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ShoppingCartActivity extends AppCompatActivity {


    ListView CartItems;
    Button cart_clear;
    Button cart_confirm;
    Button cart_home;
    Button cart_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartItems = (ListView) findViewById(R.id.CartItems);
        LayoutInflater layoutInflater = getLayoutInflater();

        final Cart cart = CartHelper.getCart();
        final TextView TotalPrice = (TextView) findViewById(R.id.Subtotalcart);
        TotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
//calculate total prive for menu


       CartItems.addHeaderView(layoutInflater.inflate(R.layout.cart_header, CartItems, false));

        final CartItemAdapter cartItemAdapter = new CartItemAdapter(this);
        cartItemAdapter.updateCartItems(getCartItems(cart));

        CartItems.setAdapter(cartItemAdapter);

        cart_clear = (Button) findViewById(R.id.cart_clear);
        cart_confirm = (Button) findViewById(R.id.cart_confirm);
        cart_home = (Button) findViewById(R.id.cart_home);
        cart_menu = (Button) findViewById(R.id.cart_menu);

        cart_clear.setOnClickListener(new OnClickListener() {
            @Override
            /**
             *Empty the shopping cart
             */

            public void onClick(View v) {;
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();

            }
        });

        cart_confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, CheckActivity.class);
                startActivity(intent);
            }
        });
        cart_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        cart_menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
/*set product and quantity.
* */
    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        return cartItems;
    }
}
