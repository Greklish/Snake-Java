package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Field extends JPanel implements ActionListener{
    int Width = 320;
    int Dot_Size = 16;
    int Feild_Size = (320/16)*(320/16);

    //images
    Image dot;
    Image apple;

    //XY axel array
    int appleX, appleY;
    int[] X = new int[Feild_Size];
    int[] Y = new int[Feild_Size];

    int dots;

    // game keys
    boolean left = false;
    boolean right = true;
    boolean up = false;
    boolean down = false;

    Timer timer;
    boolean inGame = true;

    //creates the apple att random places
    void createApple(){
        appleY = new Random().nextInt(Width/Dot_Size)*Dot_Size;
        appleX = new Random().nextInt(Width/Dot_Size)*Dot_Size;
    }
    void initGame(){
        dots =3;
        for (int i = 0; i <dots ; i++) {
            X[i] = 48 - i*Dot_Size;
            Y[i] = 48;
        }
        createApple();
        timer = new Timer(150,this);
        timer.start();
    }
    public void actionPerformed(ActionEvent e){
        if (inGame){
            checkCollisions();
            checkApple();
            move();
        }
        repaint();
    }
    void checkApple(){
        if (X[0]==appleX && Y[0]==appleY){
            dots++;
            createApple();
        }
    }
    void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if (i>3 && X[0] == X[i] && Y[0] == Y[i]){
                inGame = false;
            }
        }
        if (Y[0]>Width){
            inGame = false;
        }
        if (X[0]>Width){
            inGame = false;
        }
        if (Y[0]<0){
            inGame = false;
        }
        if (X[0]<0){
            inGame = false;
        }
        if (!inGame){
            timer.stop();
        }
    }
    void move(){
        for (int i = dots; i > 0; i--) {
            X[i] = X[i-1];
            Y[i] = Y[i-1];
        }
        if (left){
            X[0] = X[0] - Dot_Size;
        }
        if (right){
            X[0] = X[0] + Dot_Size;
        }
        if (up){
            Y[0] = Y[0] - Dot_Size;
        }
        if (down){
            Y[0] = Y[0] + Dot_Size;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (inGame){
        g.drawImage(apple,appleX,appleY,this);
        for (int i = 0; i < dots; i++) {
            g.drawImage(dot, X[i], Y[i], this);
            }
        }else{
            String str = "Game Over";
            Font f = new Font("Helveltica",Font.BOLD,14);
            g.setColor(Color.white);
            g.drawString(str,140,Width/2);
        }
    }

    //construktor
   public Field(){
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
        setPreferredSize(new Dimension(Width,Width));

    }
    //implement images
   public void loadImages(){
        ImageIcon iia = new ImageIcon("C:\\Users\\simon\\IdeaProjects\\games\\src\\snake\\newapple.jpg");
        apple = iia.getImage();

        ImageIcon iid = new ImageIcon("C:\\Users\\simon\\IdeaProjects\\games\\src\\snake\\snakebody.png");
        dot = iid.getImage();
    }
    class FieldKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            //If the key pressed is left
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            //If the key pressed is right
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            //If the key pressed is up
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                up = true;
                right = false;
            }
            //If the key pressed is down
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }

        }

    }
}
