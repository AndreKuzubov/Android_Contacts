package contacts.andre.myapplication;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Andre on 29.06.2016.
 */
public class SystemFragmentContacts extends Fragment {

    public ArrayList<ContactItem> contacts=new ArrayList<ContactItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor cursor=null;
        try {
            cursor= getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[] {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                    null, null, null);

            while (cursor.moveToNext()){
                ContactItem contact=new ContactItem(cursor.getString(1),cursor.getString(2));
                contacts.add(contact);
            }

        }finally {
            if (cursor!=null&&!cursor.isClosed())
            cursor.close();
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListAdapter adapter=new SimpleAdapter(this.getActivity(),contacts,R.layout.list_item,new String[]{ContactItem.NAME,ContactItem.NUMBER},
                new int[]{R.id.textName,R.id.textNumber});
        ListView listView=new ListView(this.getActivity());

        listView.setAdapter(adapter);
        return listView;
    }

     class ContactItem extends HashMap<String,String> {
         static  final String NAME = "name";
         static final String NUMBER = "number";

        public  ContactItem(String name,String number){
            super();
            put(NAME, name);
            put(NUMBER,number);
        }

    }

}

