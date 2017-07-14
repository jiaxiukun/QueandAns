package baway.com.queandans.ask.model;


import baway.com.queandans.AppConstants;
import baway.com.queandans.MyApplication;
import baway.com.queandans.ask.model.bean.AskRequestBean;
import baway.com.queandans.core.net.CallBackLister;
import baway.com.queandans.utils.ACache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public class HomeService {
    private static volatile HomeService instance;
    private ACache aCache;

    public HomeService() {
        aCache = ACache.get(MyApplication.instance);
    }

    public static HomeService getInstace() {
        if (instance == null) {
            synchronized (HomeService.class) {
                if (instance == null) {
                    instance = new HomeService();
                }
            }
        }
        return instance;
    }

    public Call getConfig(String sid, String rowNumber, String lastID, final CallBackLister<AskRequestBean> callBackLister) {
        callBackLister.onNetStart();
        boolean isCallBack = false;
        AskRequestBean bean = (AskRequestBean) aCache.getAsObject(AppConstants.ASKBARPLUS_LIST_KEY);
        if (bean != null) {
            callBackLister.onNetSuccess(bean);
            isCallBack = true;
        }
        Call<String> call = ApiHome.getInstance().getConfigFromServer(sid, rowNumber, lastID);
        final boolean finalIsCallBack = isCallBack;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response != null && response.isSuccessful()) {
                    if (response.body() != null) {
                        AskRequestBean askRequestBean = AskRequestBean.objectFromData(response.body().toString());

                        if (!finalIsCallBack) {
                            callBackLister.onNetSuccess(askRequestBean);
                        }
                        aCache.put(AppConstants.ASKBARPLUS_LIST_KEY, askRequestBean);
                    } else {
                        if (!finalIsCallBack) {
                            callBackLister.onNetField(null);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBackLister.onNetField(null);
            }
        });
        return call;
    }
}
