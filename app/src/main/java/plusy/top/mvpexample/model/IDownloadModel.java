package plusy.top.mvpexample.model;

import android.net.Uri;

/**
 * Author: wanghui
 * Date: 2018/11/3.
 */
public interface IDownloadModel {
    void toDownload(Uri uri, onDownloadListener listener);
    interface onDownloadListener {
        void onComplete(Object o);
        void onDownloadProcess(int process);
        void onDownloadError();
    }
}
