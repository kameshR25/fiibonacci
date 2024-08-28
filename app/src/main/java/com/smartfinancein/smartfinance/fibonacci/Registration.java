package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.content.DialogInterface;
import android.provider.Settings;
import android.view.View;

import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by smartfinance on 18-Jan-18.
 */
public class Registration {
    Activity activity;
    public Registration(Activity activity){
        this.activity=activity;
    }
    //This mehod will be CALLED during app on creation
    public void checkSystemIDpresentInDatabse(){
        String SSID=getSystemId();
        String logDate=systemLogDate();
        List<String> SSIDandExp=userExpAndSSIDfromDatabase(SSID,logDate);
        String SSIDfromDatabase=SSIDandExp.get(0);
          //If user already registred and SSID present in dataabse,check log status
        if (SSIDfromDatabase.equals("present")){
            MainActivity.registration_layout.setVisibility(View.GONE);
            MainActivity.main_layout.setVisibility(View.GONE);
            MainActivity.navigationView.setVisibility(View.GONE);
            MainActivity.disclaimerLayout.setVisibility(View.VISIBLE);
            String expiryFromDatabase=SSIDandExp.get(1);
            MainActivity.disclaimer.setText("Your application will expire on:"+expiryFromDatabase);
        }
        //Else show registration form
        else{
            MainActivity.main_layout.setVisibility(View.GONE);
            MainActivity.navigationView.setVisibility(View.GONE);
            //MainActivity.userLogin_layout.setVisibility(View.GONE);
            MainActivity.disclaimerLayout.setVisibility(View.GONE);
            MainActivity.registration_layout.setVisibility(View.VISIBLE);

        }

    }
    //Posting Registration form to database
    public void postingRegisteredFormToDatabase(String userName, String emailId, String phoneNo){
        String SSID=getSystemId();
        String date=systemLogDate();
        String expiry=expiryDate();
        String logDate=systemLogDate();
        if(userName.equals("")||emailId.equals("")||phoneNo.equals("")){
            messageBox("Fill all the details then submit");
        }else{
            try {
                String url= MessageFormat.format(allLinks.registerLink,"","",userName,emailId,phoneNo,SSID,date,expiry,"1",MainActivity.appName);
                new DownloadSymbolAndExpiryDll().execute(url).get();
                MainActivity.registration_layout.setVisibility(View.GONE);
                MainActivity.main_layout.setVisibility(View.GONE);
                MainActivity.navigationView.setVisibility(View.GONE);
                MainActivity.disclaimerLayout.setVisibility(View.VISIBLE);
                //MainActivity.userLogin_layout.setVisibility(View.GONE);
                List<String> SSIDandExp=userExpAndSSIDfromDatabase(SSID, logDate);
                MainActivity.disclaimer.setText("Your application will expire on:"+SSIDandExp.get(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    //uuid
    public  String getSystemId(){
        String unique_id = Settings.Secure.getString(activity.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return unique_id;
    }
    //System date
    public String systemLogDate(){
        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyyMMdd").format(cDate);
        //String currentDateString = DateFormat.getDateTimeInstance().format(new Date());
        return fDate;
    }
    //APP EXPIRY DATE
    public String expiryDate() {
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        Format f = new SimpleDateFormat("yyyyMMdd");
        System.out.println(f.format(date.getTime()));
        date.add(Calendar.YEAR, 1);
        String oneYearExpiry = f.format(date.getTime());

        return oneYearExpiry;
    }
    //alert box
    public void messageBox(String message)
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);

        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        //Creating dialog box
        androidx.appcompat.app.AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Alert Box");
        alert.show();
    }
    //STRING TO LIST CONVERTION
    public static List<String> stringToList(String data) {
        List<String> myList = new ArrayList<String>(Arrays.asList(data.split("<br>")));
        return myList;
    }
    //USER EXPIRY AND SSID FROM DATABASE
    public List<String> userExpAndSSIDfromDatabase(String SSID, String logDate){
        String url= MessageFormat.format(allLinks.registerLink,"","","","","",SSID,logDate,"","",MainActivity.appName);
        String id = null;
        try {
            id = new DownloadSymbolAndExpiryDll().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<String> findSSID=stringToList(id);
        return findSSID;
    }
    //CLASS ENDS
}
