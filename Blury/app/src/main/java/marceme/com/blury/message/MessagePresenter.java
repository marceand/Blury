package marceme.com.blury.message;

import java.util.List;

import marceme.com.blury.model.Message;
import marceme.com.blury.remote.DataManager;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class MessagePresenter {

    private DataManager dataManager;
    private MessageViewController messageViewController;
    private Subscription subscription;

    public MessagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void attachView(MessageViewController messageViewController) {
        this.messageViewController = messageViewController;
    }

    public void detachView() {
        messageViewController = null;
    }


    public void loadMessage() {

        messageViewController.showProgressBar(true);

        unSubscribe();

        subscription = dataManager.getMessages()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Message>>() {
                    @Override
                    public void onCompleted() {
                        messageViewController.showProgressBar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageViewController.showProgressBar(false);
                        messageViewController.showErrorMessage();
                    }

                    @Override
                    public void onNext(List<Message> messages) {
                        if(messages.size() > 0) {
                            messageViewController.showMessages(messages);
                        }else {
                            messageViewController.showEmptyMessage();
                        }
                    }
                });
    }

    private void unSubscribe() {
        if(subscription != null){
            subscription.unsubscribe();
        }
    }

    public void stopLoadMessage() {
        unSubscribe();
    }
}
