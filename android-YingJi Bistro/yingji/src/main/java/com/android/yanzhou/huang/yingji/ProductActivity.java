package com.android.yanzhou.huang.yingji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;
import com.android.yanzhou.huang.yingji.constant.Constant;
import com.android.yanzhou.huang.yingji.model.Product;
import com.android.yanzhou.huang.model.Cart;
import com.android.yanzhou.huang.util.CartHelper;

public class ProductActivity extends AppCompatActivity {


    TextView ProductName;
    TextView ProductDesc;
    ImageView ProductImage;
    Spinner spQuantity;
    Button bOrder;
    Product product;
    Button product_cart;
    Button product_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product);

        Bundle data = getIntent().getExtras();
        product = (Product) data.getSerializable("product");



        //Set Shopping Cart link
        setShoppingCartLink();

        //Retrieve views
        retrieveViews();

        //Set product properties
        setProductProperties();

        //Initialize quantity
        initializeQuantity();

        //On ordering of product
        onOrderProduct();
    }

    private void setShoppingCartLink() {
        TextView ViewShoppingCart = (TextView)findViewById(R.id.View);

    }

    private void retrieveViews() {
        ProductName = (TextView) findViewById(R.id.ProductName);
        ProductDesc = (TextView) findViewById(R.id.ProductDesc);
        ProductImage = (ImageView) findViewById(R.id.ProductImage);
        spQuantity = (Spinner) findViewById(R.id.spQuantity);
        bOrder = (Button) findViewById(R.id.bOrder);
        product_cart = (Button) findViewById(R.id.product_cart);
        product_menu = (Button) findViewById(R.id.product_menu);
        product_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        product_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
//set the picture function
    private void setProductProperties() {
        ProductName.setText(product.getName());
        ProductDesc.setText(product.getDescription());
        ProductImage.setImageResource(this.getResources().getIdentifier(product.getImageName(), "drawable", this.getPackageName()));
    }

    private void initializeQuantity() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Constant.QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);
    }
//ener ther qty to attay . enter the menu to array
    private void onOrderProduct() {
        bOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = CartHelper.getCart();

                cart.add(product, Integer.valueOf(spQuantity.getSelectedItem().toString()));
                Intent intent = new Intent(ProductActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

}
