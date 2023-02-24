package vue;

import modele.*;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/*  L'AIRE DE JEU  */

public class VuePlateau extends JLabel {
    protected Plateau plat;
	public JLabel[][] bloc;
	public MouseAdapter ma;
	public FinDePartie fin;


	//public static final String PATH="./src/icones/";
	public static final String PATH="./icones/";
	//gestion des différents écran
	public int GameState;
	final int StartState = 0;
	final int LevelsState = 1;
	final int Niveau = 2;

    /*   CONSTRUCTEUR  */
    //initialise aussi les deplacements avec un mouseAdapter
	public VuePlateau(){
		GameState = 0;
		ma = new MouseAdapter() {
			JLabel selectionPanel = null;
			Point selectionlabelposition = null;
			Point panelClickposition = null;
			//coordonnées en pixels de la nouvelle position du bloc quand il est déplacé avec la souris
			int newX, newY;
			//coordonnées dans le tableau de la nouvelle position du bloc quand il est déplacé avec la souris
			int newI, newJ;

			@Override
			public void mousePressed(MouseEvent e) {
				Component pressedComp = findComponentAt(e.getX(), e.getY());
				if (pressedComp != null && pressedComp instanceof JLabel && "Bloc".equals(pressedComp.getName())) {

					selectionPanel = (JLabel) pressedComp;
					selectionlabelposition = selectionPanel.getLocation();
					panelClickposition = e.getPoint();
					super.mousePressed(e);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (selectionPanel != null
						&& selectionlabelposition != null
						&& panelClickposition != null && selectionPanel.getName() != "null") {

					Point newPanelClickPoint = e.getPoint();

					newX = selectionlabelposition.x + (newPanelClickPoint.x - panelClickposition.x);
					newY = selectionlabelposition.y + (newPanelClickPoint.y - panelClickposition.y);
					newI = newY/50;
					newJ = newX/50;
					if(newI > 0 && newJ > 0 && newJ < plat.getWidth() && newI < plat.getHeight() &&
							plat.deplacementPossible(selectionlabelposition.y/50, selectionlabelposition.x/50, newI, newJ)){
						selectionPanel.setLocation((newJ)*50, (newI)*50);
					}
					else {
						newX = selectionlabelposition.x;
						newY = selectionlabelposition.y;
						newI = newY / 50;
						newJ = newX / 50;

						selectionPanel.setLocation((newJ) * 50, (newI) * 50);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(newI > 0 && newJ > 0 && newJ < plat.getWidth() && newI < plat.getHeight() &&
						plat.deplacementPossible(selectionlabelposition.y/50, selectionlabelposition.x/50, newI, newJ)){
					selectionPanel.setLocation((newJ)*50, (newI)*50);
					plat.deplacerBloc(selectionlabelposition.y/50, selectionlabelposition.x/50, newI, newJ);
					plat.initLaser();
					selectionlabelposition.y = newI*50;
					selectionlabelposition.x = newJ*50;
					repaint();
				}
			}
		};
		addMouseListener(ma);
		addMouseMotionListener(ma);
		
		this.setFocusable(true);
		setLayout(null);
		setOpaque(true);

		setIcon(new ImageIcon(VuePlateau.PATH + "arriereplan.png"));

	}

	public Plateau getPlat() {
		return plat;
	}
	public void setPlat(Plateau plat) {
        clear();

		this.plat = plat;
        initbloc();
        paintComponent(this.getGraphics());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (GameState == StartState) {

			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text = "Lazors";
			int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			int x = this.getWidth()/2 - length/2;
			int y = this.getHeight()/4;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
		   
			ButtonMenu bm1 = new ButtonMenu(300, 400, 1, this);
			//ButtonMenu bm2 = new ButtonMenu(300, 600, 2, this);

			add(bm1);
			//add(bm2);
		}

		if (GameState == LevelsState) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
			String text = "Lazors";
			int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			int x = this.getWidth()/2 - length/2;
			int y = this.getHeight()/4;
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			ButtonMenu retour = new ButtonMenu(20, this.getHeight()-150, 4, this);
			add(retour);

			LevelButton lv1 = new LevelButton(100, 250, 1, this);
            add(lv1);
            LevelButton lv2 = new LevelButton(100, 320, 2, this);
            add(lv2);
            LevelButton lv3 = new LevelButton(100, 390, 3, this);
            add(lv3);
            LevelButton lv4 = new LevelButton(100, 460, 4, this);
            add(lv4);
            LevelButton lv5 = new LevelButton(100, 530, 5, this);
            add(lv5);
            LevelButton lv6 = new LevelButton(450, 250, 6, this);
            add(lv6);
            LevelButton lv7 = new LevelButton(450, 320, 7, this);
            add(lv7);
            LevelButton lv8 = new LevelButton(450, 390, 8, this);
            add(lv8);
            LevelButton lv9 = new LevelButton(450, 460, 9, this);
            add(lv9);
            LevelButton lv10 = new LevelButton(450, 530, 10, this);
            add(lv10);
			
		}
		

		if (GameState == Niveau) {
		   	Plateau(g);
		   	TraceLaser(g);
		   	Cible(g);
		   	ButtonMenu retour = new ButtonMenu(20, this.getHeight()-150, 4, this);
		   	add(retour);
			
		   	if(plat.isWin()){
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,26F));
                g2.setColor(Color.black);
			    g2.drawString("GAGNÉ", 650, 200);
				if (plat.niveau <10) {
					ButtonMenu next = new ButtonMenu(650, this.getHeight()-150, 5, this);
                	add(next);
				}
                
		   	}
		}
	}
    /**
     * Affichage des lasers.
     * Trace un trait entre chaque point du laser
     * @param g "feuille" sur laquelle on peint
     */
	public void TraceLaser(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for (Laser l : plat.getLasers()) {
			//on vérifie bien que l n'est pas null
			if(l != null){
				//on parcours l'ensemble des points de notre laser
				Point p, suiv;
				for(int i = 0; i<l.getPoints().size()-1; i++){
					if(l.getPoints().get(i+1) != null){//et la on vérifie que le point suivant n'est pas null
						p = l.getPoints().get(i);
						suiv = l.getPoints().get(i+1);
						Graphics gpl = (Graphics)g2;
						Line2D line = new Line2D.Float(50 + p.y*25,50 + p.x*25, 50 + suiv.y*25, 50 + suiv.x*25);
						g2.setColor(Color.red);
						g2.setStroke(new BasicStroke((float) 4.0,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));
						g2.draw(line);

					}
				}
			}
		}


	}
    /**
     * Affichage de l'ensemble du plateau
     * @param g "feuille" sur laquelle on peint
     */
	public void Plateau(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(int i=1; i < plat.getHeight(); i++){
			for(int j=1; j < plat.getWidth(); j++){
				if(plat.getCase(i, j) instanceof CaseVisible){
					g2.setColor(Color.gray.brighter());
					int thickness = 2;
					Stroke oldStroke = g2.getStroke();
					g2.setStroke(new BasicStroke(thickness));
					Image img1 = Toolkit.getDefaultToolkit().getImage(PATH + "case.png");
					g2.drawImage(img1, j*50, i*50, this);
					if(plat.getCase(i, j).blocPresent()){
						g2.fillRect(j*50, i*50, 50, 50);

					}
				}
				if(plat.getCase(i, j).blocPresent()){
					bloc[i][j]=new JLabel();
					bloc[i][j].setOpaque(true);
					bloc[i][j].setLayout(null);
				    //bloc[i][j].setName("Bloc");
					bloc[i][j].setBounds(50*j, 50*i, 50, 50);
					if (plat.getCase(i, j) instanceof CaseVisible){
						bloc[i][j].setIcon(new ImageIcon(PATH + "case.png"));
					}
				}
			}
		}
	}

