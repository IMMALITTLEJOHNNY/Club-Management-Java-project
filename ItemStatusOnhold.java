import java.util.ArrayList;
public class ItemStatusOnhold implements ItemStatus{
    private Member onhold_m;
    private Day Due_d;
    private ArrayList<Member> queue_list;
    private static ItemStatusOnhold intance = new ItemStatusOnhold();
    public ItemStatusOnhold(){};
    public static ItemStatusOnhold getInstance(){
        return intance;
    }
    public String getItemStatusDescription(){
        if (queue_list.size()!=0){
            return "On holdshelf for " + onhold_m.getMemberIdName() + " until " + Due_d + " + " + queue_list.size() + " request(s): " + printQueueList(queue_list);
        }
        else{
            return "On holdshelf for " + onhold_m.getMemberIdName() + " until " + Due_d;
        }
    }
    public void setOnholdMember(Member m){
        this.onhold_m = m;
    }
    public void setDue_d(Day d){
        this.Due_d = d;
    }
    public void setQueueList(ArrayList<Member> ql){
        this.queue_list = ql;
    }
    public void addQueueList(Member m){
        queue_list.add(m);
    }
    public void removeQueueList(Member m){
        queue_list.remove(m);
    }
    public String printQueueList(ArrayList<Member> ql){
        String result = "";
        for (Member m: ql){
            result += " " + m.getMemberID();
        }
        return result.trim();
    }
}
