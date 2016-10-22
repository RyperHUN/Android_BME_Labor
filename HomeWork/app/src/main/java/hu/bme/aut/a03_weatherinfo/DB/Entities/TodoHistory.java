package hu.bme.aut.a03_weatherinfo.DB.Entities;

import com.orm.SugarRecord;

import java.sql.Date;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoHistory extends SugarRecord {
    String desc;
    Date start; //TODO
    Date end;
    String category;

    public TodoHistory() {}

    public TodoHistory(TodoProgress progress) {
        this.desc     = progress.desc;
        this.category = progress.category;
        this.start    = progress.start;

        this.end      = new Date (System.currentTimeMillis());

    }
}
