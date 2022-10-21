package com.example.mycloset;

import android.app.Application;
import android.widget.EditText;

import com.classes.utility.DB;

public class MyCloset extends Application {
    public static DB db;

    public static void inputError(EditText input, String errMsg) {
        input.setError(errMsg);
    }
}
