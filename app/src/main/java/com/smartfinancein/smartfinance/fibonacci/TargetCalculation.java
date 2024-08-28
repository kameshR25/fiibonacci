package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.content.DialogInterface;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by smartfinance on 29-Jan-18.
 */
public class TargetCalculation {
    Activity activity;
    public TargetCalculation(Activity activity){
        this.activity=activity;
    }
    public ArrayList<ArrayList> targetCalculation(String dayHigh, String dayLow,String curPrice) {
        ArrayList<Double> uptargets=new ArrayList<>();
        ArrayList<Double> downtargets=new ArrayList<>();
        ArrayList<Double> allTargets=new ArrayList<>();
        ArrayList<ArrayList> multiArrayList=new ArrayList<>();


        Double high = stringToDouble(dayHigh);
        Double low = stringToDouble(dayLow);
        Double CurrentPrice = stringToDouble(curPrice);
        
        Double mid=(high+low)/2;
        Double priceRange=((high-low)/high)*high;
        
        Double utrend=mid+priceRange*0.382;
        Double dtrend=mid-priceRange*0.382;
        Double dstop=0.0;
        Double utgt1=0.0;
        Double utgt2=0.0;
        Double utgt3=0.0;
        Double utgt4=0.0;
        Double utgt5=0.0;
        Double utgt6=0.0;

        Double ustop=0.0;
        Double dtgt1=0.0;
        Double dtgt2=0.0;
        Double dtgt3=0.0;
        Double dtgt4=0.0;
        Double dtgt5=0.0;
        Double dtgt6=0.0;
        if ((utrend>CurrentPrice)||(dtrend<CurrentPrice)){
            dstop=mid+priceRange*0.272;
            utgt1=mid+priceRange*0.5;
            utgt2=mid+priceRange*0.618;
            utgt3=mid+priceRange*0.786;
            utgt4=mid+priceRange*1;
            utgt5=mid+priceRange*1.272;
            utgt6=mid+priceRange*1.618;
            
            ustop=mid-priceRange*0.272;
            dtgt1=mid-priceRange*0.5;
            dtgt2=mid-priceRange*0.618;
            dtgt3=mid-priceRange*0.786;
            dtgt4=mid-priceRange*1;
            dtgt5=mid-priceRange*1.272;
            dtgt6=mid-priceRange*1.618;
        }else{
            dstop=CurrentPrice+priceRange*0.272;
            utgt1=CurrentPrice+priceRange*0.5;
            utgt2=CurrentPrice+priceRange*0.618;
            utgt3=CurrentPrice+priceRange*0.786;
            utgt4=CurrentPrice+priceRange*1;
            utgt5=CurrentPrice+priceRange*1.272;
            utgt6=CurrentPrice+priceRange*1.618;

            ustop=CurrentPrice-priceRange*0.272;
            dtgt1=CurrentPrice-priceRange*0.5;
            dtgt2=CurrentPrice-priceRange*0.618;
            dtgt3=CurrentPrice-priceRange*0.786;
            dtgt4=CurrentPrice-priceRange*1;
            dtgt5=CurrentPrice-priceRange*1.272;
            dtgt6=CurrentPrice-priceRange*1.618;
        }
        
          
            //Uptrend
            uptargets.add(utrend);
            uptargets.add(utgt1);
            uptargets.add(utgt2);
            uptargets.add(utgt3);
            uptargets.add(utgt4);
            uptargets.add(utgt5);
            uptargets.add(utgt6);
            //Down trend
            downtargets.add(dtrend);
            downtargets.add(dtgt1);
            downtargets.add(dtgt2);
            downtargets.add(dtgt3);
            downtargets.add(dtgt4);
            downtargets.add(dtgt5);
            downtargets.add(dtgt6);
            //All targets
            allTargets.add(utrend);
            allTargets.add(ustop);
            allTargets.add(utgt1);
            allTargets.add(utgt2);
            allTargets.add(utgt3);
            allTargets.add(utgt4);
            allTargets.add(utgt5);
            allTargets.add(utgt6);
            allTargets.add(dtrend);
            allTargets.add(dstop);
            allTargets.add(dtgt1);
            allTargets.add(dtgt2);
            allTargets.add(dtgt3);
            allTargets.add(dtgt4);
            allTargets.add(dtgt5);
            allTargets.add(dtgt6);

        multiArrayList.add(uptargets);
        multiArrayList.add(downtargets);
        multiArrayList.add(allTargets);

        return multiArrayList;
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
    //String to double conversion
    public static Double stringToDouble(String someValue){
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number=null;
        try {
            number = format.parse(someValue);
        } catch (ParseException ex) {
            // Logger.getLogger(DownloadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number.doubleValue();
    }
}
