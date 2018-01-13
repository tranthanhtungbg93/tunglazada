package com.example.truanggg.lazada.View.DangNhap_DangKy.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truanggg.lazada.Model.ModelDangNhap_DangKy.ModelDangNhap;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.TrangChuActivity;
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

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDangNhap extends Fragment implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{


    public FragmentDangNhap() {
        // Required empty public constructor
    }
    Button btnDangNhapFB,btnDangNhapGG,btnDangNhap;
    GoogleApiClient mGoogleSignInClient;
    CallbackManager callbackManager;
    ModelDangNhap modelDangNhap;
    EditText tenDangNhap, matkhau;
    public static int SIGN_IN_GOOGLEPLUS = 100;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_dang_nhap, container, false);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        tenDangNhap = view.findViewById(R.id.edtDiaChiEmailDangNhap);
        matkhau= view.findViewById(R.id.edtMatKhauDangNhap);

         modelDangNhap = new ModelDangNhap();
//         sự kiện lắng nghe GoogleApiClient.OnConnectionFailedListener nên truyền vào là this
        mGoogleSignInClient = modelDangNhap.LayGoogleCLient(getContext(),this);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess (LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btnDangNhapFB = view.findViewById(R.id.btnDangNhapFB);
        btnDangNhapGG = view.findViewById(R.id.btnDangNhapGG);
        btnDangNhapGG.setOnClickListener(this);
        btnDangNhapFB.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.btnDangNhapFB:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this,Arrays.asList("public_profile"));

                break;
            case R.id.btnDangNhapGG:
                Intent iGoogle = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
                startActivityForResult(iGoogle,SIGN_IN_GOOGLEPLUS);
                break;
            case R.id.btnDangNhap:
                String tendangnhap = tenDangNhap.getText().toString();
                String matkhaudangnhap = matkhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhaudangnhap);
                if(kiemtra){
                    Intent iTrangChu = new Intent(getActivity(),TrangChuActivity.class);
                    startActivity(iTrangChu);
                }else {
                    Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if(requestCode == SIGN_IN_GOOGLEPLUS){
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d("google",result.getSignInAccount().getEmail());
            if (result.isSuccess() ){
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        }
    }
//     báo lỗi của google plus
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
