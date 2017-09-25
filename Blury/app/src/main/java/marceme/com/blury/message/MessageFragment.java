package marceme.com.blury.message;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marceme.com.blury.R;
import marceme.com.blury.adapter.MessageAdapter;
import marceme.com.blury.callback.MessageCallback;
import marceme.com.blury.dependencyinjection.Injector;
import marceme.com.blury.model.Chat;
import marceme.com.blury.model.Message;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment implements MessageViewController, MessageCallback{


    @BindView(R.id.list_message)
    RecyclerView messageRecyclerView;

    private MessagePresenter messagePresenter;
    private MessageAdapter messageAdapter;
    private Unbinder unbinder;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupToolbar(view);
        setupMessageRecyclerView();
        setupMessagePresenter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        messagePresenter.loadMessage();
    }

    @Override
    public void onPause() {
        super.onPause();
        messagePresenter.stopLoadMessage();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        messagePresenter.detachView();
    }

    private void setupMessageRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageAdapter = Injector.provideMessageAdapter(this);
        messageRecyclerView.setAdapter(messageAdapter);
    }

    private void setupMessagePresenter() {
        messagePresenter = Injector.provideMessagePresenter();
        messagePresenter.attachView(this);
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_message);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void showMessages(List<Message> messages) {
        messageAdapter.add(messages);
    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public void showEmptyMessage() {
        Timber.i("No message in list");
    }

    @Override
    public void showErrorMessage() {
        Timber.i("error happen while loading");
    }

    @Override
    public void launchMessageActivity() {
        Intent intent = new Intent(getActivity(),ChatActivity.class);
        startActivity(intent);
    }
}
