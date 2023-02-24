package modele;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

import controleur.Controleur;

public class Plateau implements Serializable{

    private static final long serialVersionUID = 1371416004898345184L;

    public final int width;
    public final int height;
    public Case[][] cases;
    protected Cible[] cibles; //Toutes les cibles du plateau
    protected LinkedList<Laser> lasers;//Tous les lasers du plateau
    public int niveau;
    protected final int nblasers;
    protected boolean win;
    public static String PATH = "./";
    //public static String PATH = "./src/";

    /*  CONSTRUCTEUR   */
    public Plateau(int height, int width, LinkedList<Laser> las, Cible[] cibles) {
        this.height=height;
        this.width=width;
        this.cases = new Case[height][width];
        this.lasers = las;
        this.nblasers = lasers.size();
        this.cibles = cibles;
    }

    public Plateau(int niveau){
        this.niveau = niveau;
        Plateau sauvegarde = reprisePartie("Niveau" + niveau);
        this.height = sauvegarde.height;
        this.width = sauvegarde.width;
        this.cases = sauvegarde.cases;
        this.lasers = (LinkedList<Laser>) sauvegarde.lasers.clone();
        this.cibles = sauvegarde.getCibles();
        this.nblasers = sauvegarde.lasers.size();
       
    }
    
    /* GETTER ET SETTER */
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Case getCase(int x, int y){
        return cases[x][y];
    }
    public void setCase(int x, int y,Case c){
        cases[x][y] = c;
    }
    public LinkedList<Laser> getLasers() {
        return lasers;
    }
    public void setLasers(LinkedList<Laser> l) {
        this.lasers = l;
    }
    public Cible[] getCibles() {
        return cibles;
    }
    public void setCibles(Cible[] cibles) {
        this.cibles = cibles;
    }
    public boolean isWin() {
        return win;
    }  

    
    /**
     * Vérifie que toutes les cibles du plateau sont atteinte
     * et va ensuite passer a vrai notre boolean win
     */
    public boolean winCondition() {
        boolean res=true;
        for(Cible c: this.cibles){
            if(!c.isAtteint())
                res = false;
        }
        if(res){
            win = true;
        }
        return res;
    }

    /**
     * Pour chaque cibles du plateau, vérifie si un point d'un des lasers
     * est a la meme coordonées que notre cible.
     */
     public void calculerCibles(){
        for(int i = 0; i < cibles.length; i++){
            cibles[i].atteint = false;
        }
        for(Laser l : lasers){
            for(Point point : l.points){
                for(int i = 0; i < cibles.length; i++){
                    if(cibles[i].p.x == point.x && cibles[i].p.y == point.y)
                        cibles[i].atteint = true;
                }
            }
        }
    }
     
    /**
     *  Va effectuer le déplacement d'un bloc sur le plateau
     *  uniquement si cela est possible grace a la méthode deplacementPosible.
     * @param x1 x de départ
     * @param y1 y de départ
     * @param x2 x d'arriver
     * @param y2 y d'arriver
     * @return vrai si le bloc a été deplacer.
     */
    public boolean deplacerBloc(int x1,int y1,int x2,int y2) {
        
        if (deplacementPossible(x1, y1, x2, y2) && !(x1 == x2 && y1 == y2)){
            getCase(x2, y2).ajouterBloc(getCase(x1, y1).getBloc());
            getCase(x1, y1).enleverBloc();
            return true;
        }

        return false;
    }

    /**
     * Boolean qui nous permet de savoir si la case a la position
     * (x,y) est une instance de CaseVisible
     * @param x coordonées x
     * @param y coordonées y
     * @return vrai si il est possible de placer un bloc
     * sur la case en question
     */
    public boolean estVisible(int x, int y){
        return cases[x][y] instanceof CaseVisible;
    }

    
    /**
     * Méthode qui va initialiser le calcul des points des lasers du plateau.
     * Tous en vérifiant ses déviations et la condition de victoire.
     */
    public void initLaser(){
        int size = lasers.size();
        for(int i = nblasers; i<size; i++){
            lasers.remove(nblasers);
        }
        for(int i=0;i < lasers.size();i++){
            if (lasers.get(i)!=null ){
                lasers.get(i).points = new LinkedList<Point>();
                calculerChemin(lasers.get(i));
            }
        }
        calculerCibles();
        winCondition();
    }

        /**
     * 
     * @param x1 x de départ
     * @param y1 y de départ
     * @param x2 x d'arriver
     * @param y2 y d'arriver
     * @return vrai si il est possible de deplacer un bloc
     * d'un point (x1,y1) a un autre point (x2,y2)
     * */
    public boolean deplacementPossible(int x1, int y1, int x2, int y2){
        if(x1 == x2 && y1 == y2){
            return true;
        }
        return (!(getCase(x1, y1) instanceof CaseCachee) &&
        !(getCase(x2, y2) instanceof CaseCachee) &&
        !getCase(x2, y2).blocPresent());
    }


