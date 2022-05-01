import java.util.*;

public class Bar {

    public List <String> commandesBar;
    private Scanner scanner;

    public Bar(Scanner scanner){
        this.scanner = scanner;
    }

    public void addOrders(List <String> liste){

        this.commandesBar = liste;
    }

    public void removeOrder(){
        System.out.println("Veuillez rentrer l'index du produit à supprimmer");
        int id = this.scanner.nextInt();
        this.commandesBar.remove(id);
    }

    public boolean needToRemoveOrder(){
        System.out.println("Voulez-vous retirer une commande à la liste des commandes ? (n/y)");
        String myChar = this.scanner.next();

        while (myChar.equalsIgnoreCase("y") || myChar.equalsIgnoreCase("n")){
            myChar = this.scanner.next();
        }

        return myChar.equalsIgnoreCase("y");
    }

    public void printOrders(){
        for (int i = 0 ; i < this.commandesBar.size() ; i++){
            System.out.println("Boisson n°" + i + " : " + this.commandesBar.get(i));
        }
    }
}
