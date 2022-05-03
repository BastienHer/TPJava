import java.io.*;
import java.util.*;

public class Bar {

    public List <String> commandesBar;
    private Scanner scanner;
    File orderRemainingFile = new File("commandesRestantes.txt");
    public List <String> tmp = new ArrayList<>();

    public Bar(Scanner scanner){
        this.scanner = scanner;
    }

    public void addOrders(List <String> liste){
        this.commandesBar = liste;
    }

    //remove orderItem from DrinkList
    public void removeOrder(){
        System.out.println("");
        System.out.println("Veuillez rentrer l'index de la commande");

        int id = this.scanner.nextInt();

        if(!checkIdPresence(id)){
            System.out.println("Cette commande n'existe pas dans le fichier commandes.txt");
            return;
        }

        getItemList(id);
        System.out.println("Rentrer l'id de l'élément à supprimer");
        int drinkId = this.scanner.nextInt();
        String name = tmp.get(drinkId);

        try {
            // input the (modified) file content to the StringBuffer "input"
            Scanner myReader = new Scanner(orderRemainingFile);
            StringBuffer inputBuffer = new StringBuffer();

            while (myReader.hasNextLine()){
                if(myReader.nextLine() == name){
                    System.out.println(name);
                    System.out.println(myReader.nextLine());
                }

                else{
                    String line = myReader.nextLine();
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }

            FileOutputStream fileOut = new FileOutputStream("commandesRestantes.txt");
            fileOut.write(inputBuffer);
            fileOut.close();

        }
        catch (Exception e) {
            System.out.println("Une erreur est survenue");
        }
        tmp.removeAll(tmp);
    }

    //get all drinks of an order
    private void getItemList(int id) {
        boolean executeLoop = false;
        boolean executeLoop2 = false;
        int i = 0;

        try {
            if (orderRemainingFile.createNewFile()) {
                System.out.println("Fichier commandesRestantes.txt initialisé !");
                System.out.println("");
            }

            else { //file already exists
                System.out.println("Ouverture du fichier commandesRestantes.txt");
                System.out.println("");
                Scanner myReader = new Scanner(orderRemainingFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    if(data.startsWith("##" + String.valueOf(id))){ //début de la commande
                        System.out.println(data);
                        executeLoop = true;
                    }

                    if(data == "") { //fin des boissons de la commade
                        executeLoop2 = false;
                        executeLoop = false;
                        System.out.println("");
                    }

                    if (executeLoop && executeLoop2){
                        System.out.println(i + "- " + data);
                        i++;
                        tmp.add(data);
                    }

                    if(executeLoop){ //début des boissons de la commande
                        if(data.startsWith("[DRINK]")) executeLoop2 = true;
                    }
                }
                myReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //check if an order has this id
    private boolean checkIdPresence(int id) {
        try {
            if (orderRemainingFile.createNewFile()) {
                System.out.println("Fichier commandesRestantes.txt initialisé !");
                System.out.println("");
            }

            else { //file already exists
                System.out.println("Ouverture du fichier commandesRestantes.txt");
                System.out.println("");
                Scanner myReader = new Scanner(orderRemainingFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    if(data.startsWith("##")){
                        String idString = String.valueOf(data.charAt(2));
                        if (data.length() == 4) idString += String.valueOf(data.charAt(3));// si l'id est supérieur à 9
                        if (data.length() == 5) idString += String.valueOf(data.charAt(4)); //si l'id est supérieur à 99
                        int idTmp = Integer.parseInt(idString);

                        if (idTmp == id) return true;
                    }
                }
                myReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean needToRemoveOrder(){
        System.out.println("Voulez-vous retirer une commande à la liste des commandes ? (n/y)");
        String myChar = this.scanner.next();

        while (!myChar.equals("y") && !myChar.equals("Y") && !myChar.equals("n") && !myChar.equals("N")){
            myChar = this.scanner.next();
        }

        boolean answer = false;
        if (myChar.equals("y") || myChar.equals("Y")){
            answer = true;
        }
        return answer;
    }

    //lecture dans le ficher commandesRestantes.txt et affichage
    public void printOrders(){

        boolean executeLoop = false;
        boolean executeLoop2 = false;

        try {
            if (orderRemainingFile.createNewFile()) {
                System.out.println("Fichier commandesRestantes.txt initialisé !");
                System.out.println("");
            }

            else { //file already exists
                System.out.println("Ouverture du fichier commandesRestantes.txt");
                System.out.println("");
                Scanner myReader = new Scanner(orderRemainingFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    if(data.startsWith("##")){ //début de la commande
                        System.out.println(data);
                        executeLoop = true;
                    }

                    if(data == "") { //fin des boissons de la commade
                        executeLoop2 = false;
                        executeLoop = false;
                        System.out.println("");
                    }

                    if (executeLoop && executeLoop2){
                        System.out.println(data);
                    }

                    if(executeLoop){ //début des boissons de la commande
                        if(data.startsWith("[DRINK]")) executeLoop2 = true;
                    }
                }
                myReader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
