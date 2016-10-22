package hu.bme.aut.a03_weatherinfo.UI.Main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoProgress;
import hu.bme.aut.a03_weatherinfo.Model.Categories;
import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class AddTodoDialogFragment extends AppCompatDialogFragment{
    public static final String TAG = "AddTodoDialogFragment";
    private AddTodoDialogListener listener;
    private EditText descText;
    private Spinner categorySpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof AddTodoDialogListener) {
            listener = (AddTodoDialogListener) getActivity();
        } else {
            throw new RuntimeException("Activity must implement AddTodoDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext()).setTitle(R.string.new_todo).
                setView(getContentView()).setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isValidInput ())
                    listener.onTodoAdded(getParsedInputTODO ());
                else
                    ;//TODO Snackbar message if empty -> Does not work
            }
        }).setNegativeButton(R.string.cancel, null).create();
    }

    private TodoProgress getParsedInputTODO() {
        String desc = descText.getText().toString();
        int pos = categorySpinner.getSelectedItemPosition();
        String category = Categories.getKey(Categories.getCategoriesStringArray()[pos]);

        TodoProgress todoProgress = null;
        try {
            todoProgress = new TodoProgress (desc, category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoProgress;
    }

    private boolean isValidInput() {
        return descText.length() > 0;
    }

    private View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_todo, null);
        descText = (EditText) view.findViewById(R.id.NewTodoDescText);
        categorySpinner = (Spinner) view.findViewById(R.id.NewTodoSpinner);
        categorySpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Categories.getCategoriesStringArray()));
        return view;
    }
}

