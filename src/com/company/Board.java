package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {

    JButton[][] buttons = new JButton[9][8];
    ImageIcon BlueCircle = new ImageIcon("src/BluePlayer.jpg");
    ImageIcon GreenCircle = new ImageIcon("src/RedPlayer.jpg");
    boolean faound = false;
    int tor = 0;
    JLabel label = new JLabel();

    Board(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800,650);
        this.setLayout(new GridLayout(9,8,5,5));

        for(int i =0;i<8;i++){
            for(int j =0;j<8;j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.GRAY);
                this.add(buttons[i][j]);
            }
        }
        for(int k =0;k<8;k++){
            buttons[8][k] = new JButton();
            buttons[8][k].setBackground(Color.CYAN);
            this.add(buttons[8][k]);
            buttons[8][k].addActionListener(this);
        }
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int k =0;k<8;k++){
            if(e.getSource()==buttons[8][k]&&tor == 0&&faound == false){
                PutInTheRightPozition(buttons,k,BlueCircle);
                faound=ChackColWin(buttons,faound,BlueCircle);
                faound=ChackRowWin(buttons,faound,BlueCircle);
                faound=ChackA1Win(buttons,faound,BlueCircle);
                faound=ChackA2Win(buttons,faound,BlueCircle);
                System.out.println(faound);
                tor=1;
            }
            else if(e.getSource()==buttons[8][k]&&tor == 1&&faound == false){
                PutInTheRightPozition(buttons,k,GreenCircle);
                faound=ChackColWin(buttons,faound,GreenCircle);
                faound=ChackRowWin(buttons,faound,GreenCircle);
                faound=ChackA1Win(buttons,faound,GreenCircle);
                faound=ChackA2Win(buttons,faound,GreenCircle);
                System.out.println(faound);
                tor = 0;
            }
        }
        if(tor == 0&& faound==true){
            EndGame endGame = new EndGame();
            label.setText("the red player is the winner");
            endGame.add(label);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
        }
        if(tor == 1&& faound==true){
            EndGame endGame = new EndGame();
            label.setText("the blue player is the winner");
            endGame.add(label);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
        }
    }
    public static JButton[][] PutInTheRightPozition(JButton[][] b,int k,ImageIcon icon) {
        int i = 7;
        boolean faound = false;
        while (i >= 0) {
            if(b[i][k].getIcon()== null&& faound == false){
                b[i][k].setIcon(icon);
                faound = true;
            }
            i--;
        }
        return  b;
    }
    public static boolean ChackColWin(JButton[][] a ,boolean faound,ImageIcon icon){
        int count = 1;
        for(int i = 7; i >=0 ; i--)
        {
            for(int j = 7; j >= 3; j--)
            {
                if(a[i][j].getIcon()==icon&&a[i][j].getIcon()!=null)
                {
                    int s = j - 1;
                    for(int k = 0; k < 3; k++)
                    {
                        if (a[i][j].getIcon() == a[i][s].getIcon()&&a[i][j].getIcon()!=null)
                        {
                            count++;
                            s--;
                        }
                    }
                }
                if (count == 4)
                {
                    faound = true;
                }
                count = 1;
            }
        }
        return faound;
    }
    public static boolean ChackRowWin(JButton[][] a ,boolean faound,ImageIcon icon){
        int count = 1;
        for (int i = 7; i >= 3; i--)
        {
            for (int j = 7; j >= 0; j--)
            {
                if (a[i][j].getIcon()==icon&&a[i][j].getIcon()!=null)
                {
                    int s = i - 1;
                    for (int k = 0; k < 3; k++)
                    {
                        if (a[i][j].getIcon() == a[s][j].getIcon()&&a[i][j].getIcon()!=null)
                        {
                            count++;
                            s--;
                        }
                    }
                }
                if (count == 4)
                {
                    faound = true;
                }
                count = 1;
            }
        }
        return faound;
    }
    public static boolean ChackA1Win(JButton[][] a ,boolean faound,ImageIcon icon){
        int count = 1;
        for(int i = 0; i <= 4; i++)
        {
            for(int j = 0; j <=4; j++)
            {
                if (a[i][j].getIcon() == icon&&a[i][j].getIcon()!=null)
                {
                    int s = i + 1;
                    int k = j + 1;
                    for(int q = 0; q < 3; q++)
                    {
                        if (a[i][j].getIcon() == a[s][k].getIcon()&&a[i][j].getIcon()!=null)
                        {
                            count++;
                            s++;
                            k++;
                        }
                    }
                }
                if(count == 4)
                {
                    faound = true;
                }
                count = 1;
            }
        }
        return faound;
    }
    public static boolean ChackA2Win(JButton[][] a ,boolean faound,ImageIcon icon){
        int count = 1;
        for (int i = 7; i >= 4; i--)
        {
            for (int j = 7; j >= 4; j--)
            {
                if (a[i][j].getIcon() == icon)
                {
                    int s = i + 1;
                    int k = j - 1;
                    for (int q = 0; q < 3; q++)
                    {
                        if (a[i][j].getIcon() == a[s][k].getIcon())
                        {
                            count++;
                            s++;
                            k--;
                        }
                    }
                }
                if (count == 4)
                {
                    faound = true;
                }
                count = 1;
            }
        }
        return faound;
    }

}
