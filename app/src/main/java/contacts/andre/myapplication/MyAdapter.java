package contacts.andre.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;

/**
 * Created by Andre on 30.06.2016.
 */
public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyContactViewHolder> {


    RealmResults<MyContact> contacts = null;
    MyAdapter(RealmResults<MyContact> contacts) {
        super();
        this.contacts = contacts;
    }


    @Override
    public MyContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyContactViewHolder holder, int position) {
        (holder).name.setText(contacts.get(position).name);
        (holder).number.setText(contacts.get(position).number);

    }


    @Override
    public int getItemCount() {
        if (contacts == null) return 0;
        return contacts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class MyContactViewHolder extends RecyclerView.ViewHolder{
        public TextView name,number;
        public MyContactViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textName);
            number=(TextView)itemView.findViewById(R.id.textNumber);
        }
    }


}



