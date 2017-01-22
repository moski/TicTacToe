package com.doski.moski.tictactoe.widget;

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;

import com.doski.moski.tictactoe.R;

public class PlayGridView extends GridLayout {

  public PlayGridView(Context context) {
    this(context, null);
  }

  public PlayGridView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PlayGridView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    setRowCount(3);
    setColumnCount(3);
    setUseDefaultMargins(true);
    inflate(context, R.layout.view_play_board, this);
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    super.onMeasure(widthSpec, heightSpec);

    int width = getMeasuredWidth();
    setMeasuredDimension(width, width);
  }
}
