import javax.management.monitor.Monitor;
import java.util.*;

public class PriseCommande{

    private Scanner scanner;

    public PriseCommande(Scanner scanner){
        this.scanner = scanner;
    }

    public int checkIfWaiterAvailable(Monitoring monitoring){ //returns -1 if no waiter available and waiter's id if available
        boolean tmp = false;
        int id;
        for (id = 0; id < monitoring.waiterList.size(); id++){
            if (monitoring.waiterList.get(id).isAvailable){
                monitoring.waiterList.get(id).isAvailable = false;
                return id;
            }
        }
        return -1;
    }

    public int getPeopleNumber(){
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
            System.out.println("Sélectionner le type de plat n°" + i);
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


}