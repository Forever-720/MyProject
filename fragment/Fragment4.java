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
import android.widget.SeekBar;

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
public class Fragment4  extends Fragment implements View.OnClickListener,View.OnTouchListener,SeekBar.OnSeekBarChangeListener{
    private SeekBar mSB1;
    private SeekBar mSB2;
    private MainActivity mMainActivity;
    private Context mContext;
    private ImageView[] mImgTag;
    private ExecutorService mExecutorService;
    private boolean is_Visible=false;
    private boolean  mShowDialog = false;
    private boolean HS =false;
    private   View view;
    private FrameLayout frameLayout;

    public Fragment4 (MainActivity mainActivity){mMainActivity=mainActivity;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frameLayout=new FrameLayout(getActivity());
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        LayoutInflater _inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){    //竖屏
            view = _inflater.inflate(R.layout.port_fragment4,null);
            initView(view);
            frameLayout.addView(view);
            return frameLayout;
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){  //横屏
            view = _inflater.inflate(R.layout.fragment4,null);
            initView(view);
            frameLayout.addView(view);
            return frameLayout;
        }

        return null;
    }
        @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){
            frameLayout.removeAllViews();
            view = inflater.inflate(R.layout.port_fragment4,null);
            frameLayout.addView(view);
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            frameLayout.removeAllViews();
            view = inflater.inflate(R.layout.fragment4,null);
            frameLayout.addView(view);
        }
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
//            Log.d("Fragment4","screen_wight>screen_height,为横屏");
//            view = inflater.inflate(R.layout.fragment4,container,false);
//            HS = false;
//            initView(view);
//            ((MainActivity)mContext).setQuanping();
//            return view;
//        }
//        //竖屏
//        if( ScreenOrient(((MainActivity)mContext))==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  )
//        {
//            Log.d("Fragment4","screen_wight<screen_height,为竖屏");
//            view = inflater.inflate(R.layout.port_fragment4,container,false);
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

    private void initView(View view) {
        mImgTag=new ImageView[13];
        mImgTag[0]=(ImageView)view.findViewById(R.id.IV_up_41);
        mImgTag[1]=(ImageView)view.findViewById(R.id.IV_up_42);
        mImgTag[2]=(ImageView)view.findViewById(R.id.IV_up_43);
        mImgTag[3]=(ImageView)view.findViewById(R.id.IV_up_44);
        mImgTag[4]=(ImageView)view.findViewById(R.id.IV_up_45);

        mImgTag[5]=(ImageView)view.findViewById(R.id.IV_down_41);
        mImgTag[6]=(ImageView)view.findViewById(R.id.IV_down_42);
        mImgTag[7]=(ImageView)view.findViewById(R.id.IV_down_43);
        mImgTag[8]=(ImageView)view.findViewById(R.id.IV_down_44);
        mImgTag[9]=(ImageView)view.findViewById(R.id.IV_down_45);

        mImgTag[10]=(ImageView)view.findViewById(R.id.imageView410);
        mImgTag[11]=(ImageView)view.findViewById(R.id.IV_up_40);
        mImgTag[12]=(ImageView)view.findViewById(R.id.IV_down_40);

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

        mSB1 = (SeekBar) view.findViewById(R.id.seekBar1);
        mSB1.setOnSeekBarChangeListener(this);

        mSB2 = (SeekBar) view.findViewById(R.id.seekBar2);
        mSB2.setOnSeekBarChangeListener(this);

        mExecutorService= Executors.newFixedThreadPool(3);

    }
    private int mImgSrc[][]={
            {R.drawable.pu4_1_1,R.drawable.pu4_2_1,R.drawable.pu4_3_1,R.drawable.pu4_4_1,R.drawable.pu4_5_1,
             R.drawable.pd4_1_1,R.drawable.pd4_2_1,R.drawable.pd4_3_1,R.drawable.pd4_4_1,R.drawable.pd4_5_1,
             R.drawable.page0_1_1,R.drawable.pu4_6_0,R.drawable.pd4_6_0},
            {R.drawable.pu4_1_2,R.drawable.pu4_2_2,R.drawable.pu4_3_2,R.drawable.pu4_4_2,R.drawable.pu4_5_2,
             R.drawable.pd4_1_2,R.drawable.pd4_2_2,R.drawable.pd4_3_2,R.drawable.pd4_4_2,R.drawable.pd4_5_2, R.drawable.page0_1_2,
             R.drawable.pu4_6_1,R.drawable.pu4_6_2,R.drawable.pu4_6_3,R.drawable.pu4_6_4,R.drawable.pu4_6_5,R.drawable.pd4_6_1,
             R.drawable.pd4_6_2,R.drawable.pd4_6_3,R.drawable.pd4_6_4,R.drawable.pd4_6_5}

    };
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(is_Visible ==true)
                switch (msg.what){
                    case MyData.MSG_BUTTON4_1_DOWN:
                        mImgTag[0].setImageResource(mImgSrc[1][0]);
                        break;
                    case MyData.MSG_BUTTON4_1_UP:
                        mImgTag[0].setImageResource(mImgSrc[0][0]);
                        break;
                    case MyData.MSG_BUTTON4_2_DOWN:
                        mImgTag[1].setImageResource(mImgSrc[1][1]);
                        break;
                    case MyData.MSG_BUTTON4_2_UP:
                        mImgTag[1].setImageResource(mImgSrc[0][1]);
                        break;
                    case MyData.MSG_BUTTON4_3_DOWN:
                        mImgTag[2].setImageResource(mImgSrc[1][2]);
                        break;
                    case MyData.MSG_BUTTON4_3_UP:
                        mImgTag[2].setImageResource(mImgSrc[0][2]);
                        break;
                    case MyData.MSG_BUTTON4_4_DOWN:
                        mImgTag[3].setImageResource(mImgSrc[1][3]);
                        break;
                    case MyData.MSG_BUTTON4_4_UP:
                        mImgTag[3].setImageResource(mImgSrc[0][3]);
                        break;
                    case MyData.MSG_BUTTON4_5_DOWN:
                        mImgTag[4].setImageResource(mImgSrc[1][4]);
                        break;
                    case MyData.MSG_BUTTON4_5_UP:
                        mImgTag[4].setImageResource(mImgSrc[0][4]);
                        break;
                    case MyData.MSG_BUTTON4_6_DOWN:
                        mImgTag[5].setImageResource(mImgSrc[1][5]);
                        break;
                    case MyData.MSG_BUTTON4_6_UP:
                        mImgTag[5].setImageResource(mImgSrc[0][5]);
                        break;
                    case MyData.MSG_BUTTON4_7_DOWN:
                        mImgTag[6].setImageResource(mImgSrc[1][6]);
                        break;
                    case MyData.MSG_BUTTON4_7_UP:
                        mImgTag[6].setImageResource(mImgSrc[0][6]);
                        break;
                    case MyData.MSG_BUTTON4_8_DOWN:
                        mImgTag[7].setImageResource(mImgSrc[1][7]);
                        break;
                    case MyData.MSG_BUTTON4_8_UP:
                        mImgTag[7].setImageResource(mImgSrc[0][7]);
                        break;
                    case MyData.MSG_BUTTON4_9_DOWN:
                        mImgTag[8].setImageResource(mImgSrc[1][8]);
                        break;
                    case MyData.MSG_BUTTON4_9_UP:
                        mImgTag[8].setImageResource(mImgSrc[0][8]);
                        break;
                    case MyData.MSG_BUTTON4_10_DOWN:
                        mImgTag[9].setImageResource(mImgSrc[1][9]);
                        break;
                    case MyData.MSG_BUTTON4_10_UP:
                        mImgTag[9].setImageResource(mImgSrc[0][9]);
                        break;
                    case MyData.MSG_BUTTON4_11_DOWN:
                        mImgTag[10].setImageResource(mImgSrc[1][10]);
                        break;
                    case MyData.MSG_BUTTON4_11_UP:
                        mImgTag[10].setImageResource(mImgSrc[0][10]);
                        break;
                    case MyData.MSG_BUTTON4_12_0:
                        mImgTag[11].setImageResource(mImgSrc[0][11]);
                        break;
                    case MyData.MSG_BUTTON4_12_1:
                        mImgTag[11].setImageResource(mImgSrc[1][11]);
                        break;
                    case MyData.MSG_BUTTON4_12_2:
                        mImgTag[11].setImageResource(mImgSrc[1][12]);
                        break;
                    case MyData.MSG_BUTTON4_12_3:
                        mImgTag[11].setImageResource(mImgSrc[1][13]);
                        break;
                    case MyData.MSG_BUTTON4_12_4:
                        mImgTag[11].setImageResource(mImgSrc[1][14]);
                        break;
                    case MyData.MSG_BUTTON4_12_5:
                        mImgTag[11].setImageResource(mImgSrc[1][15]);
                        break;
                    case MyData.MSG_BUTTON4_13_0:
                        mImgTag[12].setImageResource(mImgSrc[0][12]);
                        break;
                    case MyData.MSG_BUTTON4_13_1:
                        mImgTag[12].setImageResource(mImgSrc[1][16]);
                        break;
                    case MyData.MSG_BUTTON4_13_2:
                        mImgTag[12].setImageResource(mImgSrc[1][17]);
                        break;
                    case MyData.MSG_BUTTON4_13_3:
                        mImgTag[12].setImageResource(mImgSrc[1][18]);
                        break;
                    case MyData.MSG_BUTTON4_13_4:
                        mImgTag[12].setImageResource(mImgSrc[1][19]);
                        break;
                    case MyData.MSG_BUTTON4_13_5:
                        mImgTag[12].setImageResource(mImgSrc[1][20]);
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
    private  void UPdataICON()
    {
        if( MyData.MyBool[0x0130])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_1_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_12_1).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_1_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0136])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_2_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_12_2).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_2_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0133])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_3_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_12_3).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_3_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0134])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_4_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_12_4).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_4_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0135])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_5_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_12_5).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_5_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0230])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_6_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_13_1).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_6_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0236])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_7_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_13_2).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_7_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0233])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_8_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_13_3).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_8_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0234])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_9_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_13_4).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_9_UP).sendToTarget();
        }
        if( MyData.MyBool[0x0235])
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_10_DOWN).sendToTarget();
            mHandler.obtainMessage(MyData.MSG_BUTTON4_13_5).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON4_10_UP).sendToTarget();
        }
    }

    private void showDialog(String title) {
        mMainActivity.setQuanping();   //调用全屏
        if(mShowDialog = true){
            final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(title).setConfirmText("确定");
            pDialog.show();
            pDialog.setCancelable(false);

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
            UPdataICON();
        }else {
            is_Visible=false;
        }

    }

    private void myTouchListener(MotionEvent motionEvent,String addr){

        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            mShowDialog = false;
            startServer("A2 78"+addr);
            if(BuildConfig.DEBUG) Log.d("Fragment4","手指抬起");
        }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            mShowDialog = true;
            mMainActivity.setQuanping();   //调用全屏
            startServer("A2 79"+addr);
            if(BuildConfig.DEBUG) Log.d("Fragment4","手指按下");
        }
    }
    private void startServer(final String item){
        mExecutorService.submit(new Thread(){
            @Override
            public void run() {
                if(BuildConfig.DEBUG) Log.d("Fragment4","开始请求，发送："+item);
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
                }catch (IOException e){
                    e.printStackTrace();
                    if(mShowDialog == true) {
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
                case R.id.IV_up_41:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_12_1).sendToTarget();
                        myTouchListener(motionEvent, "01 30");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "01 30");
                    }
                    break;
                case R.id.IV_up_42:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_12_2).sendToTarget();
                        myTouchListener(motionEvent, "01 36");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "01 36");
                    }
                    break;
                case R.id.IV_up_43:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_12_3).sendToTarget();
                        myTouchListener(motionEvent, "01 33");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "01 33");
                    }
                    break;
                case R.id.IV_up_44:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_12_4).sendToTarget();
                        myTouchListener(motionEvent, "01 34");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "01 34");
                    }
                    break;
                case R.id.IV_up_45:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_12_5).sendToTarget();
                        myTouchListener(motionEvent, "01 35");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "01 35");
                    }
                    break;
                case R.id.IV_down_41:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_13_1).sendToTarget();
                        myTouchListener(motionEvent, "02 30");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "02 30");
                    }
                    break;
                case R.id.IV_down_42:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_13_2).sendToTarget();
                        myTouchListener(motionEvent, "02 36");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "02 36");
                    }
                    break;
                case R.id.IV_down_43:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_13_3).sendToTarget();
                        myTouchListener(motionEvent, "02 33");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "02 33");
                    }
                    break;
                case R.id.IV_down_44:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_13_4).sendToTarget();
                        myTouchListener(motionEvent, "02 34");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "02 34");
                    }
                    break;
                case R.id.IV_down_45:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_13_5).sendToTarget();
                        myTouchListener(motionEvent, "02 35");
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        myTouchListener(motionEvent, "02 35");
                    }
                    break;
                case R.id.imageView410:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mMainActivity.changeFrag(0);
                    }
                    break;
                case R.id.IV_up_40:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_12_0).sendToTarget();
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    }
                    break;
                case R.id.IV_down_40:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mHandler.obtainMessage(MyData.MSG_BUTTON4_13_0).sendToTarget();
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    }
                    break;
                default:
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
     //   mMainActivity.finish();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int aaa;
        String site;
        switch (seekBar.getId()) {
            case R.id.seekBar1:
                site = Integer.toHexString(mSB1.getProgress());
                site= site.toUpperCase();
                if (mSB1.getProgress() < 16)
                    startServer("A2 78 01 32 0" + site + " 00");
                else
                    startServer("A2 78 01 32 " + site + " 00");

                break;

            case R.id.seekBar2:
                site = Integer.toHexString(mSB2.getProgress());
                site= site.toUpperCase();
                if (mSB2.getProgress() < 16)
                    startServer("A2 78 01 32 0" + site + " 00");
                else
                    startServer("A2 78 01 32 " + site + " 00");

                break;


        }
        aaa=mSB1.getProgress();

        aaa=0;

    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
