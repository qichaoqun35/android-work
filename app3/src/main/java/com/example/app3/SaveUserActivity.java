package com.example.app3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app3.utils.Utils;

public class SaveUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserName;
    private EditText mUserPwd;
    private Button mButton;
    private Utils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        mUserName = findViewById(R.id.user_name);
        mUserPwd = findViewById(R.id.user_pwd);
        mButton = findViewById(R.id.login_button);
        mButton.setOnClickListener(this);

        //获取文件存储中的值
        mUtils = new Utils(this);
        String message = mUtils.getFile();

        if(message != null && !"".equals(message)){
            String[] content = message.split(":::");
            mUserName.setText(content[0]);
            mUserPwd.setText(content[1]);
        }
    }

    @Override
    public void onClick(View v) {
        String name = mUserName.getText().toString().trim();
        String pwd = mUserPwd.getText().toString().trim();
        if(!"".equals(name) && !"".equals(pwd)){
            if(mUtils.saveFile(name,pwd)){
                Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"保存失败",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_LONG).show();
        }
    }
}
