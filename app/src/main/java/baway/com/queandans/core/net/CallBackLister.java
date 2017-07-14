package baway.com.queandans.core.net;
/**
 * Created by 贾秀坤 on 2017/7/14.
 */
public interface CallBackLister<T> {

    void onNetStart();

    void onNetSuccess(T t);

    void onNetField(T t);

}
