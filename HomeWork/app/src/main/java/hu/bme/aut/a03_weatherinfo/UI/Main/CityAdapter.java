package hu.bme.aut.a03_weatherinfo.UI.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 20..
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>
implements DraggableItemAdapter<CityAdapter.CityViewHolder>{

    private final List<MyItem> cities;
//    private OnCitySelectedListener listener;
//public CityAdapter(OnCitySelectedListener) {

    public CityAdapter() {
        //this.listener = listener;
        cities = new ArrayList<>();
        setHasStableIds(true); // this is required for D&D feature.
    }

    @Override
    public long getItemId(int position) {
        return cities.get(position).id; // need to return stable (= not change even after reordered) value
    }

    //Ez fog letrehozni egy sort,
    //Fog egy sort es berakja a viewHolderbe es ezt adja vissza
    //
    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        CityViewHolder viewHolder = new CityViewHolder(view);
        return viewHolder;
    }

    // Parameterkent megkapja a viewHoldert, es mi megadjuk neki a poziciot amivel beallutja??
    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.position = position;
        holder.nameTextView.setText(cities.get(position).text);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static long id = 0;
    public void addCity(String newCity) {
        MyItem newItem = new MyItem (id++, newCity);
        cities.add(newItem);
        notifyItemInserted(cities.size() - 1); //Ertesitjuk az adaptert a cityrol
    }

    public void removeCity(int position) {
        cities.remove(position);
        notifyItemRemoved(position);
        if (position < cities.size()) {
            notifyItemRangeChanged(position, cities.size() - position);
        }
    }

    @Override
    public boolean onCheckCanStartDrag(CityViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(CityViewHolder holder, int position) {
        return null;
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        MyItem movedItem = cities.remove(fromPosition);
        cities.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    public class CityViewHolder extends AbstractDraggableItemViewHolder {
        int position;
        TextView nameTextView;
        Button removeButton;

        public CityViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.CityItemNameTextView);
            removeButton = (Button) itemView.findViewById(R.id.CityItemRemoveButton);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        listener.onCitySelected(cities.get(position));
//                    }
//                }
//            });
        }
    }

    static class MyItem {
        public final long id;
        public final String text;

        public MyItem(long id, String text) {
            this.id = id;
            this.text = text;
        }
    }

}