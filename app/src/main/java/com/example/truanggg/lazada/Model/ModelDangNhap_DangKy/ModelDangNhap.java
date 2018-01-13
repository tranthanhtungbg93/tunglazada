package com.example.truanggg.lazada.Model.ModelDangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.View.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 12/10/2017.
 **/

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFBHienTai(){

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    accessToken = currentAccessToken;
            }
        };
        accessToken =AccessToken.getCurrentAccessToken();

        return accessToken;
    }
    public String LayCachedDAngNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String tennv = cachedDangNhap.getString("tennv","");
        return tennv;
    }
    public void UpdateCachedDangNhap (Context context,String tennv){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("tennv",tennv);
        editor.commit();
    }
    public boolean KiemTraDangNhap(Context context,String tendangnhap,String matkhau){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hasHam = new HashMap<>();
        hasHam.put("ham","KiemTraDangNhap");

        HashMap<String,String> hsTenDangNHap = new HashMap<>();
        hsTenDangNHap.put("tendangnhap",tendangnhap);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        attrs.add(hasHam);
        attrs.add(hsTenDangNHap);
        attrs.add(hsMatKhau);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieu = downLoadJSON.get();
            JSONObject jsonObject =new JSONObject(dulieu);
            String JsonKQ = jsonObject.getString("ketqua");
            if (JsonKQ.equals("true")){
                String tennv = jsonObject.getString("tennv");
                kiemtra = true;
                UpdateCachedDangNhap(context,tennv);
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

    public GoogleApiClient LayGoogleCLient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((AppCompatActivity) context,failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        return mGoogleSignInClient;
    }

    public GoogleSignInResult LayThongTinDangNhapGG(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            return opr.get();
        }else {
            return null;
        }
    }
    public void HuyTokenChecker(){
        accessTokenTracker.stopTracking();
    }
}
