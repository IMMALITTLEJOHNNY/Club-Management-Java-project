public class CmdCancelRequest extends RecordedCommand{
    private Member m;
    private Item i;
    private int m_pos;
    @Override
    public void execute(String[] cmdParts) throws ExRequestRecordNotFound, ExMemberNotFound, ExItemNotFound, ExItemIsAvailable, ExInsufficientCmdArguments{
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
        if (i.isinQueueList(m)==false){
            throw new ExRequestRecordNotFound();
        }
        else{
            m_pos = i.getQueueList().indexOf(m);
            if (i.getItemOwner()!=null){
                m.CancelRequestedItem();
                ItemStatusBorrowed is = new ItemStatusBorrowed();
                is.setBorrowingMember(i.getItemOwner());
                is.setLoanDate(i.getBorrowedDay());
                is.setQueueList(i.getQueueList());
                is.removeQueueList(m);
                i.setItemStatus(is);
                i.getQueueList().remove(m);
                clearRedoList();
                addUndoCommand(this);
                System.out.println("Done.");
            }
            else{
                m.CancelRequestedItem();
                ItemStatusOnhold is = new ItemStatusOnhold();
                is.setOnholdMember(i.getItemOnholder());
                is.setDue_d(i.getItemOnholdDay());
                is.setQueueList(i.getQueueList());
                is.removeQueueList(m);
                i.setItemStatus(is);
                i.getQueueList().remove(m);
                clearRedoList();
                addUndoCommand(this);
                System.out.println("Done.");
            }
        }
    }

    @Override
    public void undoMe() {
        if (i.getItemOwner()!=null){
            m.RequestedItem();
            i.addQueueList_index(m_pos, m);
            ItemStatusBorrowed is = new ItemStatusBorrowed();
            is.setBorrowingMember(i.getItemOwner());
            is.setLoanDate(i.getBorrowedDay());
            is.setQueueList(i.getQueueList());
            i.setItemStatus(is);
            addRedoCommand(this);
        }
        else{
            m.RequestedItem();
            i.addQueueList_index(m_pos, m);
            ItemStatusOnhold is = new ItemStatusOnhold();
            is.setOnholdMember(i.getItemOnholder());
            is.setDue_d(i.getItemOnholdDay());
            is.setQueueList(i.getQueueList());
            i.setItemStatus(is);
            addRedoCommand(this);
        }
    }

    @Override
    public void redoMe() {
        if (i.getItemOwner()!=null){
            m.CancelRequestedItem();
            ItemStatusBorrowed is = new ItemStatusBorrowed();
            is.setBorrowingMember(i.getItemOwner());
            is.setLoanDate(i.getBorrowedDay());
            is.setQueueList(i.getQueueList());
            is.removeQueueList(m);
            i.setItemStatus(is);
            i.getQueueList().remove(m);
            addUndoCommand(this);
        }
        else{
            m.CancelRequestedItem();
            ItemStatusOnhold is = new ItemStatusOnhold();
            is.setOnholdMember(i.getItemOnholder());
            is.setDue_d(i.getItemOnholdDay());
            is.setQueueList(i.getQueueList());
            is.removeQueueList(m);
            i.setItemStatus(is);
            i.getQueueList().remove(m);
            addUndoCommand(this);
        }
    }
    
}
