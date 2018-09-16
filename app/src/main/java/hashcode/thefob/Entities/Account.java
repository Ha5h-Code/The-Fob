package hashcode.thefob.Entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Account {
    private String accountId;
    private String accountName;
    private Hashtable<String, String> accountDetails = new Hashtable<String, String>();

    public Account(String accountId, String accountName, Hashtable<String, String> accountDetails) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountDetails = accountDetails;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
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
