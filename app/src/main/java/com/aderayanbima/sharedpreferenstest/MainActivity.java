package com.aderayanbima.sharedpreferenstest;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //current count,
    private int mCount = 0;
    //current backgorund
    private int mColor;
    //current untuk display count dan color
    private TextView mShowCountTextView;

    //key
    private final String COUNT_KEY = "count";
    //hshs
    private final String COLOR_KEY = "color";

    private SharedPreferences mPreferences;

    private static final String mSharedPrefFile = " com.aderayanbima.sharedpreferenstest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowCountTextView = (TextView) findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

        //Restore Prefrence
        mCount = mPreferences.getInt(COUNT_KEY,0);
        mShowCountTextView.setText(String.format("%s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COUNT_KEY, mColor);
        preferencesEditor.apply();
    }

    public void countUp(View view)
    {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }

    public void reset(View view)
    {
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));

        //reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);

        //clear prefrences
        SharedPreferences.Editor prefrencesEditor = mPreferences.edit();
        prefrencesEditor.clear();
        prefrencesEditor.apply();


    }

    public void changeBackground(View view)
    {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;

    }
}
