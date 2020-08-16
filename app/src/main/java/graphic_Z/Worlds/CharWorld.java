package graphic_Z.Worlds;

import android.content.Intent;
import android.util.Log;

import dogfight_Z.GameRun;
import graphic_Z.HUDs.CharHUD;
import graphic_Z.Managers.CharObjectsManager;
import graphic_Z.Managers.CharVisualManager;
import graphic_Z.Managers.EventManager;
import graphic_Z.Objects.CharObject;

public class CharWorld extends TDWorld<CharWorld, CharObject, CharHUD>
{
	private static final String TAG = "CharWorld";
	public CharObjectsManager	objectsManager;
	public CharVisualManager	visualManager;
	
	public CharWorld(short resolution_X, short resolution_Y)
	{
		super(60);				//default
		Log.e(TAG, "CharWorld:  error");
//		Intent intent = new Intent(GameRun.context, EventManager.class);
//		GameRun.context.startActivity(intent);
		eventManager = new EventManager();
		objectsManager = new CharObjectsManager();
		visualManager = new CharVisualManager(resolution_X, resolution_Y, this, eventManager.mainScr);

	}
	
	public CharWorld(short resolution_X, short resolution_Y, int refresh_rate)
	{
		super(refresh_rate);
		Log.e(TAG, "CharWorld: error" );
//		Intent intent = new Intent(GameRun.context, EventManager.class);
//		GameRun.context.startActivity(intent);
		eventManager = new EventManager();
		objectsManager = new CharObjectsManager();
		visualManager = new CharVisualManager(resolution_X, resolution_Y, this, eventManager.mainScr);

	}
	
	public void setRefreshRate(int refresh_rate)
	{
		refreshDelay = 1000 / refresh_rate;
		visualManager.refreshDelay = refreshDelay;
	}
	
	public void printNew()
	{
		visualManager.printNew();
	}
	
}
