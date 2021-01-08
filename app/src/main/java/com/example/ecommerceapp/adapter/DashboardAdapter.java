package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.LstProduct;
import com.example.ecommerceapp.models.TopBar;
import com.example.ecommerceapp.onclick.DashboardOnClick;

import java.util.ArrayList;


public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;

    private static final int TYPE_HEADER_SLIDER = 0;
    private static final int TYPE_MENU = 1;
    private static final int TYPE_VEHICLE = 2;
    private static final int TYPE_OTHER_CATEGORY = 3;
    private static final int TYPE_PRODUCT_LIST_TITLE = 4;
    private static final int TYPE_PRODUCT_LIST = 5;

    private ArrayList<LstProduct> lstProducts;
    private ArrayList<TopBar> topBar;


    private final DashboardOnClick onClick;


    public DashboardAdapter(ArrayList<LstProduct> lstProducts,
                            ArrayList<TopBar> topBar,
                            DashboardOnClick onClick) {
        this.lstProducts = lstProducts;
        this.topBar = topBar;
        this.onClick = onClick;
    }

    public void UpdateDashboardAdapter(ArrayList<LstProduct> lstProducts
    ) {
        this.lstProducts = lstProducts;

        this.notifyDataSetChanged();
        if (sliderAdapter != null) {
            sliderAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER_SLIDER;
        } else if (position == 1) {
            return TYPE_MENU;
        } else if (position == 2) {
            return TYPE_VEHICLE;
        } else if (position == 3) {
            return TYPE_OTHER_CATEGORY;
        } else if (position == 4) {
            return TYPE_PRODUCT_LIST_TITLE;
        } else {
            return TYPE_PRODUCT_LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (inflater == null)
            inflater = (LayoutInflater) viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (i == TYPE_HEADER_SLIDER) {
            View v = inflater.inflate(R.layout.item_header_slider, viewGroup, false);
            return new HeaderSliderViewHolder(v);
        } else if (i == TYPE_MENU) {
            View v = inflater.inflate(R.layout.item_menu_, viewGroup, false);
            return new MenuViewHolder(v);
        } else if (i == TYPE_VEHICLE) {
            View v = inflater.inflate(R.layout.item_vehicle_, viewGroup, false);
            return new VehicleViewHolder(v);
        } else if (i == TYPE_OTHER_CATEGORY) {
            View v = inflater.inflate(R.layout.item_other_vehicle_, viewGroup, false);
            return new OtherCategoryViewHolder(v);
        } else {
            View v = inflater.inflate(R.layout.item_product_list_vehicle_, viewGroup, false);
            return new ProductListViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ProductListViewHolder) {
            ProductListViewHolder viewHolder0 = (ProductListViewHolder) viewHolder;
            LstProduct data = lstProducts.get(i);
            viewHolder0.mrp.setText("₹" + data.getMrp());
            viewHolder0.selling_price.setText("₹" + data.getSellingPrice());
            //viewHolder0.ndp.setText("NDP - Rs." + String.valueOf(data.getNdp()));
            viewHolder0.product_name.setText(String.valueOf(data.getProductName()) + " (" + data.getProductCode() + ")");


            Glide.with(viewHolder.itemView).load(data.getImageLink())
                    .fitCenter()
                    .error(R.drawable.no_image).into(viewHolder0.product_image);

            viewHolder0.parent.setOnClickListener(view -> {
                onClick.clickProduct(data);
            });


            if ((data.getMrp() - data.getSellingPrice()) == 0.0) {
                viewHolder0.parent_price.setVisibility(View.GONE);
            } else {
                viewHolder0.parent_price.setVisibility(View.VISIBLE);
            }


        } else if (viewHolder instanceof HeaderSliderViewHolder) {

            HeaderSliderViewHolder headerSliderViewHolder = (HeaderSliderViewHolder) viewHolder;

        }
    }

    @Override
    public int getItemCount() {

        return lstProducts.size();
    }

    private SliderAdapter sliderAdapter;

    private class HeaderSliderViewHolder extends RecyclerView.ViewHolder {

        private final LoopingViewPager viewpager;


        private HeaderSliderViewHolder(View v) {
            super(v);

            viewpager = itemView.findViewById(R.id.viewpager);
            init();

        }

        private void init() {
            sliderAdapter = new SliderAdapter(itemView.getContext(), topBar, new SliderAdapter.ClickTopSlider() {
                @Override
                public void onClick(int position, int catID) {

                }
            });
            viewpager.setAdapter(sliderAdapter);
        }

    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {

        final ImageView category;
        final ImageView vehicle;
        final ImageView top_selling;
        final ImageView history;
        final ImageView retrofit;

        final RelativeLayout category_;
        final RelativeLayout vehicle_;
        final RelativeLayout top_selling_;
        final RelativeLayout history_;
        final RelativeLayout retrofit_;


        private MenuViewHolder(View v) {
            super(v);

            category = v.findViewById(R.id.category);
            vehicle = v.findViewById(R.id.vehicle);
            top_selling = v.findViewById(R.id.top_selling);
            history = v.findViewById(R.id.history);
            retrofit = v.findViewById(R.id.retrofit);

            category_ = v.findViewById(R.id.category_);
            vehicle_ = v.findViewById(R.id.vehicle_);
            top_selling_ = v.findViewById(R.id.top_selling_);
            history_ = v.findViewById(R.id.history_);
            retrofit_ = v.findViewById(R.id.retrofit_);

            Glide.with(v).load(R.drawable.ic_list)
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(category);
            Glide.with(v).load(R.drawable.ic_vehicle)
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(vehicle);
            Glide.with(v).load(R.drawable.ic_top_selling)
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(top_selling);
            Glide.with(v).load(R.drawable.ic_menu)
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(history);
            Glide.with(v).load(R.drawable.ic_retrofit)
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(retrofit);


        }
    }

    private class VehicleViewHolder extends RecyclerView.ViewHolder {

        final ImageView scooter_image;
        final ImageView motor_image;
        final ImageView moped_image;

        private VehicleViewHolder(View v) {
            super(v);

            scooter_image = v.findViewById(R.id.image_1);
            motor_image = v.findViewById(R.id.image_2);
            moped_image = v.findViewById(R.id.image_3);

            Glide.with(v).load("https://www.advantagetvs.com/Pgm_Prod_Img/Zest.png")
                    .transition(DrawableTransitionOptions.withCrossFade(2000))
                    .into(scooter_image);
            Glide.with(v).load("https://www.advantagetvs.com/Pgm_Prod_Img/Apache.png")
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(motor_image);
            Glide.with(v).load("https://www.advantagetvs.com/Pgm_Prod_Img/top3.png")
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(moped_image);

        }
    }

    private class OtherCategoryViewHolder extends RecyclerView.ViewHolder {

        final ImageView image_1;
        final ImageView image_2;
        final ImageView image_3;

        final TextView text1;
        final TextView text2;
        final TextView text3;

        private OtherCategoryViewHolder(View v) {
            super(v);


            image_1 = v.findViewById(R.id.image_1);
            image_2 = v.findViewById(R.id.image_2);
            image_3 = v.findViewById(R.id.image_3);

            text1 = v.findViewById(R.id.text1);
            text2 = v.findViewById(R.id.text2);
            text3 = v.findViewById(R.id.text3);


            Glide.with(v).load("https://www.advantagetvs.com/Pgm_Prod_Img/RetroFitment.png")
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(image_1);
            Glide.with(v).load("https://www.advantagetvs.com/Pgm_Prod_Img/RidingWear.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(image_2);
            Glide.with(v).load("https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png")
                    .transition(DrawableTransitionOptions.withCrossFade(2000)).into(image_3);


        }
    }

    private class ProductTitleViewHolder extends RecyclerView.ViewHolder {

        private ProductTitleViewHolder(View v) {
            super(v);


        }
    }

    private class ProductListViewHolder extends RecyclerView.ViewHolder {

        final TextView product_name;
        final TextView mrp;
        final TextView selling_price;
        final TextView ndp;
        final ImageView product_image;
        final LinearLayout parent_price;

        final CardView parent;

        private ProductListViewHolder(View v) {
            super(v);
            product_name = itemView.findViewById(R.id.product_name);
            mrp = itemView.findViewById(R.id.mrp);
            selling_price = itemView.findViewById(R.id.selling_price);
            ndp = itemView.findViewById(R.id.ndp);
            product_image = itemView.findViewById(R.id.image_1);

            parent_price = itemView.findViewById(R.id.parent_price);

            parent = itemView.findViewById(R.id.parent);
        }
    }


}
