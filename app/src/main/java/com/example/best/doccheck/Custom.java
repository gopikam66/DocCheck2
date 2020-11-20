package com.example.best.doccheck;

import android.R.color;
import android.R.layout;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends BaseAdapter{

	private Context Context;
	ArrayList<String> c;
	ArrayList<String> d;
	ArrayList<String> e;
	ArrayList<String> f;

	public Custom(Context applicationContext, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e, ArrayList<String> f) {

		this.Context=applicationContext;
		this.c=c;
		this.d=d;
		this.e=e;
		this.f=f;
	}

	@Override
	public int getCount() {
		
		return d.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {

		
		LayoutInflater inflator=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		if(convertview==null)
		{
			gridView=new View(Context);
			gridView=inflator.inflate(R.layout.booking_details, null);
			
		}
		else
		{
			gridView=(View)convertview;
			
		}
		
		TextView tv1=(TextView)gridView.findViewById(R.id.tname);
		TextView tv2=(TextView)gridView.findViewById(R.id.tplace);
		TextView tv3=(TextView)gridView.findViewById(R.id.tphone);
		TextView tv4=(TextView)gridView.findViewById(R.id.ttoken);
		
		tv1.setTextColor(Color.BLACK);
		tv2.setTextColor(Color.BLACK);
		tv3.setTextColor(Color.BLACK);
		tv4.setTextColor(Color.BLACK);
		
		tv1.setText(c.get(position));
		tv2.setText(d.get(position));

		tv3.setText(e.get(position));
		tv4.setText(f.get(position));


		return gridView;
	}

}
