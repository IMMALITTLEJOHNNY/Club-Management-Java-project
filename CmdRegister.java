public class CmdRegister extends RecordedCommand{
    private Member m;
    @Override 
    public void execute(String[] cmdParts) throws ExMemberIdInUse, ExInsufficientCmdArguments{
        Club c = Club.getInstance();
        String id = cmdParts[1];
        if (cmdParts[1] == null){
            throw new ExInsufficientCmdArguments();
        }
        if (c.findMemberIdInUse(id)){
            m = c.findMember(id);
            throw new ExMemberIdInUse(m);
        }
        else{
        m = new Member(id, cmdParts[2]);
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
        }
    }
    @Override
	public void undoMe()
	{
        Club c = Club.getInstance();
		c.removeMember(m);
        addRedoCommand(this);
	}
	
	@Override
	public void redoMe()
	{
		Club c = Club.getInstance();
        c.addMember(m);
        addUndoCommand(this);
	}
}
