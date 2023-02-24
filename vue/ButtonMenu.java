package vue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;

import modele.Plateau;
import vue.VuePlateau;

/*  CLASSE QUI GERE LE MENU  */

public class ButtonMenu extends JButton implements ActionListener{
    private int CommandNumber; /* 1-play,2-continue,3-son,4-retour */
    VuePlateau rect;

    public ButtonMenu(int x,int y,int CommandNumber,VuePlateau ecran) {
        addActionListener(this);
        setBorderPainted(true);
        this.setBounds(x, y, 200, 100);
        this.CommandNumber = CommandNumber;
        this.rect = ecran;
        setLayout(null);
        setOpaque(true);

        if (CommandNumber == 1) 
        setIcon(new ImageIcon(VuePlateau.PATH+"laz.png"));

        if (CommandNumber == 2) 
        setIcon(new ImageIcon(VuePlateau.PATH+"laz.png"));

        if (CommandNumber == 4) {
            setIcon(new ImageIcon(VuePlateau.PATH+"fleche-arriere.png"));
            setBounds(x, y, 103, 74);
        }
        if (CommandNumber == 5) {
            setIcon(new ImageIcon(VuePlateau.PATH+"fleche-avant.png"));
            setBounds(x, y, 103, 74);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (CommandNumber) {
            case 1:
                rect.SetState(1);
                break;
            case 2:
                rect.SetState(2);
                break;
            case 4:
                rect.SetState(rect.GameState-1);
                break;
            case 5:
                rect.setPlat(new Plateau(rect.plat.niveau+1));
                break;
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        String text = "";

        if (CommandNumber == 1) {
            text = "Play";
        }

        if (CommandNumber == 2){
            text = "Continue";
        }

        if (CommandNumber == 4) {
            //text = "<";
        }

        if (CommandNumber == 5) {
            //text = ">";
        }

        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = this.getWidth()/2 - length/2;
        int y = this.getHeight()/2;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

}
