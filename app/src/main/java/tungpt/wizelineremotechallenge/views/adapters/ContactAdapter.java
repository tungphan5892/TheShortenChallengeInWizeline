package tungpt.wizelineremotechallenge.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import tungpt.wizelineremotechallenge.R;
import tungpt.wizelineremotechallenge.models.Contact;

/**
 * Created by Tung Phan on 2/22/2017.
 */

public class ContactAdapter extends
        RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mContacts;
    // Store the context for easy access
    private Context mContext;

    public ContactAdapter(Context context, List<Contact> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact_recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int position) {
        Contact contact = mContacts.get(position);
        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());
        Button button = viewHolder.messageButton;
        button.setText("Message");
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
