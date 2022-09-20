package com.ftm.factory.View.mainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ftm.factory.Model.ItemModel;
import com.ftm.factory.R;

public class SingleTestActivity extends AppCompatActivity {
    private static final String TAG = SingleTestActivity.class.getSimpleName();
    private ItemModel mItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_test);
    }
}