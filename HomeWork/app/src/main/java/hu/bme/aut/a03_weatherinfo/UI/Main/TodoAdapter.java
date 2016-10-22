package hu.bme.aut.a03_weatherinfo.UI.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoHistory;
import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoProgress;
import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 20..
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder>
implements DraggableItemAdapter<TodoViewHolder>, OnListItemChangedNotifier {

    private final List<ExtendedProgress> todos;

    public TodoAdapter() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_todo, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view, this);
        return viewHolder;
    }

    // Parameterkent megkapja a viewHoldert, es mi megadjuk neki a poziciot amivel beallutja??
    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.position = position;
        holder.nameTextView.setText(todos.get(position).progress.getDesc());
        holder.dateTextView.setText(todos.get(position).progress.getStartDate());
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

    @Override
    public void ItemRemoved(int pos) {
        removeItem (pos);
    }

    private void removeItem(int position) {
        ExtendedProgress removed = todos.remove(position);
        removed.progress.delete (); // Delete from DB
        notifyItemRemoved(position);
        if (position < todos.size()) {
            notifyItemRangeChanged(position, todos.size() - position);
        }
    }

    @Override
    public void ItemCompleted(int pos) {
        completeItem (pos);
    }

    private void completeItem (int position) {
        ExtendedProgress soonRemove  = todos.get(position);
        TodoHistory todoHistory = new TodoHistory (soonRemove.progress);
        todoHistory.save (); // Write to DB

        removeItem (position);
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