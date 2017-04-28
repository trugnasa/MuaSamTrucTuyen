package com.project.luulinhson.muasamtructuyen.Connection;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 3/1/2017.
 */

public class DownLoadJSON extends AsyncTask<String,Void,String> {

    String duongdan;
    List<HashMap<String,String>> attrs;
    StringBuilder dulieu;
    Boolean method = true;

    //Hàm khởi tạo khi lấy dữ liệu bằng phương thúc GET
    public DownLoadJSON(String duongdan){
        this.duongdan = duongdan;
        method = true;
    }

    //Hàm khởi tạo khi lấy dữ liệu bằng phương thúc POST
    public DownLoadJSON(String duongdan, List<HashMap<String,String>> attrs){
        this.duongdan = duongdan;
        this.attrs = attrs;
        method = false;
    }

    @Override
    protected String doInBackground(String... params) {

        String data = "";

        try {
            URL url = new URL(duongdan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(!method){
                data = methodPOST(httpURLConnection);
            }else {
                data = methodGET(httpURLConnection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("data", "doInBackground: " + data);
        return data;
    }

    private String methodGET(HttpURLConnection httpURLConnection){
        String data = "";

        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            dulieu = new StringBuilder();
            String result = "";
            while ((result = bufferedReader.readLine()) != null){
                dulieu.append(result);
            }
            data = dulieu.toString();

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String methodPOST(HttpURLConnection httpURLConnection){
        String data = "";
        String value = "";
        String key = "";

        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder();

            int count = attrs.size();
            for (int i = 0;i < count;i++){
                for (Map.Entry<String,String> giatri : attrs.get(i).entrySet()){
                    key = giatri.getKey();
                    value = giatri.getValue();
                }
                builder.appendQueryParameter(key,value);
            }
            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            data = methodGET(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("alo", "methodPOST: " + data);
        return data;
    }
}
