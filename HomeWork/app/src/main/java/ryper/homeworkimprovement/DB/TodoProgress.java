package ryper.homeworkimprovement.DB;

import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Ryper on 2016. 10. 22..
 */

public class TodoProgress extends SugarRecord {
    String desc;
    long   start;
    String category;

    public TodoProgress() {}

    public TodoProgress(String desc, String categoryKey) throws Exception {
        this.desc     = desc;
        if (Categories.isValidCategory(categoryKey))
            this.category = categoryKey;
        else
            throw new Exception("No valid category");
        this.start    = System.currentTimeMillis();
    }

    public String getDesc () {return desc;}

    public String getStartDate() {
        Date date = new Date(start);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }
}
