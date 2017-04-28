package com.project.luulinhson.muasamtructuyen.Model.Object;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Admin on 4/7/2017.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener{

    int firldItemVisible = 0;
    int totalItem = 0;
    int itemLoadTruoc = 6;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager,ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItem = layoutManager.getItemCount();

        if(layoutManager instanceof LinearLayoutManager){
            firldItemVisible = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else if (layoutManager instanceof GridLayoutManager){
            firldItemVisible = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if(totalItem <= (firldItemVisible + itemLoadTruoc)){
            Log.d("onScrolled: ",totalItem + " - " + firldItemVisible);

            iLoadMore.LoadMore(totalItem);
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
