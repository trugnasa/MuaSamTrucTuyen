package com.project.luulinhson.muasamtructuyen.Model.LogIn_Register;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 3/17/2017.
 */

public class ModelLogin {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public String LayDuLieuTrongSharePreference(Context context){
        SharedPreferences sftennv = context.getSharedPreferences("luutennv",Context.MODE_PRIVATE);
        String tennv = sftennv.getString("tennv","");
        return tennv;
    }


    public String CapNhatSharePreference(Context context,String tennv){
        SharedPreferences sftennv = context.getSharedPreferences("luutennv",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sftennv.edit();
        editor.putString("tennv",tennv);
        editor.commit();
        return tennv;
    }


    public boolean KiemTraDangNhap(Context context,String tendangnhap,String matkhau){

        boolean kiemtra = false;

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraDangNhap");

        HashMap<String,String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap",tendangnhap);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String result = jsonObject.getString("ketqua");

            if(result.equals("true")){
                kiemtra = true;
                String tennv = jsonObject.getString("tennv");

                CapNhatSharePreference(context,tennv);

            }else {
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

    public AccessToken LayTokenFacebookHienTai(){
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public GoogleApiClient LayGoogleAPIClient(Context context, GoogleApiClient.OnConnectionFailedListener connectionFailedListener){
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context),connectionFailedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinAccountGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            return opr.get();
        }else {
            return null;
        }
    }

    public void HuyToken(){
        accessTokenTracker.stopTracking();
    }
}
