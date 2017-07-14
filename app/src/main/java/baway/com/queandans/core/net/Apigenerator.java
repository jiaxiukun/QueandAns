package baway.com.queandans.core.net;


import baway.com.queandans.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public class Apigenerator {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    public static BaseService getBaseNetService() {
        return retrofit.create(BaseService.class);
    }
}
