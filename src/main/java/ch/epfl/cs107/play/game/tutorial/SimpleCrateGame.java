package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

/**
 * Simple game, to show basic the basic architecture
 */
public class SimpleCrateGame implements Game {
	

    // Store context
    private Window window;

    // We need our physics engine
    private World world;

    // And we need to keep references on our game objects
    private Entity blockBody;
    private Entity crateBody;

    // graphical representation of the body
    private ImageGraphics block;
    private ImageGraphics crate ;
    
    // This event is raised when game has just started
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        world = new World();

        world.setGravity(new Vector(0.0f,-9.81f));

        this.window = window;
        
        EntityBuilder entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(1.0f, 0.5f));
        blockBody = entityBuilder.build();
        
        PartBuilder partBuilder = blockBody.createPartBuilder(); 
         
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
        entityBuilder2.setPosition(new Vector(0.2f, 4.0f));
        crateBody = entityBuilder2.build();
        
        PartBuilder partBuilder2 = crateBody.createPartBuilder();   
        partBuilder2.setShape(polygon);
        partBuilder2.build();
        
        block = new ImageGraphics("stone.broken.4.png", 1, 1);
        block.setAlpha(1.0f);
        block.setDepth(0.0f);
        
        crate = new ImageGraphics("box.4.png", 1, 1);
        crate.setAlpha(1.0f);
        crate.setDepth(0.0f);
        
        block.setParent(blockBody);
        crate.setParent(crateBody);
        
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
        block.draw(window);
        crate.draw(window);

        // The actual rendering will be done now, by the program loop
    }

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
}