package com.liwy.recyclerviewdemo;

import android.view.View;

/**
 * Created by liwy on 16/9/25.
 */

public interface OnRecyclerViewClickListener {
    public void onClick(View view, int position);
    public void onLongClick(View view, int position);
}