    /**
     * Ajoute au laser l tous les points qu'il traverse, en fonction de 
     * son point de départ et de son orientation.
     * @param l le laser en question
     */
    public void calculerChemin(Laser l){
        int i = l.x;
        int j = l.y;
        int angletmp = l.orientation;
        int oldtmp;
        while(i <= 2*this.height && j <= 2*this.width && i >=0 && j >=0) {
            l.points.add(new Point(i,j));
            //cas du bloc prismatique
            oldtmp = angletmp;
            angletmp = nouvelAngle(i, j, angletmp);
            if (angletmp == 90 ) {
                l.points.add(new Point(i, j+2));
                j += 2;
                angletmp = oldtmp;
            }
            if(angletmp == 270){
                l.points.add(new Point(i, j-2));
                j -= 2;
                angletmp = oldtmp;
            }
            if(angletmp == 180) {
                l.points.add(new Point(i+2, j));
                i+=2;
                angletmp = oldtmp;
            }
            if(angletmp == 0){
                l.points.add(new Point(i-2, j));
                i-=2;
                angletmp = oldtmp;
            }
            if (angletmp == 45) {
                i--;
                j++;
            } else if (angletmp == 135) {
                i--;
                j--;

            } else if (angletmp == 225) {
                i++;
                j--;

            } else if (angletmp == 315) {
                i++;
                j++;

            } else if (angletmp == -1) {
                i=1-i;
                j=1-j;
            }
        }
    }

   /**
    * Renvoie l'angle que le laser va avoir après être passé par le point (x, y) 
    * (coordonnées de type cible)
    * Cet angle est calculé en fonction du type de bloc présent 
    * à côté du point (x,y) dans la direction "angle".
    * Pour les bloc SemiReflechissant Teleporteur et Plus, on traitera ces cas
    * directement dans cette méthode, étant donné qu'elle nécesite l'ajout d'un 
    * nouveau laser.
    * Pour les autres types de blocs, on appelle leur méthode déviationLaser pour calculer l'angle
    * @param x, y: les coordonnées du laser
    * @param angle d'orientaion du laser
    * @return le nouvel angle d'orientation du laser après être passé par le point x, y
    */
    public int nouvelAngle(int x, int y, int angle){
        Point caseVerif = caseAVerifier(x, y, angle);
        if (caseVerif!=null && getCase(caseVerif.x, caseVerif.y).blocPresent()){
            String nomBloc=getCase(caseVerif.x, caseVerif.y).getBloc().getType();

            switch (nomBloc){
                case "BlocSemiReflechissant":{
                    int k=0;
                    //point où on doit ajouter un nouveau laser (si il n'existe pas déjà)
                    Point newLaserCoords = null;
                    switch (angle) {
                        case 45:{
                            newLaserCoords = new Point(x - 1, y + 1);
                            break;
                        }
                        case 135:{
                            newLaserCoords = new Point(x - 1, y - 1);
                            break;
                        }
                        case 225:{
                            newLaserCoords = new Point(x + 1, y - 1);
                            break;
                        }
                        case 315:{
                            newLaserCoords = new Point(x + 1, y + 1);
                            break;
                        }
                        default:
                    }
                    if(newLaserCoords != null){
                        Laser nouveauLaser = new Laser(newLaserCoords.x, newLaserCoords.y, angle);
                        if(!dejaPresent(nouveauLaser)){
                            lasers.add(nouveauLaser);
                        }
                    }
                    return getCase(caseVerif.x, caseVerif.y).getBloc().deviationLaser(x, y, angle);
                }
                case "BlocTeleporteur":
                    int a = 0,b = 0;
                    for (int i = 0; i < this.height; i++) {
                        for (int j = 0; j < this.width; j++) {
                            if (!(caseVerif.x == i && caseVerif.y == j) && getCase(i,j).getBloc() instanceof BlocTeleporteur) {
                                a = i;
                                b = j;
                            }
                        }
                    }
                    int diff_i = caseVerif.x-a;
                    int diff_j = caseVerif.y-b;
                    Point newLaserCoords = null;
                    if (x%2 == 1 && y%2==0) {
                        switch (angle){
                            case 45:
                            case 315:{
                                newLaserCoords = new Point(x - 2*diff_i, y - 2*diff_j + 2);
                                break;
                            }
                            case 135:
                            case 225:{
                                newLaserCoords = new Point(x - 2*diff_i, y - 2*diff_j - 2);
                                break;
                            }
                        }
                    }
                    if (x%2 == 0 && y%2==1) {
                        switch (angle){
                            case 45:
                            case 135:{
                                newLaserCoords = new Point(x - 2*diff_i - 2, y - 2*diff_j);
                                break;
                            }
                            case 315:
                            case 225:{
                                newLaserCoords = new Point(x - 2*diff_i + 2, y - 2*diff_j);
                                break;
                            }
                        }
                    }
                    if(newLaserCoords != null){
                        Laser nouveauLaser = new Laser(newLaserCoords.x, newLaserCoords.y, angle);
                        if(!dejaPresent(nouveauLaser)){
                            lasers.add(nouveauLaser);
                        }
                    }
                    return -1;
                case "BlocPlus":{
                    Point newLaserCoords1 = new Point((caseVerif.x)*2-1, (caseVerif.y)*2);
                    Laser Laser1 = new Laser(newLaserCoords1, -1);
                    Point newLaserCoords2 = new Point((caseVerif.x)*2, (caseVerif.y)*2-1);
                    Laser Laser2 = new Laser(newLaserCoords2, -1);
                    Point newLaserCoords3 = new Point((caseVerif.x)*2-2, (caseVerif.y)*2-1);
                    Laser Laser3 = new Laser(newLaserCoords3, -1);
                    Point newLaserCoords4 = new Point((caseVerif.x)*2-1, (caseVerif.y)*2-2);
                    Laser Laser4 = new Laser(newLaserCoords4, -1);
                    if(!dejaPresent(Laser1)){
                        lasers.add(Laser1);
                    }
                    if(!dejaPresent(Laser2)){
                        lasers.add(Laser2);
                    }
                    if(!dejaPresent(Laser3)){
                        lasers.add(Laser3);
                    }
                    if(!dejaPresent(Laser4)){
                        lasers.add(Laser4);
                    }
                    return getCase(caseVerif.x, caseVerif.y).getBloc().deviationLaser(x, y, angle);
                }
                default:{
                    return getCase(caseVerif.x, caseVerif.y).getBloc().deviationLaser(x, y, angle);
                }
            }
        }
        return angle ;
    }

