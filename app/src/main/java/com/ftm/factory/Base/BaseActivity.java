package com.ftm.factory.Base;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import static androidx.core.view.WindowInsetsCompat.*;
import androidx.core.view.WindowInsetsControllerCompat;

import com.ape.factory.Utils;
import com.ftm.factory.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = BaseActivity.class.getSimpleName();
    public static final int TIME_OUT = 500;
    public static final int SHOW_CALI_BT = 111;

    public Button mBtnPass, mBtnFail;
    public boolean mMMITestConfig = false;
    public int mFullTestFlag = -1;
    public int mMMI1TestFlag = 0;

    protected boolean isMonkey = false;

    private View mLayoutPassAndFail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        setWindowFlag();
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_test_item_base);
        initView();
        initIntentExtra();
        Log.i(TAG, "============== onCreate ==============");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TAG = this.getClass().getSimpleName();
        Log.i(TAG, TAG + "============== onResume ==============");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, TAG + "------------ onBackPressed ------------ ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        TAG = this.getClass().getSimpleName();
        Log.i(TAG, TAG + "------------ onPause ------------ ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        TAG = this.getClass().getSimpleName();
        Log.i(TAG, TAG + "------------ onStart ------------ ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        TAG = this.getClass().getSimpleName();
        Log.i(TAG, TAG + "------------ onStop ------------ ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TAG = this.getClass().getSimpleName();
        Log.i(TAG, TAG + "------------ onDestroy ------------ ");
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * ???????????????????????????
     */
    private void setWindowFlag() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        WindowInsetsController controller = getWindow().getInsetsController();
        if (controller != null) {
            controller.hide(Type.statusBars());
            controller.hide(Type.navigationBars());
        }
    }

    /**
     * ?????????layout???button
     */
    private void initView() {
        mLayoutPassAndFail = findViewById(R.id.layout_PassAndFail);
        mBtnPass = this.findViewById(R.id.btn_pass);
        mBtnFail = this.findViewById(R.id.btn_fail);
        if (mBtnPass != null) {
            mBtnPass.setOnClickListener(this);
        }
        if (mBtnFail != null) {
            mBtnFail.setOnClickListener(this);
        }
    }

    /**
     * ????????? intent
     */
    private void initIntentExtra() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            mFullTestFlag = 0;
            return;
        }
        mMMI1TestFlag = bundle.getInt(Utils.TEST_TYPE_MMI1);
        mFullTestFlag = bundle.getInt(Utils.TEST_TYPE);
        Log.i(TAG, "MMI test flag: " + mMMI1TestFlag + " mFullTestFlag: " + mFullTestFlag);
        if (mFullTestFlag != Utils.FULL_TEST_TYPE) {
            mFullTestFlag = 0;
        }
    }

    /**
     * ?????????????????????????????????Activity
     *
     * @param layoutResID
     */
    public void setContentView_FTM(int layoutResID) {
        final ViewGroup parent = findViewById(R.id.content_frame);
        if (parent != null) {
            parent.removeAllViews();
        }

        // LayoutInflater????????????layout???xml????????????????????????View????????????
        LayoutInflater.from(this).inflate(layoutResID, parent);
    }

    public void setPassFailBtnVisible(int visibility) {
        if (mLayoutPassAndFail != null) {
            mLayoutPassAndFail.setVisibility(visibility);
        }
        if (mBtnPass != null) {
            mBtnPass.setVisibility(visibility);
        }
        if (mBtnPass != null) {
            mBtnFail.setVisibility(visibility);
        }
    }

}
