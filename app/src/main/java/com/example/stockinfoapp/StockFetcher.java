//Name: Xiao Yan
//Andrew ID: xyan3

package com.example.stockinfoapp;

// 导入所需的类
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 这是一个专门用于在后台线程中获取股票信息的类
public class StockFetcher {

    // Activity 引用，用于在主线程更新 UI
    private final Activity activity;

    // 显示查询结果的 TextView
    private final TextView outputView;

    // 构造函数：初始化 Activity 和 TextView
    public StockFetcher(Activity activity, TextView outputView) {
        this.activity = activity;
        this.outputView = outputView;
    }

    // 启动一个新线程，在后台执行网络请求
    public void fetch(final String companyName, final String phoneModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 在后台请求数据
                String result = doInBackground(companyName, phoneModel);

                // 请求完成后切回主线程更新 UI

                //	runOnUiThread()：是系统提供的，接受一个 Runnable，它负责“切换线程”
                //	onPostExecute(result)：是你写的，用来在主线程做具体的 UI 更新
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
                    }
                });
            }
        }).start();
    }

    // 执行实际的 HTTP 请求，获取股票数据
    private String doInBackground(String companyName, String phoneModel) {
        try {
            // 拼接请求 URL：替换成你实际部署的 servlet 地址
            String urlString = "https://laughing-space-garbanzo-wr5xwwwx96vxcjq-8080.app.github.dev/stockinfo"
                    + "?companyName=" + companyName
                    + "&phoneModel=" + phoneModel;

            // 创建 URL 对象
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            conn.setRequestMethod("GET");

            // 读取响应内容（字符流）
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            // 把所有行拼接成一个完整的字符串
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 关闭流
            reader.close();

            System.out.println("resp-url: "+urlString);
            System.out.println("resp-raw: "+response);
            // 返回响应结果
            return response.toString();

        } catch (Exception e) {
            // 如果发生错误，打印日志，并返回错误信息
            Log.e("StockFetcher", "Error: " + e.getMessage());
            return "Error: " + e.toString();
        }
    }

    // 在主线程中更新 UI，展示结果
    private void onPostExecute(String result) {
        try {
            JSONObject json = new JSONObject(result);

            // 检查是否返回了错误
            if (json.has("errorMessage")) {
                outputView.setText("Error: " + json.getString("errorMessage"));
                return;
            }

            // 如果没有错误，就显示正常结果
            String formatted = "Name: " + json.getString("name") +
                    "\nSymbol: " + json.getString("symbol") +
                    "\nPrices: " + json.getJSONArray("prices").toString() +
                    "\nVolumes: " + json.getJSONArray("volumes").toString();
            outputView.setText(formatted);

        } catch (Exception e) {
            outputView.setText("Error parsing result: " + e.getMessage());
        }
    }
}
