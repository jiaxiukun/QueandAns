package baway.com.queandans.ask.presenter;


import baway.com.queandans.ask.model.HomeService;
import baway.com.queandans.ask.model.bean.AskRequestBean;
import baway.com.queandans.ask.view.IConfigView;
import baway.com.queandans.core.net.CallBackLister;
import retrofit2.Call;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public class ConfigPresenter implements IConfigInterface {

    private IConfigView configView;
    public String sid;
    public String rowNumber;
    public String lastID;
    private Call<String> call;

    public ConfigPresenter() {

    }

    public ConfigPresenter(String sid, String rowNumber, String lastID, IConfigView configView) {
        this.sid = sid;
        this.rowNumber = rowNumber;
        this.lastID = lastID;
        this.configView = configView;
    }

    @Override
    public void start() {
        loadData(sid, rowNumber, lastID);
    }

    @Override
    public void loadData(String sid, String rowNumber, String lastID) {
        HomeService.getInstace().getConfig(sid, rowNumber, lastID, new CallBackLister<AskRequestBean>() {
            @Override
            public void onNetStart() {
                configView.showOrHidePregress(true);
            }

            @Override
            public void onNetSuccess(AskRequestBean askRequestBean) {
                if (configView != null) {
                    configView.showOrHidePregress(false);
                    if (askRequestBean != null) {
                        configView.refreshView(askRequestBean);
                    } else {
                        configView.showOrHideErrorView(true);
                    }
                }
            }

            @Override
            public void onNetField(AskRequestBean askRequestBean) {
                if (askRequestBean != null) {
                    configView.showOrHidePregress(false);
                }
            }
        });
    }

    @Override
    public void detachView() {
        if (configView != null) {
            configView = null;
        }
    }

}
