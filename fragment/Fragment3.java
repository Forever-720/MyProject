package com.example.asus.app_723_9002.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.asus.app_723_9002.BuildConfig;
import com.example.asus.app_723_9002.MainActivity;
import com.example.asus.app_723_9002.MyData;
import com.example.asus.app_723_9002.R;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.pedant.SweetAlert.SweetAlertDialog;

@SuppressLint("ValidFragment")
public class Fragment3  extends Fragment implements View.OnClickListener,View.OnTouchListener{
    private MainActivity mMainActivity;
    private Context mContext;
    private ImageView[] mImgTag;
    private ExecutorService mExecutorService;
    private boolean is_Visible=false;
    private boolean  mShowDialog = false;
    private   View view;
    private FrameLayout frameLayout;

    public Fragment3 (MainActivity mainActivity){mMainActivity=mainActivity;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frameLayout=new FrameLayout(getActivity());
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        LayoutInflater _inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){    //竖屏
            view = _inflater.inflate(R.layout.port_fragment3,null);
            initView(view);
            frameLayout.addView(view);
            return frameLayout;
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){  //横屏
            view = _inflater.inflate(R.layout.fragment3,null);
            initView(view);
            frameLayout.addView(view);
            return frameLayout;
        }

        return null;
    }
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View view;
//
//        //横屏
//        if( ScreenOrient(((MainActivity)mContext))==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE  )
//        {
//            Log.d("Fragment3","screen_wight>screen_height,为横屏");
//            view = inflater.inflate(R.layout.fragment3,container,false);
//            HS = false;
//            initView(view);
//            ((MainActivity)mContext).setQuanping();
//            return view;
//        }
//        //竖屏
//        if( ScreenOrient(((MainActivity)mContext))==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  )
//        {
//            Log.d("Fragment3","screen_wight<screen_height,为竖屏");
//            view = inflater.inflate(R.layout.port_fragment3,container,false);
//            HS = true ;
//            initView(view);
//            ((MainActivity)mContext).setQuanping();
//            return view;
//        }
//
//
//
//        return null;
//    }
//
//    //判定当前的屏幕是竖屏还是横屏
//    public int ScreenOrient(Activity activity)
//    {
//        int orient = activity.getRequestedOrientation();
//        if(orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//            WindowManager windowManager = activity.getWindowManager();
//            Display display = windowManager.getDefaultDisplay();
//            int screenWidth  = display.getWidth();
//            int screenHeight = display.getHeight();
//            orient = screenWidth < screenHeight ?  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//        }
//        return orient;
//    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){
            frameLayout.removeAllViews();
            view = inflater.inflate(R.layout.port_fragment3,null);
            frameLayout.addView(view);
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            frameLayout.removeAllViews();
            view = inflater.inflate(R.layout.fragment3,null);
            frameLayout.addView(view);
        }
    }

    private void initView(View view) {
        mImgTag=new ImageView[40];
        mImgTag[0]=(ImageView)view.findViewById(R.id.imageView31);
        mImgTag[1]=(ImageView)view.findViewById(R.id.imageView31_1);
        mImgTag[2]=(ImageView)view.findViewById(R.id.imageView31_2);
        mImgTag[3]=(ImageView)view.findViewById(R.id.imageView31_3);
        mImgTag[4]=(ImageView)view.findViewById(R.id.imageView31_4);
        mImgTag[5]=(ImageView)view.findViewById(R.id.imageView31_5);
        mImgTag[6]=(ImageView)view.findViewById(R.id.imageView31_6);
        mImgTag[7]=(ImageView)view.findViewById(R.id.imageView31_7);
        mImgTag[8]=(ImageView)view.findViewById(R.id.imageView31_8);
        mImgTag[9]=(ImageView)view.findViewById(R.id.imageView31_9);
        mImgTag[10]=(ImageView)view.findViewById(R.id.imageView31_10);
        mImgTag[11]=(ImageView)view.findViewById(R.id.imageView31_11);
        mImgTag[12]=(ImageView)view.findViewById(R.id.imageView31_12);
        mImgTag[13]=(ImageView)view.findViewById(R.id.imageView31_13);
        mImgTag[14]=(ImageView)view.findViewById(R.id.imageView31_14);
        mImgTag[15]=(ImageView)view.findViewById(R.id.imageView32);
        mImgTag[16]=(ImageView)view.findViewById(R.id.imageView33);
        mImgTag[17]=(ImageView)view.findViewById(R.id.imageView34);
        mImgTag[18]=(ImageView)view.findViewById(R.id.imageView35);
        mImgTag[19]=(ImageView)view.findViewById(R.id.imageView36);
        mImgTag[20]=(ImageView)view.findViewById(R.id.imageView36_1);
        mImgTag[21]=(ImageView)view.findViewById(R.id.imageView36_2);
        mImgTag[22]=(ImageView)view.findViewById(R.id.imageView36_3);
        mImgTag[23]=(ImageView)view.findViewById(R.id.imageView36_4);
        mImgTag[24]=(ImageView)view.findViewById(R.id.imageView36_5);
        mImgTag[25]=(ImageView)view.findViewById(R.id.imageView36_6);
        mImgTag[26]=(ImageView)view.findViewById(R.id.imageView36_7);
        mImgTag[27]=(ImageView)view.findViewById(R.id.imageView36_8);
        mImgTag[28]=(ImageView)view.findViewById(R.id.imageView36_9);
        mImgTag[29]=(ImageView)view.findViewById(R.id.imageView36_10);
        mImgTag[30]=(ImageView)view.findViewById(R.id.imageView36_11);
        mImgTag[31]=(ImageView)view.findViewById(R.id.imageView37);
        mImgTag[32]=(ImageView)view.findViewById(R.id.imageView310);
        mImgTag[33]=(ImageView)view.findViewById(R.id.imageView311);
        mImgTag[34]=(ImageView) view.findViewById(R.id.imageView312);

        mImgTag[0].setOnTouchListener(this);
        mImgTag[1].setOnTouchListener(this);
        mImgTag[2].setOnTouchListener(this);
        mImgTag[3].setOnTouchListener(this);
        mImgTag[4].setOnTouchListener(this);
        mImgTag[5].setOnTouchListener(this);
        mImgTag[6].setOnTouchListener(this);
        mImgTag[7].setOnTouchListener(this);
        mImgTag[8].setOnTouchListener(this);
        mImgTag[9].setOnTouchListener(this);
        mImgTag[10].setOnTouchListener(this);
        mImgTag[11].setOnTouchListener(this);
        mImgTag[12].setOnTouchListener(this);
        mImgTag[13].setOnTouchListener(this);
        mImgTag[14].setOnTouchListener(this);
        mImgTag[15].setOnTouchListener(this);
        mImgTag[16].setOnTouchListener(this);
        mImgTag[17].setOnTouchListener(this);
        mImgTag[18].setOnTouchListener(this);
        mImgTag[19].setOnTouchListener(this);
        mImgTag[20].setOnTouchListener(this);
        mImgTag[21].setOnTouchListener(this);
        mImgTag[22].setOnTouchListener(this);
        mImgTag[23].setOnTouchListener(this);
        mImgTag[24].setOnTouchListener(this);
        mImgTag[25].setOnTouchListener(this);
        mImgTag[26].setOnTouchListener(this);
        mImgTag[27].setOnTouchListener(this);
        mImgTag[28].setOnTouchListener(this);
        mImgTag[29].setOnTouchListener(this);
        mImgTag[30].setOnTouchListener(this);
        mImgTag[31].setOnTouchListener(this);
        mImgTag[32].setOnTouchListener(this);
        mImgTag[33].setOnTouchListener(this);
        mImgTag[34].setOnTouchListener(this);

        mImgTag[0].setOnClickListener(this);
        mImgTag[1].setOnClickListener(this);
        mImgTag[2].setOnClickListener(this);
        mImgTag[3].setOnClickListener(this);
        mImgTag[4].setOnClickListener(this);
        mImgTag[5].setOnClickListener(this);
        mImgTag[6].setOnClickListener(this);
        mImgTag[7].setOnClickListener(this);
        mImgTag[8].setOnClickListener(this);
        mImgTag[9].setOnClickListener(this);
        mImgTag[10].setOnClickListener(this);
        mImgTag[11].setOnClickListener(this);
        mImgTag[12].setOnClickListener(this);
        mImgTag[13].setOnClickListener(this);
        mImgTag[14].setOnClickListener(this);
        mImgTag[15].setOnClickListener(this);
        mImgTag[16].setOnClickListener(this);
        mImgTag[17].setOnClickListener(this);
        mImgTag[18].setOnClickListener(this);
        mImgTag[19].setOnClickListener(this);
        mImgTag[20].setOnClickListener(this);
        mImgTag[21].setOnClickListener(this);
        mImgTag[22].setOnClickListener(this);
        mImgTag[23].setOnClickListener(this);
        mImgTag[24].setOnClickListener(this);
        mImgTag[25].setOnClickListener(this);
        mImgTag[26].setOnClickListener(this);
        mImgTag[27].setOnClickListener(this);
        mImgTag[28].setOnClickListener(this);
        mImgTag[29].setOnClickListener(this);
        mImgTag[30].setOnClickListener(this);
        mImgTag[31].setOnClickListener(this);
        mImgTag[32].setOnClickListener(this);
        mImgTag[33].setOnClickListener(this);
        mImgTag[34].setOnClickListener(this);

        mExecutorService= Executors.newFixedThreadPool(3);
    }
    private int mImgSrc[][] = {
            {R.drawable.p31_1_1, R.drawable.p32_1, R.drawable.p31_3_1, R.drawable.p31_4_1, R.drawable.p31_5_1, R.drawable.p31_6_1, R.drawable.p31_7_1, R.drawable.p31_8_1, R.drawable.p31_9_1, R.drawable.p31_10_1, R.drawable.p31_11_1, R.drawable.p31_12_1, R.drawable.p31_13_1, R.drawable.p31_14_1, R.drawable.p31_15_1,
             R.drawable.p32_1_1, R.drawable.p32_2_1, R.drawable.p32_3_1, R.drawable.p32_4_1, R.drawable.p33_1_1, R.drawable.p33_2_1, R.drawable.p33_3_1, R.drawable.p33_4_1, R.drawable.p33_5_1, R.drawable.p33_6_1, R.drawable.p33_7_1, R.drawable.p33_8_1, R.drawable.p33_9_1, R.drawable.p33_10_1, R.drawable.p33_11_1,
             R.drawable.p33_12_1, R.drawable.page0_1_1},
            {R.drawable.p31_1_2, R.drawable.p31_2_2, R.drawable.p31_3_2, R.drawable.p31_4_2, R.drawable.p31_5_2, R.drawable.p31_6_2, R.drawable.p31_7_2, R.drawable.p31_8_2, R.drawable.p31_9_2, R.drawable.p31_10_2, R.drawable.p31_11_2, R.drawable.p31_12_2, R.drawable.p31_13_2, R.drawable.p31_14_2, R.drawable.p31_15_2,
             R.drawable.p32_1_2, R.drawable.p32_2_2, R.drawable.p32_3_2, R.drawable.p32_4_2, R.drawable.p33_1_2, R.drawable.p33_2_2, R.drawable.p33_3_2, R.drawable.p33_4_2, R.drawable.p33_5_2, R.drawable.p33_6_2, R.drawable.p33_7_2, R.drawable.p33_8_2, R.drawable.p33_9_2, R.drawable.p33_10_2, R.drawable.p33_11_2,
             R.drawable.p33_12_2, R.drawable.page0_1_2}
    };

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(is_Visible == true)
                switch (msg.what){
                    case MyData.MSG_BUTTON3_1_DOWN:
                        mImgTag[0].setImageResource(mImgSrc[1][0]);
                        break;
                    case MyData.MSG_BUTTON3_1_UP:
                        mImgTag[0].setImageResource(mImgSrc[0][0]);
                        break;
                    case MyData.MSG_BUTTON3_2_DOWN:
                        mImgTag[1].setImageResource(mImgSrc[1][1]);
                        break;
                    case MyData.MSG_BUTTON3_2_UP:
                        mImgTag[1].setImageResource(mImgSrc[0][1]);
                        break;
                    case MyData.MSG_BUTTON3_3_DOWN:
                        mImgTag[2].setImageResource(mImgSrc[1][2]);
                        break;
                    case MyData.MSG_BUTTON3_3_UP:
                        mImgTag[2].setImageResource(mImgSrc[0][2]);
                        break;
                    case MyData.MSG_BUTTON3_4_DOWN:
                        mImgTag[3].setImageResource(mImgSrc[1][3]);
                        break;
                    case MyData.MSG_BUTTON3_4_UP:
                        mImgTag[3].setImageResource(mImgSrc[0][3]);
                        break;
                    case MyData.MSG_BUTTON3_5_DOWN:
                        mImgTag[4].setImageResource(mImgSrc[1][4]);
                        break;
                    case MyData.MSG_BUTTON3_5_UP:
                        mImgTag[4].setImageResource(mImgSrc[0][4]);
                        break;
                    case MyData.MSG_BUTTON3_6_DOWN:
                        mImgTag[5].setImageResource(mImgSrc[1][5]);
                        break;
                    case MyData.MSG_BUTTON3_6_UP:
                        mImgTag[5].setImageResource(mImgSrc[0][5]);
                        break;
                    case MyData.MSG_BUTTON3_7_DOWN:
                        mImgTag[6].setImageResource(mImgSrc[1][6]);
                        break;
                    case MyData.MSG_BUTTON3_7_UP:
                        mImgTag[6].setImageResource(mImgSrc[0][6]);
                        break;
                    case MyData.MSG_BUTTON3_8_DOWN:
                        mImgTag[7].setImageResource(mImgSrc[1][7]);
                        break;
                    case MyData.MSG_BUTTON3_8_UP:
                        mImgTag[7].setImageResource(mImgSrc[0][7]);
                        break;
                    case MyData.MSG_BUTTON3_9_DOWN:
                        mImgTag[8].setImageResource(mImgSrc[1][8]);
                        break;
                    case MyData.MSG_BUTTON3_9_UP:
                        mImgTag[8].setImageResource(mImgSrc[0][8]);
                        break;
                    case MyData.MSG_BUTTON3_10_DOWN:
                        mImgTag[9].setImageResource(mImgSrc[1][9]);
                        break;
                    case MyData.MSG_BUTTON3_10_UP:
                        mImgTag[9].setImageResource(mImgSrc[0][9]);
                        break;
                    case MyData.MSG_BUTTON3_11_DOWN:
                        mImgTag[10].setImageResource(mImgSrc[1][10]);
                        break;
                    case MyData.MSG_BUTTON3_11_UP:
                        mImgTag[10].setImageResource(mImgSrc[0][10]);
                        break;
                    case MyData.MSG_BUTTON3_12_DOWN:
                        mImgTag[11].setImageResource(mImgSrc[1][11]);
                        break;
                    case MyData.MSG_BUTTON3_12_UP:
                        mImgTag[11].setImageResource(mImgSrc[0][11]);
                        break;
                    case MyData.MSG_BUTTON3_13_DOWN:
                        mImgTag[12].setImageResource(mImgSrc[1][12]);
                        break;
                    case MyData.MSG_BUTTON3_13_UP:
                        mImgTag[12].setImageResource(mImgSrc[0][12]);
                        break;
                    case MyData.MSG_BUTTON3_14_DOWN:
                        mImgTag[13].setImageResource(mImgSrc[1][13]);
                        break;
                    case MyData.MSG_BUTTON3_14_UP:
                        mImgTag[13].setImageResource(mImgSrc[0][13]);
                        break;
                    case MyData.MSG_BUTTON3_15_DOWN:
                        mImgTag[14].setImageResource(mImgSrc[1][14]);
                        break;
                    case MyData.MSG_BUTTON3_15_UP:
                        mImgTag[14].setImageResource(mImgSrc[0][14]);
                        break;
                    case MyData.MSG_BUTTON3_16_DOWN:
                        mImgTag[15].setImageResource(mImgSrc[1][15]);
                        break;
                    case MyData.MSG_BUTTON3_16_UP:
                        mImgTag[15].setImageResource(mImgSrc[0][15]);
                        break;
                    case MyData.MSG_BUTTON3_17_DOWN:
                        mImgTag[16].setImageResource(mImgSrc[1][16]);
                        break;
                    case MyData.MSG_BUTTON3_17_UP:
                        mImgTag[16].setImageResource(mImgSrc[0][16]);
                        break;
                    case MyData.MSG_BUTTON3_18_DOWN:
                        mImgTag[17].setImageResource(mImgSrc[1][17]);
                        break;
                    case MyData.MSG_BUTTON3_18_UP:
                        mImgTag[17].setImageResource(mImgSrc[0][17]);
                        break;
                    case MyData.MSG_BUTTON3_19_DOWN:
                        mImgTag[18].setImageResource(mImgSrc[1][18]);
                        break;
                    case MyData.MSG_BUTTON3_19_UP:
                        mImgTag[18].setImageResource(mImgSrc[0][18]);
                        break;
                    case MyData.MSG_BUTTON3_20_DOWN:
                        mImgTag[19].setImageResource(mImgSrc[1][19]);
                        break;
                    case MyData.MSG_BUTTON3_20_UP:
                        mImgTag[19].setImageResource(mImgSrc[0][19]);
                        break;
                    case MyData.MSG_BUTTON3_21_DOWN:
                        mImgTag[20].setImageResource(mImgSrc[1][20]);
                        break;
                    case MyData.MSG_BUTTON3_21_UP:
                        mImgTag[20].setImageResource(mImgSrc[0][20]);
                        break;
                    case MyData.MSG_BUTTON3_22_DOWN:
                        mImgTag[21].setImageResource(mImgSrc[1][21]);
                        break;
                    case MyData.MSG_BUTTON3_22_UP:
                        mImgTag[21].setImageResource(mImgSrc[0][21]);
                        break;
                    case MyData.MSG_BUTTON3_23_DOWN:
                        mImgTag[22].setImageResource(mImgSrc[1][22]);
                        break;
                    case MyData.MSG_BUTTON3_23_UP:
                        mImgTag[22].setImageResource(mImgSrc[0][22]);
                        break;
                    case MyData.MSG_BUTTON3_24_DOWN:
                        mImgTag[23].setImageResource(mImgSrc[1][23]);
                        break;
                    case MyData.MSG_BUTTON3_24_UP:
                        mImgTag[23].setImageResource(mImgSrc[0][23]);
                        break;
                    case MyData.MSG_BUTTON3_25_DOWN:
                        mImgTag[24].setImageResource(mImgSrc[1][24]);
                        break;
                    case MyData.MSG_BUTTON3_25_UP:
                        mImgTag[24].setImageResource(mImgSrc[0][24]);
                        break;
                    case MyData.MSG_BUTTON3_26_DOWN:
                        mImgTag[25].setImageResource(mImgSrc[1][25]);
                        break;
                    case MyData.MSG_BUTTON3_26_UP:
                        mImgTag[25].setImageResource(mImgSrc[0][25]);
                        break;
                    case MyData.MSG_BUTTON3_27_DOWN:
                        mImgTag[26].setImageResource(mImgSrc[1][26]);
                        break;
                    case MyData.MSG_BUTTON3_27_UP:
                        mImgTag[26].setImageResource(mImgSrc[0][26]);
                        break;
                    case MyData.MSG_BUTTON3_28_DOWN:
                        mImgTag[27].setImageResource(mImgSrc[1][27]);
                        break;
                    case MyData.MSG_BUTTON3_28_UP:
                        mImgTag[27].setImageResource(mImgSrc[0][27]);
                        break;
                    case MyData.MSG_BUTTON3_29_DOWN:
                        mImgTag[28].setImageResource(mImgSrc[1][28]);
                        break;
                    case MyData.MSG_BUTTON3_29_UP:
                        mImgTag[28].setImageResource(mImgSrc[0][28]);
                        break;
                    case MyData.MSG_BUTTON3_30_DOWN:
                        mImgTag[29].setImageResource(mImgSrc[1][29]);
                        break;
                    case MyData.MSG_BUTTON3_30_UP:
                        mImgTag[29].setImageResource(mImgSrc[0][29]);
                        break;
                    case MyData.MSG_BUTTON3_31_DOWN:
                        mImgTag[30].setImageResource(mImgSrc[1][30]);
                        break;
                    case MyData.MSG_BUTTON3_31_UP:
                        mImgTag[30].setImageResource(mImgSrc[0][30]);
                        break;
                    case MyData.MSG_BUTTON3_32_DOWN:
                        mImgTag[31].setImageResource(mImgSrc[1][31]);
                        break;
                    case MyData.MSG_BUTTON3_32_UP:
                        mImgTag[31].setImageResource(mImgSrc[0][31]);
                        break;
                    case 10001:
                        showDialog("网络连接超时，请检查WIFI连接是否正确！");
                        break;
                    case 10002:
                        showDialog("网络波动，请重启WIFI!");
                        break;
                    default:
                        break;
                }
        }
    };

    @Override
    public void onClick(View v) {
        }
    private void showDialog(String title) {
        if(mShowDialog == true){
            final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(title).setConfirmText("确定");
            pDialog.show();
            pDialog.setCancelable(false);
            mMainActivity.setQuanping();   //调用全屏
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(mContext != null)
                mMainActivity=(MainActivity)mContext;
            mMainActivity.setmHandler(mHandler);
            is_Visible=true;
            mMainActivity.setQuanping();   //调用全屏

        }else {
            is_Visible=false;
        }

    }

    private void myTouchListener(MotionEvent motionEvent,String addr){
        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            mShowDialog = false;
            startServer("A2 78"+addr);
            if(BuildConfig.DEBUG) Log.d("Fragment3","手指抬起");
        }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            mShowDialog = true;
            startServer("A2 79"+addr);
            if(BuildConfig.DEBUG) Log.d("Fragment3","手指按下");
        }
    }
    private void startServer(final String item){
        mExecutorService.submit(new Thread(){
            @Override
            public void run() {
                if(BuildConfig.DEBUG) Log.d("Fragment3","开始请求，发送："+item);
                Socket ss=null;
                try{
                    ss = new Socket();
                    SocketAddress socAddress = new InetSocketAddress("10.10.100.254", 8899);
                    ss.connect(socAddress, 500);
                    try{
                        OutputStream outputStream =ss.getOutputStream();
                        outputStream.write(hex2byte(item));
                        outputStream.write('\n');
                        outputStream.flush();
                        ss.close();
                        if(BuildConfig.DEBUG) Log.d("Send",item);
                    }catch (SocketTimeoutException e){
                        e.printStackTrace();
                    }finally {
                        ss.close();
                    }
                }catch (SocketTimeoutException e){
                    mHandler.obtainMessage(10001).sendToTarget();
                    return;
                }catch (IOException e) {
                    e.printStackTrace();
                    if (mShowDialog == true) {
                        mHandler.obtainMessage(10002).sendToTarget();
                    }
                    return;
                }
            }
        });
    }
    public static byte[] hex2byte(String hex){
        String digtal="0123456789ABCDEF";
        String hex1 =hex.replace(" ","");
        char[] hex2char=hex1.toCharArray();
        byte[] bytes = new byte[hex1.length()/2];
        byte temp;
        for(int p=0;p<bytes.length;p++){
            temp=(byte)(digtal.indexOf(hex2char[2*p])*16);
            temp += digtal.indexOf(hex2char[2*p+1]);
            bytes[p]=(byte)(temp & 0xff);
        }
        return bytes;
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if(is_Visible == true)
            switch (v.getId()){
                case R.id.imageView31:
                    myTouchListener(motionEvent,"01 B1");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_1_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_1_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_1:
                    myTouchListener(motionEvent, "01 90");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_2_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_2_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_2:
                    myTouchListener(motionEvent, "01 91");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_3_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_3_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_3:
                    myTouchListener(motionEvent, "01 92");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_4_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_4_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_4:
                    myTouchListener(motionEvent, "01 93");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_5_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_5_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_5:
                    myTouchListener(motionEvent, "01 94");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_6_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_6_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_6:
                    myTouchListener(motionEvent, "01 95");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_7_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_7_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_7:
                    myTouchListener(motionEvent, "01 96");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_8_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_8_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_8:
                    myTouchListener(motionEvent, "01 97");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_9_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_9_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_9:
                    myTouchListener(motionEvent, "01 98");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_10_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_10_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_10:
                    myTouchListener(motionEvent, "01 99");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_11_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_11_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_11:
                    myTouchListener(motionEvent, "01 9B");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_12_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_12_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_12:
                    myTouchListener(motionEvent, "01 9A");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_13_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_13_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_13:
                    myTouchListener(motionEvent, "01 9D");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_14_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_14_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView31_14:
                    myTouchListener(motionEvent, "01 9C");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_15_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_15_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView32:
                    myTouchListener(motionEvent, "00 17");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_16_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_16_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView33:
                    myTouchListener(motionEvent, "00 18");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_17_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_17_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView34:
                    myTouchListener(motionEvent, "00 19");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_18_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_18_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView35:
                    myTouchListener(motionEvent, "00 1A");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_19_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_19_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36:
                    myTouchListener(motionEvent, "01 B0");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_20_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_20_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_1:
                    myTouchListener(motionEvent, "01 A0");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_21_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_21_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_2:
                    myTouchListener(motionEvent, "01 A1");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_22_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_22_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_3:
                    myTouchListener(motionEvent, "01 A2");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_23_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_23_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_4:
                    myTouchListener(motionEvent, "01 A3");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_24_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_24_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_5:
                    myTouchListener(motionEvent, "01 A4");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_25_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_25_DOWN).sendToTarget();
                    }

                    break;
                case R.id.imageView36_6:
                    myTouchListener(motionEvent, "01 A5");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_26_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_26_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_7:
                    myTouchListener(motionEvent, "01 A6");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_27_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_27_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_8:
                    myTouchListener(motionEvent, "01 A7");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_28_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_28_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_9:
                    myTouchListener(motionEvent, "01 A8");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_29_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_29_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_10:
                    myTouchListener(motionEvent, "01 AA");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_30_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_30_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView36_11:
                    myTouchListener(motionEvent, "01 A9");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_31_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_31_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView37:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mMainActivity.finish();
                        mHandler.obtainMessage(MyData.MSG_BUTTON3_32_UP).sendToTarget();
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                        mHandler.obtainMessage(MyData.MSG_BUTTON3_32_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView310:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mMainActivity.changeFrag(0);
                    }
                    break;
                case R.id.imageView311:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mMainActivity.changeFrag(1);
                    }
                    break;
            }
        return false;
    }

    @Override
    public void onStart() {   //可见
        super.onStart();
        mExecutorService= Executors.newFixedThreadPool(3);
    }

    @Override
    public void onStop() {     //不可见
        super.onStop();
        mExecutorService.shutdown();
      //  mMainActivity.finish();
    }
}
