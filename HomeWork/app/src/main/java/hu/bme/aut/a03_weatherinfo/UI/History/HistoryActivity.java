package hu.bme.aut.a03_weatherinfo.UI.History;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoHistory;
import hu.bme.aut.a03_weatherinfo.R;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initFloatingButton ();
        initRecyclerView ();
    }

    private void initFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TodoHistory> items = TodoHistory.listAll(TodoHistory.class);
                for (TodoHistory item : items) {
                    item.delete();
                }
                adapter.histories.clear ();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.HistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoHistoryAdapter();
        recyclerView.setAdapter(adapter);
    }

}
