
package jp.kisaka.toshihiro.android.wimm.tally;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wimm.framework.app.LauncherActivity;
import com.wimm.framework.widget.Button;

public class TallyActivity extends LauncherActivity {
    // public class TallyActivity extends LauncherActivity implements
    // View.OnClickListener {
    private static final boolean LOCAL_LOGV = false;
    private static final String TAG = "TallyActivity";

    private static final String PREF_COUNT = "count";

    private int mCount = 0;

    private TextView mCountView;
    private Button mResetButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (LOCAL_LOGV)
            Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mCountView = (TextView) findViewById(R.id.countView);
        mResetButton = (Button) findViewById(R.id.resetButton);

        restoreCount(savedInstanceState);
    }

    // @Override
    // protected void onRestart() {
    // if (LOCAL_LOGV)
    // Log.v(TAG, "onRestart");
    // super.onRestart();
    // }

    // @Override
    // protected void onStart() {
    // if (LOCAL_LOGV)
    // Log.v(TAG, "onStart");
    // super.onStart();
    // }

    // @Override
    // protected void onResume() {
    // if (LOCAL_LOGV)
    // Log.v(TAG, "onResume");
    // super.onResume();
    // }

    @Override
    protected void onPause() {
        if (LOCAL_LOGV)
            Log.v(TAG, "onPause");
        super.onPause();

        savePrefs();
    }

    // @Override
    // protected void onStop() {
    // if (LOCAL_LOGV)
    // Log.v(TAG, "onStop");
    // super.onStop();
    // }

    // @Override
    // protected void onDestroy() {
    // if (LOCAL_LOGV)
    // Log.v(TAG, "onDestroy");
    // super.onDestroy();
    // }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (LOCAL_LOGV)
            Log.v(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);

        outState.putInt(PREF_COUNT, getCount());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (LOCAL_LOGV)
            Log.v(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);

        restoreCount(savedInstanceState);
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        int oldCount = mCount;
        mCount = count;

        if (mCount != oldCount) {
            updateCountView();
        }
    }

    public void onClick(View v) {
        if (LOCAL_LOGV)
            Log.v(TAG, "onClick");

        switch (v.getId()) {
            case R.id.countView:
                setCount(getCount() + 1);
                break;
            case R.id.resetButton:
                setCount(0);
                break;
        }
    }

    private void restoreCount(Bundle savedInstanceState) {
        if (LOCAL_LOGV)
            Log.v(TAG, "restoreCount");

        if (savedInstanceState != null) {
            setCount(savedInstanceState.getInt(PREF_COUNT, 0));
        } else {
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            setCount(prefs.getInt(PREF_COUNT, 0));
        }
    }

    private void updateCountView() {
        if (LOCAL_LOGV)
            Log.v(TAG, "updateCountView");

        mCountView.setText(Integer.toString(getCount()));
    }

    private void savePrefs() {
        if (LOCAL_LOGV)
            Log.v(TAG, "savePrefs");

        SharedPreferences.Editor editor =
                getPreferences(MODE_PRIVATE).edit();
        editor.putInt(PREF_COUNT, getCount());
        editor.commit();
    }
}
