package com.example.fragmentlazyload;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/11/15.
 */

public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    private View mView;
    //视图是否初始化
    public boolean mIsInit = false;
    //视图是否可见
    public boolean isLoad = false;
    //是否第一次可见
    public boolean isFirstVisible = true;

    /**
     * 获取Fragmnet依附的Activity对象
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 返回Fragment的layout
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setContentView(), container, false);
        return mView;
    }


    /**
     * 在此可以初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        //Framgent是否初始化
        mIsInit = true;
        //Fragment初始化的时候去加载数据
        isCanLoadData();
    }


    /**
     * Framgent可见的时候调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 判断是否能加载数据
     */
    protected void isCanLoadData() {
        if (!mIsInit) {
            //没有初始化
            return;
        } else {
            //初始化，并判断是否可见
            if (getUserVisibleHint() && isFirstVisible) {
                isFirstVisible = false;
                //可见，加载数据
                isLoad = true;
                loadData();
            } else {
                if (isLoad) {
                    //停止加载
                    stopLoadData();
                }
            }
        }
    }

    /**
     * 视图销毁的时候，将标记置为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsInit = false;
        isLoad = false;
    }

    /**
     * 停止加载数据
     */
    protected abstract void stopLoadData();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的LayoutID
     */
    protected abstract int setContentView();

    /**
     * 返回Fragment中显示的布局
     *
     * @return
     */
    protected View getContentView() {
        return mView;
    }

    protected <T extends View> T findViewById(int id) {
        return (T) getContentView().findViewById(id);
    }
}
