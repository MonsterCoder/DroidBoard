package com.ericharlow.DragNDrop;

import Codemeditation.DroidBoard.DroidBoardApplication;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DragNDropListView extends ListView {
	GestureDetector mGestureDetector;
	
	DropListener mDropListener;
	RemoveListener mRemoveListener;
	DragListener mDragListener;

	private Context context;

	private DragDropManager _dragdropManager;

	public DragNDropListView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		_dragdropManager = ((DroidBoardApplication)context.getApplicationContext()).GetDragDropManager();
		
		setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0,
					View view, int arg2, long arg3) {
				_dragdropManager.SetDragMode(true);	
				Toast.makeText(getContext(), "long click", Toast.LENGTH_SHORT).show();
				return false;
			}			
		});
	}
	
	
	public void setDropListener(DropListener l) {
		mDropListener = l;
	}

	public void setRemoveListener(RemoveListener l) {
		mRemoveListener = l;
	}
	
	public void setDragListener(DragListener l) {
		mDragListener = l;
	}
	
	public void setContext(Context context) {
	 this.context = context;
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		final int x = (int) ev.getX();
		final int y = (int) ev.getY();	
		/// Toast.makeText(getContext(), "view tough event", Toast.LENGTH_SHORT).show();
		
		if (_dragdropManager.GetDragMode()) {
			
		
			if (_dragdropManager.getDragView() == null) {
				_dragdropManager.setStartPosition(pointToPosition(x,y));
				if (_dragdropManager.getStartPosition() != INVALID_POSITION) {
					int mItemPosition = _dragdropManager.getStartPosition() - getFirstVisiblePosition();
					_dragdropManager.setDragPointOffset_y(y - getChildAt(mItemPosition).getTop());
					_dragdropManager.setDragPointOffset_y(_dragdropManager.getDragPointOffset_y()
							- (((int)ev.getRawY()) - y));
					_dragdropManager.setDragPointOffset_x(x);
		           _dragdropManager.startDrag(getChildAt(mItemPosition),x, y, context);

				}	
			}
			
			switch (action) {
				case MotionEvent.ACTION_DOWN:
					Toast.makeText(getContext(), "listview down", Toast.LENGTH_SHORT).show();
					break;
				case MotionEvent.ACTION_MOVE:
				 _dragdropManager.drag(x,y, context);
					break;
				case MotionEvent.ACTION_CANCEL:	
				case MotionEvent.ACTION_UP:
				default:
					Toast.makeText(getContext(), "view drop", Toast.LENGTH_SHORT).show();
					_dragdropManager.SetDragMode(false);
					_dragdropManager.setEndPosition(pointToPosition(x,y));
					_dragdropManager.stopDrag(getChildAt(_dragdropManager.getStartPosition() - getFirstVisiblePosition()), context);
					if (mDropListener != null && _dragdropManager.getStartPosition() != INVALID_POSITION && _dragdropManager.getEndPosition() != INVALID_POSITION) {
		        		 mDropListener.onDrop(_dragdropManager.getStartPosition(), _dragdropManager.getEndPosition());
		        		 this.invalidateViews();
					}
					break;
			}
			
		} else {
			return super.onTouchEvent(ev);
		}
		
		return false;
	}	
}
