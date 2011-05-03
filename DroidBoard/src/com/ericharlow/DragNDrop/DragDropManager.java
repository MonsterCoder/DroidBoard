package com.ericharlow.DragNDrop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class DragDropManager {
	private boolean _isDragging;
	private int mDragPointOffset_x;
	private int mDragPointOffset_y;
	private ImageView mDragView;
	private int mStartPosition;
	private int mEndPosition;

	public DragDropManager() {

	}
	
	public boolean GetDragMode() {
		return _isDragging;
	}

	public void SetDragMode(boolean isDragging) {
		_isDragging = isDragging;
		
	}
	// move the drag view
	public void drag(int x, int y, Context context) {
		if (getDragView() != null) {
			WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getDragView().getLayoutParams();
			layoutParams.x = x - getDragPointOffset_x();
			layoutParams.y = y - getDragPointOffset_y();
			WindowManager mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
			mWindowManager.updateViewLayout(getDragView(), layoutParams);

//			if (mDragListener != null){
//			
//				mDragListener.onDrag(x, y, null);// change null to "this" when ready to use
//			}
		}
	}
	
	// enable the drag view for dragging
	public void startDrag(View item,int x, int y, Context context) {
		stopDrag(item, context);

//		View item = getChildAt(itemIndex);
		if (item == null) return;
		
		item.setDrawingCacheEnabled(true);
	
//		if (mDragListener != null) {
//			
//			mDragListener.onStartDrag(item);
//		}
		
        // Create a copy of the drawing cache so that it does not get recycled
        // by the framework when the list tries to clean up memory
        Bitmap bitmap = Bitmap.createBitmap(item.getDrawingCache());
		
		item.getDrawingCacheBackgroundColor();
		item.setVisibility(View.INVISIBLE);
		
        WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.gravity = Gravity.TOP;
        mWindowParams.x = 0;
        mWindowParams.y = y - getDragPointOffset_y();

        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.windowAnimations = 0;
        
        ImageView v = new ImageView(context);
        v.setImageBitmap(bitmap);      

        WindowManager mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(v, mWindowParams);
        setDragView(v);
	}

	// destroy drag view
	public void stopDrag(View itemView, Context context ) {
		if (getDragView() != null) {
//			if (mDragListener != null)
//				mDragListener.onStopDrag(getChildAt(itemIndex));
		
			itemView.setVisibility(View.VISIBLE);
	
            getDragView().setVisibility(View.GONE);
            WindowManager mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            mWindowManager.removeView(getDragView());
            getDragView().setImageDrawable(null);
            setDragView(null);
        }
	}

	public void setDragView(ImageView mDragView) {
		this.mDragView = mDragView;
	}

	public ImageView getDragView() {
		return mDragView;
	}

	public void setStartPosition(int mStartPosition) {
		this.mStartPosition = mStartPosition;
	}

	public int getStartPosition() {
		return mStartPosition;
	}

	public void setDragPointOffset_y(int mDragPointOffset_y) {
		this.mDragPointOffset_y = mDragPointOffset_y;
	}

	public int getDragPointOffset_y() {
		return mDragPointOffset_y;
	}

	public void setDragPointOffset_x(int mDragPointOffset_x) {
		this.mDragPointOffset_x = mDragPointOffset_x;
	}

	public int getDragPointOffset_x() {
		return mDragPointOffset_x;
	}

	public void setEndPosition(int mEndPosition) {
		this.mEndPosition = mEndPosition;
	}

	public int getEndPosition() {
		return mEndPosition;
	}
}
