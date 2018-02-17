package com.example.dc_raz.foodpeon_again.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dc_raz.foodpeon_again.API.ItemClickListener;
import com.example.dc_raz.foodpeon_again.R;

/**
 * Created by dcastalia on 2/14/18.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.textViewmenue);
        imageView=itemView.findViewById(R.id.Imagemenu);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
