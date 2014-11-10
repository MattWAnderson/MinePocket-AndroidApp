package com.MinePocket.mpapp;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.MinePocket.mpapp.R;


public class WebViewAppApplication extends Application
{
	private static WebViewAppApplication mInstance;

	private Tracker mTracker;


	public WebViewAppApplication()
	{
		mInstance = this;
	}


	@Override
	public void onCreate()
	{
		super.onCreate();
		
		// force AsyncTask to be initialized in the main thread due to the bug:
		// http://stackoverflow.com/questions/4280330/onpostexecute-not-being-called-in-asynctask-handler-runtime-exception
		try
		{
			Class.forName("android.os.AsyncTask");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}


	public static Context getContext()
	{
		return mInstance;
	}


	public synchronized Tracker getTracker()
	{
		if(mTracker==null)
		{
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			analytics.setDryRun(!WebViewAppConfig.ANALYTICS);
			mTracker = analytics.newTracker(R.xml.analytics_app_tracker);
		}
		return mTracker;
	}
}
