package hu.bme.aut.a03_weatherinfo.DB.Entities;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        this.category = progress.category;
        this.start    = progress.start;

        this.end      = System.currentTimeMillis();

    }

    public String getDesc () {return desc;}

    public String getStartDate() {
        Date date = new Date(start);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }
}
