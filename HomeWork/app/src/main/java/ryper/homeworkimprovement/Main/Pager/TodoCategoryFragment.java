package ryper.homeworkimprovement.Main.Pager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ryper.homeworkimprovement.DB.TodoProgress;
import ryper.homeworkimprovement.Main.RecyclerViewHelper.MainTodoAdapter;
import ryper.homeworkimprovement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoCategoryFragment extends Fragment {
    MainTodoAdapter adapter;
    Context context;
    String categoryKey;

    public TodoCategoryFragment() {
        // Required empty public constructor
    }
//
//    public TodoCategoryFragment (Context context, String categoryKey) {
//
//    }
    ///TODO CALL THIS!!
    public TodoCategoryFragment Init (Context context, String categoryKey) {
        this.context = context;
        this.categoryKey = categoryKey;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_category, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        InitRecyclerView();
    }

    private void InitRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MainTodoAdapter();

        recyclerView.setAdapter(adapter);

        refreshAdapterDataFromDB();
    }

    public void refreshAdapterDataFromDB() {
        adapter.clear();
        List<TodoProgress> loaded = TodoProgress.find(TodoProgress.class, "category = ?", categoryKey);
        for (TodoProgress todo : loaded)
            adapter.add(todo);
    }
}
