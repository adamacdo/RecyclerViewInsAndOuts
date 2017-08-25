package com.adamacdo.recyclerviewinsandouts.managers;

import com.adamacdo.recyclerviewinsandouts.R;
import com.adamacdo.recyclerviewinsandouts.models.CatFact;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by adammacdonald on 7/4/17.
 */

public class CatManager {

    private static  CatFact[] mCatFacts = new CatFact[]{
        new CatFact(R.drawable.ic_material_cat_1, R.string.cat_fact_01_title, R.string.cat_fact_01_description),
        new CatFact(R.drawable.ic_material_cat_2, R.string.cat_fact_02_title, R.string.cat_fact_02_description),
        new CatFact(R.drawable.ic_material_cat_3, R.string.cat_fact_03_title, R.string.cat_fact_03_description),
        new CatFact(R.drawable.ic_material_cat_4, R.string.cat_fact_04_title, R.string.cat_fact_04_description),
        new CatFact(R.drawable.ic_material_cat_5, R.string.cat_fact_05_title, R.string.cat_fact_05_description),
        new CatFact(R.drawable.ic_material_cat_1, R.string.cat_fact_06_title, R.string.cat_fact_06_description),
        new CatFact(R.drawable.ic_material_cat_2, R.string.cat_fact_07_title, R.string.cat_fact_07_description),
        new CatFact(R.drawable.ic_material_cat_3, R.string.cat_fact_08_title, R.string.cat_fact_08_description),
        new CatFact(R.drawable.ic_material_cat_4, R.string.cat_fact_09_title, R.string.cat_fact_09_description),
        new CatFact(R.drawable.ic_material_cat_5, R.string.cat_fact_10_title, R.string.cat_fact_10_description),
        new CatFact(R.drawable.ic_material_cat_1, R.string.cat_fact_11_title, R.string.cat_fact_11_description),
        new CatFact(R.drawable.ic_material_cat_2, R.string.cat_fact_12_title, R.string.cat_fact_12_description),
        new CatFact(R.drawable.ic_material_cat_3, R.string.cat_fact_13_title, R.string.cat_fact_13_description),
        new CatFact(R.drawable.ic_material_cat_4, R.string.cat_fact_14_title, R.string.cat_fact_14_description),
        new CatFact(R.drawable.ic_material_cat_5, R.string.cat_fact_15_title, R.string.cat_fact_15_description),
        new CatFact(R.drawable.ic_material_cat_1, R.string.cat_fact_16_title, R.string.cat_fact_16_description),
        new CatFact(R.drawable.ic_material_cat_2, R.string.cat_fact_17_title, R.string.cat_fact_17_description),
        new CatFact(R.drawable.ic_material_cat_3, R.string.cat_fact_18_title, R.string.cat_fact_18_description),
        new CatFact(R.drawable.ic_material_cat_4, R.string.cat_fact_19_title, R.string.cat_fact_19_description),
        new CatFact(R.drawable.ic_material_cat_5, R.string.cat_fact_20_title, R.string.cat_fact_20_description)};

    public static CatFact[] getCatFacts() {
        return mCatFacts.clone();
    }

    public static CatFact getCatFact() {
        Random random = new Random();
        return mCatFacts[random.nextInt(20)];
    }
}
