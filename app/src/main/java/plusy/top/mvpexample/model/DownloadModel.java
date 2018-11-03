package plusy.top.mvpexample.model;

import android.net.Uri;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: wanghui
 * Date: 2018/11/3.
 */
public class DownloadModel implements IDownloadModel {

    @Override
    public void toDownload(Uri uri, final onDownloadListener listener) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                for(Integer i = 1; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                        emitter.onNext(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        emitter.onNext(-1);
                    }
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                if(t > 0 ) {
                    listener.onDownloadProcess(t);
                    if(t == 100) {
                        listener.onComplete(t);
                    }
                } else {
                    listener.onDownloadError();
                }
            }
        }).isDisposed();
    }
}
