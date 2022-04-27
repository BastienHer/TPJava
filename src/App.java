import java.util.*;

public class App {

    public static void main(String[] args){

        //setup variables objet
        Scanner scanner = new Scanner(System.in);
        Cuisine cuisine = new Cuisine(scanner);
        PriseCommande priseCommande = new PriseCommande(scanner);
        Monitoring monitoring = new Monitoring(scanner);
        Bar bar = new Bar(scanner);

        while(true) {
            System.out.println();
            System.out.println("----Quel écran souhaitez vous afficher?----");
            System.out.println("1- Ecran prise de commande");
            System.out.println("2- Ecran cuisine");
            System.out.println("3- Ecran bar");
            System.out.println("4- Ecran Monitoring");
            System.out.println("5- Fin du programme");
            System.out.println();


            int choixEcran = scanner.nextInt();
            System.out.println("Vous avez choisi l'écran: " + choixEcran);

            switch (choixEcran) {
                case 1:
                    int waiterId = priseCommande.checkIfWaiterAvailable(monitoring);
                    int peopleNumber = priseCommande.getPeopleNumber();
                    List <String> drinkList = priseCommande.getDrink(peopleNumber);
                    bar.addOrders(drinkList);
                    List <String> foodList = priseCommande.getFood(peopleNumber);
                    cuisine.addOrders(foodList);

                    break;

                case 2:
                    cuisine.printOrders();
                    if(cuisine.needToRemoveOrder()){
                        cuisine.removeOrder();
                    }
                    break;

                case 3:
                    bar.printOrders();

                    if(bar.needToRemoveOrder()){
                        bar.removeOrder();
                    }

                    break;

                case 4:
                    monitoring.updateStock();
                    break;

                case 5:
                    return;
            }
        }
    }
}

