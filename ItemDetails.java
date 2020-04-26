import java.util.ArrayList;

public class ItemDetails {
    ArrayList<String> item;
    ArrayList<String> quantity;
    ArrayList<String> price;
    int intIndex=0;
    ItemDetails(){
        item = new ArrayList<>();
        quantity = new ArrayList<>();
        price = new ArrayList<>();
    }
    public void addtoItem(String details){
        String array[]=details.split(",");
        String itemstr="";
        for(int i=0;i<array.length;i++){
            if(Character.isDigit(array[i].charAt(0))){
                intIndex=i;
                for (int j=0;j<i;j++){
                    itemstr+=array[j]+" ";
                }
                break;
            }
        }
        item.add(itemstr.trim());
    }
    public void addtoQuantity(String details){
        String array[]=details.split(",");
        quantity.add(array[intIndex]);
    }
    public void addtoPrice(String details){
        String array[]=details.split(",");
        price.add(array[intIndex+1]);
    }
    public ArrayList<String> getItems(){
        return item;
    }
    public ArrayList<String> getQuantity(){
        return quantity;
    }
    public ArrayList<String> getPrice(){
        return price;
    }
}
