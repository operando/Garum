package com.os.operando.garum.serializers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.os.operando.garum.Garum;
import com.os.operando.garum.annotations.Pref;
import com.os.operando.garum.annotations.PrefKey;
import com.os.operando.garum.models.PrefModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class DoubleSerializerTest {

    private static final String PREFERENCE_NAME = "test";

    private Context context;

    @SuppressLint("CommitPrefEdits")
    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getTargetContext();
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
        Garum.initialize(context, true);
    }

    @SuppressLint("CommitPrefEdits")
    @After
    public void tearDown() throws Exception {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
    }

    @Test
    public void test() {
        double expected = 35.6581;

        DoubleModel doubleModel = new DoubleModel();
        doubleModel.value = expected;
        doubleModel.save();

        DoubleModel actualDoubleModel = new DoubleModel();

        Assert.assertThat(actualDoubleModel.value, is(expected));
    }

    @Pref(name = PREFERENCE_NAME)
    private static class DoubleModel extends PrefModel {

        @PrefKey
        double value;
    }
}
