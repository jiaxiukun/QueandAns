package baway.com.queandans.ask.view;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public interface IConfigView<T> {

    void showOrHidePregress(boolean flag);

    void showOrHideErrorView(boolean flag);

    void refreshView(T t);
}
