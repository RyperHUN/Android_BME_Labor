package ryper.homeworkimprovement.History;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ryper.homeworkimprovement.DB.TodoHistory;
import ryper.homeworkimprovement.R;


/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoHistoryAdapter extends RecyclerView.Adapter<TodoHistoryViewHolder> {
    List<TodoHistory> histories;

    public TodoHistoryAdapter ()
    {
        histories = TodoHistory.listAll (TodoHistory.class);
        Collections.sort(histories, new TodoHistory.EndDateComparator());
    }

    @Override
    public TodoHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main_todo, parent, false);
        TodoHistoryViewHolder viewHolder = new TodoHistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TodoHistoryViewHolder holder, int position) {
        holder.position = position;
        holder.dateEndTextView.setText (histories.get(position).getEndDate());
        holder.dateStartTextView.setText(histories.get(position).getStartDate());
        holder.nameTextView.setText(histories.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return histories.size ();
    }
}
