import java.util.List;

public class Commande {
    public int id;
    public int nbPersonnes;
    public List <Item> plats;
    public List <Item> boissons;

    public void addOrder(Item item){
        if (item.type == "boisson"){
            this.boissons.add(item);
        }
        else this.plats.add(item);
    }
}
