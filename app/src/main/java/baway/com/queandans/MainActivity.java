package baway.com.queandans;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import baway.com.queandans.ask.adapter.MyAdapter;
import baway.com.queandans.ask.model.bean.AskRequestBean;
import baway.com.queandans.ask.presenter.ConfigPresenter;
import baway.com.queandans.ask.view.IConfigView;
import baway.com.queandans.base.BaseActivity;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements IConfigView<AskRequestBean> {

    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.progress_main)
    ProgressBar progressMain;

    private ConfigPresenter present;
    private MyAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        present = new ConfigPresenter("xkycs", "0", "0", this);
        present.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (present != null) {
            present.detachView();
        }
    }

    @Override
    public void showOrHidePregress(boolean flag) {
        if (flag && progressMain != null) {
            progressMain.setVisibility(View.VISIBLE);
        } else {
            if (progressMain != null) {
                progressMain.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showOrHideErrorView(boolean flag) {

    }

    @Override
    public void refreshView(AskRequestBean askRequestBean) {
        if (askRequestBean != null) {
            if (adapter == null) {
                adapter = new MyAdapter(askRequestBean.list, MainActivity.this);
                listView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

}
