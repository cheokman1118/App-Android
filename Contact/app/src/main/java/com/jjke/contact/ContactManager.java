package com.jjke.contact;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/26/2016.
 */
public class ContactManager {
    public static List<GetBean> contacts = new ArrayList<GetBean>();
    public static List<GetBean> getContact(Context context){
        ContentResolver resolver = context.getContentResolver();
        Cursor c = resolver.query(ContactsContract.RawContacts.CONTENT_URI,
                null,// new String[]{ContactsContract.RawContacts._ID},
                null,
                null,
                null);
        GetBean getBean ;
        while (c.moveToNext()){
            getBean = new GetBean();
            long rawContactId = c.getLong(c.getColumnIndex(ContactsContract.RawContacts._ID));
            getBean.setRawContactID(rawContactId);
            Cursor dataCursor = resolver.query(ContactsContract.Data.CONTENT_URI,null,
                    ContactsContract.Data.RAW_CONTACT_ID + "=?",new String[]{String.valueOf(rawContactId)},null);
            while (dataCursor.moveToNext()){
                String data1 = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DATA1));
                String mimetype = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                if (mimetype.equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)){
                    getBean.setName(data1);
                }else if (mimetype.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
                    getBean.setPhone(data1);
                }

            }
            contacts.add(getBean);
            dataCursor.close();
        }
        c.close();
        return contacts;
    }


    public static void addContact(Context context,GetBean contact){
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        Uri rawContactUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactId = ContentUris.parseId(rawContactUri);

        ContentValues valuesData1 = new ContentValues();
        valuesData1.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
        valuesData1.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        valuesData1.put(ContactsContract.CommonDataKinds.Phone.NUMBER,contact.getPhone());
        resolver.insert(ContactsContract.Data.CONTENT_URI,valuesData1);

        ContentValues valuesData2 = new ContentValues();
        valuesData2.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
        valuesData2.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        valuesData2.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,contact.getName());
        resolver.insert(ContactsContract.Data.CONTENT_URI,valuesData2);

    }
    public static void updateContact(Context context,GetBean contact){
        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation
                .newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(ContactsContract.Data.RAW_CONTACT_ID+ "=? AND" + ContactsContract.Data.MIMETYPE+ "=?",
                        new String[]{String.valueOf(contact.getRawContactID()), ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE})
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,contact.getName()).build());

        try {
            resolver.applyBatch(ContactsContract.AUTHORITY,ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }


    }
    public static void deleteContact(Context context,GetBean contact){
        ContentResolver resolver = context.getContentResolver();
        resolver.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts._ID + "?",
                new String[]{String.valueOf(contact.getRawContactID())});
    }
}
