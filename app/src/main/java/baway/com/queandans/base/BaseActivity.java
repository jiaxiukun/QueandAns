package baway.com.queandans.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public abstract class BaseActivity extends Activity{
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        unbinder = ButterKnife.bind(this);

        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();

    }

    public abstract int getLayoutID();

    public abstract void initView();

    public abstract void initData();

}
