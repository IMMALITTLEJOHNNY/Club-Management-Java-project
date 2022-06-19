public class CmdRequest extends RecordedCommand{
    private Member m;
    private Item i;
    @Override
    public void execute(String[] cmdParts) throws ExItemIsAvailable, ExItemIsBorrowedSameMember, ExItemIsRequestedSameMember, ExItemRequestQuotaExceeded, ExItemNotFound, ExMemberNotFound, ExInsufficientCmdArguments {
        Club c = Club.getInstance();
        m = c.findMember(cmdParts[1]);
        i = c.findItem(cmdParts[2]);
        if (cmdParts[1]==null||cmdParts[2]==null){
            throw new ExInsufficientCmdArguments();
        } 
        if (m == null){
            throw new ExMemberNotFound();
        }
        if (i == null){
            throw new ExItemNotFound();
        }
        if (i.getItemOwner() == null && i.getItemOnholder() == null){
            throw new ExItemIsAvailable();
        }
        if (m == i.getItemOwner()){
            throw new ExItemIsBorrowedSameMember();
        }
        if (i.isinQueueList(m)){
            throw new ExItemIsRequestedSameMember();
        }
        if (m.getMemberRequestedItemNum()==3){
            throw new ExItemRequestQuotaExceeded();
        }
        else{
            if (i.getItemOwner()!=null){
                m.RequestedItem();
                i.addQueueList(m);
                ItemStatusBorrowed is = new ItemStatusBorrowed();
                is.setBorrowingMember(i.getItemOwner());
                is.setLoanDate(i.getBorrowedDay());
                is.setQueueList(i.getQueueList());
                i.setItemStatus(is);
                clearRedoList();
                addUndoCommand(this);
                System.out.println("Done. This request is no. " + i.getQueueList().size() + " in the queue.");
            }
            else{
                m.RequestedItem();
                i.addQueueList(m);
                ItemStatusOnhold is = new ItemStatusOnhold();
                is.setOnholdMember(i.getItemOnholder());
                is.setDue_d(i.getItemOnholdDay());
                is.setQueueList(i.getQueueList());
                i.setItemStatus(is);
                clearRedoList();
                addUndoCommand(this);
                System.out.println("Done. This request is no. " + i.getQueueList().size() + " in the queue.");
            }
        }
    }
    @Override
    public void undoMe() {
        m.CancelRequestedItem();
        i.removeQueueList(m);
        ItemStatusBorrowed is = new ItemStatusBorrowed();
        is.setBorrowingMember(i.getItemOwner());
        is.setLoanDate(i.getBorrowedDay());
        is.setQueueList(i.getQueueList());
        i.setItemStatus(is);
        addRedoCommand(this);
    }
    @Override
    public void redoMe() {
        m.RequestedItem();
        i.addQueueList(m);
        ItemStatusBorrowed is = new ItemStatusBorrowed();
        is.setBorrowingMember(i.getItemOwner());
        is.setLoanDate(i.getBorrowedDay());
        is.setQueueList(i.getQueueList());
        i.setItemStatus(is);
        addUndoCommand(this);
    }
}
