package com.example.asus.app_723_9002.fragment;

import android.annotation.SuppressLint;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
public class Fragment1 extends Fragment implements View.OnTouchListener,View.OnClickListener{
    private String TAG="Fragment1";
    private MainActivity mMainActivity;
    private Context mContext;
    private ImageView[] mImgTag;
    private ExecutorService mExecutorService;
    private boolean is_Visible=false;
    private boolean  mShowDialog = false;
    private   View view;
    private FrameLayout frameLayout;


    public Fragment1 (MainActivity mainActivity){mMainActivity=mainActivity;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        int orientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR;
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screen_Wight  = dm.widthPixels;
//        int screen_Height = dm.heightPixels;
//        if (screen_Wight>screen_Height) {
//            Log.d("Fragment1","screen_wight>screen_height,为横屏");
//            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ;
//            view=inflater.inflate(R.layout.fragment1,container,false);//设置横屏
//            initView(view);
//            return view;
//        }
//        if (screen_Wight<screen_Height){
//            Log.d("Fragment1","screen_wight<screen_height,为竖屏");
//            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//            view= inflater.inflate(R.layout.port_fragment1,container,false); //设置竖屏
//            initView(view);
//            return view;
//        }
        frameLayout=new FrameLayout(getActivity());
        LayoutInflater _inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){
          //  frameLayout.removeAllViews();
            view=_inflater.inflate(R.layout.port_fragment1,null);
            initView(view);
            frameLayout.addView(view);
            return frameLayout;
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            view=inflater.inflate(R.layout.fragment1,null);
            frameLayout.addView(view);
            initView(view);
            return frameLayout;
        }
        return null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if(mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT){
            frameLayout.removeAllViews();
            view = inflater.inflate(R.layout.port_fragment1,null);
            frameLayout.addView(view);
        }else if(mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            frameLayout.removeAllViews();
            view=inflater.inflate(R.layout.fragment1,null);
            frameLayout.addView(view);
        }
    }

    public void initView(View view){
                mImgTag=new ImageView[9];
                mImgTag[0]=(ImageView)view.findViewById(R.id.imageView11);
                mImgTag[1]=(ImageView)view.findViewById(R.id.imageView12);
                mImgTag[2]=(ImageView)view.findViewById(R.id.imageView13);
                mImgTag[3]=(ImageView)view.findViewById(R.id.imageView14);
                mImgTag[4]=(ImageView)view.findViewById(R.id.imageView15);
                mImgTag[5]=(ImageView)view.findViewById(R.id.imageView16);
                mImgTag[6]=(ImageView)view.findViewById(R.id.imageView110);
                mImgTag[7]=(ImageView)view.findViewById(R.id.imageView111);
                mImgTag[8]=(ImageView)view.findViewById(R.id.imageView112);

                mImgTag[0].setOnClickListener(this);
                mImgTag[1].setOnClickListener(this);
                mImgTag[2].setOnClickListener(this);
                mImgTag[3].setOnClickListener(this);
                mImgTag[4].setOnClickListener(this);
                mImgTag[5].setOnClickListener(this);
                mImgTag[6].setOnClickListener(this);
                mImgTag[7].setOnClickListener(this);
                mImgTag[8].setOnClickListener(this);

                mImgTag[0].setOnTouchListener(this);
                mImgTag[1].setOnTouchListener(this);
                mImgTag[2].setOnTouchListener(this);
                mImgTag[3].setOnTouchListener(this);
                mImgTag[4].setOnTouchListener(this);
                mImgTag[5].setOnTouchListener(this);
                mImgTag[6].setOnTouchListener(this);
                mImgTag[7].setOnTouchListener(this);
                mImgTag[8].setOnTouchListener(this);

                mExecutorService= Executors.newFixedThreadPool(3);


            }


            private int mImgSrc[][]={
                    {R.drawable.page1_1_1, R.drawable.page1_2_1,R.drawable.page1_3_1,R.drawable.page1_4_1,R.drawable.page1_5_1,R.drawable.page0_1_1},
                    {R.drawable.page1_1_2,R.drawable.page1_2_2,R.drawable.page1_3_2,R.drawable.page1_4_2,R.drawable.page1_5_2,R.drawable.page0_1_2}
            };
            private Handler mHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(is_Visible == true)
                        switch (msg.what){
                            case MyData.MSG_BUTTON1_1_DOWN:
                                mImgTag[0].setImageResource(mImgSrc[1][0]);
                                break;
                            case MyData.MSG_BUTTON1_1_UP:
                                mImgTag[0].setImageResource(mImgSrc[0][0]);
                                break;
                            case MyData.MSG_BUTTON1_2_DOWN:
                                mImgTag[1].setImageResource(mImgSrc[1][1]);
                                break;
                            case MyData.MSG_BUTTON1_2_UP:
                                mImgTag[1].setImageResource(mImgSrc[0][1]);
                                break;
                            case MyData.MSG_BUTTON1_3_DOWN:
                                mImgTag[2].setImageResource(mImgSrc[1][2]);
                                break;
                            case MyData.MSG_BUTTON1_3_UP:
                                mImgTag[2].setImageResource(mImgSrc[0][2]);
                                break;
                            case MyData.MSG_BUTTON1_4_DOWN:
                                mImgTag[3].setImageResource(mImgSrc[1][3]);
                                break;
                            case MyData.MSG_BUTTON1_4_UP:
                                mImgTag[3].setImageResource(mImgSrc[0][3]);
                                break;
                            case MyData.MSG_BUTTON1_5_DOWN:
                                mImgTag[4].setImageResource(mImgSrc[1][4]);
                                break;
                            case MyData.MSG_BUTTON1_5_UP:
                                mImgTag[4].setImageResource(mImgSrc[0][4]);
                                break;
                            case MyData.MSG_BUTTON1_6_DOWN:
                                mImgTag[5].setImageResource(mImgSrc[1][5]);
                                break;
                            case MyData.MSG_BUTTON1_6_UP:
                                mImgTag[5].setImageResource(mImgSrc[0][5]);
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
            public void onAttach(Context context) {
                super.onAttach(context);
                mContext=context;
            }

            private void showDialog(String title) {

                final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(title).setConfirmText("确定");
                pDialog.show();
                pDialog.setCancelable(false);
                mMainActivity.setQuanping();   //调用全屏


            }

            @Override
            public void setUserVisibleHint(boolean isVisibleToUser) {
                super.setUserVisibleHint(isVisibleToUser);
                if(isVisibleToUser){
                    if(mContext != null)
                        mMainActivity=(MainActivity)mContext;
                    mMainActivity.setmHandler(mHandler);
                    is_Visible=true;
                    mMainActivity.setQuanping();

                    UPdataICON();
                }else {
                    is_Visible=false;
                }


            }
            private void myTouchListener(MotionEvent motionEvent,String addr){
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    mShowDialog = false;
                    startServer("A2 78"+addr);
                    if(BuildConfig.DEBUG) Log.d("Fragment1","手指抬起");
                }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mShowDialog = true;
                    startServer("A2 79"+addr);
                    if(BuildConfig.DEBUG) Log.d("Fragment1","手指按下");
                }
            }
            private void startServer(final String item){
                mExecutorService.submit(new Thread(){
                    @Override
                    public void run() {
                        if(BuildConfig.DEBUG) Log.d("Fragment1","开始请求，发送："+item);
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
            private  void UPdataICON()
            {
                if( MyData.MyBool[0x0001])
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_1_DOWN).sendToTarget();
                }else
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_1_UP).sendToTarget();
                }
                if( MyData.MyBool[0x0003])
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_2_DOWN).sendToTarget();
                }else
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_2_UP).sendToTarget();
                }
                if( MyData.MyBool[0x0005])
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_3_DOWN).sendToTarget();
                }else
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_3_UP).sendToTarget();
                }
                if( MyData.MyBool[0x0007])
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_5_DOWN).sendToTarget();
                }else
                { mHandler.obtainMessage(MyData.MSG_BUTTON1_5_UP).sendToTarget();
                }
            }

            @Override
            public void onClick(View v) {


            }



            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (v.getId()){
                    case R.id.imageView11:
                        myTouchListener(motionEvent,"00 01");
                        break;
                    case R.id.imageView12:
                        myTouchListener(motionEvent,"00 03");
                        break;
                    case R.id.imageView13:
                        myTouchListener(motionEvent,"00 05");
                        break;
                    case R.id.imageView14:
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            mHandler.obtainMessage(MyData.MSG_BUTTON1_4_UP).sendToTarget();
                            mMainActivity.changeFrag(3);
                        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            mHandler.obtainMessage(MyData.MSG_BUTTON1_4_DOWN).sendToTarget();
                        }
                        break;
                    case R.id.imageView15:
                        myTouchListener(motionEvent, "00 07");
                        break;
                    case R.id.imageView16:
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            mHandler.obtainMessage(MyData.MSG_BUTTON1_6_UP).sendToTarget();
                        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            mHandler.obtainMessage(MyData.MSG_BUTTON1_6_DOWN).sendToTarget();
                            mMainActivity.finish();
                        }
                        break;
                    case R.id.imageView111:
                        if(motionEvent.getAction()==MotionEvent.ACTION_UP){

                        }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                            mMainActivity.changeFrag(1);
                        }break;
                    case R.id.imageView112:
                        if(motionEvent.getAction()==MotionEvent.ACTION_UP){

                        }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                            mMainActivity.changeFrag(2);
                        }break;

                }

                return false;
            }

            @Override
            public void onStart() {
                super.onStart();
                mExecutorService=Executors.newFixedThreadPool(3);
            }

            @Override
            public void onStop() {
                super.onStop();
                mExecutorService.shutdown();
            }




    }
