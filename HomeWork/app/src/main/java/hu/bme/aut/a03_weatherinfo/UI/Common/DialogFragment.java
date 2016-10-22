package hu.bme.aut.a03_weatherinfo.UI.Common;

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
import hu.bme.aut.a03_weatherinfo.UI.Main.AddTodoDialogListener;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class DialogFragment extends AppCompatDialogFragment {
    public static final String TAG = "DialogFragmentOkCancel";
    public boolean isOkClicked = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
        b.setTitle("Please enter a password");
        b.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                isOkClicked = true;
            }
        });
        b.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isOkClicked = false;
            }
        });
        return b.create();
    }
}
