import java.util.ArrayList;
public class Item implements Comparable<Item>{
    private String id;
    private String name;
    private Day arrival_date;
    private ItemStatus item_status;
    private Member item_owner;
    private Member item_onholder;
    private Day Borrowed_day;
    private Day Onhold_day;
    private ArrayList<Member> queue_list;
    public Item(String id, String name) {
        this.id = id;
        this.name = name;
        this.arrival_date = SystemDate.getInstance().clone();
        this.item_status = new ItemStatusAvailable();
        this.item_owner = null;
        this.Borrowed_day = null;
        this.item_onholder = null;
        this.Onhold_day = null;
        this.queue_list = new ArrayList<Member>();
        Club.getInstance().addItem(this);
    }
    @Override
    public int compareTo(Item another){
        if (this.id.equals(another.id)){
            return 0;
        }
        else if (this.id.compareTo(another.id)>0){
            return 1;
        }
        else{return -1;}
    }
    @Override
    public String toString() {
        return String.format("%-5s%-17s%11s   %s", id, name, arrival_date, item_status.getItemStatusDescription().trim());
    }
    public static String getListingHeader() {
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status");
    }
    public ItemStatus getItemStatus(){
        return item_status;
    }
    public void setItemStatus(ItemStatus is){
        this.item_status = is;
    }
    public String getItemID() {
        return id;
    }
    public String getItemIdName(){
        return id + " " + name;
    }
    public void setItemOwner(Member m){
        this.item_owner = m;
    }
    public Member getItemOwner(){
        return item_owner;
    }
    public void setBorrowedDay(Day d){
        this.Borrowed_day = d;
    }
    public Day getBorrowedDay(){
        return Borrowed_day;
    }
    public void setQueueList(ArrayList<Member> ql){
        this.queue_list = ql;
    }
    public void addQueueList(Member m){
        queue_list.add(m);
    }
    public void addQueueList_index(int a, Member m){
        queue_list.add(a, m);
    }
    public void removeQueueList(Member m){
        queue_list.remove(m);
    }
    public boolean isinQueueList(Member m){
        if (queue_list.contains(m)){
            return true;
        }
        else{
            return false;
        }
    }
    public ArrayList<Member> getQueueList(){
        return queue_list;
    }
    public void printQueueList(ArrayList<Member> ql){
        for (Member m: queue_list){
            System.out.println(m.getMemberID()+ " ");
        }
    }
    public void setItemOnholder(Member m){
        this.item_onholder = m;
    }
    public Member getItemOnholder(){
        return item_onholder;
    }
    public void setItemOnholdDay(Day d){
        this.Onhold_day = d;
    }
    public Day getItemOnholdDay(){
        return Onhold_day;
    }
}
