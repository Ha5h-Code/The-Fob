package hashcode.thefob.Entities;

import java.util.Hashtable;

public class Account {
    private int accountId;
    private String accountName;
    private Hashtable<String, String> accountDetails = new Hashtable<String, String>();

    public Account(int accountId, String accountName, Hashtable<String, String> accountDetails)
    {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountDetails = accountDetails;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Hashtable<String, String> getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(Hashtable<String, String> accountDetails) {
        this.accountDetails = accountDetails;
    }
}
