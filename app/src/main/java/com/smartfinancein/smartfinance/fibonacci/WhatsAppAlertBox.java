package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

/**
 * Created by smartfinance on 01-Mar-18.
 */

public class WhatsAppAlertBox {
    Activity activity;
    public WhatsAppAlertBox(Activity activity){
        this.activity=activity;
    }



    public void whatsAppStatusValidation(String userWhatsAppStatus) {
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

        if(status.equals("0")){
                whatsAppMessageBox(allLinks.updateWhatsAppStatus);
            }else{

            }
    }
    public void whatsAppMessageBox(final String updateWhatsAppStatus)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater factory = LayoutInflater.from(activity);
        final View view = factory.inflate(R.layout.whats_app_alert_box, null);
        builder.setView(view);

        builder.setCancelable(false);
        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Join now", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String whatAppLink = null;


                try {
                    //Updating WhatsApp status
                    Registration obj=new Registration(activity);
                    String SSID=obj.getSystemId();
                    String url= MessageFormat.format(updateWhatsAppStatus,SSID,"1");
                    new DownloadSymbolAndExpiryDll().execute(url).get();
                    //Navigating user to join WhatsApp group
                    whatAppLink =  new DownloadSymbolAndExpiryDll().execute(allLinks.joinWhatsAppLink).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Uri uri =  Uri.parse(whatAppLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.parseColor("#f7636463"));
        nbutton.setBackgroundColor(Color.WHITE);
        nbutton.setTextSize(16);
        nbutton.setTypeface(nbutton.getTypeface(), Typeface.BOLD);
        nbutton.setPadding(10,10,10,10);

        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor("#00b0ff"));
        pbutton.setTextSize(16);
        pbutton.setTypeface(pbutton.getTypeface(), Typeface.BOLD);
        pbutton.setPadding(10,10,10,10);

    }


}
