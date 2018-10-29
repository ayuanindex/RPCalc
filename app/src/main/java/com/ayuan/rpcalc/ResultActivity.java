package com.ayuan.rpcalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        String userSex = intent.getStringExtra("sex");

        TextView name = (TextView) findViewById(R.id.tv_name);
        TextView sex = (TextView) findViewById(R.id.tv_sex);
        TextView result = (TextView) findViewById(R.id.tv_result);

        name.setText("姓名:" + userName);
        sex.setText("性别:" + userSex);
        //计算人品结果
        byte[] userNameBytes = userName.getBytes();
        byte[] userSexBytes = userSex.getBytes();
        int total = 0;
        for (byte b : userNameBytes) {
            for (byte bb : userSexBytes) {
                int number = b & 0xFF & bb;
                total += number;
            }
        }

        String userResult = "";
        int score = Math.abs(total) % 100;
        if (score > 90) {
            userResult = "您的人品非常好,你家的祖坟都冒青烟了";
        } else if (score > 80) {
            userResult = "人品还可以";
        } else if (score > 60) {
            userResult = "你的人品刚及格";
        } else {
            userResult = "你的人品太次了，你需要努力了";
        }
        result.setText(userResult);
    }
}
