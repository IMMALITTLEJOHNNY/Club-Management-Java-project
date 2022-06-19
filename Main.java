import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
 
 
public class Main {
 
    public static void main(String [] args) throws FileNotFoundException {  
         
        Scanner in = new Scanner(System.in);
         
        System.out.print("Please input the file pathname: ");
        String filepathname = in.nextLine();
        Scanner inFile = null;
        try{
            inFile = new Scanner(new File(filepathname));
			String cmdLine1 = inFile.nextLine();
            String[] cmdLine1Parts = cmdLine1.split(" ");
            System.out.println("\n> "+cmdLine1);
            SystemDate.createTheInstance(cmdLine1Parts[1]);


            while (inFile.hasNext()) {
                String cmdLine = inFile.nextLine().trim();  
                if (cmdLine.equals("")) 
                    continue;
                System.out.println("\n> "+cmdLine);
                String[] cmdParts = cmdLine.split(" "); 
                if (cmdParts[0].equals("register"))
                    try{
                        (new CmdRegister()).execute(cmdParts);
                    } catch (ExMemberIdInUse e){
                        System.out.println("Member ID already in use: " + e.getIdInUseMember().getMemberIdName());
                    } catch (ExInsufficientCmdArguments e){
						System.out.println("Insufficient command arguments.");
					}
                else if (cmdParts[0].equals("listMembers"))
                    (new CmdListMembers()).execute(cmdParts);
                else if (cmdParts[0].equals("listItems"))
                    (new CmdListItems()).execute(cmdParts);
                else if (cmdParts[0].equals("startNewDay"))
                    (new CmdStartNewDay()).execute(cmdParts);
                else if (cmdParts[0].equals("arrive")){
                    try{
                        (new CmdArrival()).execute(cmdParts);
                    } catch (ExItemIdInUse e){
                        System.out.println("Item ID already in use: " + e.getIdInUse_Item().getItemIdName());
                    } catch (ExInsufficientCmdArguments e){
                        System.out.println("Insufficient command arguments.");
                    }
                }
                else if (cmdParts[0].equals("checkout")){
                    try{
                        (new CmdCheckOut()).execute(cmdParts);
                    } catch (ExMemberNotFound e){
                        System.out.println("Member not found.");
                    } catch (ExLoanQuotaExceeded e){
                        System.out.println("Loan quota exceeded.");
                    } catch (ExItemNotAvailable e){
                        System.out.println("Item not available.");
                    } catch (ExItemNotFound e){
                        System.out.println("Item not found.");
                    } catch (ExInsufficientCmdArguments e){
                        System.out.println("Insufficient command arguments.");
                    }
                }
                else if (cmdParts[0].equals("checkin"))
                    try{
                        (new CmdCheckin()).execute(cmdParts);
                    } catch (ExItemNotBorrowedThisMember e){
                        System.out.println("The item is not borrowed by this member.");
                    } catch (ExInsufficientCmdArguments e){
                        System.out.println("Insufficient command arguments.");
                    }
                else if (cmdParts[0].equals("request"))
                    try{
                        (new CmdRequest()).execute(cmdParts);
                    } catch (ExMemberNotFound e){
                        System.out.println("Member not found.");
                    } catch(ExItemNotFound e){
                        System.out.println("Item not found.");
                    } catch (ExItemIsAvailable e){
                        System.out.println("The item is currently available.");
                    } catch (ExItemIsBorrowedSameMember e){
                        System.out.println("The item is already borrowed by the same member.");
                    } catch (ExItemIsRequestedSameMember e){
                        System.out.println("The same member has already requested the item.");
                    } catch (ExItemRequestQuotaExceeded e){
                        System.out.println("Item request quota exceeded.");
                    } catch (ExInsufficientCmdArguments e){
                        System.out.println("Insufficient command arguments.");
                    }
                else if (cmdParts[0].equals("cancelRequest"))
                    try{
                        (new CmdCancelRequest()).execute(cmdParts);
                    } catch (ExRequestRecordNotFound e){
                        System.out.println("Request record is not found.");
                    } catch (ExMemberNotFound e){
                        System.out.println("Member not found.");
                    } catch (ExItemNotFound e){
                        System.out.println("Item not found.");
                    } catch (ExItemIsAvailable e){
                        System.out.println("The item is currently available.");
                    } catch (ExInsufficientCmdArguments e){
                        System.out.println("Insufficient command arguments.");
                    }
                else if (cmdParts[0].equals("undo"))
                    RecordedCommand.undoOneCommand();
                else if (cmdParts[0].equals("redo"))
                    RecordedCommand.redoOneCommand();
                else
                    throw new ExCmdUnknown();
            }
        } catch (ExCmdUnknown e){
            System.out.println("Unknown command - ignored.");
        } finally{
		    if (inFile != null){
			    inFile.close();
			    in.close();
		    }
	    }
    }
}



