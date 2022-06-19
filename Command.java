interface Command{
    void execute(String[] cmdParts) 
    throws ExItemIdInUse, 
           ExMemberIdInUse, 
           ExMemberNotFound, 
           ExItemNotAvailable, 
           ExItemNotFound,
           ExLoanQuotaExceeded, 
           ExItemNotBorrowedThisMember,
           ExItemIsBorrowedSameMember,
           ExItemIsRequestedSameMember, 
           ExItemIsAvailable,
           ExItemRequestQuotaExceeded,
           ExRequestRecordNotFound, ExInsufficientCmdArguments;
}
