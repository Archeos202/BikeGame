package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;;

public class CrateGame extends ActorGame {
	

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		Crate crate1 = new Crate(this , false, new Vector(0.0f ,5.0f), 1, 1, "crate.1.png");
		addActor(crate1);
		Crate crate2 = new Crate(this , false, new Vector(0.2f ,7.0f), 1, 1, "crate.1.png");
		addActor(crate2);
		Crate crate3 = new Crate(this , false, new Vector(2.0f ,6.0f), 1, 1, "crate.1.png");
		addActor(crate3);
		 return true;
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
}
