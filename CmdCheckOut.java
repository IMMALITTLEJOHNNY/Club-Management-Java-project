public class CmdCheckOut extends RecordedCommand{
    private Member m;
    private Item i;
    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCmdArguments,ExMemberNotFound, ExItemNotAvailable, ExItemNotFound, ExLoanQuotaExceeded{
        Club c = Club.getInstance();
        m = c.findMember(cmdParts[1]);
        i = c.findItem(cmdParts[2]);
        if (cmdParts[1]==null||cmdParts[2]==null){
            throw new ExInsufficientCmdArguments();
        } 
        if (m == null){
            throw new ExMemberNotFound();
        }
        if (m.getMemberBorrowedNum()==6){
            throw new ExLoanQuotaExceeded();
        }
        if (i == null){
            throw new ExItemNotFound();
        }
        if (i.getItemOwner() != null){
            throw new ExItemNotAvailable();
        }
        else{
            Day d = SystemDate.getInstance().clone();
            m.BorrowedItem();
            ItemStatusBorrowed is = new ItemStatusBorrowed();
            is.setLoanDate(d);
            is.setBorrowingMember(m);
            is.setQueueList(i.getQueueList());
            i.setItemStatus(is);
            i.setItemOwner(m);
            i.setBorrowedDay(d);
            clearRedoList();
            addUndoCommand(this);
            System.out.println("Done.");
        }
    }

    @Override
    public void undoMe() {
        m.ReturnedItem();
        ItemStatusAvailable is = new ItemStatusAvailable(); 
        i.setItemStatus(is);
        i.setItemOwner(null);
        i.setBorrowedDay(null);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Day d = SystemDate.getInstance().clone();
        m.BorrowedItem();
        ItemStatusBorrowed is = new ItemStatusBorrowed(); 
        is.setLoanDate(d);
        is.setBorrowingMember(m);
        is.setQueueList(i.getQueueList());
        i.setItemStatus(is);
        i.setItemOwner(m);
        i.setBorrowedDay(d);
        addUndoCommand(this);
    }

}