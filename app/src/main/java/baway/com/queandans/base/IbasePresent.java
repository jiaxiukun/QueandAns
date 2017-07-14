package baway.com.queandans.base;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public interface IbasePresent {
    void start();
    void loadData(String sid, String rowNumber, String lastID);
    void detachView();
}