	/**
     * Affichage des cibles
     * @param g "feuille" sur laquelle on peint
     */
	public void Cible(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < plat.getCibles().length; i++) {
			int x = plat.getCibles()[i].getPoint().x;
			int y = plat.getCibles()[i].getPoint().y;
			int diametre = 7;
			if(plat.getCibles()[i].isAtteint()) {
				g2.setColor(Color.GREEN);
				g2.fillOval(50-diametre/2+y*25, 50-diametre/2+x*25, diametre, diametre);
			} else {
				 g2.setColor(Color.red);
				 g2.fillOval(50-diametre/2+y*25, 50-diametre/2+x*25, diametre, diametre);
			}
			   
		}  
	}

    /**
     * Affichage des différentes image(dans le package icone)
     */
	public void initbloc() {
		bloc = new JLabel[plat.getHeight()][plat.getWidth()];
		for (int i = 0; i < plat.height; i++) {
			for (int j = 0; j < plat.width; j++) {
				if(plat.getCase(i, j).blocPresent()){
					bloc[i][j]=new JLabel();
					bloc[i][j].setOpaque(true);
					bloc[i][j].setLayout(null);
					bloc[i][j].setBounds(50*j, 50*i, 50, 50);
					if (plat.getCase(i, j) instanceof CaseVisible){
						bloc[i][j].setIcon(new ImageIcon(VuePlateau.PATH + "case.png"));
					}
					String type = plat.getCase(i, j).getBloc().getType();
					if(plat.getCase(i, j).getBloc().fixe){
						bloc[i][j].setName("BlocFixe");
						bloc[i][j].setIcon(new ImageIcon(VuePlateau.PATH + type+ "Fixe" + ".png"));
					}else{
						bloc[i][j].setName("Bloc");
						bloc[i][j].setIcon(new ImageIcon(VuePlateau.PATH + type + ".png"));
					}
					add(bloc[i][j]);
					
				}
			}
		}
	}
    
    /**
     * Arrondi une image.
     * @param image
     * @param cornerRadius
     * @return le paramètres image arrondi
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
    /**
     * Change l'état actuel du jeu(de la scène).
     * @param state etat actuel du jeu
     */
    public void SetState(int state) {
        
        clear();
        this.GameState = state;
       // paintComponent(this.getGraphics());
        if (state == 2) {
            initbloc();
        }
    }

    /**
     * Efface toute l'aire de jeu, avant de la repaindre
     */
    public void clear() {
        removeAll();
        repaint();
    }
}