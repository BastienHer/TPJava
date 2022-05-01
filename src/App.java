import java.util.*;

public class App {

    public static void main(String[] args){

        //setup variables objet
        Scanner scanner = new Scanner(System.in);
        Cuisine cuisine = new Cuisine(scanner);
        PriseCommande priseCommande = new PriseCommande(scanner);
        Monitoring monitoring = new Monitoring(scanner);
        Bar bar = new Bar(scanner);
        int waiterId = -1;

        while(true) {
            System.out.println();
            System.out.println("----Quel écran souhaitez vous afficher?----");
            System.out.println("1- Ecran prise de commande");
            System.out.println("2- Ecran cuisine");
            System.out.println("3- Ecran bar");
            System.out.println("4- Ecran Monitoring");
            System.out.println("5- Régler une commande");
            System.out.println("6- Fin du programme");
            System.out.println();


            int choixEcran = scanner.nextInt();


            switch (choixEcran) {
                case 1:
                    waiterId = priseCommande.checkIfWaiterAvailable(monitoring);

                    if (waiterId != -1) { //si un serveur est dispo, on prend la commande
                        Waiter waiter = monitoring.waiterList.get(waiterId);
                        priseCommande.getOrderId(waiter); //creates file commandes.txt
                        int peopleNumber = priseCommande.getPeopleNumber();
                        List <String> drinkList = priseCommande.getDrink(peopleNumber);
                        bar.addOrders(drinkList);
                        List <String> foodList = priseCommande.getFood(peopleNumber);
                        cuisine.addOrders(foodList);
                        priseCommande.addOrderToFile(waiter,foodList,drinkList);
                    }
                    else System.out.println("Il n'y a pas de serveur disponible, veuillez en ajouter via le menu monitoring");

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
                    System.out.println();
                    System.out.println("----Quelle action voulez-vous effectuer ?----");
                    System.out.println("1- Ajouter un serveur");
                    System.out.println("2- Consulter/Actualiser le stock");
                    System.out.println();

                    int choixAction = scanner.nextInt();
                    switch(choixAction){
                        case 1 -> {
                            monitoring.addWaiter();
                        }

                        case 2 -> {
                            monitoring.updateStock();
                        }
                    }
                    break;

                case 5:
                    priseCommande.askWhichOrderPay(monitoring);
                    Waiter waiter = monitoring.waiterList.get(waiterId);
                    priseCommande.getTotalOrderPrice(waiter);
                    break;

                case 6:
                    return;
            }
        }
    }
}

