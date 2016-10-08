package com.example.ryper.shoppinglist;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Ryper on 2016. 10. 08..
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
