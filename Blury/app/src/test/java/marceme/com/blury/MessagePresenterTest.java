package marceme.com.blury;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import marceme.com.blury.message.MessagePresenter;
import marceme.com.blury.message.MessageViewController;
import marceme.com.blury.model.Message;
import marceme.com.blury.remote.DataManager;
import rx.Observable;

import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessagePresenterTest {

    @Mock
    DataManager dataManager;
    @Mock
    MessageViewController messageViewController;

    private MessagePresenter messagePresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        messagePresenter = new MessagePresenter(dataManager);
        messagePresenter.attachView(messageViewController);
    }

    @Test
    public void loadMessageSuccessfully() throws Exception{

        List<Message> messages = MessageFactory.makeMessages(10);

        stubDataManagerGetMessage(Observable.just(messages));

        messagePresenter.loadMessage();

        verify(messageViewController, times(1)).showMessages(messages);
        verify(messageViewController, times(1)).showProgressBar(false);
    }

    @Test
    public void loadMessageEmpty() throws Exception{
        List<Message> messages = new ArrayList<>();
        stubDataManagerGetMessage(Observable.just(messages));

        messagePresenter.loadMessage();

        verify(messageViewController, never()).showMessages(messages);
        verify(messageViewController, times(1)).showEmptyMessage();
        verify(messageViewController, times(1)).showProgressBar(false);
    }

    @Test
    public void loadMessageFail() throws Exception{
        stubDataManagerGetMessage(Observable.error(new RuntimeException()));

        messagePresenter.loadMessage();

        verify(messageViewController, never()).showMessages(anyListOf(Message.class));
        verify(messageViewController, times(1)).showErrorMessage();
        verify(messageViewController, times(1)).showProgressBar(false);
    }

    private void stubDataManagerGetMessage(Observable observable) {
        doReturn(observable)
                .when(dataManager)
                .getMessages();
    }
}
