package com.example.fragmentlazyload;

import android.util.Log;
import android.widget.TextView;

import com.example.fragmentlazyload.BaseFragment;

/**
 * Created by admin on 2016/11/22.
 */

public class Fragment2 extends BaseFragment {

    private TextView textView;

    /**
     * 不可见停止加载数据
     */
    @Override
    protected void stopLoadData() {
        Log.d("Fragment2","Fragment2停止加载数据");
    }

    /**
     * 可见的时候加载数据
     */
    @Override
    protected void loadData() {
        Log.d("Fragment2","Fragment2开始加载数据");
        textView.setText("Fragment2");
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        textView = findViewById(R.id.text);
    }

    /**
     * 设置Fragmnet布局
     *
     * @return
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_layout;
    }
}
