package com.jjke.contact;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btadd;
    private ListView lvcontact;
    private  MyAdapter adapter;
  //  private List<GetBean> contacts = new ArrayList<GetBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btadd = (Button) findViewById(R.id.button);
        btadd.setText(R.string.add);
        GetNum.getNumber(this);
        lvcontact = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(GetNum.lists, this);
        lvcontact.setAdapter(adapter);

        lvcontact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_phone);
                String text = textView.getText().toString();
                showLongClickDialog(text);
            }
        });



        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });

    }

    private void showLongClickDialog(final String text) {
        String msgT =getString(R.string.mgs);
        String callT =getString(R.string.call);

        new AlertDialog.Builder(this).setTitle(R.string.calltitle)
                .setItems(new String[]{ callT ,msgT },
                      new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        Intent intentCall = new Intent();
                                        intentCall.setAction(Intent.ACTION_CALL);
                                        intentCall.setData(Uri.parse("tel:"+text));
                                        startActivity(intentCall);
                                        break;
                                    case 1:
                                        Intent intentSend = new Intent();
                                        intentSend.setAction(Intent.ACTION_SENDTO);
                                        intentSend.setData(Uri.parse("smsto://"+text));
                                        startActivity(intentSend);
                                        break;
                                }
                            }
                        }).setNegativeButton(R.string.cancel,null)
                .show();
    }

    /** private void setContactsData(){
        List<GetBean> contactData = ContactManager.getContact(this);
        contacts.clear();
        contactData.addAll(contactData);
        adapter.notifyDataSetChanged();
    } */

    private void showAddDialog(){
      View view =  View.inflate(this,R.layout.dialog_contact,null);
        final EditText et_name = (EditText) view.findViewById(R.id.et_name);
        final EditText et_phone = (EditText) view.findViewById(R.id.et_phone);
        new AlertDialog.Builder(this)
                .setTitle(R.string.add)
                .setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GetBean contact = new GetBean();
                        contact.setName(et_name.getText()+"");
                        contact.setPhone(et_phone.getText()+"");
                        ContactManager.addContact(MainActivity.this,contact);
                        GetNum.lists.clear();
                        recreate();
                    }
                })
                .setNegativeButton(R.string.cancel,null)
        .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GetNum.lists.clear();
    }
}
