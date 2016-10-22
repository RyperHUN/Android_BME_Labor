package hu.bme.aut.a03_weatherinfo.UI.Main;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoProgress;
import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoViewHolder extends AbstractDraggableItemViewHolder {
    int position;
    TextView nameTextView;
    TextView dateTextView;
    Button completeButton;
    Button removeButton;

    OnItemChangedNotifier myNotifier;

    public TodoViewHolder(View itemView, OnItemChangedNotifier notifier) {
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
            }
        });
    }
}