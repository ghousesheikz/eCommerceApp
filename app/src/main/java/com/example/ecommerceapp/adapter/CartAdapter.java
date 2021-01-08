package com.example.ecommerceapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.custom_classes.ElegantNumberButton;
import com.example.ecommerceapp.models.CartPojo;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;

    private final ArrayList<CartPojo> prdtArrayList;

    private final changeListener changeListener;

    private final Activity activity;

    public CartAdapter(ArrayList<CartPojo> prdtArrayList, changeListener changeListener, Activity activity) {
        this.prdtArrayList = prdtArrayList;
        this.changeListener = changeListener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null)
            inflater = (LayoutInflater) viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_cart_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        if (viewHolder instanceof ViewHolder) {
            ViewHolder viewHolder0 = (ViewHolder) viewHolder;
            CartPojo lstPrdt = prdtArrayList.get(viewHolder.getAdapterPosition());
            viewHolder0.selected.setOnCheckedChangeListener(null);


            viewHolder0.product_name.setText(lstPrdt.getProductName());
            viewHolder0.mrp.setText(String.format("₹%s", lstPrdt.getMrp()));
            viewHolder0.selling_price.setText(String.format("₹%s", lstPrdt.getSellingPrice()));
            viewHolder0.product_code.setText(lstPrdt.getProductCode());

            Glide.with(viewHolder0.itemView).load(lstPrdt.getImageLink())

                    .error(R.drawable.no_image)
                    .fitCenter().into(viewHolder0.product_image);


            viewHolder0.discount_percentage.setText(String.format("%s%%", "10"));

            viewHolder0.quantity.setNumber(String.valueOf(lstPrdt.getNoOfItem()));

            viewHolder0.quantity.setOnValueChangeListener(null);
            viewHolder0.delete.setOnClickListener(null);

            viewHolder0.quantity.setOnValueChangeListener((view, oldValue, newValue) -> {
                if (viewHolder0.getAdapterPosition() >= 0 && viewHolder0.getAdapterPosition() < prdtArrayList.size())
                    changeListener.quantityChange(viewHolder0.getAdapterPosition(), String.valueOf(newValue));
            });

            viewHolder0.delete.setOnClickListener(v -> {
                if (viewHolder0.getAdapterPosition() >= 0 && viewHolder0.getAdapterPosition() < prdtArrayList.size())
                    changeListener.delete(viewHolder0.getAdapterPosition());
            });



        }
    }


    @Override
    public int getItemCount() {
        return prdtArrayList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final CheckBox selected;
        final ImageView product_image;
        final TextView product_name;
        final TextView mrp;
        final TextView selling_price;
        final ElegantNumberButton quantity;
        final TextView discount_percentage;
        final TextView product_code;
        final ImageView delete;
        final RelativeLayout product_parent;
        final LinearLayout parent_price;

        private ViewHolder(View v) {
            super(v);

            selected = itemView.findViewById(R.id.selected);
            product_image = itemView.findViewById(R.id.image_1);
            product_name = itemView.findViewById(R.id.product_name);
            mrp = itemView.findViewById(R.id.mrp);
            selling_price = itemView.findViewById(R.id.selling_price);
            product_code = itemView.findViewById(R.id.product_code);
            quantity = itemView.findViewById(R.id.quantity);
            delete = itemView.findViewById(R.id.delete);
            product_parent = itemView.findViewById(R.id.product_parent);
            parent_price = itemView.findViewById(R.id.parent_price);
            discount_percentage = itemView.findViewById(R.id.discount_percentage);
        }
    }

    public interface changeListener {
        void quantityChange(int position, String quantity);
        void delete(int position);
    }
}
