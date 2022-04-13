import java.util.*;

public class Cuisine {

    public List <String> commandesCuisine;
    private Scanner scanner;

    public Cuisine(Scanner scanner){
        this.scanner = scanner;
    }

    public void addOrders(List <String> liste){
        this.commandesCuisine = liste;
    }

    public void removeOrder(List <String> liste){
        System.out.println("Veuillez rentrer l'index du produit à supprimmer");
        int id = this.scanner.nextInt();
        liste.remove(id); //remove PriseCommande
        this.commandesCuisine.remove(id); //remove commandeCuisine
    }

    public void printOrders(){
        for (int i = 0 ; i < this.commandesCuisine.size() ; i++){
            System.out.println("Plat n°" + i + " : " + this.commandesCuisine.get(i));
        }
    }
}
