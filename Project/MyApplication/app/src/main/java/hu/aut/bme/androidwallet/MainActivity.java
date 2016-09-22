package hu.aut.bme.androidwallet;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText amountEditText;
    ToggleButton typeChooserButton;
    Button saveButton;
    LinearLayout listOfRows;
    LayoutInflater inflater;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Butterknife bind sokkal jobb!!!! mint ezek
        nameEditText      = (EditText) findViewById(R.id.salary_name);
        amountEditText    = (EditText) findViewById(R.id.salary_amount);
        typeChooserButton = (ToggleButton) findViewById(R.id.expense_or_income);
        saveButton        = (Button) findViewById(R.id.save_button);
        listOfRows        = (LinearLayout) findViewById(R.id.list_of_rows);

        inflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        //Ez egy android service elkerjuk a rendszertol!

        //Esemenykezelo gombra
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().isEmpty() || amountEditText.getText().toString().isEmpty())
                {
                    Snackbar snackbar = Snackbar.make (findViewById(R.id.id_main_layout), R.string.error_check_no_input, 3000);
                    snackbar.show ();
                    //Ez egy hibakezeles!
                    return;
                }


                View rowItem = inflater.inflate(R.layout.list_item_salary, null);
                ImageView icon               = (ImageView) rowItem.findViewById(R.id.salary_direction_icon);
                TextView rowItemSalaryName   = (TextView)  rowItem.findViewById(R.id.row_salary_name);
                TextView rowItemSalaryAmount = (TextView)  rowItem.findViewById(R.id.row_salary_amount);

                //Sum
                LinearLayout sumLayout = (LinearLayout) findViewById(R.id.sum_layout_id);
                sumLayout.setVisibility(LinearLayout.VISIBLE);
                int expenseValue = Integer.parseInt (amountEditText.getText().toString());
                TextView sumValueView = (TextView)  findViewById(R.id.sum_value);
                int sumExpenseValue = Integer.parseInt(sumValueView.getText().toString());

                if (typeChooserButton.isChecked()) {
                    icon.setImageResource(R.drawable.income);
                    sumValueView.setText ("" + (expenseValue + sumExpenseValue));
                } else {
                    icon.setImageResource(R.drawable.expense);
                    sumValueView.setText ("" + (sumExpenseValue - expenseValue));
                }

                rowItemSalaryName.setText(nameEditText.getText().toString());
                rowItemSalaryAmount.setText(amountEditText.getText().toString());



                listOfRows.addView(rowItem);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            listOfRows.removeAllViews();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
