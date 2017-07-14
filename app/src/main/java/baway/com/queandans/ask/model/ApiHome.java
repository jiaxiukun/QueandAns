package baway.com.queandans.ask.model;


import baway.com.queandans.Constants;
import baway.com.queandans.core.net.Apigenerator;
import baway.com.queandans.core.net.BaseService;
import retrofit2.Call;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public class ApiHome {

    private static volatile ApiHome instance = null;
    private BaseService baseService;

    public ApiHome() {
        baseService = Apigenerator.getBaseNetService();
    }

    public static ApiHome getInstance() {
        if (instance == null) {
            synchronized (ApiHome.class) {
                if (instance == null) {
                    instance = new ApiHome();
                }
            }
        }
        return instance;
    }

    public Call<String> getConfigFromServer(String sid, String rowNumber, String lastID) {
        if (baseService == null) {
            baseService = Apigenerator.getBaseNetService();
        }
        return baseService.baseGetRequest(getConfigUrl(sid, rowNumber, lastID));
    }
    //http://h5.newaircloud.com/api/getAskBarPlusList?sid=xkycs&rowNumber=0&lastID=0

    public String getConfigUrl(String sid, String rowNumber, String lastID) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constants.BASEURL).append(Constants.ASKBARPLUS_PATH)
                .append("sid=").append(sid).append("&rowNumber=")
                .append(rowNumber).append("&lastID=").append(lastID);
        return stringBuffer.toString();
    }

}
