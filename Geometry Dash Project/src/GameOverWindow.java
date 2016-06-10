import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import ch.hevs.gdx2d.lib.physics.PhysicsWorld;
import ch.hevs.gdx2d.lib.utils.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class GameOverWindow extends RenderingScreen{
	Skin skin;
	Stage stage;
	TextButton newGameButton, quitGameButton;
	TextField textArea;
	int stateCounter = 0; 
	String state; 

	public static void main(String[] args) {
		new GameOverWindow();
	}

	
	public void onInit(){
		
		int buttonWidth = 180;
		int buttonHeight = 30;

//		setTitle("Jumping Cube");

		stage = new Stage();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(Gdx.input.getInputProcessor());
		Gdx.input.setInputProcessor(multiplexer);

		// Load the default skin (which can be configured in the JSON file)
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		newGameButton = new TextButton("Return to Menu", skin); // Use the initialized skin
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);

		quitGameButton = new TextButton("Quit", skin); // Use the initialized skin
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);

		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .6));
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));

		textArea = new TextField("Enter your name...", skin);
		textArea.setWidth(buttonWidth);
		textArea.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .4));

		textArea.setTextFieldListener(new TextFieldListener() {
			public void keyTyped(TextField textField, char key) {
				textArea.setSelection(0, 0);

				// When you press 'enter', do something
				if (key == 13)
					Logger.log("You have typed " + textArea.getText());
			}
		});

		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(newGameButton);
		stage.addActor(quitGameButton);
		stage.addActor(textArea);

		/**
		 * Register listener
		 */
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				ScreenHub.s.transitionTo(0, ScreenManager.TransactionType.SMOOTH); 
			}
		});
	
		quitGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				System.exit(0); 
			}
		});
	}

	public void onGraphicRender(GdxGraphics g) {
		g.clear();

		// This is required for having the GUI work properly

//		stage.act();
//		stage.draw();
//		g.setColor(Color.GREEN); 
//		g.drawStringCentered(Gdx.graphics.getHeight()/2, "Congratulation! Your journey was " + Gsing.get().totalDistance + "Long!");
//		System.out.println("HIHI" + Gsing.get().totalDistance/10);
//		g.drawStringCentered(700, "Seed : " + Gsing.get().mapGenSeed);
//		g.drawStringCentered(getWindowHeight() / 4, "Colin Cina & Martin Juon");
		g.drawSchoolLogo();
		g.drawFilledRectangle(100,100,100,100,0,Color.BLACK);
		g.drawFilledCircle(100,100,100,Color.CYAN);
		g.drawFilledRectangle(100,100,100,100,0,Color.GREEN);
		g.drawFPS();
	}

	@Override
	public void dispose() {
		PhysicsWorld.dispose();
		super.dispose();
		GameWindow.sGameMusic.stop(); 
		GameWindow.sGameMusic.dispose(); 
	}
}

