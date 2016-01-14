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
        category.setTableName("category");
        category.addIdProperty().autoincrement().notNull().columnName("id");
        category.addStringProperty("name").notNull().columnName("name");
        category.addIntProperty("parent").columnName("parent");
        category.addStringProperty("pinxin").columnName("pinxin");
        category.addIntProperty("sequence").columnName("sequence");
        category.addBooleanProperty("del").columnName("del");
    }

    private static void createDreamTable(Schema schema){
        Entity dream = schema.addEntity("Dream");
        dream.setTableName("dream");
        dream.addIdProperty().autoincrement().notNull().columnName("id");
        dream.addIntProperty("category1").columnName("category1");
        dream.addIntProperty("category2").columnName("category2");

        dream.addStringProperty("name").notNull();
        dream.addStringProperty("content").notNull();
        dream.addBooleanProperty("del");
        dream.addBooleanProperty("recommend");
        dream.addIntProperty("sequence");
        dream.addIntProperty("hit");
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
