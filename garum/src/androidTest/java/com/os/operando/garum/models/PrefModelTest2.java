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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

// PrefName of PrefModel test.
@RunWith(AndroidJUnit4.class)
public class PrefModelTest2 extends ApplicationTestCase<Application> {

    public PrefModelTest2() {
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
        Assert.assertFalse(prefs.contains("stringValue"));
        Assert.assertFalse(prefs.contains("intValue"));
        Assert.assertFalse(prefs.contains("floatValue"));
        Assert.assertFalse(prefs.contains("longValue"));
        Assert.assertFalse(prefs.contains("booleanValue"));

        Assert.assertFalse(prefs.contains("PrefA")); // String value isn't exist if it's null.
        Assert.assertTrue(prefs.contains("PrefB"));
        Assert.assertTrue(prefs.contains("PrefC"));
        Assert.assertTrue(prefs.contains("PrefD"));
        Assert.assertTrue(prefs.contains("PrefE"));
    }

    // Test of raw preferences values.
    @Test
    public void test2() {
        Model model = new Model();
        model.stringValue = "keep it simple stupid.";
        model.intValue = 34565;
        model.floatValue = 543.22f;
        model.longValue = 123432L;
        model.booleanValue = true;
        boolean result = model.save();
        Assert.assertTrue(result); // Check save

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertTrue(prefs.contains("PrefA"));
        Assert.assertTrue(prefs.contains("PrefB"));
        Assert.assertTrue(prefs.contains("PrefC"));
        Assert.assertTrue(prefs.contains("PrefD"));
        Assert.assertTrue(prefs.contains("PrefE"));

        Assert.assertEquals("keep it simple stupid.", prefs.getString("PrefA", "DEFAULT"));
        Assert.assertEquals(34565, prefs.getInt("PrefB", 6503));
        Assert.assertEquals(543.22f, prefs.getFloat("PrefC", 204.3f));
        Assert.assertEquals(123432L, prefs.getLong("PrefD", 3407L));
        Assert.assertEquals(true, prefs.getBoolean("PrefE", false));
    }

    // Test of load.
    @Test
    public void test3() {
        Model model = new Model();
        model.stringValue = "keep it simple stupid.";
        model.intValue = 34565;
        model.floatValue = 543.22f;
        model.longValue = 123432L;
        model.booleanValue = true;
        model.save();

        // Load data
        model = new Model();

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertTrue(prefs.contains("PrefA"));
        Assert.assertTrue(prefs.contains("PrefB"));
        Assert.assertTrue(prefs.contains("PrefC"));
        Assert.assertTrue(prefs.contains("PrefD"));
        Assert.assertTrue(prefs.contains("PrefE"));

        Assert.assertEquals("keep it simple stupid.", prefs.getString("PrefA", "DEFAULT"));
        Assert.assertEquals(34565, prefs.getInt("PrefB", 6503));
        Assert.assertEquals(543.22f, prefs.getFloat("PrefC", 204.3f));
        Assert.assertEquals(123432L, prefs.getLong("PrefD", 3407L));
        Assert.assertEquals(true, prefs.getBoolean("PrefE", false));

        Assert.assertEquals("keep it simple stupid.", model.stringValue);
        Assert.assertEquals(34565, model.intValue);
        Assert.assertEquals(543.22f, model.floatValue);
        Assert.assertEquals(123432L, model.longValue);
        Assert.assertEquals(true, model.booleanValue);
    }

    // Clear check.
    @Test
    public void test4() {
        Model model = new Model();
        model.stringValue = "keep it simple stupid.";
        model.intValue = 34565;
        model.floatValue = 543.22f;
        model.longValue = 123432L;
        model.booleanValue = true;
        model.save();

        model.clear();

        SharedPreferences prefs = getContext().getSharedPreferences("model", Context.MODE_PRIVATE);
        Assert.assertFalse(prefs.contains("PrefA"));
        Assert.assertFalse(prefs.contains("PrefB"));
        Assert.assertFalse(prefs.contains("PrefC"));
        Assert.assertFalse(prefs.contains("PrefD"));
        Assert.assertFalse(prefs.contains("PrefE"));

        Assert.assertNull(model.stringValue);
        Assert.assertEquals(0, model.intValue);
        Assert.assertEquals(0f, model.floatValue);
        Assert.assertEquals(0L, model.longValue);
        Assert.assertEquals(false, model.booleanValue);
    }

    @Pref(name = "model")
    public static class Model extends PrefModel {
        @PrefKey("PrefA")
        public String stringValue;

        @PrefKey("PrefB")
        public int intValue;

        @PrefKey("PrefC")
        public float floatValue;

        @PrefKey("PrefD")
        public long longValue;

        @PrefKey("PrefE")
        public boolean booleanValue;
    }
}