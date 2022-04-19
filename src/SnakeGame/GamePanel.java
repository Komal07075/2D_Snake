
package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    
    //For storing snake's length position in x and y-axis
    private int[] snake_X_Length = new int[750];
    private int[] snake_Y_Length = new int[750];
    
    private int lengthOfSnake = 3;//Initial length of snake will be 3
    
    //Declaring x and y-axis position for snake's movement in game 
    private int[] xPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] yPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    private Random random = new Random();
    private int enemyX, enemyY;//declaring enemy for x and y-axis
    
    //Setting snake's initial position towards right
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    
    private int moves = 0;
    private int score = 0;
    private boolean gameOver = false;
    
    //Creating ImageIcon with reference to the named resources
    private ImageIcon snaketitle = new ImageIcon( getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftMouth = new ImageIcon( getClass().getResource("leftMouth.png"));
    private ImageIcon rightMouth = new ImageIcon( getClass().getResource("rightMouth.png"));
    private ImageIcon upMouth = new ImageIcon( getClass().getResource("upMouth.png"));
    private ImageIcon downMouth = new ImageIcon( getClass().getResource("downMouth.png"));
    private ImageIcon snakeImage = new ImageIcon( getClass().getResource("snakeImage.png"));
    private ImageIcon enemy = new ImageIcon( getClass().getResource("enemy.png"));
    
    //Using timer to schedule task and delaying it by 100 milliseconds
    private Timer timer;
    private int delay = 100;
    
    //Creating Constructor of GamePanel
    GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        
        //Initializing timer 
        timer = new Timer(delay, this);
        timer.start();
        
        newEnemy();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose tools | templates
        
        //Creating border of upper and lower rectangle of GamePanel
        g.setColor(Color.RED);//Set color of border
        g.drawRect(24, 10, 851, 55);//Upper rectangle of GamePanel 
        g.drawRect(24, 74, 851, 576);//Lower rectangle of GamePanel
        snaketitle.paintIcon(this, g, 25, 11);//adding 'snaketitle' image in upper rectangle with components (this JPanel, graphics object, x axis, y axis) )
        
        //adding black color in lower rectangle
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);//with these x axis, y axis, width, height components
        
        //Snake's initial position in lower rectangular panel with moves = 0
        if(moves == 0) {
            //Snake's initial postion in x-axis
            snake_X_Length[0] = 100;//initial position of snake's mouth
            snake_X_Length[1] = 75;//Initial position of snake's body
            snake_X_Length[2] = 50;
            
            //Snake's initial position in y-axis
            snake_Y_Length[0] = 100;
            snake_Y_Length[1] = 100;
            snake_Y_Length[2] = 100;
        }
        
        //Snake's head image according to it's position
        //If snake's position is towards left
        if(left) {
            leftMouth.paintIcon(this, g, snake_X_Length[0], snake_Y_Length[0]);
        }
        //If snake's position is towards right
        if(right) {
            rightMouth.paintIcon(this, g, snake_X_Length[0], snake_Y_Length[0]);
        }
        //If snake's position is towards upward
        if(up) {
            upMouth.paintIcon(this, g, snake_X_Length[0], snake_Y_Length[0]);
        }
        //If snake's position is towards downward
        if(down) {
            downMouth.paintIcon(this, g, snake_X_Length[0], snake_Y_Length[0]);
        }
        
        //Snake's body image according to it's length
        for(int i = 1; i < lengthOfSnake; i++) {
            snakeImage.paintIcon(this, g, snake_X_Length[i], snake_Y_Length[i]);
        }
        
        //Enemy's image in x and y-axis
        enemy.paintIcon(this, g, enemyX, enemyY);
        
        //painting 'Game Over' and 'Press SPACE to Restart' when game is over
        if(gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD,50));
            g.drawString("Game Over", 300, 300);
            
            g.setFont(new Font("Arial", Font.PLAIN,20));
            g.drawString("Press SPACE to Restart", 320, 350);
        }
        
        //Painting scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Score : " + score, 750, 30);
        g.drawString("Length : " + lengthOfSnake, 750, 50);
        
        g.dispose();//It will remove the component from view as well as from allocated memory
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //moving snake's body in x and y-axis
        for(int i = lengthOfSnake-1; i>0; i--) {
            snake_X_Length[i] = snake_X_Length[i-1];
            snake_Y_Length[i] = snake_Y_Length[i-1];
        }
        
        //Snake's head position moving in x and y-axis
        if(left) {
            snake_X_Length[0] = snake_X_Length[0] - 25;
        }
        if(right) {
            snake_X_Length[0] = snake_X_Length[0] + 25;
        }
        if(up) {
            snake_Y_Length[0] = snake_Y_Length[0] - 25;
        }
        if(down) {
            snake_Y_Length[0] = snake_Y_Length[0] + 25;
        }
        
        //if snake move across the x and y-axis, it should re-enter in that axis
        if(snake_X_Length[0] > 850) snake_X_Length[0] = 25;
        if(snake_X_Length[0] < 25) snake_X_Length[0] = 850;
        
        if(snake_Y_Length[0] > 625) snake_Y_Length[0] = 75;
        if(snake_Y_Length[0] < 75) snake_Y_Length[0] = 625;
        
        collidesWithEnemy();//Calling method
        collidesWithBody();//Calling method
        
        repaint();//Recalling paint method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        //If space pressed then restart the game
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            restart();
        }
        
        //if we press left key, snake should move toward left but can't move in opposite direction
        if(e.getKeyCode() == KeyEvent.VK_LEFT && (!right)) {
            left = true;
            right = false;
            up = false;
            down = false;
            moves++;
        }
        //if we press right key, snake should move toward right but can't move in opposite direction
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && (!left)) {
            left = false;
            right = true;
            up = false;
            down = false;
            moves++;
        }
        //if we press up key, snake should move toward upward but can't move in opposite direction
        if(e.getKeyCode() == KeyEvent.VK_UP && (!down)) {
            left = false;
            right = false;
            up = true;
            down = false;
            moves++;
        }
        //if we press down key, snake should move toward downward but can't move in opposite direction
        if(e.getKeyCode() == KeyEvent.VK_DOWN && (!up)) {
            left = false;
            right = false;
            up = false;
            down = true;
            moves++;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    private void newEnemy() {
        //declaring enemy's dimensions in x and y-axis
        enemyX = xPos[random.nextInt(34)];
        enemyY = yPos[random.nextInt(23)];
        
        //Enemy can't be located on snake's body
        for(int i = lengthOfSnake-1; i>=0; i--) {
            if(snake_X_Length[i] == enemyX && snake_Y_Length[i] == enemyY) {
                newEnemy();
            }
        }
    }
   //If snake collides with enemy, length of snake should get increased by 1
    private void collidesWithEnemy() {
        if(snake_X_Length[0] == enemyX && snake_Y_Length[0] == enemyY) {
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }
    
    //If enemy collides with it's own body, game should be over
    private void collidesWithBody() {
        for(int i =lengthOfSnake-1; i > 0; i--) {
            if(snake_X_Length[i] == snake_X_Length[0] && snake_Y_Length[i] == snake_Y_Length[0]) {
                timer.stop();
                gameOver = true;
            }
        }
    }
    
    //Restarting the game, when game is over
    private void restart() {
        gameOver = false;
        moves = 0;
        score = 0;
        lengthOfSnake = 3;
        left = false;
        right = true;
        up = false;
        down = false;
        timer.start();
        repaint();
    }
}
