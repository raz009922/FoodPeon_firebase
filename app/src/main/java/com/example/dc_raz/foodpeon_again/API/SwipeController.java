package com.example.dc_raz.foodpeon_again.API;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

class SwipeController extends Callback {


   /* getMovementFlags tells helper what kind of actions RecyclerView should handle — in our example just swiping left and right
    onMove, onSwiped what to do on given actions*/

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
