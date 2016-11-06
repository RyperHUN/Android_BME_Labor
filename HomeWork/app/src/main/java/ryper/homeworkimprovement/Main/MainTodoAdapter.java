package ryper.homeworkimprovement.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ryper.homeworkimprovement.DB.TodoHistory;
import ryper.homeworkimprovement.DB.TodoProgress;
import ryper.homeworkimprovement.R;

/**
 * Created by Ryper on 2016. 11. 04..
 */
public class MainTodoAdapter extends RecyclerView.Adapter<MainTodoAdapter.ViewHolder>
implements OnListItemChangedNotifier {


    ArrayList<TodoProgress> items;

    public MainTodoAdapter ()
    {
        items = new ArrayList<TodoProgress> ();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate (R.layout.list_item_main_todo, parent, false);
        return new ViewHolder (view, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoProgress aktItem = items.get(position);
        holder.nameTextView.setText(aktItem.getDesc());
        holder.dateTextView.setText(aktItem.getStartDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void ItemCompleted(int position) {
        TodoProgress soonRemove  = items.get(position);
        TodoHistory todoHistory = new TodoHistory (soonRemove);
        todoHistory.save (); // Write to DB

        ItemRemoved (position);
    }

    @Override
    public void ItemRemoved(int position) {
        items.remove(position);
        this.notifyDataSetChanged();
    }

    public void add(TodoProgress item) {
        items.add(item);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int position;
        TextView nameTextView;
        TextView dateTextView;
        Button completeButton;
        Button removeButton;

        OnListItemChangedNotifier myNotifier;

        public ViewHolder (View itemView, OnListItemChangedNotifier notifier) {
            super(itemView);
            myNotifier     = notifier;
            nameTextView   = (TextView) itemView.findViewById(R.id.TodoListItemDesc);
            completeButton = (Button) itemView.findViewById(R.id.TodoListItemCompleteBtn);
            removeButton   = (Button) itemView.findViewById(R.id.TodoListItemRemovBtn);
            dateTextView   = (TextView) itemView.findViewById(R.id.TodoListItemStartDate);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myNotifier.ItemRemoved (position);
                    //TODO Popup dialog biztos le akarja-e torolni
                }
            });

            completeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myNotifier.ItemCompleted(position);
                    //TODO Popup dialog biztos le akarja-e torolni
                }
            });
        }
    }
}
