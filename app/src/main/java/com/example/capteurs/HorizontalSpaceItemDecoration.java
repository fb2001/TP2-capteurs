package com.example.capteurs;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int horizontalSpaceWidth;

    public HorizontalSpaceItemDecoration(int horizontalSpaceWidth) {
        this.horizontalSpaceWidth = horizontalSpaceWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter() != null ? parent.getAdapter().getItemCount() : 0;
        if (position < itemCount - 1) {
            outRect.right = horizontalSpaceWidth;}
        if (position > 0) {
            outRect.left = horizontalSpaceWidth / 2;
        }
        outRect.top = horizontalSpaceWidth / 2;
        outRect.bottom = horizontalSpaceWidth / 2;
    }
}