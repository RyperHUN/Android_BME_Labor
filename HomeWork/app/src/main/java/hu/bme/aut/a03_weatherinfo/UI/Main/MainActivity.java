package hu.bme.aut.a03_weatherinfo.UI.Main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.List;

import hu.bme.aut.a03_weatherinfo.DB.Entities.TodoProgress;
import hu.bme.aut.a03_weatherinfo.Model.Categories;
import hu.bme.aut.a03_weatherinfo.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions ();
        initFab();

        DBFillwithTestData ();

        initRecyclerView();
    }

    private void DBFillwithTestData() {
        try {
            TodoProgress.deleteAll(TodoProgress.class);

            InitProgressDatabase ();

        } catch (Exception e) {
            e.printStackTrace(); //TODO Better handling
        }
    }

    private void InitProgressDatabase() throws Exception {
        TodoProgress todoProgress = new TodoProgress("Elmenni tejert", Categories.AKey);
        todoProgress.save();
        TodoProgress todoProgress2 = new TodoProgress("Elmenni Virsliert", Categories.BKey);
        todoProgress2.save ();

        List<TodoProgress> loaded = TodoProgress.listAll(TodoProgress.class); //For debug
    }

    private void initFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // TODO: új város dialógus megjelení™ése//
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);

        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CityAdapter ();
// new OnCitySelectedListener() {
//            @Override
//            public void onCitySelected(String city) {
//                // Todo: új DetailsActivity indítása és a
//                // kiválasztott város hozzáadása
//            }
//        } );

        adapter.fillFromDb ();
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));

        dragMgr.attachRecyclerView(recyclerView);
    }

    private boolean checkPermissions ()
    {
        requestNeededPermission();

        int externalWriteEnabled = ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int externalReadEnabled = ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.READ_EXTERNAL_STORAGE);

        if (externalWriteEnabled == android.content.pm.PackageManager.PERMISSION_DENIED ||
            externalReadEnabled == android.content.pm.PackageManager.PERMISSION_DENIED)
                return false; //Permission denied

        return true;
    }

    public static final int REQUEST_CODE_LOCATION_PERMISSION = 401;

    public void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                                                // aszinkron módon magyarázat megjelenítése dialógusban,
                                                                // majd újra kérés manuálisan
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                 Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_LOCATION_PERMISSION);
        } else {
                                                                // megkaptuk az engedélyt, indíthatjuk a kívánt műveletet
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_LOCATION_PERMISSION: {
                // Ha mégsem (vissza gomb) lett választva a
                // kéréskor akkor üres a tömb
                if (grantResults.length > 1) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        // Megkaptuk az engedélyt, indítható a művelet
                    } else {
                        //TODO                                      // Nem kaptuk meg az engedélyt,
                        // üzenet jelzése a felhasználónak
                    }
                    return;
                }
            }
        }
    }
}
