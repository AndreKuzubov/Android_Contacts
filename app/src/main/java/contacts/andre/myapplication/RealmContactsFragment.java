package contacts.andre.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Andre on 29.06.2016.
 */
public class RealmContactsFragment extends Fragment {

    MyAdapter adapter=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListView listView=new ListView(this.getActivity());
        return new ListView(this.getActivity());
    }



    public void updateRealm(){
        SystemFragmentContacts fr=(SystemFragmentContacts)getFragmentManager().findFragmentById(R.id.sys_contacts);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for(SystemFragmentContacts.ContactItem contactItem : fr.contacts){
            MyContact  myContact=realm.createObject(MyContact.class);
            myContact.name=contactItem.get(contactItem.NAME);
            myContact.number=contactItem.get(contactItem.NUMBER);
        }
        realm.commitTransaction();

        loadRealm();
    }


    private  void loadRealm(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults <MyContact> contacts= realm.where(MyContact.class).findAll();

        adapter=new MyAdapter(contacts);
        ((ListView)getView()).setAdapter(adapter);
    }

    private class  MyAdapter implements ListAdapter {

        RealmResults <MyContact> contacts=null;
        MyAdapter( RealmResults <MyContact> contacts){
            this.contacts=contacts;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int i) {
            return true;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            if (contacts==null)return 0;
            return contacts.size();
        }

        @Override
        public Object getItem(int i) {
            return contacts.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,null);
            ((TextView)v.findViewById(R.id.textName)).setText(contacts.get(i).name);
            ((TextView)v.findViewById(R.id.textNumber)).setText(contacts.get(i).number);
            return v;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return contacts==null;
        }
    }

}

