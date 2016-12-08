package ryper.homeworkimprovement.History;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ryper.homeworkimprovement.DB.TodoHistory;
import ryper.homeworkimprovement.R;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_fast_rewind_black_24dp);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_go_back) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
