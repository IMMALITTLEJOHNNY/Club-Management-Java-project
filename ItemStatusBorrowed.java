import java.util.ArrayList;
public class ItemStatusBorrowed implements ItemStatus{
    private Day loan_date;
    private Member borrowing_Member;
    private ArrayList<Member> queue_list;
    public ItemStatusBorrowed(){};
    private static ItemStatusBorrowed intance = new ItemStatusBorrowed(){};
    public static ItemStatusBorrowed getInstance(){
        return intance;
    }
    @Override
    public String getItemStatusDescription() {
        if (queue_list.size()!=0){
            return "Borrowed by " + borrowing_Member.getMemberIdName() + " on " + loan_date + " + " + queue_list.size() + " request(s): " + printQueueList(queue_list);
        }
        else{
            return "Borrowed by " + borrowing_Member.getMemberIdName() + " on " + loan_date;
        }
    }
    public void setLoanDate(Day d){
        this.loan_date = d;
    }
    public void setBorrowingMember(Member m){
        this.borrowing_Member = m;
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
