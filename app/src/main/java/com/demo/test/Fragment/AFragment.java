package com.demo.test.Fragment;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.test.Activity.R;
import com.demo.test.util.BadgeUtil;
import com.demo.test.util.SHA1;
import com.lidroid.xutils.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AFragment extends Fragment {

    View mView;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_a, container, false);

        init();

        return mView;
    }

    private void init() {

        initView();
        initDate();
    }

    private void initView() {

        mView.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick", "start run");
                BadgeUtil.setBadgeCount(getContext(), 10, R.mipmap.ic_launcher);
            }
        });

        mView.findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick", "stop run");
                BadgeUtil.resetBadgeCount(getContext(), R.mipmap.ic_launcher);
            }
        });

        mView.findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick", "get");
                getDataFromService();
            }
        });

    }

    private void initDate() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        mRequestQueue = getRequestQueue();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getContext());
            mRequestQueue.getCache().clear();
        }

        return mRequestQueue;
    }

    private void getDataFromService() {
        LogUtils.i("AFragment: " + "getDataFromService");
        String url = "http://app.aobosh.cn/api/index/banner";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (!"".equals(s)) {
                    LogUtils.i("AFragment: result1 " + s);

                    try {
                        JSONObject resultJson = new JSONObject(s);
                        String resultcode = resultJson.getString("code");

                        if ("200".equals(resultcode)) {
                            //                            mHandler.sendEmptyMessage(LOAD_DATA1_SUCCESS);
                            return;
                        }

                        //                        mHandler.sendEmptyMessage(LOAD_DATA1_FAILE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtils.e("AFragment: volleyError1 " + volleyError.toString());
//                mHandler.sendEmptyMessage(NETWORK_ERROR);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                JSONObject obj = new JSONObject();
                JSONObject json = new JSONObject();

                try {


                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String time = df.format(new Date());

                    String token = "Indexbanner" + time + "5QmXczkdfVTd";
                    LogUtils.i("AFragment: token " + token);
                    String sha_token = SHA1.encryptToSHA(token);

                    obj.put("access_token", sha_token);
                    obj.put("device", "android");

//                    json.put("dt", obj.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LogUtils.i("AFragment json1 " + json.toString());

                map.put("dt", obj.toString());
                return map;
            }

        };
        mRequestQueue.add(stringRequest);
    }


}
