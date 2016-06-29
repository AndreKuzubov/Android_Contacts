package contacts.andre.myapplication;

import io.realm.RealmObject;

public class MyContact extends RealmObject {
    String name;
    String number;
    public MyContact(){}
    public MyContact(String name,String number){this.name=name;this.number=number;}

}
