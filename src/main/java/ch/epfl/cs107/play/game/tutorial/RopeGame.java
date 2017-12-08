package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

/**
 * Simple game, to show basic the basic architecture
 */
public class RopeGame implements Game {
	
    private Window window;
    private World world;

    private Entity block;
    private Entity ball;

    // graphical representation of the body
    private ImageGraphics blockGraphics;
    private ShapeGraphics ballGraphics ;
    
    // This event is raised when game has just started
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        world = new World();

        world.setGravity(new Vector(0.0f,-9.81f));

        this.window = window;
        
        EntityBuilder entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(1.0f, 0.5f));
        block = entityBuilder.build();
        
        PartBuilder partBuilder = block.createPartBuilder(); 
         
        Polygon polygon = new Polygon(
        		new Vector(0.0f, 0.0f),
        		new Vector(1.0f, 0.0f),
        		new Vector(1.0f, 1.0f),
        		new Vector(0.0f, 1.0f) ); 
        
        partBuilder.setShape(polygon);
        partBuilder.setFriction(0.5f);
        partBuilder.build();
        
        EntityBuilder entityBuilder2 = world.createEntityBuilder();
        entityBuilder2.setFixed(false);
        entityBuilder2.setPosition(new Vector(0.6f, 4.0f));
        ball = entityBuilder2.build();
        
        PartBuilder partBuilder2 = ball.createPartBuilder();   
        Circle circle = new Circle(0.6f);
        partBuilder2.setShape(circle);
        partBuilder2.build();
        
        blockGraphics = new ImageGraphics("box.4.png", 1f, 1f);
        blockGraphics.setAlpha(1.0f);
        blockGraphics.setDepth(0.0f);
        blockGraphics.setParent(block);
        
        ballGraphics = new ShapeGraphics(circle, Color.ORANGE, Color.RED, .1f, .1f, 0);
        ballGraphics.setAlpha(1.0f);
        ballGraphics.setDepth(0.0f);
        ballGraphics.setParent(ball);
       
        RopeConstraintBuilder ropeConstraintBuilder = 
        		world.createRopeConstraintBuilder(); 
        ropeConstraintBuilder.setFirstEntity(block); 
        ropeConstraintBuilder.setFirstAnchor(new Vector(1.0f/2,
        		0.5f/2));
        ropeConstraintBuilder.setSecondEntity(ball); 
        ropeConstraintBuilder.setSecondAnchor(Vector.ZERO); 
        ropeConstraintBuilder.setMaxLength(6.0f); 
        ropeConstraintBuilder.setInternalCollision(true); 
        ropeConstraintBuilder.build();

        
        return true;
    }

    // This event is called at each frame
    @Override
    public void update(float deltaTime) {
       
        // Game logic comes here
    	//nothing to do, yet
    	
    	// Simulate physics
        // Our body is fixed , though , nothing will move
        world.update(deltaTime);
       
        // we must place the camera where we want
        // We will look at the origin (identity) and increase the view size
        // a bit
        window.setRelativeTransform(Transform.I.scaled(10.0f));
       
        // We can render our scene now ,
        blockGraphics.draw(window);
        ballGraphics.draw(window);

        // The actual rendering will be done now, by the program loop
    }

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
}
