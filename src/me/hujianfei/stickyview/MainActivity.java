package me.hujianfei.stickyview;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayAdapter<String> adapter;

	private ListView listView; // must
	
	private Button mStickyButton ;  // must
	
	private View mPlaceHolderView; // must
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initData();
	}

	@SuppressLint("InflateParams")
	public void initView()
	{
		this.listView = (ListView)findViewById(R.id.listView);
		
		this.mStickyButton = (Button)findViewById(R.id.stickyButton);
		
		
		// must
		View headerView = LayoutInflater.from(this).inflate(R.layout.listview_header, null);
		this.mPlaceHolderView = headerView.findViewById(R.id.placeHolderView);
		this.listView.addHeaderView(headerView);
				

		
		// must
		this.listView.setOnScrollListener( new AbsListView.OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
				onScrollListView(view);
			}
		});
		
		
		// for test
		
		this.mStickyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "点击了悬停按钮", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	
	// must
	public void onScrollListView(AbsListView   lv)
	{
		View v = lv.getChildAt(0);
		int top = (v == null) ? 0 : v.getTop();

		// This check is needed because when the first element reaches the top of the window, the top values from top are not longer valid. 
		if (lv.getFirstVisiblePosition() == 0) 
		{
			mStickyButton.setTranslationY(
					Math.max(0, mPlaceHolderView.getTop() + top));

		}	
	}
	
	public void initData()
	{
		List<String> dataList = new ArrayList<String>();

		for (int i = 0; i < 200; i++)
		{
			dataList.add(String.format("这是ListView中的项目序号%d",i+1));
		}

		this.adapter = new ArrayAdapter<String>(this, R.layout.listview_item,
				R.id.listviewItemText, dataList);

		this.listView.setAdapter(this.adapter);
	}
}
