public class CmdArrival extends RecordedCommand{
    private Item i;
    @Override 
    public void execute(String[] cmdParts) throws ExItemIdInUse, ExInsufficientCmdArguments{
        Club c = Club.getInstance();
        String id = cmdParts[1];
        if (cmdParts[1] == null){
            throw new ExInsufficientCmdArguments();
        }
        if (c.findItemIdInUse(id)){
            i = c.findItem(id);
            throw new ExItemIdInUse(i);
        }
        else{
        i = new Item(id, cmdParts[2]);
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
        }
    }
    @Override
    public void undoMe() {
        Club c = Club.getInstance();
		c.removeItem(i);
        addRedoCommand(this);
    }
    @Override
    public void redoMe() {
        Club c = Club.getInstance();
        c.addItem(i);
        addUndoCommand(this);
    }
}
