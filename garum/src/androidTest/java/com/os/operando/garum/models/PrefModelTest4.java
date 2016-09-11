package com.os.operando.garum.models;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import com.os.operando.garum.Garum;
import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class PrefModelTest4 extends ApplicationTestCase<Application> {

    public PrefModelTest4() {
        super(Application.class);
    }

    @Before
    public void setUp() throws Exception {
        // Clear a model pref.
        getContext().getSharedPreferences("model", Context.MODE_PRIVATE).edit().clear().commit();

        Garum.initialize(getContext());
    }

    @After
    public void tearDown() throws Exception {
        // Clear a model pref.
        getContext().getSharedPreferences("model", Context.MODE_PRIVATE).edit().clear().commit();
    }

    // Test of raw preferences.
    @Test
    public void test1() {
        Model model = new Model();
        boolean result = model.save();
        Assert.assertTrue(result); // Check save

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertFalse(prefs.contains("date"));
        Assert.assertFalse(prefs.contains("json"));

        Assert.assertNull(model.date);
        Assert.assertNull(model.json);
    }

    @Test
    public void test2() throws JSONException {
        String jsonString = "{\"screen_name\":\"katty0324\",\"age\":23}";
        JSONObject jsonObject = new JSONObject(jsonString);
        Date date = new Date();

        Model model = new Model();
        model.date = date;
        model.json = jsonObject;
        model.save();

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertTrue(prefs.contains("date"));
        Assert.assertTrue(prefs.contains("json"));
    }

    // Test of load.
    @Test
    public void test3() throws JSONException {
        String jsonString = "{\"screen_name\":\"katty0324\",\"age\":23}";
        JSONObject jsonObject = new JSONObject(jsonString);
        Date date = new Date();

        Model model = new Model();
        model.date = date;
        model.json = jsonObject;
        model.save();

        // Load data
        model = new Model();

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertTrue(prefs.contains("date"));
        Assert.assertTrue(prefs.contains("json"));

        Assert.assertEquals(date, model.date);
        Assert.assertEquals("katty0324", model.json.getString("screen_name"));
        Assert.assertEquals("katty0324", jsonObject.getString("screen_name"));
        Assert.assertEquals(23, model.json.getInt("age"));
        Assert.assertEquals(23, jsonObject.getInt("age"));
    }

    // Test of #clear method.
    @Test
    public void test4() throws JSONException {
        String jsonString = "{\"screen_name\":\"katty0324\",\"age\":23}";
        JSONObject jsonObject = new JSONObject(jsonString);
        Date date = new Date();

        Model model = new Model();
        model.date = date;
        model.json = jsonObject;
        model.save();

        model.clear();

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertFalse(prefs.contains("date"));
        Assert.assertFalse(prefs.contains("json"));

        Assert.assertNull(model.date);
        Assert.assertNull(model.json);
    }

    @Pref(name = "model")
    public static class Model extends PrefModel {
        @PrefKey
        public Date date;

        @PrefKey
        public JSONObject json;
    }
}