import java.util.*;

public class Bar {

    public List <Commande> commandesBar;
    private Scanner scanner;

    public Bar(Scanner scanner){
        this.scanner = scanner;
    }

    public void addOrders(List <Commande> liste){
        this.commandesBar = liste;
    }

    public void removeOrder(List <String> liste){
        System.out.println("Veuillez rentrer l'index du produit à supprimmer");
        int id = this.scanner.nextInt();
        liste.remove(id); //remove PriseCommande
        this.commandesBar.remove(id); //remove commandeBar
    }

    public void printOrders(){

        for (int i = 0 ; i < this.commandesBar.size() ; i++){
            System.out.println("Boisson n°" + i + " : " + this.commandesBar.get(i));
        }
    }
}
