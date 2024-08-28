package com.smartfinancein.smartfinance.fibonacci;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    //EditTextView
    EditText userId;
    EditText password;
    EditText dayHigh;
    EditText dayLow;
    EditText currentPrice;
    //Layouts
    static LinearLayout main_layout,first_layout;
    //LinearLayout graphLayout;
    static LinearLayout registration_layout;
    static LinearLayout disclaimerLayout;
    static LinearLayout userLogin_layout;
    LinearLayout howToUse_layout;
    static LinearLayout targetLayout;
    LinearLayout graphLayout;
    LinearLayout tableLayout;
    //Table layout
    TableLayout upCycleTable;
    TableLayout downCycleTable;
    //Navigation view field
    static NavigationView navigationView;
    //Textview
    static TextView disclaimer;
    //Registration calss initialization
    Registration regFormObj=new Registration(this);
    //Target class Initializtion
    TargetCalculation obj=new TargetCalculation(this);
    //Webview
    WebView howToUse_weview;
    WebView latest_strategy;
    //String
    static String appName="F";
    //Imageview
    ImageView fiboCalculator,ebook,Profile,paidSoftware,paidBook,webinar,videos,trialApp,paidCourse;

    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    public GoogleSignInAccount GoogleSignInAccount;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 1;

    AdView mAdView,mAdView1,mAdView2;

    // comment is added

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isNetworkAvailable(this)) {
            Toast.makeText(this,"No Internet connection",Toast.LENGTH_LONG).show();
            finish(); //Calling this method to close this activity when internet is not available.
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_layout.setVisibility(View.VISIBLE);
            }
        });


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //EditTextView
        userId=(EditText)findViewById(R.id.userId);
        password=(EditText)findViewById(R.id.password);
        dayHigh=(EditText)findViewById(R.id.dayHigh);
        dayLow=(EditText)findViewById(R.id.dayLow);
        currentPrice=(EditText)findViewById(R.id.currentPrice);
        //Linearlayouts
        first_layout = (LinearLayout)findViewById(R.id.first_layout);
        main_layout=(LinearLayout)findViewById(R.id.main_layout);
       //graphLayout=(LinearLayout)findViewById(R.id.graphLayout);
        registration_layout=(LinearLayout)findViewById(R.id.registration_layout);
        disclaimerLayout=(LinearLayout)findViewById(R.id.disclaimer_layout);
        userLogin_layout=(LinearLayout)findViewById(R.id.userLogin_layout);
        howToUse_layout=(LinearLayout)findViewById(R.id.howToUse_layout);
        targetLayout=(LinearLayout)findViewById(R.id.targetLayout);
        graphLayout=(LinearLayout)findViewById(R.id.graphLayout);
        tableLayout=(LinearLayout)findViewById(R.id.tableLayout);
        //Table layout
        upCycleTable=(TableLayout)findViewById(R.id.upCycleTable);
        downCycleTable=(TableLayout)findViewById(R.id.downCycleTable);
        //Textview
        disclaimer=(TextView)findViewById(R.id.disclaimer);
        //Webview
        howToUse_weview=(WebView)findViewById(R.id.howToUse_weview);
        latest_strategy=(WebView)findViewById(R.id.latest_strategy);
        webview(latest_strategy,allLinks.latestStrategy);

        //Imageview
        fiboCalculator = (ImageView)findViewById(R.id.fiboCalculator);
        ebook = (ImageView)findViewById(R.id.ebook);
        Profile = (ImageView)findViewById(R.id.Profile);
        paidSoftware = (ImageView)findViewById(R.id.paidSoftware);
        paidBook = (ImageView)findViewById(R.id.paidBook);
        webinar = (ImageView)findViewById(R.id.webinar);
        videos = (ImageView)findViewById(R.id.videos);
        trialApp = (ImageView)findViewById(R.id.trialApp);
        paidCourse = (ImageView)findViewById(R.id.paidCourse);
        first_layout.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://api.whatsapp.com/send?phone=919941105705&text=Dear%20Smart%20Finance%20Team.%20I'm%20Interested%20In%20...%20&source=&data=";
                //String url = allLinks.joinWhatsAppLink;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);

            }
        });

        //AD code starts
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        mAdView1 = findViewById(R.id.adView1);
        mAdView2 = findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.LARGE_BANNER);adView.setAdUnitId("ca-app-pub-9031633523121800/3079785256");

        AdView adView1 = new AdView(this);
        adView1.setAdSize(AdSize.MEDIUM_RECTANGLE);adView1.setAdUnitId("ca-app-pub-9031633523121800/6332634559");

        AdView adView2 = new AdView(this);
        adView2.setAdSize(AdSize.BANNER);adView2.setAdUnitId("ca-app-pub-9031633523121800/7757396862");

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        mAdView1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        mAdView2.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        //Ad code ends


         /*Google Signin starts*/
        // [START configure_signin]
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Set the dimensions of the sign-in button.
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        /*Google Signin ends*/

        CardView rateus = (CardView) findViewById(R.id.rateme);
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" +"com.calc.smartfinance.fibonacci")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + "getPackageName()")));
                }
            }
        });

        LinearLayout couselink = (LinearLayout) findViewById(R.id.banner);
        couselink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.smartfinance.in/fibonacci-course-details.php";
                Intent course=new Intent(Intent.ACTION_VIEW);
                course.setData(Uri.parse(url));
                startActivity(course);
            }
        });
    }
    /*Google Signin Methods starts*/
    @Override
    public void onStart() {
        super.onStart();
        //SSID Validation
        //regFormObj.checkSystemIDpresentInDatabse();

        //ACCESS TO APLLICATION LAYOUT STARTS
       first_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // [START onActivityResult]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount = result.getSignInAccount();
            String email = GoogleSignInAccount.getEmail();
            String name = GoogleSignInAccount.getGivenName();
            regFormObj.postingRegisteredFormToDatabase(name,email,"0000000000");
        }else{
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
        }
    }
    // [END handleSignInResult]
    /*Google Signin Methods Ends*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //Press again to exit
            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!",
                        Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==android.R.id.home) {
            first_layout.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.calculator) {
            howToUse_layout.setVisibility(View.GONE);
            main_layout.setVisibility(View.VISIBLE);
        } else if (id == R.id.howToUse) {
            howToUse_layout.setVisibility(View.VISIBLE);
            main_layout.setVisibility(View.GONE);
            webview(howToUse_weview,allLinks.howToUseLink);
        }
        else  if (id == R.id.joinUs) {
            //Join Whats app link
            WhatsAppAlertBox obj=new WhatsAppAlertBox(this);
            obj.whatsAppStatusValidation(allLinks.userWhatsAppStatus);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //icons starts here

    //fibonacci calculator
    public void fibo_Calculator(View v){
        first_layout.setVisibility(View.GONE);
        main_layout.setVisibility(View.VISIBLE);
    }

    //ebook
    public void Ebook(View v){
        Intent myIntent = new Intent(MainActivity.this, ebook.class);
        startActivity(myIntent);
    }

    //Profile
    public void Profile(View v){
        Intent myIntent = new Intent(MainActivity.this, profile.class);
        startActivity(myIntent);
    }

    //paid software
    public void paid_software(View v){
        Intent myIntent = new Intent(MainActivity.this, paidSoftware.class);
        startActivity(myIntent);
    }

    //paid book
    public void paid_book(View v){
        Intent myIntent = new Intent(MainActivity.this, paidBook.class);
        startActivity(myIntent);
    }

    //webinar
    public void Webinar(View v){
        Intent myIntent = new Intent(MainActivity.this, webinar.class);
        startActivity(myIntent);
    }

    //fibonacci videos
    public void video(View v){
        Intent myIntent = new Intent(MainActivity.this, video.class);
        startActivity(myIntent);
    }

    //trial app
    public void trial_app(View v){
        Intent myIntent = new Intent(MainActivity.this, freeApp.class);
        startActivity(myIntent);
    }

    // paid course
    public void paid_Course(View v){
        Intent myIntent = new Intent(MainActivity.this, paidCourse.class);
        startActivity(myIntent);
    }

    //icons starts here

    //Access to app button
    public void accessToAppBtn(View v){
        //Expiry Validation is done during access to application
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
        String SSID=regFormObj.getSystemId();
        String logDate=regFormObj.systemLogDate();
        List<String> expiryFromDatabase=regFormObj.userExpAndSSIDfromDatabase(SSID,logDate);

        try {
            Date appExpiry = myFormat.parse(expiryFromDatabase.get(1));
            Date todaysDate =myFormat.parse(regFormObj.systemLogDate());
            long difference = appExpiry.getTime() - todaysDate.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            if(daysBetween>=1.0){
                disclaimerLayout.setVisibility(View.GONE);
                main_layout.setVisibility(View.VISIBLE);
                navigationView.setVisibility(View.VISIBLE);
                //User AGREEMENT
                UserAgreement obj=new UserAgreement(this);
                obj.UserAgreementStatusValidation(allLinks.userAgreementStatusUrl);
            }else{
                regFormObj.messageBox("The application has expired.Contact admin@smartfinance.in");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Calculate button
    public void calculateBtn(View v){
        hideKeyBoard(this);
        String high=dayHigh.getText().toString();
        String low=dayLow.getText().toString();
        String curPrice=currentPrice.getText().toString();
        targetTableAndGraphDisplay(high,low,curPrice);
        //Gragh layout
    }

    private void targetTableAndGraphDisplay(String high, String low,String curPrice) {
        ArrayList<ArrayList> multiArrayList=new ArrayList<>();
        if ((high.equals(""))||(low.equals(""))||(curPrice.equals(""))){
            obj.messageBox("Fill all the blanks then click on calculate");
        }else {
            multiArrayList = obj.targetCalculation(high, low,curPrice);
            //Target layout
            DisplayTable displayObj=new DisplayTable(this);
            displayObj.clearTable();
            displayObj.displayCalculatedTargets(multiArrayList.get(2),upCycleTable,downCycleTable);
            //Grapgh Latout
            //adding arraylist to Graph
            ArrayList<Double> ratios=new ArrayList<>();
            ratios.add(0.382);
            ratios.add(0.500);
            ratios.add(0.168);
            ratios.add(0.786);
            ratios.add(1.000);
            ratios.add(1.272);
            ratios.add(1.618);
            LineChart graphObj=new LineChart(this);
            String analysis="Fibonacci Analysis";
            String xLabel="Targets";
            String yLabel="Ratios";
            Double minimum= Double.valueOf(Math.round((Double) Collections.min(multiArrayList.get(1))));
            int min = minimum.intValue();
            graphObj.openChart(ratios,ratios,multiArrayList.get(0),multiArrayList.get(1),0, 2, 10,min, min+200, 10,true,"Buy-price","Sell-price","",analysis,xLabel,yLabel);
            main_layout.setVisibility(View.GONE);
            targetLayout.setVisibility(View.VISIBLE);
        }
    }

    //ANALYSIS BUTTON
    public void analysis(View v){
        tableLayout.setVisibility(View.VISIBLE);
        graphLayout.setVisibility(View.GONE);
        targetTableAndGraphDisplay(dayHigh.getText().toString(),dayLow.getText().toString(),currentPrice.getText().toString());
    }
    //CHART BUTTON
    public void chart(View v){
        tableLayout.setVisibility(View.GONE);
        graphLayout.setVisibility(View.VISIBLE);
        targetTableAndGraphDisplay(dayHigh.getText().toString(),dayLow.getText().toString(),currentPrice.getText().toString());
    }
    //Re-calculate button
    public void recalculateBtn(View v) {
        targetLayout.setVisibility(View.GONE);
        main_layout.setVisibility(View.VISIBLE);

    }
    //Webview
    public void webview(WebView webPage,String link){
        WebSettings webSettings3 = webPage.getSettings();
        webSettings3.setJavaScriptEnabled(true);
        webPage.loadUrl(link);
    }

    //TO HIDE KEYBOARD
    static void hideKeyBoard(Activity activity){
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    //check internet connection method
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }
    //Class ends
}
