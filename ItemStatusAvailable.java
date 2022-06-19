public class ItemStatusAvailable implements ItemStatus{
    private static ItemStatusAvailable intance = new ItemStatusAvailable();
    public ItemStatusAvailable(){};
    public static ItemStatusAvailable getInstance(){
        return intance;
    }
    public String getItemStatusDescription(){
        return "Available";
    }
}
