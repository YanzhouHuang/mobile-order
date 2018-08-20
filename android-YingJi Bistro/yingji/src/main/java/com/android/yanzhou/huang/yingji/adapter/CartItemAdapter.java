package com.android.yanzhou.huang.yingji.adapter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.yanzhou.huang.yingji.R;
import com.android.yanzhou.huang.yingji.constant.Constant;
import com.android.yanzhou.huang.yingji.model.CartItem;
import com.android.yanzhou.huang.model.Cart;
import com.android.yanzhou.huang.util.CartHelper;

public class CartItemAdapter extends BaseAdapter {

    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView Name;
        TextView UnitPrice;
        TextView Quantity;
        TextView Price;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_cart_item, parent, false);
            Name = (TextView) convertView.findViewById(R.id.CartItemName);
            UnitPrice = (TextView) convertView.findViewById(R.id.CartItemUnitPrice);
            Quantity = (TextView) convertView.findViewById(R.id.CartItemQuantity);
            Price = (TextView) convertView.findViewById(R.id.CartItemPrice);
            convertView.setTag(new ViewHolder(Name, UnitPrice, Quantity, Price));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            Name = viewHolder.CartItemName;
            UnitPrice = viewHolder.CartItemUnitPrice;
            Quantity = viewHolder.CartItemQuantity;
            Price = viewHolder.CartItemPrice;
        }

        final Cart cart = CartHelper.getCart();
        final CartItem cartItem = getItem(position);
        Name.setText(cartItem.getProduct().getName());
        UnitPrice.setText(Constant.CURRENCY+String.valueOf(cartItem.getProduct().getPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
        Quantity.setText(String.valueOf(cartItem.getQuantity()));
        Price.setText(Constant.CURRENCY+String.valueOf(cart.getCost(cartItem.getProduct()).setScale(2, BigDecimal.ROUND_HALF_UP)));
        return convertView;
    }

    private static class ViewHolder {
        public final TextView CartItemName;
        public final TextView CartItemUnitPrice;
        public final TextView CartItemQuantity;
        public final TextView CartItemPrice;

        public ViewHolder(TextView CartItemName, TextView CartItemUnitPrice, TextView CartItemQuantity, TextView CartItemPrice) {
            this.CartItemName = CartItemName;
            this.CartItemUnitPrice = CartItemUnitPrice;
            this.CartItemQuantity = CartItemQuantity;
            this.CartItemPrice = CartItemPrice;
        }
    }
}
