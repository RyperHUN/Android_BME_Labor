package hu.bme.aut.a03_weatherinfo.DB.Entities;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoHistory extends SugarRecord {
    String desc;
    long start;
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

    private String formattedDate (long dateLong)
    {
        Date date = new Date(dateLong);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dateFormatted = formatter.format(date);

        return dateFormatted;
    }

    public String getStartDate() {
        return formattedDate (start);
    }

    public String getEndDate () {
        return " - " + formattedDate (end);
    }

    static public class EndDateComparator implements Comparator<TodoHistory> {

        @Override
        public int compare(TodoHistory o1, TodoHistory o2) {
            return o1.end < o2.end ? 1 : o1.end == o2.end ? 0 : -1;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
