package hu.bme.aut.a03_weatherinfo.DB.Entities;

import java.sql.Date;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoProgress {
    String desc;
    Date   start; //TODO Start time
    String category;

    public TodoProgress() {}

    public TodoProgress(String desc, String category) {
        this.desc     = desc;
        this.category = category;
        this.start    = new Date (System.currentTimeMillis());
    }
}
