package com.android.yanzhou.huang.yingji.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.yanzhou.huang.yingji.R;
import com.android.yanzhou.huang.yingji.constant.Constant;
import com.android.yanzhou.huang.yingji.model.Product;

public class ProductAdapter extends BaseAdapter {
    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView Name;
        TextView Price;
        ImageView Image;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_product, parent, false);
            Name = (TextView) convertView.findViewById(R.id.ProductName);
            Price = (TextView) convertView.findViewById(R.id.ProductPrice);
            Image = (ImageView) convertView.findViewById(R.id.ProductImage);
            convertView.setTag(new ViewHolder(Name, Price, Image));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            Name = viewHolder.ProductName;
            Price = viewHolder.ProductPrice;
            Image = viewHolder.ProductImage;
        }

        final Product product = getItem(position);
        Name.setText(product.getName());
        Price.setText(Constant.CURRENCY+String.valueOf(product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

        Image.setImageResource(context.getResources().getIdentifier(
                product.getImageName(), "drawable", context.getPackageName()));


        return convertView;
    }

    private static class ViewHolder {
        public final TextView ProductName;
        public final TextView ProductPrice;
        public final ImageView ProductImage;

        public ViewHolder(TextView ProductName, TextView ProductPrice, ImageView ProductImage) {
            this.ProductName = ProductName;
            this.ProductPrice = ProductPrice;
            this.ProductImage = ProductImage;
        }
    }
}
