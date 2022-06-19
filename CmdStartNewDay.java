public class CmdStartNewDay extends RecordedCommand{
    private String oldDay;
    private String newDay;
    @Override
    public void execute(String[] cmdParts){
        SystemDate sd = SystemDate.getInstance();
        oldDay = sd.toString();
        newDay = cmdParts[1];
        sd.set(newDay);
        clearRedoList();
        addUndoCommand(this);
        System.out.println("Done.");
    }
    @Override 
    public void undoMe(){
        SystemDate sd = SystemDate.getInstance();
        sd.set(oldDay);
    }
    @Override 
    public void redoMe(){
        SystemDate sd = SystemDate.getInstance();
        sd.set(newDay);
    }
}
