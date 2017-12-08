package ch.epfl.cs107.play.game.actor.bikeGame;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor {

		private ShapeGraphics graphics;

		public Terrain(ActorGame game, Vector position) { 
			super(game, true, position);
			
	        PartBuilder partBuilder = getEntity().createPartBuilder(); 
	        Polyline polyline = new Polyline( -1000.0f, -1000.0f,
	        		-1000.0f, 0.0f,
	        		0.0f, 0.0f,
	        		3.0f, 1.0f,
	        		8.0f, 1.0f,
	        		15.0f, 3.0f,
	        		16.0f, 3.0f,
	        		25.0f, 0.0f,
	        		35.0f, -5.0f,
	        		50.0f, -5.0f,
	        		55.0f, -4.0f,
	        		65.0f, 0.0f,
	        		6500.0f, -1000.0f
	        		);
	        partBuilder.setShape(polyline);
	        partBuilder.build();
	        
	        graphics = new ShapeGraphics(polyline, Color.BLUE, Color.RED , 0.05f);
	        
	        graphics.setParent(getEntity());
		}
		
		
		//supprimer des actors ???
		@Override
		public void destroy() {
			getEntity().destroy();
			// PENSER A FAIRE DISPARAITRE GRAPHICS	
		}
		
		@Override
		public Transform getTransform() {
			return getEntity().getTransform();
		}

		@Override
		public Vector getVelocity() {
			return getEntity().getVelocity();
		}

		@Override
		public void draw(Canvas canvas) {
			graphics.draw(canvas);
		}

	}

