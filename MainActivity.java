package com.example.asus.app_723_9002;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.example.asus.app_723_9002.R;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import com.example.asus.app_723_9002.fragment.Fragment1;
import com.example.asus.app_723_9002.fragment.Fragment2;
import com.example.asus.app_723_9002.fragment.Fragment3;
import com.example.asus.app_723_9002.fragment.Fragment4;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements View.OnClickListener,View.OnTouchListener {
    private final String TAG = "MainActivity";
    private ViewPager mVpMainContext;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList;
    private VpMainContextAdapter mVpContextAdapter;
    private boolean mStop = true;
    private Socket s;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    break;
                default:
                    break;
            }
        }
    };

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }
    public void changeFrag(int i){mVpMainContext.setCurrentItem(i);}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("MainActivity","========= onConfigurationChanged ==========");
    }

    private void initView(){
        mVpMainContext =(ViewPager)findViewById(R.id.vp_main_context);
        hideBottomUIMenu();
        setQuanping();
        Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg =new Message();
                msg.what=MyData.MSG_TIME_OUT;
                mHandler.sendMessage(msg);

            }
        },0,500);
    }

    private void initData(){
        Fragment1 fragment1 = new Fragment1(this);
        Fragment2 fragment2 = new Fragment2(this);
        Fragment3 fragment3 = new Fragment3(this);
        Fragment4 fragment4 = new Fragment4(this);

        mFragmentList=new ArrayList<>();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);

        mFragmentManager=getSupportFragmentManager();
        mVpContextAdapter=new VpMainContextAdapter(mFragmentManager,mFragmentList);
        mVpMainContext.setAdapter(mVpContextAdapter);
        Log.d("MainActivity","getOffScreenPegeLimit = " + mVpMainContext.getOffscreenPageLimit());
        getInfo();

    }
    public void setQuanping() {
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            hideBottomUIMenu();
            hideNavigationBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    protected void hideNavigationBar() {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if (Build.VERSION.SDK_INT >= 19) {
            uiFlags |= View.SYSTEM_UI_FLAG_IMMERSIVE;//0x00001000; // SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }

        try {
            getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        } catch (Exception e) {

        }

    }



    public void getInfo(){
        new Thread() {
            @Override
            public void run() {
                try {
                    s = new Socket();
                    SocketAddress socketAddress = new InetSocketAddress("10.10.100.254", 8899);
                    s.connect(socketAddress, 500);
                    InputStream inputStream =null;
                    DataInputStream input = null;
                    byte[] b = null;
                    Log.d(TAG,"=========== 0 ===========");
                    while (mStop){
                        Log.d(TAG,"=========== 1 ===========");
                        inputStream = s.getInputStream();
                        Log.d(TAG,"=========== 2 ===========");
                        input = new DataInputStream(inputStream);
                        Log.d(TAG,"=========== 3 ===========");
                        b=new byte[8];
                        Log.d(TAG,"=========== 4 ===========");
                        int length =input.read(b);
                        Log.d(TAG,"=========== 5 ===========");
                        String infoString = "";
                        Log.d(TAG,"=========== 6 ===========");
                        infoString = printHexString(b, length);
                        Log.d(TAG,"=========== 7 ===========");
                        if (BuildConfig.DEBUG) Log.d(TAG, "接收到：" + infoString);
                        Log.d(TAG,"=========== 8 ===========");
                        String[] info = infoString.split(",");
                        if(info.length>3){
                            if(info[0].equals("03")){
                                if(info[1].equals("00")){
                                    switch (info[2]){
                                        case "01":
                                            MyData.MyBool[0x0001]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_1_DOWN).sendToTarget();
                                            break;
                                        case "03":
                                            MyData.MyBool[0x0003]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_2_DOWN).sendToTarget();
                                            break;
                                        case "05":
                                            MyData.MyBool[0x0005]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_3_DOWN).sendToTarget();
                                            break;
                                        case "07":
                                            MyData.MyBool[0x0007]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_5_DOWN).sendToTarget();
                                            break;
//                                        case "1B":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_1_DOWN).sendToTarget();
//                                            break;
//                                        case "1C":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_2_DOWN).sendToTarget();
//                                            break;
//                                        case "1D":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_3_DOWN).sendToTarget();
//                                            break;
//                                        case "1E":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_4_DOWN).sendToTarget();
//                                            break;
//                                        case "13":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_5_DOWN).sendToTarget();
//                                            break;
//                                        case "14":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_6_DOWN).sendToTarget();
//                                            break;
//                                        case "15":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_7_DOWN).sendToTarget();
//                                            break;
//                                        case "16" :
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_8_DOWN).sendToTarget();
//                                            break;
                                        case "0B":
                                            MyData.MyBool[0x000B]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_9_DOWN).sendToTarget();
                                            break;
                                        case "0C":
                                            MyData.MyBool[0x000C]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_10_DOWN).sendToTarget();
                                            break;
//                                        case "17":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_16_DOWN).sendToTarget();
//                                            break;
//                                        case "18":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_17_DOWN).sendToTarget();
//                                            break;
//                                        case "19":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_18_DOWN).sendToTarget();
//                                            break;
//                                        case "1A":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_19_DOWN).sendToTarget();
//                                            break;
                                        default:
                                            break;
                                    }
                                }else if(info[1].equals("01")){
                                    switch (info[2]){
                                        case "B1":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_1_DOWN).sendToTarget();
                                            break;
                                        case "90":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_2_DOWN).sendToTarget();
                                            break;
                                        case "91":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_3_DOWN).sendToTarget();
                                            break;
                                        case "92":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_4_DOWN).sendToTarget();
                                            break;
                                        case "93":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_5_DOWN).sendToTarget();
                                            break;
                                        case "94":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_6_DOWN).sendToTarget();
                                            break;
                                        case "95":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_7_DOWN).sendToTarget();
                                            break;
                                        case "96":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_8_DOWN).sendToTarget();
                                            break;
                                        case "97":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_9_DOWN).sendToTarget();
                                            break;
                                        case "98":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_10_DOWN).sendToTarget();
                                            break;
                                        case "99":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_11_DOWN).sendToTarget();
                                            break;
                                        case "9B":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_12_DOWN).sendToTarget();
                                            break;
                                        case "9A":
                                             mHandler.obtainMessage(MyData.MSG_BUTTON3_13_DOWN).sendToTarget();
                                             break;
                                        case "9D":
                                             mHandler.obtainMessage(MyData.MSG_BUTTON3_14_DOWN).sendToTarget();
                                             break;
                                        case "9C":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_15_DOWN).sendToTarget();
                                            break;
                                        case "B0":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_20_DOWN).sendToTarget();
                                            break;
                                        case "A0":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_21_DOWN).sendToTarget();
                                            break;
                                        case "A1":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_22_DOWN).sendToTarget();
                                            break;
                                        case "A2":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_23_DOWN).sendToTarget();
                                            break;
                                        case "A3":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_24_DOWN).sendToTarget();
                                            break;
                                        case "A4":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_25_DOWN).sendToTarget();
                                            break;
                                        case "A5":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_26_DOWN).sendToTarget();
                                            break;
                                        case "A6":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_27_DOWN).sendToTarget();
                                            break;
                                        case "A7":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_28_DOWN).sendToTarget();
                                            break;
                                        case "A8":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_29_DOWN).sendToTarget();
                                            break;
                                        case "AA":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_30_DOWN).sendToTarget();
                                            break;
                                        case "A9":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_31_DOWN).sendToTarget();
                                            break;
                                        case "30":
                                            MyData.MyBool[0x0130]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_1_DOWN).sendToTarget();
                                            break;
                                        case "36":
                                            MyData.MyBool[0x0136]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_2_DOWN).sendToTarget();
                                            break;
                                        case "33":
                                            MyData.MyBool[0x0133]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_3_DOWN).sendToTarget();
                                            break;
                                        case "34":
                                            MyData.MyBool[0x0134]=true;

                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_4_DOWN).sendToTarget();
                                            break;
                                        case "35":
                                            MyData.MyBool[0x0135]=true;

                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_5_DOWN).sendToTarget();
                                            break;
                                        default:
                                            break;
                                    }
                                }else if(info[1].equals("02")){
                                    switch (info[2]){
                                        case "30":
                                            MyData.MyBool[0x0230]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_6_DOWN).sendToTarget();
                                            break;
                                        case "36":
                                            MyData.MyBool[0x0236]=true;

                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_7_DOWN).sendToTarget();
                                            break;
                                        case "33":
                                            MyData.MyBool[0x0233]=true;

                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_8_DOWN).sendToTarget();
                                            break;
                                        case "34":
                                            MyData.MyBool[0x0234]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_9_DOWN).sendToTarget();
                                            break;
                                        case "35":
                                            MyData.MyBool[0x0235]=true;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_10_DOWN).sendToTarget();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }else  if(info[0].equals("02")){
                                if(info[1].equals("00")){
                                    switch (info[2]){
                                        case "01":
                                            MyData.MyBool[0x0001]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_1_UP).sendToTarget();

                                            break;
                                        case "03":
                                            MyData.MyBool[0x0003]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_2_UP).sendToTarget();
                                            break;
                                        case "05":
                                            MyData.MyBool[0x0005]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_3_UP).sendToTarget();
                                            break;
                                        case "07":
                                            MyData.MyBool[0x0007]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON1_5_UP).sendToTarget();
                                            break;
