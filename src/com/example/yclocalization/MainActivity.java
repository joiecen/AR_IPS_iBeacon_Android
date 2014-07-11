package com.example.yclocalization;

/*
 * ibeacon uuid: d26d197e-4a1c-44ae-b504-dd7768870564 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;

import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements IBeaconConsumer {
	protected static final String TAG = "MainActivity";
	private IBeaconManager iBeaconManager = IBeaconManager
			.getInstanceForApplication(this);
	private ArrayList<Ibeaconsv> iblocations = new ArrayList<Ibeaconsv>();

	private MapView myMap;
	private MainApplication myApplication;
	
	private SensorManager mSensorManeger;
	private Sensor orientationSensor;
	private AugmentedRealityView ARView;
	private double mapOrientationOffset = 240;
	
	 private static final boolean POSITION_STATE_OFF = false;
	 private static final boolean POSITION_STATE_ON = true;
	 private boolean POSITION_MODE = POSITION_STATE_ON;
	    
	 private boolean MAP_MODE = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		verifyBluetooth();
        iBeaconManager.bind(this);
		Log.i("info", "0000");
		
		myApplication = (MainApplication) getApplication();
		myMap = (MapView) findViewById(R.id.myMap);
		myMap.setVisibility(View.INVISIBLE);
		myApplication.setPositionMode(POSITION_MODE);
		myApplication.setPosition(930, 1340);
		
		mSensorManeger=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		orientationSensor=mSensorManeger.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		mSensorManeger.registerListener(mListener, orientationSensor, SensorManager.SENSOR_DELAY_GAME);
		ARView = (AugmentedRealityView) findViewById(R.id.augmentedRealityView);
		ARView.setMapOrientationOffset(120);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds ite ms to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	private void verifyBluetooth() {
		// TODO Auto-generated method stub
		IBeaconManager.getInstanceForApplication(this).checkAvailability();
		Log.i("info", "9999");
		try {
			if (!IBeaconManager.getInstanceForApplication(this)
					.checkAvailability()) {
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						this);
				builder.setTitle("Bluetooth is not enabled");
				builder.setMessage("Please enable bluetooth in settings and restart this application.");
				builder.setPositiveButton(android.R.string.ok, null);
				builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						// TODO Auto-generated method stub

					}
				});
				builder.show();
			}
		} catch (RuntimeException e) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Bluetooth LE not available");
			builder.setMessage("Sorry, this device does not support Bluetooth LE.");
			builder.setPositiveButton(android.R.string.ok, null);
			builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface dialog) {
					// finish();
					// System.exit(0);
				}

			});
			builder.show();
		}
	}

	
	@Override
	public void onIBeaconServiceConnect() {
		// TODO Auto-generated method stub
		iBeaconManager.setRangeNotifier(new RangeNotifier() {

			@Override
			public void didRangeBeaconsInRegion(Collection<IBeacon> arg0,
					Region arg1) {
				// TODO Auto-generated method stub
				Log.i("info", "8888");
				Iterator<IBeacon> iterator = arg0.iterator();

				while (iterator.hasNext()) {
					IBeacon iBeacon = (IBeacon) iterator.next();
					int rssi = iBeacon.getRssi();
					int major = iBeacon.getMajor();
					int minor = iBeacon.getMinor();
					int proximity = iBeacon.getProximity();
					/* 用case为每个ibeacon分配坐标、信息等 */
					if (major == 1) {
						Log.i("info", "1111");
						switch (minor) {
						case 1:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(930, 1340), "电梯间", major, minor,
									proximity));
							break;
						case 2:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(831, 1081), "厨房", major,
									minor, proximity));
							break;
						case 3:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(567, 1122), "入口", major,
									minor, proximity));
							break;
						case 4:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(906, 709), "阳台", major,
									minor, proximity));
							break;
						case 5:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(689, 827), "办公室1", major,
									minor, proximity));
							break;
						case 6:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(406, 620), "办公室2", major,
									minor, proximity));
							break;
						case 7:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(500, 350), "会议室", major,
									minor, proximity));
							break;
						case 8:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(685, 490), "财务室", major,
									minor, proximity));
							break;
						case 9:
							iblocations.add(new Ibeaconsv("ycmap", rssi,
									new PointF(301, 509), "总经理办公室", major,
									minor, proximity));
							break;
						}
					}
				}
				/** 按信号强度排序 */
				int ibnum = iblocations.size();
				PointF locatPoint = new PointF(0, 0);// 信号强度最大的ibeacon的定位坐标
				for (int i = 0; i < ibnum - 1; i++) {
					Ibeaconsv detectedib = iblocations.get(i);
					// PointF pointa = detectedib.ibPointF;
					// filter the UNKNOWN ones
					if (detectedib.ibproximity != IBeacon.PROXIMITY_UNKNOWN&&detectedib.ibrssi>=-75) {
						// ranking
						for (int j = i + 1; j < ibnum; j++) {
							Ibeaconsv detectedibnext = iblocations.get(j);
							// PointF pointb = detectedibnext.ibPointF;
							if (detectedib.ibrssi < detectedibnext.ibrssi) {
								locatPoint.x = detectedibnext.ibPointF.x;
								locatPoint.y = detectedibnext.ibPointF.y;
							} else {
								locatPoint.x = detectedib.ibPointF.x;
								locatPoint.y = detectedib.ibPointF.y;
							}
						}
					} else {
						continue;
					}
					if (detectedib.ibproximity==IBeacon.PROXIMITY_NEAR||detectedib.ibproximity==IBeacon.PROXIMITY_IMMEDIATE) {
						if (detectedib.ibrssi>=-58) {
							myApplication.setPosition(1, locatPoint.x, locatPoint.y);
							myMap.postInvalidate();
						}
						
					}
//					
					Log.i("info", "5555");
				}
				//Sta.x = locatPoint.x;
				//Sta.y = locatPoint.y;
				Log.i("info", "x" + Sta.x + "y" + Sta.y);
				
				
			}

		});

		try {
			iBeaconManager.startRangingBeaconsInRegion(new Region(
					"myMonitoringUniqueId",
					"d26d197e-4a1c-44ae-b504-dd7768870564",1, null));
			
			Log.i("info", "fine");
		} catch (RemoteException e) {

		}

	}
	 private final SensorEventListener mListener=new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			if (ARView != null) {
				ARView.setCompass(event.values[0]);
				ARView.setPitch(event.values[1]);
				ARView.invalidate();
			}
			if (MAP_MODE) {
				if (event.values[1]>40||event.values[1]<-40) {
					MAP_MODE=false;
					myMap.setVisibility(View.INVISIBLE);
					}
			}else {
				if (event.values[1]<30&&event.values[1]>-30) {
					MAP_MODE=true;
					myMap.setVisibility(View.VISIBLE);
				}
			}
			PositionView.setOrientation((float)(event.values[0] + mapOrientationOffset));
			
		}
		
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		iBeaconManager.unBind(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (iBeaconManager.isBound(this))
			iBeaconManager.setBackgroundMode(this, true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (iBeaconManager.isBound(this))
			iBeaconManager.setBackgroundMode(this, false);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
