package com.example.truanggg.lazada.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Truang on 12/1/2017.
 **/

public class DownLoadJSON extends AsyncTask<String,Void, String> {
    String duongDan;
    List<HashMap<String,String>> attrs;
    StringBuilder dulieu;
    boolean method = true;

    public DownLoadJSON(String duongDan){
        this.duongDan = duongDan;
        method = true;
    }

    public DownLoadJSON(String duongDan, List<HashMap<String,String>> attrs){
        this.duongDan = duongDan;
        this.attrs = attrs;
        method = false;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            URL url = new URL(duongDan);
            HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();

            if(!method){
                data = methodPOST(httpURLConnection);
            }else {
               data = mothodGet(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("data",data);
        return data;
    }

    private String mothodGet(HttpURLConnection httpURLConnection){
        String data = "";
        InputStream inputStream ;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            dulieu = new StringBuilder();
            String line ;
            while ((line = bufferedReader.readLine()) != null ){
                dulieu.append(line);
            }
            data = dulieu.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  data;
    }
    private String methodPOST(HttpURLConnection httpURLConnection){
        String data ="";
        String key = "";
        String value ="";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();// add các thuộc tính vào
            int count = attrs.size();
            for (int i = 0; i < count; i++){
                // chúng ta sử dụng hashmap duyệt key:value nên chúng ta duyệt hashmap bằng for
                for (Map.Entry<String,String> values : attrs.get(i).entrySet()){
                        key = values.getKey();
                        value = values.getValue();
                }
                builder.appendQueryParameter(key,value);
            }

            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);

            writer.write(query);
            writer.flush(); // đẩy hết dữ liệu ra
            writer.close();
            streamWriter.close();
            outputStream.close(); // đóng tất cả các luồng khi xử lí thành công

            data = mothodGet(httpURLConnection);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
