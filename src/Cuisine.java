import java.util.*;

public class Cuisine {

    public List <String> commandesCuisine;
    private final Scanner scanner;

    public Cuisine(Scanner scanner){
        this.scanner = scanner;
    }

    public void addOrders(List <String> liste){
        this.commandesCuisine = liste;
    }

    public void printOrders(){
        for (int i = 0 ; i < this.commandesCuisine.size() ; i++){
            System.out.println("Plat n°" + i + " : " + this.commandesCuisine.get(i));
        }
    }

    public boolean needToRemoveOrder(){
        System.out.println("Voulez-vous retirer une commande à la liste des commandes ? (n/y)");
        String myChar = this.scanner.next();

        while (myChar != "n" | myChar != "N" | myChar != "Y" | myChar != "y"){
            myChar = this.scanner.next();
        }

        return !(myChar == "n" | myChar == "N");
    }

    public void removeOrder(){
        System.out.println("Quel plat voulez-vous retirer? ");
        int id = this.scanner.nextInt();
        this.commandesCuisine.remove(id);
    }
}
