package com.imesong.tools.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerate {

    private static final int DB_VERSION = 20160101;
    private static final String PACKAGE_NAME = "com.imesong.springdream.database";
    private static final String SOURCE_PATH = "../springdream/app/src/main/java";

    public static void main(String[] args){
        Schema schema = new Schema(DB_VERSION,PACKAGE_NAME);
        createCategoryTable(schema);
        createDreamTable(schema);
        generateDaoFile(schema);
    }
    private static void createCategoryTable(Schema schema){
        Entity category = schema.addEntity("Category");
        category.addIdProperty();
        category.addStringProperty("name").notNull();
        category.addStringProperty("parent");
        category.addStringProperty("pinxin");
        category.addShortProperty("sequence");
        category.addStringProperty("del");
    }

    private static void createDreamTable(Schema schema){
        Entity dream = schema.addEntity("Dream");
        dream.addIdProperty();
        dream.addStringProperty("category1");
        dream.addStringProperty("category2");

        dream.addStringProperty("name").notNull();
        dream.addStringProperty("content").notNull();
        dream.addStringProperty("del");
        dream.addStringProperty("recommend");
        dream.addStringProperty("sequence");
        dream.addStringProperty("hit");
        dream.addIntProperty("is_long");

    }

    private static void generateDaoFile(Schema schema){
        try {
            new DaoGenerator().generateAll(schema, SOURCE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
