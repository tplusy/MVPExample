package plusy.top.mvpexample.presenter;

import android.net.Uri;
import plusy.top.mvpexample.model.DownloadModel;
import plusy.top.mvpexample.model.IDownloadModel;
import plusy.top.mvpexample.view.IDisplayView;

import java.lang.ref.WeakReference;

/**
 * Author: wanghui
 * Date: 2018/11/3.
 */
public class DownloadPresenter {
    private IDownloadModel downloadModel;
    private WeakReference<IDisplayView> displayView;

    public DownloadPresenter(IDisplayView view)
    {
        displayView = new WeakReference<>(view);
        downloadModel = new DownloadModel();
    }

    public void download(Uri uri) {
        downloadModel.toDownload(uri, new IDownloadModel.onDownloadListener() {
            @Override
            public void onComplete(Object o) {
                IDisplayView view = displayView.get();
                if(view != null) {
                    view.showDone(o);
                }
            }

            @Override
            public void onDownloadProcess(int process) {
                IDisplayView view = displayView.get();
                if(view != null) {
                    view.showProcess(process);
                }
            }

            @Override
            public void onDownloadError() {
                IDisplayView view = displayView.get();
                if(view != null) {
                    view.showError();
                }
            }
        });
    }
}
