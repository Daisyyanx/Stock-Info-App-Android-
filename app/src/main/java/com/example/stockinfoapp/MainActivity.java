//Name: Xiao Yan
//Andrew ID: xyan3

package com.example.stockinfoapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //你虽然在 XML 写了按钮/输入框，但在 Java 代码里完全无法操作它们
        // 绑定界面上的控件
        EditText inputSymbol = findViewById(R.id.inputSymbol);    // 输入框：公司名
        Button buttonSearch = findViewById(R.id.buttonSearch);    // 按钮：Search
        TextView textResult = findViewById(R.id.textResult);      // 文本框：展示结果


        // 按钮点击后执行异步网络请求
        // 设置点击事件：点击按钮 → 查询数据
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName = inputSymbol.getText().toString().trim();
                if (!companyName.isEmpty()) {
                    // 调用后台线程执行查询
                    new StockFetcher(MainActivity.this, textResult).fetch(companyName, Build.MODEL);//String phoneModel = Build.MODEL;
                } else {
                    textResult.setText("please enter company name");
                }
            }
        });
    }
    }










//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });