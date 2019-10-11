package com.demo.test.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.test.R;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class BFragment extends Fragment {

    private PullToRefreshListView listview;
    private ArrayList<String> mList;
    private ArrayList<String> list;
    private MyAdapter adapter;
    private int page = 1;
    private Button goTop;
    private View headView;
    private View mView;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_b, container, false);
        mContext = getContext();
        initView();
        return mView;
    }

    private void initView() {

        this.goTop = (Button) mView.findViewById(R.id.goTop);
        this.listview = (PullToRefreshListView) mView.findViewById(R.id.pull_listview);

        initListView();

        mList = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new MyAdapter(mContext, mList);
        listview.setAdapter(adapter);
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                initData();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "您当前点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                int tempPos = listview.getRefreshableView().getFirstVisiblePosition();

                if (tempPos > 0) {
                    goTop.setVisibility(View.VISIBLE);
                } else {
                    goTop.setVisibility(View.GONE);
                }

            }
        });

        goTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.getRefreshableView().smoothScrollToPosition(0);//移动到首部
            }
        });

    }

    /**
     * 初始化列表控件上下拉的状态
     */
    private void initListView() {

        ILoadingLayout startLabels = listview.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = listview.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        //        headView = LayoutInflater.from(this).inflate(R.layout.headview, null);
        //        listview.getRefreshableView().addHeaderView(headView);//为ListView添加头布局
    }

    private void initData() {
        //模拟从服务端返回的数据集合
        list.clear();
        for (int i = 10 * (page - 1); i < 10 * page; i++) {
            list.add("第" + i + "条");
        }
        if (mList != null) {
            if (!listview.isFocusable()) {
                mList.clear();
                mList.addAll(list);

                adapter.notifyDataSetChanged();
                listview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview.onRefreshComplete();
                    }
                }, 1000);
            } else {
                adapter.addLast(list);
                adapter.notifyDataSetChanged();
                listview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview.onRefreshComplete();
                    }
                }, 1000);
            }
        }
    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> list;

        public MyAdapter(Context context, ArrayList<String> list) {
            this.context = context;
            this.list = list;
        }

        public void addLast(ArrayList<String> list) {
            this.list.addAll(list);
        }

        @Override
        public int getCount() {
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);
            textView.setText(list.get(position));
            return textView;
        }

    }

}
