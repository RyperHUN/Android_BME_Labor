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
import android.widget.EditText;

import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class AddTodoDialogFragment extends AppCompatDialogFragment{
    public static final String TAG = "AddCityDialogFragment";
    private AddTodoDialogListener listener;
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof AddTodoDialogListener) {
            listener = (AddTodoDialogListener) getActivity();
        } else {
            throw new RuntimeException("Activity must implement AddCityDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext()).setTitle(R.string.new_city).setView(getContentView()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                listener.onTodoAdded(editText.getText().toString());
                //TODO
            }
        }).setNegativeButton(R.string.cancel, null).create();
    }

    private View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_todo, null);
        editText = (EditText) view.findViewById(R.id.NewCityDialogEditText);
        return view;
    }
}

