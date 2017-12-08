package ch.epfl.cs107.play.game.actor.bikeGame;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame {
	 
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		Terrain terrain = new Terrain(this, new Vector(0f , 0f) );
		addActor(terrain);
		Crate crate1 = new Crate(this , false, new Vector(0.0f ,5.0f), 1, 1, "crate.1.png");
		addActor(crate1);
		Crate crate2 = new Crate(this , false, new Vector(0.2f ,7.0f), 1, 1, "crate.1.png");
		addActor(crate2);
		Crate crate3 = new Crate(this , false, new Vector(2.0f ,6.0f), 1, 1, "crate.1.png");
		addActor(crate3);
		Bike bike = new Bike(this, new Vector(4.0f, 5.0f));
		addActor(bike);
		setViewCandidate(bike);
		//ActorGame.setViewCandidate(bike); ??????????
		
		
		
		return true;
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
}

