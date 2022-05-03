import javax.management.monitor.Monitor;
import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class PriseCommande{

    private Scanner scanner;
    public File orderFile = new File("commandes.txt");
    public File orderRemainingFile = new File("commandesRestantes.txt");

    public PriseCommande(Scanner scanner){
        this.scanner = scanner;
    } //constructeur

    public int checkIfWaiterAvailable(Monitoring monitoring){ //retourne l'id du server ou -1 si personne n'est disponible
        int id;
        for (id = 0; id < monitoring.waiterList.size(); id++){
            if (monitoring.waiterList.get(id).isAvailable){
                monitoring.waiterList.get(id).isAvailable = false;
                return id;
            }
        }
        return -1;
    }

    public int getOrderId(Waiter waiter) { //retourne l'id de la commande (idCommandePrécédente + 1)
        int id = 0;
        try {
            if (orderFile.createNewFile()) { //creates files if does not exist
                System.out.println("");
                System.out.println("Fichier commandes.txt initialisé !");
                System.out.println("");
            }

            else { //file already exists
                System.out.println("");
                System.out.println("Ouverture du fichier commandes.txt");
                System.out.println("");
                Scanner myReader = new Scanner(orderFile);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data.startsWith("##")){
                        String idString = String.valueOf(data.charAt(2));
                        if (data.length() == 4) idString += String.valueOf(data.charAt(3));// si l'id est supérieur à 9
                        if (data.length() == 5) idString += String.valueOf(data.charAt(4)); //si l'id est supérieur à 99
                        id = Integer.parseInt(idString);
                    }
                }
                myReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        waiter.commandeId = id+1;
        return id+1;
    }

    public int getPeopleNumber(){ //renvoie le nombre de personne qui vont prendre un repas
        System.out.println("Combien de personnes vont prendre un repas?");
        int peopleNumber = scanner.nextInt();
        System.out.println();
        return peopleNumber;
    }

    public List <String> getDrink(int peopleNumber){
        List <String> drinkList = new ArrayList<>();

        for(int i = 0; i < peopleNumber ; i++){
            System.out.println("Sélectionner la boisson n°" + i);
            System.out.println("1- Verre d'eau");
            System.out.println("2- Limonade");
            System.out.println("3- Cidre Doux");
            System.out.println("4- Bière sans alcool");
            System.out.println("5- Jus de fruit");
            System.out.println();

            int drinkId = scanner.nextInt();

            switch (drinkId) {
                case 1 -> drinkList.add("Verre d'eau");
                case 2 -> drinkList.add("Limonade");
                case 3 -> drinkList.add("Cidre Doux");
                case 4 -> drinkList.add("Bière Sans Alcool");
                case 5 -> drinkList.add("Jus de fruit");
            }
        }
        return drinkList;
    }

    public List <String> getFood(int peopleNumber){
        List <String> foodList = new ArrayList<>();

        for(int i = 0; i < peopleNumber ; i++){
            System.out.println("Sélectionner le type de plat n°" + i+1);
            System.out.println("1- Salades");
            System.out.println("2- Potages");
            System.out.println("3- Burgers");
            System.out.println("4- Pizzas");
            System.out.println();

            int foodTypeId = scanner.nextInt();

            switch (foodTypeId) {
                case 1 -> { //Salades
                    System.out.println("Sélectionner le plat n°" + i);
                    System.out.println("1- Salade");
                    System.out.println("2- Salade et Tomates");
                    System.out.println();

                    int foodId = scanner.nextInt();

                    if(foodId == 1) foodList.add("Salade");
                    if(foodId == 2) foodList.add("Salade et Tomates");
                }

                case 2 -> { //Potages
                    System.out.println("Sélectionner le plat n°" + i);
                    System.out.println("1- Soupe à l'oignon");
                    System.out.println("2- Gazpacho");
                    System.out.println("3- Soupe aux champignons");
                    System.out.println();

                    int foodId = scanner.nextInt();

                    if(foodId == 1) foodList.add("Soupe à l'oignon");
                    if(foodId == 2) foodList.add("Gazpacho");
                    if(foodId == 3) foodList.add("Soupe aux champignons");
                }

                case 3 -> { //Burgers
                    System.out.println("Sélectionner le plat n°" + i);
                    System.out.println("1- Burger Salade et Tomate");
                    System.out.println("2- Burger Salade");
                    System.out.println("3- Burger");
                    System.out.println();

                    int foodId = scanner.nextInt();

                    if(foodId == 1) foodList.add("Burger Salade et Tomate");
                    if(foodId == 2) foodList.add("Burger Salade");
                    if(foodId == 3) foodList.add("Burger");
                }

                case 4 -> { //Pizza
                    System.out.println("Sélectionner le plat n°" + i);
                    System.out.println("1- Margherita");
                    System.out.println("2- Reine");
                    System.out.println("3- Pizza Chorizo");
                    System.out.println();

                    int foodId = scanner.nextInt();

                    if(foodId == 1) foodList.add("Margherita");
                    if(foodId == 2) foodList.add("Reine");
                    if(foodId == 3) foodList.add("Pizza chorizo");
                }
            }
        }
        return foodList;
    }

    public void addOrderToFile(Waiter waiter, List <String> foodlist, List <String> drinkList){

        int id = getOrderId(waiter);

        try { //ajout dans le fichier commandes.txt
            if (orderFile.createNewFile()) {
                System.out.println("Fichier commandes.txt initialisé !");
            }
            //écriture dans le fichier commandes
            System.out.println("Ouverture du fichier commandes.txt");
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("commandes.txt", true)));

            writer.println("##" + id);
            writer.println("[FOOD]");
            for (int i = 0 ; i < foodlist.size(); i++){
                writer.println(foodlist.get(i));
            }
            writer.print("[DRINK]");
            for (int i = 0 ; i < foodlist.size(); i++){
                writer.println(drinkList.get(i));
            }
            writer.println(" ");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{ //ajout dans le fichier commandesRestantes.txt
            if (orderRemainingFile.createNewFile()) {
                System.out.println("Fichier commandesRestantes.txt initialisé !");
            }

            //écriture dans le fichier commandesRestantes
            System.out.println("Ouverture du fichier commandesRestantes.txt");
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("commandesRestantes.txt", true)));

            writer.println("##" + id);
            writer.println("[FOOD]");
            for (int i = 0 ; i < foodlist.size(); i++){
                writer.println(foodlist.get(i));
            }
            writer.println("[DRINK]");
            for (int i = 0 ; i < foodlist.size(); i++){
                writer.println(drinkList.get(i));
            }
            writer.println("");
            writer.close();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public int askWhichOrderPay(Monitoring monitoring){
        System.out.println("Quelle commande voulez-vous payer ?");

        for (int i = 0 ; i < monitoring.waiterList.size(); i++){
            if (!monitoring.waiterList.get(i).isAvailable) {
                System.out.println(i + "-Commande " + monitoring.waiterList.get(i).commandeId + " de " + monitoring.waiterList.get(i));
            }
        }

        int answer = scanner.nextInt();
        for (int i = 0 ; i < monitoring.waiterList.size(); i++){
            if( answer == i ) return monitoring.waiterList.get(i).commandeId;
        }
        return -1;
    }

    public int getTotalOrderPrice(Waiter waiter){
        StringBuilder order = new StringBuilder();
        int totalPrice = 0;
        try{
                System.out.println("");
                System.out.println("Ouverture du fichier commandes.txt");
                System.out.println("");
                Scanner myReader = new Scanner(orderFile);
                boolean exit = false;

                while (myReader.hasNextLine() && exit) {
                    String data = myReader.nextLine();

                    boolean executeLoop = false;

                    if(data.startsWith("##" + String.valueOf(waiter.commandeId)) && !executeLoop){
                        executeLoop = true;
                    }

                    if (executeLoop && data.startsWith("[")){
                        order.append(data);
                    }
                }
                myReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(order);

        return 0;
    }


}