//                                        case "1B":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_1_UP).sendToTarget();
//                                            break;
//                                        case "1C":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_2_UP).sendToTarget();
//                                            break;
//                                        case "1D":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_3_UP).sendToTarget();
//                                            break;
//                                        case "1E":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_4_UP).sendToTarget();
//                                            break;
//                                        case "13":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_5_UP).sendToTarget();
//                                            break;
//                                        case "14":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_6_UP).sendToTarget();
//                                            break;
//                                        case "15":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_7_UP).sendToTarget();
//                                            break;
//                                        case "16" :
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_8_UP).sendToTarget();
//                                            break;
                                        case "0B":
                                            MyData.MyBool[0x000B]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_9_UP).sendToTarget();
                                            break;
                                        case "0C":
                                            MyData.MyBool[0x000C]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON2_10_UP).sendToTarget();
                                            break;
//                                        case "17":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_16_UP).sendToTarget();
//                                            break;
//                                        case "18":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_17_UP).sendToTarget();
//                                            break;
//                                        case "19":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_18_UP).sendToTarget();
//                                            break;
//                                        case "1A":
//                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_19_UP).sendToTarget();
//                                            break;
                                        default:
                                            break;
                                    }
                                }else if(info[1].equals("01")){
                                    switch (info[2]){
                                        case "B1":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_1_UP).sendToTarget();
                                            break;
                                        case "90":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_2_UP).sendToTarget();
                                            break;
                                        case "91":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_3_UP).sendToTarget();
                                            break;
                                        case "92":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_4_UP).sendToTarget();
                                            break;
                                        case "93":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_5_UP).sendToTarget();
                                            break;
                                        case "94":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_6_UP).sendToTarget();
                                            break;
                                        case "95":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_7_UP).sendToTarget();
                                            break;
                                        case "96":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_8_UP).sendToTarget();
                                            break;
                                        case "97":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_9_UP).sendToTarget();
                                            break;
                                        case "98":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_10_UP).sendToTarget();
                                            break;
                                        case "99":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_11_UP).sendToTarget();
                                            break;
                                        case "9B":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_12_UP).sendToTarget();
                                            break;
                                        case "9A":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_13_UP).sendToTarget();
                                            break;
                                        case "9D":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_14_UP).sendToTarget();
                                            break;
                                        case "9C":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_15_UP).sendToTarget();
                                            break;
                                        case "B0":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_20_UP).sendToTarget();
                                            break;
                                        case "A0":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_21_UP).sendToTarget();
                                            break;
                                        case "A1":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_22_UP).sendToTarget();
                                            break;
                                        case "A2":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_23_UP).sendToTarget();
                                            break;
                                        case "A3":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_24_UP).sendToTarget();
                                            break;
                                        case "A4":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_25_UP).sendToTarget();
                                            break;
                                        case "A5":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_26_UP).sendToTarget();
                                            break;
                                        case "A6":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_27_UP).sendToTarget();
                                            break;
                                        case "A7":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_28_UP).sendToTarget();
                                            break;
                                        case "A8":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_29_UP).sendToTarget();
                                            break;
                                        case "AA":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_30_UP).sendToTarget();
                                            break;
                                        case "A9":
                                            mHandler.obtainMessage(MyData.MSG_BUTTON3_31_UP).sendToTarget();
                                            break;
                                        case "30":
                                            MyData.MyBool[0x0130]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_1_UP).sendToTarget();
                                            break;
                                        case "36":
                                            MyData.MyBool[0x1036]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_2_UP).sendToTarget();
                                            break;
                                        case "33":
                                            MyData.MyBool[0x0133]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_3_UP).sendToTarget();
                                            break;
                                        case "34":
                                            MyData.MyBool[0x0134]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_4_UP).sendToTarget();
                                            break;
                                        case "35":
                                            MyData.MyBool[0x0135]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_5_UP).sendToTarget();
                                            break;
                                        default:
                                            break;
                                    }
                                }else if(info[1].equals("02")){
                                    switch (info[2]){
                                        case "30":
                                            MyData.MyBool[0x0230]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_6_UP).sendToTarget();
                                            break;
                                        case "36":
                                            MyData.MyBool[0x0236]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_7_UP).sendToTarget();
                                            break;
                                        case "33":
                                            MyData.MyBool[0x0233]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_8_UP).sendToTarget();
                                            break;
                                        case "34":
                                            MyData.MyBool[0x0234]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_9_UP).sendToTarget();
                                            break;
                                        case "35":
                                            MyData.MyBool[0x0235]=false;
                                            mHandler.obtainMessage(MyData.MSG_BUTTON4_10_UP).sendToTarget();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
//                            /*****************************************************/
//                            if (info[0].equals("03") && info[1].equals("01")) {
//                                switch (info[2]) {
//                                    case "40"://办公
//
//                                        break;
//
//                                    default:
//
//                                        break;
//                                }
//                            } else if (info[0].equals("02") && info[1].equals("01")) {
//                                switch (info[2]) {
//                                    case "40"://办公
//
//                                        break;
//                                    default:
//                                        break;
//                                }
//                            }
//                            /********************************************************/
                        }

                    }
                }catch (SocketTimeoutException e){
                    mHandler.obtainMessage(10001).sendToTarget();
                    return;
                }catch (IOException e){
                    e.printStackTrace();
                    mHandler.obtainMessage(10002).sendToTarget();
                }
            }
        }.start();
    }

    public String printHexString(byte[] b,int length){
        String info="";
        for(int i=0; i<length; i++){
            String hex =Integer.toHexString(b[i] & 0xFF);
            if(hex.length() == 1){
                hex='0'+hex+',';
            }else{
                hex += ",";
            }
            info += hex.toUpperCase();
        }

        return info;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


}
