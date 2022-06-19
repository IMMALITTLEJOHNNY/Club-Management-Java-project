public class ExItemIdInUse extends Exception{
    private Item i;
    public ExItemIdInUse(Item i){
        this.i = i;
    }
    public ExItemIdInUse(String message){
        super(message);
    }
    public Item getIdInUse_Item(){
        return i;
    }
}
