package contacts.andre.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Andre on 29.06.2016.
 */
public class RealmContactsFragment extends Fragment {

    RecyclerView.Adapter adapter=new MyAdapter(null);
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View con=inflater.inflate(R.layout.fragment1, container,false);
        recyclerView=(RecyclerView)con.findViewById(R.id.m_recycle_view);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setHasFixedSize(false);
        return  con;
    }



    public void updateRealm() {
        SystemFragmentContacts fr=(SystemFragmentContacts)((FragmentPagerAdapter)((MainActivity)getActivity()).viewPager.getAdapter()).getItem(0);


        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for(SystemFragmentContacts.ContactItem contactItem : fr.contacts) {
            MyContact myContact = realm.createObject(MyContact.class);
            myContact.name = contactItem.get(contactItem.NAME);
            myContact.number = contactItem.get(contactItem.NUMBER);
        }
        realm.commitTransaction();
//
        loadRealm();

    }


    private  void loadRealm(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults <MyContact> contacts= realm.where(MyContact.class).findAll();
        adapter=new MyAdapter(contacts);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}

