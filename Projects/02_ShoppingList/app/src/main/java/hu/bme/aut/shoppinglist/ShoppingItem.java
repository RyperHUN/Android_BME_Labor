package hu.bme.aut.shoppinglist;

import com.orm.SugarRecord;

/**
 * Created by Neo on 10/10/2016.
 */

public class ShoppingItem extends SugarRecord {
    public enum Category {
        FOOD, ELECTRONIC, BOOK;
        public static Category getByOrdinal(int ordinal) {
            Category ret = null;
            for (Category cat : Category.values()) {
                if (cat.ordinal() == ordinal) {
                    ret = cat;
                    break;
                }
            }
            return ret;
        }
    }
    public String name;
    public String description;
    public Category category;
    public int estimatedPrice;
    public boolean isBought;
}