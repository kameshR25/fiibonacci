package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by smartfinance on 04-Jan-18.
 */
public class LineChart {
    static Activity activity;
    private GraphicalView mChart;
    //Constructor
    public LineChart(Activity activities){
        this.activity=activities;
    }
    public void openChart(ArrayList<Double> linechart1, ArrayList<Double> linechart2,ArrayList<Double> uptarget,ArrayList<Double> downtarget, int YaxisMin, int YaxisMax, int YaxisLabel, int XaxisMin, int XaxisMax, int XaxisLabel, boolean trueOrFalse, final String graphHint1, final String graphHint2, final String graphHint3, String analytics, String xLabel, String yLabel){
        DecimalFormat format=new DecimalFormat("##.000");
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
      /*Grpah1 strats*/
        XYSeries graph1 = new XYSeries(graphHint1);
        for(int i=0;i<linechart1.size();i++)
        {
            graph1.add(Double.valueOf(format.format(uptarget.get(i))),Double.valueOf(format.format(linechart1.get(i))));

        }
        dataset.addSeries(graph1);
        // Create XYSeriesRenderer to customize XSeries
        XYSeriesRenderer grapghLine1=new XYSeriesRenderer();
        grapghLine1.setColor(Color.WHITE); //color of the graph set to cyan
        grapghLine1.setFillPoints(true);
        grapghLine1.setLineWidth(2f);
        //setting chart value distance
        grapghLine1.setDisplayChartValuesDistance(40);
        //setting line graph point style to circle
        grapghLine1.setPointStyle(PointStyle.CIRCLE);
        //setting stroke of the line chart to solid
        grapghLine1.setStroke(BasicStroke.SOLID);
        grapghLine1.setFillBelowLine(true);
        grapghLine1.setFillBelowLineColor(Color.parseColor("#4073b7"));
        grapghLine1.setFillPoints(true);
        grapghLine1.setChartValuesTextSize(25f);
        /*GRAPH 1 ENDS*/
        /*GRAPH 2 STARTS*/

        XYSeriesRenderer grapghLine2 = new XYSeriesRenderer();
            XYSeries graph2 = new XYSeries(graphHint2);
            for (int i = 0; i < linechart2.size(); i++) {
                graph2.add(Double.valueOf(format.format(downtarget.get(i))),Double.valueOf(format.format(linechart2.get(i))));

            }
            dataset.addSeries(graph2);
            grapghLine2.setColor(Color.parseColor("#FFA500")); //color of the graph set to cyan
            grapghLine2.setFillPoints(true);
            grapghLine2.setLineWidth(2f);
            //setting chart value distance
            grapghLine2.setDisplayChartValuesDistance(10);
            //setting line graph point style to circle
            grapghLine2.setPointStyle(PointStyle.CIRCLE);
            //setting stroke of the line chart to solid
            grapghLine2.setStroke(BasicStroke.SOLID);
            grapghLine2.setFillBelowLine(true);
            grapghLine2.setFillBelowLineColor(Color.parseColor("#4073b7"));
            grapghLine2.setFillPoints(true);
            grapghLine2.setChartValuesTextSize(25f);

        /*Grpah2 ends*/

// Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle(analytics);
        multiRenderer.setXTitle(xLabel);
        multiRenderer.setYTitle(yLabel);

        /**** Customizing graphs*/
        //setting text size of the title
        multiRenderer.setChartTitleTextSize(40);
        //setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
        //setting text size of the graph lable
        multiRenderer.setLabelsTextSize(30);
        //setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(true);
        //setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(true, true);
        //setting click false on graph
        multiRenderer.setClickEnabled(true);
        //setting zoom to false on both axis
        multiRenderer.setZoomEnabled(true, true);
        //setting lines to display on y axis
        multiRenderer.setShowGridY(true);
        //setting lines to display on x axis
        multiRenderer.setShowGridX(true);
        //setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
        //setting displaying line on grid
        multiRenderer.setShowGrid(true);
        //setting zoom to false
        multiRenderer.setZoomEnabled(true);
        //setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(true);
        //setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
        //setting to in scroll to false
        multiRenderer.setInScroll(true);
        //setting to set legend height of the graph
        multiRenderer.setLegendHeight(50);
        //setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
        //setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
        //setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
        //setting no of values to display in y axis
        multiRenderer.setYLabels(YaxisLabel);
        multiRenderer.setYAxisMin(YaxisMin);
        multiRenderer.setYAxisMax(YaxisMax);
        //setting no of values to display in X axis
        multiRenderer.setXLabels(XaxisLabel);
        multiRenderer.setXAxisMin(XaxisMin);
        multiRenderer.setXAxisMax(XaxisMax);
        //Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.parseColor("#104080"));
        multiRenderer.setMarginsColor(Color.parseColor("#104080"));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);

        //setting x axis point size
        multiRenderer.setPointSize(12f);
        //setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});
        multiRenderer.addSeriesRenderer(grapghLine1);
        if (linechart2!=null) {
            multiRenderer.addSeriesRenderer(grapghLine2);
        }

        //this part is used to display graph on the xml
        LinearLayout chartContainer = (LinearLayout) activity.findViewById(R.id.chart);
        // Creating a Line Chart
        mChart = (GraphicalView) ChartFactory.getLineChartView(activity.getBaseContext(), dataset, multiRenderer);

        multiRenderer.setClickEnabled(true);//
        multiRenderer.setSelectableBuffer(10);
        mChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeriesSelection seriesSelection = mChart.getCurrentSeriesAndPoint();
                if (seriesSelection != null) {
                    int seriesIndex = seriesSelection.getSeriesIndex();
                    String selectedSeries = "";
                    if (seriesIndex == 0)
                        selectedSeries = graphHint1;
                    else if (seriesIndex == 1){
                        selectedSeries = graphHint2;
                    }
                    // Getting the clicked Month
                    String month ="";
                    // Getting the y value
                    Double amount = (Double) seriesSelection.getValue();
                    Double target = (Double) seriesSelection.getXValue();
                    Toast.makeText(activity.getBaseContext(),
                            "Ratio is "  +  " \t " + amount+"\n"+ "Target is " +"\t"+target,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        //remove any views before u paint the chart
        chartContainer.removeAllViews();
        //drawing bar chart

        //adding the view to the linearlayout
        chartContainer.addView(mChart);

    }
}
