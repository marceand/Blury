package marceme.com.blury.message;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import marceme.com.blury.R;
import marceme.com.blury.adapter.ChatAdapter;
import marceme.com.blury.dependencyinjection.Injector;
import marceme.com.blury.model.Chat;
import marceme.com.blury.model.Notifier;

public class ChatActivity extends AppCompatActivity {

    private static final String[] botMessages = {"Hello there!", "did you play yesterday?","Having fun, and you?",
                                                "My score is 100 pts", "I received my game trophy, keep playing",
                                                "do we invite him? Not sure", "won 10 pts!", "joining first time"};

    @BindView(R.id.recycler_view_chat)
    RecyclerView chatRecyclerView;
    @BindView(R.id.edit_text_message)
    EditText editTextMessage;

    private ChatAdapter chatAdapter;
    private Handler handler;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        injectView();
        setupToolbar();
        setupChatRecyclerView();
        setupHandlerWithRandom();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnableMessage());
    }

    private void setupHandlerWithRandom() {
        handler = Injector.provideHandler();
        random = new Random();
    }


    private void injectView() {
        ButterKnife.bind(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_chat);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupChatRecyclerView() {
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setHasFixedSize(true);
        chatAdapter = Injector.provideChatAdapter();
        chatRecyclerView.setAdapter(chatAdapter);
    }

    @OnClick(R.id.btn_send_message)
    public void btnSendMessageListener(ImageView sendButton){

        String senderMessage = editTextMessage.getText().toString().trim();
        if(!senderMessage.isEmpty()){
            chatAdapter.add(new Chat(Notifier.SENDER, senderMessage));
            editTextMessage.setText("");
            chatRecyclerView.scrollToPosition(chatAdapter.getItemCount()-1);
            botMessage();
        }
    }

    private void botMessage() {
        handler.postDelayed(runnableMessage(), 2000);
    }

    private Runnable runnableMessage() {
        return new Runnable() {
            @Override
            public void run() {
                chatAdapter.add(new Chat(Notifier.RECIPIENT, makeRandomMessage()));
                chatRecyclerView.scrollToPosition(chatAdapter.getItemCount()-1);
            }
        };
    }

    private String makeRandomMessage() {
        return botMessages[random.nextInt(botMessages.length)];
    }
}
