package marceme.com.blury.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import marceme.com.blury.R;
import marceme.com.blury.callback.MessageCallback;
import marceme.com.blury.model.Message;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 9/24/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private List<Message> messages;
    private MessageCallback messageCallback;


    public MessageAdapter(MessageCallback messageCallback) {
        messages = Collections.emptyList();
        this.messageCallback = messageCallback;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View messageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row_layout, parent, false);
        return new MessageViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Picasso.with(holder.avatar.getContext()).load(messages.get(position).getUrl()).into(holder.avatar);
        holder.name.setText(messages.get(position).name());
        holder.message.setText(messages.get(position).message());
        holder.date.setText(messages.get(position).getDate());
        if(messages.get(position).numberOfMessage() == 0){
            holder.numberOfMessage.setVisibility(View.GONE);
        }else {
            holder.message.setVisibility(View.VISIBLE);
            holder.numberOfMessage.setText(messages.get(position).getCountAsString());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void add(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.image_avatar_message)
        ImageView avatar;
        @BindView(R.id.text_name_message)
        TextView name;
        @BindView(R.id.text_message_message)
        TextView message;
        @BindView(R.id.text_date_message)
        TextView date;
        @BindView(R.id.text_count_message)
        TextView numberOfMessage;


        public MessageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            messageCallback.launchMessageActivity();
        }
    }
}
