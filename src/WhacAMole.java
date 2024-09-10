import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.util.Scanner;


public class WhacAMole {
    int boardwidth= 600; 
    int boardheight= 650;


    JFrame frame = new JFrame("Mario: Whac A Mole");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel diffPanel = new JPanel();

    JButton[] board= new JButton[9];
    ImageIcon moleIcon;
    ImageIcon plantIcon;

    JButton currMoleTile;
    JButton currPlantTile;


    Random random = new Random();
    Timer setMoleTimer;
    Timer setPlantTimer;
    int score=0;
    Scanner sc=new Scanner(System.in);


    WhacAMole(){
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: 0");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setPreferredSize(new Dimension(600,50));
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        // boardPanel.setBounds(0, 100, boardwidth, 600);
        frame.add(boardPanel);
        
        Image plantImg = new ImageIcon(getClass().getResource("./piranha.png")).getImage();
        plantIcon = new ImageIcon(plantImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        Image moleImg = new ImageIcon(getClass().getResource("./monty.png")).getImage();
        moleIcon = new ImageIcon(moleImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));


        for(int i =0; i<9;i++){
            JButton tile = new JButton();
            board[i]=tile;
            boardPanel.add(tile);
            tile.setFocusable(false);
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton tile = (JButton)e.getSource();
                    if(tile==currMoleTile){
                        score+=10;
                        textLabel.setText("Score: "+ Integer.toString(score));
                    }
                    else if(tile==currPlantTile){
                        textLabel.setText("Game Over: "+ Integer.toString(score));
                        setMoleTimer.stop();
                        setPlantTimer.stop();
                        for(int i=0;i<9;i++){
                            board[i].setEnabled(false);
                        }
                    }
                }
            });
        }

        setMoleTimer = new Timer(900, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(currMoleTile != null){
                    currMoleTile.setIcon(null);
                    currMoleTile = null;
                }

                int num=random.nextInt(9);
                JButton tile= board[num];

                if(currPlantTile==tile) return;

                currMoleTile = tile;
                currMoleTile.setIcon(moleIcon);
            }
        });

        setPlantTimer = new Timer(1200, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(currPlantTile != null){
                    currPlantTile.setIcon(null);
                    currPlantTile = null;
                }

                int num=random.nextInt(9);
                JButton tile= board[num];

                if(currMoleTile==tile) return;

                currPlantTile = tile;
                currPlantTile.setIcon(plantIcon);
            }
        });

        setMoleTimer.start();
        setPlantTimer.start();
        frame.setVisible(true);
    }
}