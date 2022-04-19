

package SnakeGame;

import java.awt.Color;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Snake Game"); //Creating instance of Jframe with title 'Snake Game'
        frame.setBounds(10,10,905,700);//x-axis, y-axis, width, height of our frame
        frame.setResizable(false);//So that user unable to resize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit application default window close operation on frame
        
        GamePanel panel = new GamePanel();//Creating instance of our 'GamePanel' class
        panel.setBackground(Color.LIGHT_GRAY);//Adding background color on panel
        frame.add(panel);//adding panel on frame
        frame.setVisible(true); //Making the frame visible
        
        
    }
}

