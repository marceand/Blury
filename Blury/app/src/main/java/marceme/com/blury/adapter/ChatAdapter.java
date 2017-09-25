package marceme.com.blury.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import marceme.com.blury.R;
import marceme.com.blury.model.Chat;
import marceme.com.blury.model.Notifier;

/**
 * Created by Marcel on 11/7/2015.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Chat> chatList;
    private static final int SENDER = 0;
    private static final int RECIPIENT = 1;

    public ChatAdapter() {
        chatList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatList.get(position).getNotifier().equals(Notifier.SENDER)){
            return SENDER;
        }else {
            return RECIPIENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SENDER:
                View viewSender = inflater.inflate(R.layout.sender_message_layout, viewGroup, false);
                viewHolder= new ViewHolderSender(viewSender);
                break;
            case RECIPIENT:
                View viewRecipient = inflater.inflate(R.layout.recipient_message_layout, viewGroup, false);
                viewHolder=new ViewHolderRecipient(viewRecipient);
                break;
            default:
                View viewSenderDefault = inflater.inflate(R.layout.sender_message_layout, viewGroup, false);
                viewHolder= new ViewHolderSender(viewSenderDefault);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()){
            case SENDER:
                ViewHolderSender viewHolderSender=(ViewHolderSender)viewHolder;
                configureSenderView(viewHolderSender,position);
                break;
            case RECIPIENT:
                ViewHolderRecipient viewHolderRecipient=(ViewHolderRecipient)viewHolder;
                configureRecipientView(viewHolderRecipient,position);
                break;
        }


    }

    private void configureSenderView(ViewHolderSender viewHolderSender, int position) {
        Chat senderChat = chatList.get(position);
        viewHolderSender.senderTextView.setText(senderChat.getMessage());
    }

    private void configureRecipientView(ViewHolderRecipient viewHolderRecipient, int position) {
        Chat recipientChat = chatList.get(position);
        viewHolderRecipient.recipientTextView.setText(recipientChat.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }


    public void add(Chat chat){
        chatList.add(chat);
        notifyItemInserted(getItemCount()-1);
    }


    public class ViewHolderSender extends RecyclerView.ViewHolder {

        @BindView(R.id.text_sender_message)
        TextView senderTextView;

        public ViewHolderSender(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


    public class ViewHolderRecipient extends RecyclerView.ViewHolder {

        @BindView(R.id.text_recipient_message)
        TextView recipientTextView;

        public ViewHolderRecipient(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}