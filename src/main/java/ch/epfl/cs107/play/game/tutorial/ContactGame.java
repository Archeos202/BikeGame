package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

/**
 * Simple game, to show basic the basic architecture
 */
public class ContactGame implements Game {
	
    private Window window;
    private World world;

    private BasicContactListener contactListener;
    
    private Entity block;
    private ImageGraphics blockGraphics;
    
    private Entity ball;
    private ShapeGraphics ballGraphics ;
    
    // This event is raised when game has just started
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        world = new World();

        world.setGravity(new Vector(0.0f,-9.81f));

        this.window = window;
        
        
        //................block...............
        blockGraphics = new ImageGraphics("stone.broken.4.png", 10f, 1f);
        blockGraphics.setAlpha(1.0f);
        blockGraphics.setDepth(0.0f);
        
        EntityBuilder entityBuilderBlock = world.createEntityBuilder();
        entityBuilderBlock.setFixed(true);
        entityBuilderBlock.setPosition(new Vector(-5.0f, -1.0f));
        block = entityBuilderBlock.build();
        
        PartBuilder partBuilderBlock = block.createPartBuilder(); 
         
        Polygon polygon = new Polygon(
        		new Vector(0.0f, 0.0f),
        		new Vector(blockGraphics.getWidth(), 0.0f),
        		new Vector(blockGraphics.getWidth(),blockGraphics.getHeight()),
        		new Vector(0.0f, blockGraphics.getHeight()) ); 
        
        partBuilderBlock.setShape(polygon);
        partBuilderBlock.build();
        
        blockGraphics.setParent(block);
        
        
        //....................ball.................
        Circle circle = new Circle(0.5f);
        ballGraphics = new ShapeGraphics(circle, Color.BLUE, Color.BLUE,
        		0.1f, 1, 0 );
        ballGraphics.setAlpha(1.0f);
        ballGraphics.setDepth(0.0f);
        
        EntityBuilder entityBuilderBall = world.createEntityBuilder();
        entityBuilderBall.setFixed(false);
        entityBuilderBall.setPosition(new Vector(0f, 10.0f));
        ball = entityBuilderBall.build();
        
        PartBuilder partBuilderBall = ball.createPartBuilder();   
        partBuilderBall.setShape(circle);
        partBuilderBall.build();
        
        ballGraphics.setParent(ball);

        contactListener = new BasicContactListener() ;
        ball.addContactListener(contactListener);
        
        return true;
    }

    // This event is called at each frame
    @Override
    public void update(float deltaTime) {
       
    	if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) { 
    		ball.applyAngularForce(10.0f);
    	} else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) { 
    		ball.applyAngularForce(-10.0f); }

    	// contactListener is associated to ball 
    	// contactListener.getEntities() returns the list of entities in collision with ball 
    	int numberOfCollisions = contactListener.getEntities().size();
    	if (numberOfCollisions > 0){
    		ballGraphics.setFillColor(Color.RED); }
    	
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

