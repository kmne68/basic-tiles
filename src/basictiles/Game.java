/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictiles;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Keith
 */
public class Game extends Canvas implements KeyListener {
    
    /**
     * Simple tile-based collision detection
     */
    
    private Image sprite;
    private Image playerSprite;
    private Image monsterSprite;
    private BufferStrategy bufferStrategy;
    
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    private Map map;
//    private PlayerEntity player;
    private Entity player;
    private MonsterEntity monster;
    
    /**
     * Create game and begin game loop
     */
    
    public Game() {
        
        monsterSprite = spriteLoader("monster");
        playerSprite = spriteLoader("sprite");
        // load monster sprite
/*      try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("monster.png");
            if(url == null) {
                System.err.println("Unable to find sprite.");
                System.exit(0);
            }
            monsterSprite = ImageIO.read(url);
            System.out.println("monster loaded" + url.toString());
        } catch (IOException e) {
            System.err.println("Unable to load sprite");
            System.exit(0);
        }
        
        // load player sprite
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("sprite.png");
            if(url == null) {
                System.err.println("Unable to find sprite.");
                System.exit(0);
            }
            playerSprite = ImageIO.read(url);
        } catch (IOException e) {
            System.err.println("Unable to load sprite");
            System.exit(0);
        }
    */
    
        // Create the frame that holds the game
        Frame frame = new Frame("Tile Map");
        frame.setLayout(null);
        setBounds(0, 0, 500, 500);
        frame.add(this);
        frame.setSize(500, 500);
        frame.setResizable(false);
        
        // listener responds to window events
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Create key listener to respond to player key presses
        frame.addKeyListener(this);
        addKeyListener(this);
        
        // Show frame
        frame.setVisible(true);
        
        // Create buffer strategy
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        
        // Add game objects
        map = new Map();
        monster = new MonsterEntity(monsterSprite, map, 1.5f, 1.5f);
        player = new PlayerEntity(playerSprite, map, 5f, 5f);
        
        // Start game loop
        gameLoop();
    }
    
    /**
     * gameLoop will handle rendering and time tracking
     * @param e 
     */
    
    public void gameLoop() {
        boolean gameRunning = true;
        Long last = System.nanoTime();
        
        while(gameRunning) {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            
            // clear screen
            g.setColor(Color.black);
            g.fillRect(0, 0, 500, 500);
            
            // render game objects
            g.translate(100, 100);
            map.paint(g);
            player.paint(g);
            monster.paint(g);
            
            // flip buffer
            g.dispose();
            bufferStrategy.show();
            
            // pause
            try{
                Thread.sleep(400);    // was 4
            }
            catch (Exception e) {};
            
            // Time since last update
            long delta = (System.nanoTime() - last) / 1000000;
            last = System.nanoTime();
            
            // manage time for smooth movement
            for(int i = 0; i < delta / 5; i++) {
                logic(5);
            }
            
            // perform other updates
            if((delta % 5) != 0) {
                logic(delta % 5);
            }
        }        
    }
    
    
    /**
     * Perform game logic
     * @param e 
     */
    
    public void logic(float delta) {
        
        // move
        float dx = 0;
        float dy = 0;
        
        if(left) {
            dx -= 1;
        }
        
        if(right) {
            dx += 1;
        }
        
        if(up) {
            dy -= 1;
        }
        
        if(down) {
            dy += 1;
        }
        
        System.out.println("delta = " + delta + ", " + dx + ", " + dy);
        if((dx != 0) || (dy != 0)) {
            
        //    boolean object = false;
        //  if player.move returns true, check also if an object is detected  
            if (player.move(dx * delta * 0.003f, dy * delta * 0.003f) == true)
            {
                if(player.checkForObject(dx * delta * 0.003f, dy * delta * 0.003f))
        //        if(map.hasObject(dx * delta * 0.003f, dy * delta * 0.003f))
                {
                    System.out.println("Object = true" + ", " + (dx * 0.003f) + ", " + (dy * 0.003f));
                }
                else
                {
                    System.out.println("Object = false, x = " + (dx * 0.003f) + ", " + (dy * 0.003f));
                }
            }
        //    System.out.println("checkForObject from Game.logic = " + object);
        }
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        // check keyboard for keypress
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        // check keyboard for key reelase
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
    }
    
    
    public Image spriteLoader(String type) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(type + ".png");
            if (url == null) {
                System.err.println("Unable to find sprite.");
                System.exit(0);
            }
            sprite = ImageIO.read(url);
            System.out.println("monster loaded" + url.toString());
        } catch (IOException e) {
            System.err.println("Unable to load sprite");
            System.exit(0);
        }
        return sprite;
    }

}
