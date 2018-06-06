package com.nu.art.cyborg.tutorial.googleMaps;

import android.Manifest.permission;
import android.location.Location;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.maps.google.Controller_GoogleMap;
import com.nu.art.cyborg.maps.google.Controller_GoogleMap.MapMarker;
import com.nu.art.cyborg.maps.google.OnGoogleMapListener;
import com.nu.art.cyborg.maps.google.OnMapReadyListener;
import com.nu.art.cyborg.modules.LocationModule;
import com.nu.art.cyborg.modules.LocationModule.OnLocationUpdatedListener;
import com.nu.art.cyborg.modules.PermissionModule;
import com.nu.art.cyborg.modules.PermissionModule.PermissionResultListener;

public class Controller_GoogleMaps
	extends CyborgController
	implements OnLocationUpdatedListener, OnGoogleMapListener, OnMapReadyListener, PermissionResultListener {

	private int mapsToInitiate = 2;
	private Runnable locationUpdate = new Runnable() {
		@Override
		public void run() {
			if (mapsToInitiate > 0)
				return;

			LocationModule locationModule = getModule(LocationModule.class);
			locationModule.requestLocationUpdates(3000, 0);
			// need to update user position, and focus on it
		}
	};

	@ViewIdentifier(viewId = R.id.mapController1)
	private Controller_GoogleMap map1;

	@ViewIdentifier(viewId = R.id.mapController2)
	private Controller_GoogleMap map2;

	public Controller_GoogleMaps() {
		super(R.layout.cyborgview__controller_google_map_user);
	}

	@Override
	protected void onCreate() {
		super.onCreate();
	}

	@Override
	public void onLocationUpdated(Location location) {
		map1.setCameraLocation(new LatLng(location.getLatitude(), location.getLongitude()), true);
	}

	@Override
	public void onLocationUpdateError() {

	}

	@Override
	protected void onResume() {
		super.onResume();
		getModule(PermissionModule.class).requestPermission(100, permission.ACCESS_FINE_LOCATION);
		postOnUI(100, locationUpdate);
	}

	public void onAllPermissionsGranted(int i) {
		postOnUI(new Runnable() {
			@Override
			public void run() {
				map1.showMap();
				map2.showMap();
			}
		});
	}

	@Override
	public void onPermissionsRejected(int i, String[] strings) {
		toastDebug("MUST accept permissions!!");
		getActivity().onBackPressed();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onMapReady(int controllerId) {
		logInfo("ZE MAP IZ READEH");
		mapsToInitiate--;
		postOnUI(100, locationUpdate);
	}

	@Override
	public void onMapClick(int controllerId, LatLng latLng) {
		logInfo("MAP CLICK");
		Controller_GoogleMap controller = getControllerById(controllerId);
		controller.addMarker(new MapMarker(latLng, BitmapDescriptorFactory.HUE_AZURE, "Moon", R.drawable.moon));
	}

	@Override
	public void onMapLongClick(int controllerId, LatLng latLng) {
		logInfo("MAP LONG CLICK");
		Controller_GoogleMap controller = getControllerById(controllerId);
		controller.addMarker(new MapMarker(latLng, BitmapDescriptorFactory.HUE_BLUE, "Sun", R.drawable.sun));
	}

	@Override
	public void onMarkerClick(int controllerId, Marker marker) {

	}

	@Override
	public void onInfoWindowClick(int controllerId, Marker marker) {
		logInfo("INFO WINDOW CLICK");
		Controller_GoogleMap controller = getControllerById(controllerId);
		controller.removeMarker((MapMarker) marker.getTag());
	}
}
