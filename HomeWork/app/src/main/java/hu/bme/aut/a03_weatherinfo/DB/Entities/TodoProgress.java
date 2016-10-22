package hu.bme.aut.a03_weatherinfo.DB.Entities;

import com.orm.SugarRecord;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoProgress extends SugarRecord{
    String desc;
    long   start; //TODO Start time
    String category;

    public TodoProgress() {}

    public TodoProgress(String desc, String category) {
        this.desc     = desc;
        this.category = category;
        this.start    = System.currentTimeMillis();
    }
}
