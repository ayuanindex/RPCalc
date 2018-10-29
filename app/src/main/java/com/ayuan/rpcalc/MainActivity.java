package com.ayuan.rpcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private RadioGroup sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText) findViewById(R.id.et_name);
        sex = (RadioGroup) findViewById(R.id.rg_sex);
        Button calculation = (Button) findViewById(R.id.btn_Calculation);
        calculation.setOnClickListener(new MyCalculationOnClick());
    }

    //设置点击事件----------------计算人品——————计算完成跳转页面
    private class MyCalculationOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String userName = name.getText().toString().trim();
            //判断是否填入姓名
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(MainActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                return;
            }
            //判断选择了哪个按钮
            int checkedRadioButtonId = sex.getCheckedRadioButtonId();
            String userSex = "";
            switch (checkedRadioButtonId) {
                case R.id.rb_man:
                    userSex = "男";
                    break;
                case R.id.rb_girl:
                    userSex = "女";
                    break;
                case R.id.rb_othre:
                    userSex = "人妖";
                    break;
            }
            if (TextUtils.isEmpty(userSex)) {
                Toast.makeText(MainActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                return;
            }

            //跳转到ResultActivity页面
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("name", userName);
            intent.putExtra("sex", userSex);
            startActivity(intent);
        }
    }
}
