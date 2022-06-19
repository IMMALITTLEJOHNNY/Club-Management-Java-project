import java.util.ArrayList;
import java.util.Collections;

public class Club {
    private ArrayList<Member> allMembers;
	private ArrayList<Item>	itemList;
    private static Club instance = new Club(); 

	private Club() {
	    allMembers = new ArrayList<>();
		itemList = new ArrayList<>();
	}

	public static Club getInstance() { return instance; }

	public void addMember(Member a) {// See how it is called in Member constructor (Step 3)
		allMembers.add(a);
		Collections.sort(allMembers); // allMembers
	}
	public void removeMember(Member a){
		allMembers.remove(a);
	}
	
	public void listClubMembers() {
		System.out.println(Member.getListingHeader()); // Member

		for (Member m: allMembers)
			System.out.println(m); // m or m.toString()
	}
	public void listItems(){
		System.out.println(Item.getListingHeader());
		for(Item i: itemList)
		    System.out.println(i);
	}
	public void addItem(Item i){
		itemList.add(i);
		Collections.sort(itemList);
	}
	public void removeItem(Item i){
		itemList.remove(i);
	}
	public Member findMember(String id){
		for (Member m: allMembers){
			if (id.equals(m.getMemberID())){
				return m;
			}
		}
		return null;
	}
	public Item findItem(String id){
		for (Item i: itemList){
			if (id.equals(i.getItemID())){
				return i;
			}
		}
		return null;
	}
	public boolean findItemIdInUse(String id){
		for (Item i: itemList){
			if (id.equals(i.getItemID())){
				return true;
			}
		}
		return false;
	}
	public boolean findMemberIdInUse(String id){
		for (Member m: allMembers){
			if (id.equals(m.getMemberID())){
				return true;
			}
		}
		return false;
	}
	public String getItemIdName(Item i){
		return i.getItemIdName();
	}
	public String getMemberIdName(Member m){
		return m.getMemberIdName();
	}
}

