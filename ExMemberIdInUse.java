public class ExMemberIdInUse extends Exception{
    private Member m;
    public ExMemberIdInUse(Member m){
        this.m = m;
    }
    public ExMemberIdInUse(String message){
        super(message);
    }
    public Member getIdInUseMember(){
        return m;
    }
}
