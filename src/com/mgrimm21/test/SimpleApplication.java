package com.mgrimm21.test;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import com.mgrimm21.engine.core.Application;
import com.mgrimm21.engine.gameobject.Item;
import com.mgrimm21.engine.gameobject.entity.Player;
import com.mgrimm21.engine.gfx.Sprite;
import com.mgrimm21.engine.input.Keyboard;
import com.mgrimm21.engine.scene.Scene;
import com.mgrimm21.engine.ui.Button;
import com.mgrimm21.engine.ui.Menu;
import com.mgrimm21.engine.ui.ProgressBar;
import com.mgrimm21.engine.util.Language;
import com.mgrimm21.engine.util.TickListener;
import com.mgrimm21.engine.util.Utils;

public class SimpleApplication extends Application implements TickListener{
	
	private Scene gameScene = new Scene();
	private Scene mainMenu = new Scene();
	private Player player;
	private int btnStart = 100;
	private int incr = 75;
	private int btnWidth = 200;
	private int btnHeight = 40;
	private Menu pauseMenu = new Menu(gameScene);
	private Sprite normalNewGame = Sprite.createSprite("res/ui/normalNewGame.png");
	private Sprite hoverNewGame = Sprite.createSprite("res/ui/hoverNewGame.png");
	private Sprite clickedNewGame = Sprite.createSprite("res/ui/clickedNewGame.png");
	private int[] val = new int[] {100};
	private Button newGameButton = new Button(Utils.center(btnWidth, 20).x, btnStart, normalNewGame, hoverNewGame, clickedNewGame) {
		@Override
		public void onClick() {
			setupGameScene();
			sceneManager.setCurrentScene("Game");
		}
	};
	private Button continueGameButton = new Button(Utils.center(btnWidth, 20).x, btnStart+incr, btnWidth, btnHeight, "Continue Game") {
		@Override
		public void onClick() {
			setupGameScene();
			sceneManager.setCurrentScene("Game");
		}
	};
	private Button optionsButton = new Button(Utils.center(btnWidth, 20).x, btnStart+incr*2, btnWidth, btnHeight, "Options") {
		@Override
		public void onClick() {
			// TODO Auto-generated method stub
			super.onClick();
		}
	};
	private Button exitButton = new Button(Utils.center(btnWidth, 20).x, btnStart+incr*3, btnWidth, btnHeight, "Exit") {
		@Override
		public void onClick() {
			window.frame.dispatchEvent(new WindowEvent(window.frame, WindowEvent.WINDOW_CLOSING));
		}
	};
	private Button resumeButton = new Button(Utils.center(btnWidth, 20).x, btnStart, btnWidth, btnHeight, "Resume") {
		public void onClick() {
			pauseMenu.hide();
		};
	};
	private Button mainMenuButton = new Button(Utils.center(btnWidth, 20).x, btnStart+incr, btnWidth, btnHeight, "Menu") {
		public void onClick() {
			pauseMenu.hide();
			sceneManager.setCurrentScene("Main Menu");
			gameScene.remove(player);
		};
	};
	private ProgressBar testBar = new ProgressBar(100, 100, val, 100, Sprite.createSprite("res/ui/healthBar.png"));
	public SimpleApplication() {
		super(800, 600, "Engine");
		addListener(this);
		addScene("Main Menu", mainMenu);
		addScene("Game", gameScene);
		setupMainMenu();
	}
	
	private void setupGameScene() {
		pauseMenu.add(resumeButton);
		pauseMenu.add(mainMenuButton);
		pauseMenu.setPausesGame(true);
		gameScene.addMenu("Pause Menu", pauseMenu);
		player = new Player(400, 200);
		gameScene.add(player);
		gameScene.add(testBar);
	}
	
	private void setupMainMenu() {
		mainMenu.add(newGameButton);
		mainMenu.add(continueGameButton);
		mainMenu.add(optionsButton);
		mainMenu.addElement(exitButton);
	}

	public static void main(String[] args) {
		new SimpleApplication();
		Application.instance.setLanguage(Language.EnglishUS);
		Item item = new Item("engine.testItem");
		System.out.println(item.getName());
	}

	@Override
	public void tick() {
		if (Keyboard.pressed(KeyEvent.VK_Q)) {
			val[0]--;
		}
	}

}
