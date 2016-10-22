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

import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoProgress;
import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 20..
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.TodoViewHolder>
implements DraggableItemAdapter<CityAdapter.TodoViewHolder> {

    private final List<ExtendedProgress> todos;
//    private OnCitySelectedListener listener;
//public CityAdapter(OnCitySelectedListener) {

    public CityAdapter() {
        //this.listener = listener;
        todos = new ArrayList<>();
        setHasStableIds(true); // this is required for D&D feature.
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).id; // need to return stable (= not change even after reordered) value
    }

    //Ez fog letrehozni egy sort,
    //Fog egy sort es berakja a viewHolderbe es ezt adja vissza
    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);
        return viewHolder;
    }

    // Parameterkent megkapja a viewHoldert, es mi megadjuk neki a poziciot amivel beallutja??
    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.position = position;
        holder.nameTextView.setText(todos.get(position).progress.getDesc());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static long id = 0;
    public void addListElement(TodoProgress progress) {
        ExtendedProgress newItem = new ExtendedProgress (id++, progress);
        todos.add(newItem);
        notifyItemInserted(todos.size() - 1); //Ertesitjuk az adaptert a cityrol
    }

    public void removeCity(int position) {
        todos.remove(position);
        notifyItemRemoved(position);
        if (position < todos.size()) {
            notifyItemRangeChanged(position, todos.size() - position);
        }
    }

    @Override
    public boolean onCheckCanStartDrag(TodoViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(TodoViewHolder holder, int position) {
        return null;
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        ExtendedProgress movedItem = todos.remove(fromPosition);
        todos.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    public void fillFromDb() {
        List<TodoProgress> loaded = TodoProgress.listAll(TodoProgress.class);
        for (TodoProgress todoProg : loaded)
            addListElement(todoProg);
    }

    public class TodoViewHolder extends AbstractDraggableItemViewHolder {
        int position;
        TextView nameTextView;
        Button completeButton;

        public TodoViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.TodoListItemDesc);
            completeButton = (Button) itemView.findViewById(R.id.TodoListItemCompleteBtn);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        listener.onCitySelected(todos.get(position));
//                    }
//                }
//            });
        }
    }

    static class ExtendedProgress {
        public final long id;
        public final TodoProgress progress;

        public ExtendedProgress(long id, TodoProgress progress) {
            this.id = id;
            this.progress = progress;
        }
    }

}