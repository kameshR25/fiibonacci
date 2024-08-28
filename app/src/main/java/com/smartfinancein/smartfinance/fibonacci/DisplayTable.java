package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by smartfinance on 22-Jan-18.
 */
public class DisplayTable {
    static Activity activity;
    public DisplayTable(Activity activity){
        this.activity=activity;
    }

    //method to set target names
    public void displayCalculatedTargets(ArrayList targetValues, TableLayout upCycleTable, TableLayout downCycleTable) {

        ArrayList<String> targetNames=new ArrayList<>();

        targetNames.add("Buy at(0.382)");
        targetNames.add("Stop loss");
        targetNames.add("Target1(0.500)");
        targetNames.add("Target2(0.168)");
        targetNames.add("Target3(0.786)");
        targetNames.add("Target4(1.000)");
        targetNames.add("Target5(1.272)");
        targetNames.add("Target6(1.618)");
        targetNames.add("Sell at(0.382)");
        targetNames.add("Stop loss");
        targetNames.add("Target1(0.500)");
        targetNames.add("Target2(0.168)");
        targetNames.add("Target3(0.786)");
        targetNames.add("Target4(1.000)");
        targetNames.add("Target5(1.272)");
        targetNames.add("Target6(1.618)");
        displayTarget(targetValues,targetNames,upCycleTable,downCycleTable);
    }
    //Display Targets
    public void displayTarget(ArrayList targetValues, ArrayList targetNames, TableLayout upCycleTable, TableLayout downCycleTable){
        int loopSize=targetNames.size()/2;
        //Upcycle
        for (int i=0;i<=loopSize-1;i++) {
            TableRow upRows = new TableRow(activity);
            upRows.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView upCycleTargetNames = new TextView(activity);
            upCycleTargetNames.setText(String.valueOf(targetNames.get(i)));
            upCycleTargetNames.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            upCycleTargetNames.setTextColor(Color.parseColor("#000000"));
            upCycleTargetNames.setPadding(5, 5, 5, 5);
            upRows.addView(upCycleTargetNames);// add the column to the table row here

            TextView upCycleTargetValues = new TextView(activity);
            upCycleTargetValues.setText(String.format("%.2f",targetValues.get(i)));
            upCycleTargetValues.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            upCycleTargetValues.setTextColor(Color.parseColor("#000000"));
            upCycleTargetValues.setPadding(5, 5, 5, 5);
            upRows.addView(upCycleTargetValues);// add the column to the table row here


            upCycleTable.addView(upRows, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        //Down cycle
        for (int j=loopSize;j<=targetNames.size()-1;j++){
            TableRow downRows = new TableRow(activity);
            downRows.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView downCycleTargetNames = new TextView(activity);
            downCycleTargetNames.setText(String.valueOf(targetNames.get(j)));
            downCycleTargetNames.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            downCycleTargetNames.setTextColor(Color.parseColor("#000000"));
            downCycleTargetNames.setPadding(5, 5, 5, 5);
            downRows.addView(downCycleTargetNames);// add the column to the table row here

            TextView downCycleTargetValues = new TextView(activity);
            downCycleTargetValues.setText(String.valueOf(String.format("%.2f",targetValues.get(j))));
            downCycleTargetValues.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            downCycleTargetValues.setTextColor(Color.parseColor("#000000"));
            downCycleTargetValues.setPadding(5, 5, 5, 5);
            downRows.addView(downCycleTargetValues);// add the column to the table row here

            downCycleTable.addView(downRows, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }
    //method to clear the table
    public static void clearTable(){
        ArrayList<Object> table=new ArrayList<Object>();
        TableLayout t1=(TableLayout)activity.findViewById(R.id.upCycleTable);
        TableLayout t2=(TableLayout)activity.findViewById(R.id.downCycleTable);

        table.add(t1);
        table.add(t2);

        for(int j=0;j<=table.size()-1;j++) {
            TableLayout clearTable= (TableLayout) table.get(j);
            for (int i = 0; i < clearTable.getChildCount(); i = 0) {
                View child = clearTable.getChildAt(0);
                if (child != null && child instanceof TableRow) {
                    TableRow row = (TableRow) child;
                    row.removeAllViews();
                    clearTable.removeViewAt(i);
                }
            }
        }

    }


}
