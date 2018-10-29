package com.example.app3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app3.utils.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditName;
    private EditText mEditPhone;
    private TextView mResultText;
    private MyHelper mHelper;
    private SQLiteDatabase mDb;
    private ListView mListView;
    private List<Infor> mList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);

        mEditName = findViewById(R.id.edit_name);
        mEditPhone = findViewById(R.id.edit_phone);
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonUpdate = findViewById(R.id.button_update);
        Button buttonDelete = findViewById(R.id.button_delete);
        Button buttonSelect = findViewById(R.id.button_select);
        mResultText = findViewById(R.id.result_content);
        mListView = findViewById(R.id.list_view);

        buttonAdd.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonSelect.setOnClickListener(this);

        mHelper = new MyHelper(this);
        mDb = mHelper.getReadableDatabase();
        mList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        String name = mEditName.getText().toString().trim();
        String phone = mEditPhone.getText().toString().trim();
        switch (v.getId()) {
            case R.id.button_add:
                mDb.execSQL("insert into contacts (name,phone) values (?,?)",new Object[]{name,phone});
                Toast.makeText(this,"操作成功",Toast.LENGTH_LONG).show();
                mResultText.setText("");
                break;
            case R.id.button_update:
                mDb.execSQL("update contacts set name = ? where phone = ?",new Object[]{name,phone});
                Toast.makeText(this,"操作成功",Toast.LENGTH_LONG).show();
                mResultText.setText("");
                break;
            case R.id.button_delete:
                mDb.execSQL("delete from contacts where name = ?",new Object[]{name});
                Toast.makeText(this,"操作成功",Toast.LENGTH_LONG).show();
                mResultText.setText("");
                break;
            case R.id.button_select:
                Cursor cursor = mDb.rawQuery("select * from contacts",null);
                while(cursor.moveToNext()){
                    Infor infor = new Infor();
                    infor.setName(cursor.getString(cursor.getColumnIndex("name")));
                    infor.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                    mList.add(infor);
                }
                mListView.setAdapter(new MyAdapter(this,mList));
                cursor.close();
                break;
            default:
        }
    }

}
