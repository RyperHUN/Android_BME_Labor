package ryper.homeworkimprovement.Main.Pager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ryper.homeworkimprovement.DB.Categories;
import ryper.homeworkimprovement.DB.TodoProgress;
import ryper.homeworkimprovement.Dialog.AddTodoDialogFragment;
import ryper.homeworkimprovement.Dialog.AddTodoDialogListener;
import ryper.homeworkimprovement.History.HistoryActivity;
import ryper.homeworkimprovement.R;

public class PagerMainActivity extends AppCompatActivity implements AddTodoDialogListener {
    CategoryPagerAdapter categoryPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Categories.InitCategories(this);

        DBFillwithTestData();
        setContentView(R.layout.activity_pager);
        ViewPager mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        categoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(), this);
        mainViewPager.setAdapter(categoryPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setFloatingButton ();

    }

    private void setFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddTodoDialogFragment().show( getSupportFragmentManager(), AddTodoDialogFragment.TAG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity (intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DBFillwithTestData() {
        try {
            TodoProgress.deleteAll(TodoProgress.class);

            InitProgressDatabase ();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void InitProgressDatabase() throws Exception {
        TodoProgress todoProgress = new TodoProgress("Elmenni tejert", Categories.AKey);
        todoProgress.save();
        TodoProgress todoProgress2 = new TodoProgress("Elmenni Virsliert", Categories.BKey);
        todoProgress2.save ();
        TodoProgress todoProgress3 = new TodoProgress("Zuckerberg Ebed", Categories.CKey);
        todoProgress3.save();
        TodoProgress todoProgress4 = new TodoProgress("Pizzazas", Categories.DKey);
        todoProgress4.save();
    }

    @Override
    public void onTodoAdded(TodoProgress todoProg) {
        todoProg.save();
        categoryPagerAdapter.refresh();
    }

}
