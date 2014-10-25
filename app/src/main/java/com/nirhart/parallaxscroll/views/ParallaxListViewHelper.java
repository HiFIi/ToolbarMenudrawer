package com.nirhart.parallaxscroll.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.kyler.toolbarmenudrawer.R;

public class ParallaxListViewHelper implements OnScrollListener {

	private static final float DEFAULT_ALPHA_FACTOR = -1F;
	private static final float DEFAULT_PARALLAX_FACTOR = 1.9F;
	private static final boolean DEFAULT_IS_CIRCULAR = false;
	private float parallaxFactor = DEFAULT_PARALLAX_FACTOR;
	private float alphaFactor = DEFAULT_ALPHA_FACTOR;
	private ParallaxedView parallaxedView;
	private boolean isCircular;
	private OnScrollListener listener = null;
	private ListView listView;

	protected ParallaxListViewHelper(Context context, AttributeSet attrs, ListView listView) {
		init(context, attrs, listView);
	}

	protected void init(Context context, AttributeSet attrs, ListView listView) {
		this.listView = listView;
		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.ParallaxScroll);
		this.parallaxFactor = typeArray.getFloat(R.styleable.ParallaxScroll_parallax_factor, DEFAULT_PARALLAX_FACTOR);
		this.alphaFactor = typeArray.getFloat(R.styleable.ParallaxScroll_alpha_factor, DEFAULT_ALPHA_FACTOR);
		this.isCircular = typeArray.getBoolean(R.styleable.ParallaxScroll_circular_parallax, DEFAULT_IS_CIRCULAR);
		typeArray.recycle();
	}

	protected void setOnScrollListener(OnScrollListener l) {
		this.listener = l;
	}
	
	protected void addParallaxedHeaderView(View v) {
		addParallaxedView(v);
	}

	protected void addParallaxedHeaderView(View v, Object data, boolean isSelectable) {
		addParallaxedView(v);
	}

	protected void addParallaxedView(View v) {
		this.parallaxedView = new ListViewParallaxedItem(v);
	}

	protected void parallaxScroll() {
		if (isCircular)
			circularParallax();
		else
			headerParallax();
	}

	private void circularParallax() {
		if (listView.getChildCount() > 0) {
			int top = -listView.getChildAt(0).getTop();
			if (top >= 0) {
				fillParallaxedViews();
				setFilters(top);
			}
		}
	}

	private void headerParallax() {
		if (parallaxedView != null) {
			if (listView.getChildCount() > 0) {
				int top = -listView.getChildAt(0).getTop();
				if (top >= 0) {
					setFilters(top);
				}
			}
		}
	}

	private void setFilters(int top) {
		parallaxedView.setOffset((float)top / parallaxFactor);
		if (alphaFactor != DEFAULT_ALPHA_FACTOR) {
			float alpha = (top <= 0) ? 1 : (100 / ((float)top * alphaFactor));
			parallaxedView.setAlpha(alpha);
		}
		parallaxedView.animateNow();
	}

	private void fillParallaxedViews() {
		if (parallaxedView == null || parallaxedView.is(listView.getChildAt(0)) == false) {
			if (parallaxedView != null) {
				resetFilters();
				parallaxedView.setView(listView.getChildAt(0));
			} else {
				parallaxedView = new ListViewParallaxedItem(listView.getChildAt(0));
			}
		}
	}

	private void resetFilters() {
		parallaxedView.setOffset(0);
		if (alphaFactor != DEFAULT_ALPHA_FACTOR)
			parallaxedView.setAlpha(1F);
		parallaxedView.animateNow();
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		parallaxScroll();
		if (this.listener != null)
			this.listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (this.listener != null)
			this.listener.onScrollStateChanged(view, scrollState);
	}
	
	protected class ListViewParallaxedItem extends ParallaxedView {

		public ListViewParallaxedItem(View view) {
			super(view);
		}
		
		@Override
		protected void translatePreICS(View view, float offset) {
			addAnimation(new TranslateAnimation(0, 0, offset, offset));
		}
	}
}
