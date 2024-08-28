package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.view.View;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by smartfinance on 18-Jan-18.
 */
public class Login {
    Activity activity;
    public Login(Activity activity){
        this.activity=activity;
    }
    public void loginForm(String userid, String passWord, String SSID){
        String url= MessageFormat.format(allLinks.registerLink,userid,passWord,"","","",SSID,"","",1,MainActivity.appName);
        Registration obj=new Registration(activity);
        String status = null;
        Registration regFormObj=new Registration(activity);
        String logDate=regFormObj.systemLogDate();
        try {
            status = new DownloadSymbolAndExpiryDll().execute(url).get();
            if (status.equals("present")){
                List<String> SSIDandExp=obj.userExpAndSSIDfromDatabase(SSID,logDate);
                MainActivity.userLogin_layout.setVisibility(View.GONE);
                MainActivity.disclaimerLayout.setVisibility(View.VISIBLE);
                String expiryFromDatabase=SSIDandExp.get(1);
                MainActivity.disclaimer.setText("Your application will expire on:"+expiryFromDatabase);
            }else{
                obj.messageBox("User-id or Password is incorrect");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
