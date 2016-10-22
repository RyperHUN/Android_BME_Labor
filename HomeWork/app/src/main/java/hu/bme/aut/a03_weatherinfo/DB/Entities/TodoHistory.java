package hu.bme.aut.a03_weatherinfo.DB.Entities;

import com.orm.SugarRecord;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoHistory extends SugarRecord {
    String desc;
    long start; //TODO
    long end;
    String category;

    public TodoHistory() {}

    public TodoHistory(TodoProgress progress) {
        this.desc     = progress.desc;
        this.category = progress.categoryKey;
        this.start    = progress.start;

        this.end      = System.currentTimeMillis();

    }
}
