package com.project.luulinhson.muasamtructuyen.View.LogIn_Register.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.project.luulinhson.muasamtructuyen.Model.LogIn_Register.ModelLogin;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import java.util.Arrays;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 3/12/2017.
 */

public class FragmentDangNhap extends Fragment implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{

    CallbackManager callbackManager;
    Button btnDangNhapFacebook,btnDangNhapGoogle,btnDangNhap;
    GoogleApiClient mGoogleApiClient;
    public static final int REQUES_GOOGLE_API = 111;
    ProgressDialog progressDialog;
    ModelLogin modelLogin;
    EditText edEmail,edMatKhau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_nhap,container,false);

        modelLogin = new ModelLogin();
        mGoogleApiClient = modelLogin.LayGoogleAPIClient(getActivity(),this);

        //code đăng nhập google
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                .enableAutoManage(getActivity(),this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
//                .build();

        // kết thúc code đăng nhập google

        // code đăng nhập facebook
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Toast.makeText(getActivity(),"Đăng nhập Facebook thành công!",Toast.LENGTH_SHORT).show();
                Toasty.success(getActivity(),"Đăng nhập Facebook thành công",Toast.LENGTH_SHORT).show();
                Intent iTrangChu = new Intent(getActivity(),MainActivity.class);
                startActivity(iTrangChu);

            }

            @Override
            public void onCancel() {
                Log.d("thoat", "onCancel: " + "Thoát");
            }

            @Override
            public void onError(FacebookException error) {
//                Toast.makeText(getActivity(),"Đã có lỗi xảy ra khi đăng nhập Facebook!",Toast.LENGTH_SHORT).show();
                Toasty.error(getActivity(),"Đã có lỗi xảy ra khi đăng nhập Facebook!",Toast.LENGTH_SHORT).show();

            }
        });
        // kết thúc code đăng nhập facebook


        btnDangNhapFacebook = (Button) view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = (Button) view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        edEmail = (EditText) view.findViewById(R.id.edEmail);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhau);

        btnDangNhapFacebook.setOnClickListener(this);
        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNhapFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGoogle:
                Intent iGoogle = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGoogle,REQUES_GOOGLE_API);
                showProgressDialog();
                break;
            case R.id.btnDangNhap:
                String tendangnhap = edEmail.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                boolean kiemtra = modelLogin.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
                if(kiemtra){
                    Intent iTrangChu = new Intent(getActivity(),MainActivity.class);
                    startActivity(iTrangChu);
//                    Toast.makeText(getActivity(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Toasty.success(getActivity(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                }else {
//                    Toast.makeText(getActivity(),"Tên và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                    Toasty.error(getActivity(),"Tên và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Vui lòng đợi");
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data); // callback nhận lại dữ liệu khi đăng nhập facebook thành công hoặc thất bại
         if(requestCode == REQUES_GOOGLE_API){
             GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
             Log.d("email", "onActivityResult: " + result.getSignInAccount().getEmail());
             if(result.isSuccess()){
                 progressDialog.cancel();
                 Intent iTrangChu = new Intent(getActivity(),MainActivity.class);
                 startActivity(iTrangChu);
                 Toasty.success(getActivity(),"Đăng nhập Google thành công!",Toast.LENGTH_SHORT).show();
             }
         }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Toast.makeText(getActivity(),"Kết nối bị lỗi!",Toast.LENGTH_SHORT).show();
        Toasty.error(getActivity(),"Kết nối bị lỗi",Toast.LENGTH_SHORT).show();
        progressDialog.cancel();
    }
}
