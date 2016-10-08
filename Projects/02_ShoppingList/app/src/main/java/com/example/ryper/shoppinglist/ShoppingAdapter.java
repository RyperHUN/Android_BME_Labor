package com.example.ryper.shoppinglist;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryper on 2016. 10. 08..
 */

//Viewholdereken keresztül érjük el a listaElemekhez tartozo view-kat.
public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder> {
    private final List<ShoppingItem> items;

    public ShoppingAdapter() {
        items = new ArrayList<>();
    }

    //Adott sort megjelenito view letrehozasa
    @Override
    public ShoppingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        ShoppingViewHolder viewHolder = new ShoppingViewHolder(itemView);
        return viewHolder;
    }

    //Modellhez a nezet hozzakotese
    @Override
    public void onBindViewHolder(final ShoppingViewHolder holder, int position) {
        final ShoppingViewHolder holderr = holder;
        ShoppingItem item = items.get(position);
        holderr.nameTextView.setText(item.name);
        holderr.descriptionTextView.setText(item.description);
        holderr.categoryTextView.setText(item.category.name());
        holderr.priceTextView.setText(item.estimatedPrice + " Ft");
        holderr.iconImageView.setImageResource(getImageResource(item.category));
        holderr.isBoughtCheckBox.setChecked(item.isBought);

        holder.isBoughtCheckBox.setOnClickListener(new View.OnClickListener()
            {
                public static final String TAG = "TAGG DEUG??";

                @Override
                public void onClick(View view)
                {
                    boolean checked = holderr.isBoughtCheckBox.isChecked();
                    Log.d(TAG, "onClick: checked = " + checked + " in position " + holderr.getAdapterPosition());
                    ShoppingItem item = items.get(holderr.getAdapterPosition());
                    item.isBought = checked;
                    item.save();
                }
            }
        );
    }

    private @DrawableRes int getImageResource(ShoppingItem.Category category) {
        @DrawableRes int ret;
        switch (category) {
            case BOOK:
                ret = R.drawable.open_book;
                break;
            case ELECTRONIC:
                ret = R.drawable.lightning;
                break;
            case FOOD:
                ret = R.drawable.groceries;
                break;
            default:
                ret = 0;
        }
        return ret;
    }

    public void addItem(ShoppingItem item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void update(List<ShoppingItem> shoppingItems) {
        items.clear();
        items.addAll(shoppingItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView nameTextView;
        TextView descriptionTextView;
        TextView categoryTextView;
        TextView priceTextView;
        CheckBox isBoughtCheckBox;
        ImageButton removeButton;

        public ShoppingViewHolder(View itemView) {
            super(itemView);
            iconImageView = (ImageView) itemView.findViewById(R.id.ShoppingItemIconImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.ShoppingItemNameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.ShoppingItemDescriptionTextView);
            categoryTextView = (TextView) itemView.findViewById(R.id.ShoppingItemCategoryTextView);
            priceTextView = (TextView) itemView.findViewById(R.id.ShoppingItemPriceTextView);
            isBoughtCheckBox = (CheckBox) itemView.findViewById(R.id.ShoppingItemIsBoughtCheckBox);
            removeButton = (ImageButton) itemView.findViewById(R.id.ShoppingItemRemoveButton);
        }
    }
}

