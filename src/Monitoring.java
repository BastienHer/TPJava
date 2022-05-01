import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Monitoring {
    public int waiterNb;
    public List <Waiter> waiterList = new ArrayList<>();
    public List <String> stockList = new ArrayList<>();
    public String[] stockListName = {"Salade","Tomates","Oignons", "Champignons", "Pains Burger", "Steak", "Pâte à pizza", "Fromage", "Saucisse"};
    public int[] stockListNumber = {0,0,0,0,0,0,0,0,0};
    File stockFile = new File("stock.txt");

    private Scanner scanner;

    public Monitoring(Scanner scanner){
        this.scanner = scanner;
    }

    public void updateStockString(){
        this.stockList.removeAll(stockList);
        this.stockList.add("Salade " + stockListNumber[0]);
        this.stockList.add("Tomates " + stockListNumber[1]);
        this.stockList.add("Oignons " + stockListNumber[2]);
        this.stockList.add("Champignons " + stockListNumber[3]);
        this.stockList.add("Pains Burger " + stockListNumber[4]);
        this.stockList.add("Steak " + stockListNumber[5]);
        this.stockList.add("Pâte à pizza " + stockListNumber[6]);
        this.stockList.add("Fromage " + stockListNumber[7]);
        this.stockList.add("Saucisse " + stockListNumber[8]);
        System.out.println("stock : " + this.stockList);
    }


    public void updateStock(){
        try {
            if (stockFile.createNewFile()) { //creates files if does not exist
                System.out.println("Fichier stock.txt initialisé !");
                System.out.println("");
                updateStockString();
            }

            else { //file already exists
                System.out.println("Ouverture du fichier stock.txt");
                System.out.println("");
                Scanner myReader = new Scanner(stockFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Voulez-vous actualiser le stock maintenant ? (y/n)");
        String myChar = this.scanner.next();

        boolean exit = true;
        while (exit){ //continue tant que entrée différente de y/n
            myChar = this.scanner.next();
            if (myChar.equalsIgnoreCase(String.valueOf('y')) || myChar.equalsIgnoreCase(String.valueOf('n'))) exit = false;
        }

        if (myChar.equalsIgnoreCase("y")) { //si on veut modifier le stock
            System.out.println("De quel ingrédient voulez-vous modifier le stock ?");
            System.out.println("0- Salade");
            System.out.println("1- Tomates");
            System.out.println("2- Oignons");
            System.out.println("3- Champignons");
            System.out.println("4- Pains Burger");
            System.out.println("5- Steak");
            System.out.println("6- Pâte à pizza");
            System.out.println("7- Fromage");
            System.out.println("8- Saucisse");
            System.out.println("9- Sortie");

            int tmp = this.scanner.nextInt();
            while (tmp != 9){
                if (tmp < 9 && tmp >= 0 ){
                    System.out.println("Veuillez rentrer la valeur souhaitée de " + this.stockListName[tmp]);
                    int tmp2 = this.scanner.nextInt();
                    this.stockListNumber[tmp] = tmp2;
                    updateStockString();
                    updateStockFile();
                }
                System.out.println("De quel ingrédient voulez-vous modifier le stock ?");
                System.out.println("0- Salade");
                System.out.println("1- Tomates");
                System.out.println("2- Oignons");
                System.out.println("3- Champignons");
                System.out.println("4- Pains Burger");
                System.out.println("5- Steak");
                System.out.println("6- Pâte à pizza");
                System.out.println("7- Fromage");
                System.out.println("8- Saucisse");
                System.out.println("9- Sortie");
                tmp = this.scanner.nextInt();
            }
        }
    }

    public void updateStockFile(){
        try{
            PrintWriter writer = new PrintWriter("stock.txt", "UTF-8");
            writer.println(this.stockList);
            writer.close();
        }
        catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addWaiter(){
        Waiter newWaiter = new Waiter();

        newWaiter.id = waiterList.size();

        System.out.println("Quel est le nom du serveur");
        newWaiter.name = this.scanner.next();

        System.out.print("Le serveur s'appelle " + newWaiter.name + ", son id est " + newWaiter.id);
        System.out.println("");
        this.waiterList.add(newWaiter);
    }
}
