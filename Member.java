public class Member implements Comparable<Member>{
    private String id;
    private String name;
    private Day joinDate;
    private int borrowed;
    private int requested;

    public Member(String id, String name) {	
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
        this.borrowed = 0;
        this.requested = 0;

        Club.getInstance().addMember(this);
}
    @Override
    public int compareTo(Member another){
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
   //Learn: "-" means left-aligned
   return String.format("%-5s%-9s%11s%7d%13d", id, name, joinDate, borrowed, requested);
    }

    public static String getListingHeader() {
  return String.format("%-5s%-9s%11s%11s%13s", "ID", "Name", "Join Date ", "#Borrowed", "#Requested");
    }
    public String getMemberID(){
        return id;
    }
    public int getMemberBorrowedNum(){
        return borrowed;
    }
    public int getMemberRequestedItemNum(){
        return requested;
    }
    public void BorrowedItem(){
        this.borrowed += 1;
    }
    public void ReturnedItem(){
        this.borrowed -= 1;
    }
    public void RequestedItem(){
        this.requested += 1;
    }
    public void CancelRequestedItem(){
        this.requested -= 1;
    }
    public String getMemberIdName() {
        return id + " " + name;
    }
}
