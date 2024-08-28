package com.smartfinancein.smartfinance.fibonacci;

/**
 * Created by SmartFinance on 01-03-2017.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *used only for json, through text file
 * @author welcome
 */
class DownloadSymbolAndExpiryDll extends AsyncTask<String, Integer, String> {
    //List<String> myList=null;
    String theString=null;
    @Override
    protected String doInBackground(String... urlPara) {
        try {
            URL url = new URL(urlPara[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestProperty("Accept","*/*");

            //Object data=urlConnection.getInputStream();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            StringWriter writer=new StringWriter();
            IOUtils.copy(in,writer,"UTF-8");
            theString=writer.toString();

            return theString;
            //myList = new ArrayList<String>(Arrays.asList(theString.split("\n")));
            //return myList;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return myList;
        return theString;
    }
    @Override
    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }


    @Override
    protected void onPostExecute(String sult) {

        //return theString;
    }

}
