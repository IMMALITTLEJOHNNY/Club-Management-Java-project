public class CmdCheckin extends RecordedCommand{
    private Member m;
    private Item i;
    private Day d_old;
    @Override
    public void execute(String[] cmdParts) throws ExItemNotBorrowedThisMember ,ExInsufficientCmdArguments{
        Club c = Club.getInstance();
        m = c.findMember(cmdParts[1]);
        i = c.findItem(cmdParts[2]);
        d_old = i.getBorrowedDay();
        if (cmdParts[1]==null||cmdParts[2]==null){
            throw new ExInsufficientCmdArguments();
        } 
        if (m != i.getItemOwner()){
            throw new ExItemNotBorrowedThisMember();
        }
        else{
            m.ReturnedItem();
            if (i.getQueueList().size()!=0){
                Day d = SystemDate.getInstance().clone();
                d.OnholdDue();
                i.getQueueList().get(0).CancelRequestedItem();
                ItemStatusOnhold is = new ItemStatusOnhold();
                is.setDue_d(d);
                is.setOnholdMember(i.getQueueList().get(0));
                System.out.printf("Item [%s] is ready for pick up by [%s].  On hold due on %s.\n", i.getItemIdName(), i.getQueueList().get(0).getMemberIdName(), d);
                i.setItemOnholder(i.getQueueList().get(0));
                i.setItemOnholdDay(d);
                i.getQueueList().remove(i.getQueueList().get(0));
                is.setQueueList(i.getQueueList());
                i.setItemStatus(is);
                i.setItemOwner(null);
                i.setBorrowedDay(null);
                clearRedoList();
                addUndoCommand(this);
                System.out.println("Done.");
            }
            else{
                ItemStatusAvailable is = new ItemStatusAvailable();
                i.setItemStatus(is);
                i.setItemOwner(null);
                i.setBorrowedDay(null);
                clearRedoList();
                addUndoCommand(this);
                System.out.println("Done.");
            }
        }
    }

    @Override
    public void undoMe() {
        m.BorrowedItem();
        ItemStatusBorrowed is = new ItemStatusBorrowed();
        is.setLoanDate(d_old);
        is.setBorrowingMember(m);
        is.setQueueList(i.getQueueList());
        i.setItemStatus(is);
        i.setItemOwner(m);
        i.setBorrowedDay(d_old);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        m.ReturnedItem();
        if (i.getQueueList().size()!=0){
            Day d = SystemDate.getInstance().clone();
            d.OnholdDue();
            ItemStatusOnhold is = new ItemStatusOnhold();
            is.setDue_d(d);
            is.setOnholdMember(i.getQueueList().get(0));
            i.setItemStatus(is);
            i.setItemOnholder(i.getQueueList().get(0));
            i.setItemOnholdDay(d);
            i.getQueueList().remove(i.getQueueList().get(0));
            i.setItemOwner(null);
            i.setBorrowedDay(null);
            addUndoCommand(this);
        }
        else{
            ItemStatusAvailable is = new ItemStatusAvailable();
            i.setItemStatus(is);
            i.setItemOwner(null);
            i.setBorrowedDay(null);
            addUndoCommand(this);
        }
    }

}
