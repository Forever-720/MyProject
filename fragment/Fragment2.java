package com.example.asus.app_723_9002.fragment;

import android.annotation.SuppressLint;
import android.app.slice.SliceItem;
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
public class Fragment2 extends Fragment implements View.OnClickListener,View.OnTouchListener{
    private MainActivity mMainActivity;
    private Context mContext;
    private ImageView[] mImgTag;
    private ExecutorService mExecutorService;
    private boolean is_Visible=false;
    private boolean  mShowDialog = false;
    private  View view;
    private FrameLayout frameLayout;




    public Fragment2 (MainActivity mainActivity){mMainActivity=mainActivity;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        int orientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR;
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screen_Wight  = dm.widthPixels;
//        int screen_Height = dm.heightPixels;
//        if (screen_Wight>screen_Height) {
//            Log.d("Fragment2","screen_wight>screen_height,为横屏");
//            view=inflater.inflate(R.layout.fragment2,container,false);//设置横屏
//            initView(view);
//            return view;
//        }
//        if (screen_Wight<screen_Height){
//            Log.d("Fragment2","screen_wight<screen_height,为竖屏");
//            view= inflater.inflate(R.layout.port_fragment2,container,false); //设置竖屏
//            initView(view);
//            return view;
//        }
       frameLayout=new FrameLayout(getActivity());
       int mCurrentOrientation = getResources().getConfiguration().orientation;
       LayoutInflater _inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){
           view=_inflater.inflate(R.layout.port_fragment2,null);
           initView(view);
           frameLayout.addView(view);
           return frameLayout;
       }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){
           view = _inflater.inflate(R.layout.fragment2,null);
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
            view = inflater.inflate(R.layout.port_fragment2,null);
            frameLayout.addView(view);
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            frameLayout.removeAllViews();
            view = inflater.inflate(R.layout.fragment2,null);
            frameLayout.addView(view);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void initView(View view){
        mImgTag=new ImageView[14];
        mImgTag[0]=(ImageView)view.findViewById(R.id.imageView20);
        mImgTag[1]=(ImageView)view.findViewById(R.id.imageView21);
        mImgTag[2]=(ImageView)view.findViewById(R.id.imageView22);
        mImgTag[3]=(ImageView)view.findViewById(R.id.imageView23);
        mImgTag[4]=(ImageView)view.findViewById(R.id.imageView24);
        mImgTag[5]=(ImageView)view.findViewById(R.id.imageView25);
        mImgTag[6]=(ImageView)view.findViewById(R.id.imageView26);
        mImgTag[7]=(ImageView)view.findViewById(R.id.imageView27);
        mImgTag[8]=(ImageView)view.findViewById(R.id.imageView28);
        mImgTag[9]=(ImageView)view.findViewById(R.id.imageView29);
        mImgTag[10]=(ImageView)view.findViewById(R.id.imageView221);
        mImgTag[11]=(ImageView)view.findViewById(R.id.imageView210);
        mImgTag[12]=(ImageView)view.findViewById(R.id.imageView211);
        mImgTag[13]=(ImageView)view.findViewById(R.id.imageView212);

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

        mExecutorService= Executors.newFixedThreadPool(3);

    }
    private  int mImgSrc[][]={
            {R.drawable.page2_1_1,R.drawable.page2_2_1,R.drawable.page2_3_1,R.drawable.page2_4_1,R.drawable.page2_5_1,R.drawable.page2_6_1,R.drawable.page2_7_1,R.drawable.page2_8_1,R.drawable.page2_9_1,R.drawable.page2_10_1,R.drawable.page0_1_1},
            {R.drawable.page2_1_2,R.drawable.page2_2_2,R.drawable.page2_3_2,R.drawable.page2_4_2,R.drawable.page2_5_2,R.drawable.page2_6_2,R.drawable.page2_7_2,R.drawable.page2_8_2,R.drawable.page2_9_2,R.drawable.page2_10_2,R.drawable.page0_1_2}
    };

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(is_Visible == true)
                switch (msg.what){
                    case MyData.MSG_BUTTON2_1_DOWN:
                        mImgTag[0].setImageResource(mImgSrc[1][0]);
                        break;
                    case MyData.MSG_BUTTON2_1_UP:
                        mImgTag[0].setImageResource(mImgSrc[0][0]);
                        break;
                    case MyData.MSG_BUTTON2_2_DOWN:
                        mImgTag[1].setImageResource(mImgSrc[1][1]);
                        break;
                    case MyData.MSG_BUTTON2_2_UP:
                        mImgTag[1].setImageResource(mImgSrc[0][1]);
                        break;
                    case MyData.MSG_BUTTON2_3_DOWN:
                        mImgTag[2].setImageResource(mImgSrc[1][2]);
                        break;
                    case MyData.MSG_BUTTON2_3_UP:
                        mImgTag[2].setImageResource(mImgSrc[0][2]);
                        break;
                    case MyData.MSG_BUTTON2_4_DOWN:
                        mImgTag[3].setImageResource(mImgSrc[1][3]);
                        break;
                    case MyData.MSG_BUTTON2_4_UP:
                        mImgTag[3].setImageResource(mImgSrc[0][3]);
                        break;
                    case MyData.MSG_BUTTON2_5_DOWN:
                        mImgTag[4].setImageResource(mImgSrc[1][4]);
                        break;
                    case MyData.MSG_BUTTON2_5_UP:
                        mImgTag[4].setImageResource(mImgSrc[0][4]);
                        break;
                    case MyData.MSG_BUTTON2_6_DOWN:
                        mImgTag[5].setImageResource(mImgSrc[1][5]);
                        break;
                    case MyData.MSG_BUTTON2_6_UP:
                        mImgTag[5].setImageResource(mImgSrc[0][5]);
                        break;
                    case MyData.MSG_BUTTON2_7_DOWN:
                        mImgTag[6].setImageResource(mImgSrc[1][6]);
                        break;
                    case MyData.MSG_BUTTON2_7_UP:
                        mImgTag[6].setImageResource(mImgSrc[0][6]);
                        break;
                    case MyData.MSG_BUTTON2_8_DOWN:
                        mImgTag[7].setImageResource(mImgSrc[1][7]);
                        break;
                    case MyData.MSG_BUTTON2_8_UP:
                        mImgTag[7].setImageResource(mImgSrc[0][7]);
                        break;
                    case MyData.MSG_BUTTON2_9_DOWN:
                        mImgTag[8].setImageResource(mImgSrc[1][8]);
                        break;
                    case MyData.MSG_BUTTON2_9_UP:
                        mImgTag[8].setImageResource(mImgSrc[0][8]);
                        break;
                    case MyData.MSG_BUTTON2_10_DOWN:
                        mImgTag[9].setImageResource(mImgSrc[1][9]);
                        break;
                    case MyData.MSG_BUTTON2_10_UP:
                        mImgTag[9].setImageResource(mImgSrc[0][9]);
                        break;
                    case MyData.MSG_BUTTON2_11_DOWN:
                        mImgTag[10].setImageResource(mImgSrc[1][10]);
                        break;
                    case MyData.MSG_BUTTON2_11_UP:
                        mImgTag[10].setImageResource(mImgSrc[0][10]);
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

    private void showDialog(String title) {
            final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(title).setConfirmText("确定");
            pDialog.show();
            pDialog.setCancelable(false);
            mMainActivity.setQuanping();   //调用全屏


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
        }else{
            is_Visible=false;
        }
    }

    private  void UPdataICON()
    {
        if( MyData.MyBool[0x000B])
        { mHandler.obtainMessage(MyData.MSG_BUTTON2_9_DOWN).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON2_9_UP).sendToTarget();
        }
        if( MyData.MyBool[0x000C])
        { mHandler.obtainMessage(MyData.MSG_BUTTON2_10_DOWN).sendToTarget();
        }else
        { mHandler.obtainMessage(MyData.MSG_BUTTON2_10_UP).sendToTarget();
        }
    }
    @Override
    public void onClick(View v) {

    }
    private void myTouchListener(MotionEvent motionEvent,String addr){
        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            mShowDialog = false;
            startServer("A2 78" + addr);
            if(BuildConfig.DEBUG) Log.d("Fragment2","手指抬起");
        }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            mShowDialog = true;
            startServer("A2 79" + addr);
            if(BuildConfig.DEBUG) Log.d("Fragment2","手指按下");
        }
    }
    private void startServer(final String item){
        mExecutorService.submit(
                new Thread(){
                    @Override
                    public void run() {
                        if(BuildConfig.DEBUG)Log.d("Fragment2","开始请求，发送："+item);
                        Socket ss=null;
                        try {
                            ss=new Socket();
                            SocketAddress socAddress = new InetSocketAddress("10.10.100.254", 8899);
                            ss.connect(socAddress, 500);
                            try {
                                OutputStream outputStream=ss.getOutputStream();
                                outputStream.write(hex2byte(item));
                                outputStream.write('\n');
                                outputStream.flush();
                                ss.close();
                                if(BuildConfig.DEBUG) Log.d("Send",item);
                            }catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                ss.close();
                            }
                        }catch (SocketTimeoutException e){

                                mHandler.obtainMessage(10001).sendToTarget();
                                return;

                        }catch (IOException e){
                            e.printStackTrace();
                            if(mShowDialog == true){
                                mHandler.obtainMessage(10002).sendToTarget();
                            }
                            return;
                        }
                    }

                });
    }
    public static byte[] hex2byte(String hex){
        String digital="0123456789ABCDEF";
        String hex1=hex.replace(" ","");
        char[] hex2char = hex1.toCharArray();
        byte[] bytes = new byte[hex1.length() / 2];
        byte temp;
        for (int p = 0; p < bytes.length; p++) {
            temp = (byte) (digital.indexOf(hex2char[2 * p]) * 16);
            temp += digital.indexOf(hex2char[2 * p + 1]);
            bytes[p] = (byte) (temp & 0xff);
        }
        return bytes;
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if(is_Visible){
            switch (v.getId()){
                case R.id.imageView20:
                    myTouchListener(motionEvent,"00 1B");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_1_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_1_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView21:
                    myTouchListener(motionEvent,"00 1C");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_2_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_2_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView22:
                    myTouchListener(motionEvent,"00 1D");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_3_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_3_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView23:
                    myTouchListener(motionEvent,"00 1E");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_4_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_4_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView24:
                    myTouchListener(motionEvent,"00 13");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_5_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_5_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView25:
                    myTouchListener(motionEvent,"00 14");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_6_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_6_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView26:
                    myTouchListener(motionEvent,"00 15");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_7_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_7_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView27:
                    myTouchListener(motionEvent,"00 16");
                    if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_8_UP).sendToTarget();
                    }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_8_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView28:
                    myTouchListener(motionEvent, "00 0B");
                    break;
                case R.id.imageView29:
                    myTouchListener(motionEvent,"00 0C");
                    break;
                case R.id.imageView221:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mMainActivity.finish();
                        mHandler.obtainMessage(MyData.MSG_BUTTON2_11_UP).sendToTarget();
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                        mHandler.obtainMessage(MyData.MSG_BUTTON2_11_DOWN).sendToTarget();
                    }
                    break;
                case R.id.imageView210:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mMainActivity.changeFrag(0);
                    }
                    break;
                case R.id.imageView212:
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mMainActivity.changeFrag(2);
                    }
                    break;
                default:
                    break;

            }
        }
        return false;
    }

    @Override
    public void onStart() {   //可见
        super.onStart();
        mExecutorService=Executors.newFixedThreadPool(3);
    }

    @Override
    public void onStop() {     //不可见
        super.onStop();
        mExecutorService.shutdown();
      //  mMainActivity.finish();
    }


}