    //retourne true si le laser "laser" est déjà présent dans la liste "this.lasers" et false sinon
    public boolean dejaPresent(Laser laser){
        for(Laser l: this.lasers){
            if(l.equals(laser)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne les coordonnées du bloc à côté du point x, y (type cible) en regardant dans la direction "angle"
     * @param x, y: coordonnées de type "laser"
     * @param angle: la direction dans laquelle il faut regarder
     * @return un tableau de taille 2, composé des coordonées du bloc
     */
    public Point caseAVerifier(int x, int y, int angle){
        //int[] res = new int[2];
        Point res = new Point();
        if(x < 0 || y < 0 || x >= 2*this.height || y >= 2*this.width){
            return null;
        }
        if(x%2 == 1){
            if(angle == 45 || angle == 315){
                res.x = (x+1)/2;
                res.y = (y+2)/2;
            }

            else if(angle == 225 || angle == 135){
                res.x = (x+1)/2;
                res.y = (y)/2;
            }
        }
        else if(y%2 == 1){
            if(angle == 45 || angle == 135){
                res.x = (x)/2;
                res.y = (y+1)/2;
            }
            else if(angle == 225 || angle == 315){
                res.x = (x+2)/2;
                res.y = (y+1)/2;
            }
        }
        if(res.x >= this.height || res.y >= this.width)
            return null;
        return res;
    }

    /*
            MÉTHODE DE SAUVEGARDE DU PLATEAU
    */
    public void sauvegarder(String fileName) throws IOException {
        try {

            FileOutputStream file = new FileOutputStream(Plateau.PATH + "niveaux/"+fileName+".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            System.out.println(fileName + " sauvegardé");
            out.close();

        }catch(FileNotFoundException fnf){
            System.out.println("fichier de sauvegarde non trouvé");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Partie non sauvegardée erreur (fichier de sauvegarde?)");
        }catch(NullPointerException np){
            System.out.println("ressource pas trouvée");
        }

    }

    public static Plateau reprisePartie (String filename){
        Plateau p = null;
        try {
            System.out.println(Plateau.PATH + "niveaux/"+filename+".ser");
            FileInputStream file = new FileInputStream(Plateau.PATH + "niveaux/"+filename+".ser");
            ObjectInputStream in = new ObjectInputStream(file);

            p = (Plateau)in.readObject();

            System.out.println("objet récupéré");

            in.close();
            return (Plateau)p;
        }
        catch(FileNotFoundException e) {
            System.out.println("fichier non trouvé");
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("erreur");
            e.printStackTrace();
        }catch(NullPointerException e) {
            System.out.println("ressource pas trouvée");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("L'objet n'est pas de la bonne classe");
            e.printStackTrace();
        }
        return p;
    }

} 