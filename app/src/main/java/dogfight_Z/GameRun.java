package dogfight_Z;



//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


//import javax.swing.JOptionPane;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/*

//xlgame
 */
public class GameRun
{
public static Context context;
	private static final String TAG = "GameRun";
	public static void main(String[] args)
	{
		String dir = context.getFilesDir().getPath();

		try {
			 dir = context.getFilesDir().getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dir += File.separator;
		Log.i(TAG, "dir = "+dir);
		if(args.length==0){
			args = new String []{dir+"resources/Jet.dat",
					dir+"resources/Crosshair2.hud",
					dir+"resources/LoopingScrollBar1.hud",
					dir+"resources/LoopingScrollBar3.hud",
					dir+"resources/HUD2.hud",
					dir+"resources/MyJetHUD_Friend.hud",
					dir+"resources/MyJetHUD_Enemy.hud",
					dir+"resources/MyJetHUD_Locking.hud",
					dir+"resources/MyJetHUD_Locked.hud",
					dir+"resources/MissileWarning.hud",
					dir+"resources/RadarHUD.hud",
					dir+"resources/RaderPainter.hud",
					dir+"resources/ScoreHUD.hud",
					dir+"resources/GameOver.hud",
					dir+"resources/config_NPC.cfg",
					dir+"resources/gameRecord.rec",
					dir+"resources/config_OST.cfg",
					"107", "57", "64"};
		}
		// TODO 设定各类游戏参数
		
//		JFrame formMenu = new JFrame("Dog Fight Z -- Setting Menu");
//		formMenu.setSize(400, 300);
//		formMenu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
//		JButton btnStart = new JButton("Start the Game !");
//		btnStart.setActionCommand("Act_gameStart");
//		
//		btnStart.setFont(new Font("Times New Roman", 3, 32));
		
//		formMenu.add(btnStart);
		
		
		/*Thread game = new Thread(new Game
				(
					args[0] ,args[1] , args[2], args[3], args[4],
					args[5] ,args[6] , args[7], args[8], args[9],
					args[10], args[11],
					Short.parseShort(args[12]),
					Short.parseShort(args[13]),
					Short.parseShort(args[14])
				));*/
		Game game = new Game
		(
			args[0] ,args[1] , args[2], args[3], args[4], args[5] ,
			args[6] , args[7], args[8], args[9], args[10], args[11], 
			args[12], args[13], args[14], args[15], args[16], 
			Short.parseShort(args[17]), 
			Short.parseShort(args[18]), 
			Short.parseShort(args[19])
		);
		game.context = context;
		//game.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				game.run();
			}
		}).start();

		//formMenu.dispose();
		/*
		btnStart.addActionListener
		(
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if(e.getActionCommand() == "Act_gameStart")
					{
						JOptionPane.showMessageDialog(null, "Act_gameStart !");
						Thread game = new Thread(new Game
								(
									args[0] ,args[1] , args[2], args[3], args[4], 
									args[5] ,args[6] , args[7], args[8], args[9], 
									args[10], args[11], 
									Short.parseShort(args[12]), 
									Short.parseShort(args[13]), 
									Short.parseShort(args[14])
								));
						game.start();
								//game.run();	
						formMenu.dispose();
										
					}
				}
			}
		);
		*/
		//`formMenu.setVisible(true);
		
		//--------------------------------------
		
	}

}
