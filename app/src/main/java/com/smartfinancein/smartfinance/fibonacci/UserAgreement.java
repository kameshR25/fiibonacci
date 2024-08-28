package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

/**
 * Created by smartfinance on 01-Mar-18.
 */

public class UserAgreement {
    Activity activity;
    public UserAgreement(Activity activity){
        this.activity=activity;
    }



    public void UserAgreementStatusValidation(String userWhatsAppStatus) {
        String status = null;

        //URL Construction
        Registration obj=new Registration(activity);
        String SSID=obj.getSystemId();
        String url= MessageFormat.format(userWhatsAppStatus,SSID);


        try {
            status =  new DownloadSymbolAndExpiryDll().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(status.equals("")){
            agreementMessageBox(allLinks.userAgreementUrl);
        }else{

        }
    }
    public void agreementMessageBox(final String updateWhatsAppStatus)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater factory = LayoutInflater.from(activity);
        final View view = factory.inflate(R.layout.user_agreement, null);
        builder.setView(view);

        builder.setCancelable(false);
        builder.setNegativeButton("disagree", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    //Updating WhatsApp status
                    Registration obj=new Registration(activity);
                    String SSID=obj.getSystemId();
                    String url= MessageFormat.format(updateWhatsAppStatus,SSID,"0");
                    new DownloadSymbolAndExpiryDll().execute(url).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setPositiveButton("agree", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String whatAppLink = null;


                try {
                    //Updating WhatsApp status
                    Registration obj=new Registration(activity);
                    String SSID=obj.getSystemId();
                    String url= MessageFormat.format(updateWhatsAppStatus,SSID,"1");
                    new DownloadSymbolAndExpiryDll().execute(url).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) nbutton.getLayoutParams();

        nbutton.setTextColor(Color.parseColor("#f7636463"));
        nbutton.setBackgroundColor(Color.WHITE);
        nbutton.setTextSize(16);
        nbutton.setTypeface(nbutton.getTypeface(), Typeface.BOLD);
        nbutton.setPadding(120,10,120,10);

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor("#FF4081"));
        pbutton.setTextSize(16);
        pbutton.setTypeface(pbutton.getTypeface(), Typeface.BOLD);
        pbutton.setPadding(120,10,120,10);

    }


}
