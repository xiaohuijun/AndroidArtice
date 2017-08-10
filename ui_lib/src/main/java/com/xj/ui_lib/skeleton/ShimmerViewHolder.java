package com.xj.ui_lib.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xj.ui_lib.R;


/**
 * Created by ethanhua on 2017/7/29.
 */

public class ShimmerViewHolder extends RecyclerView.ViewHolder {

    public ShimmerViewHolder(LayoutInflater inflater, ViewGroup parent, int innerViewResId) {
        super(inflater.inflate(R.layout.skeleton_layout_shimmer, parent, false));
        ShimmerFrameLayout layout = (ShimmerFrameLayout) itemView;
        layout.setGroup(Integer.toString(innerViewResId));
        View innerView = inflater.inflate(innerViewResId, layout, false);
        layout.addView(innerView);
        layout.setAutoStart(false);
    }
}
