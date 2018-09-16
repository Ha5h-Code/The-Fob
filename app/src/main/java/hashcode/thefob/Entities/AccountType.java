package hashcode.thefob.Entities;


import android.media.Image;

import java.util.ArrayList;

public class AccountType {

    private String accountTypeName;

    private int accountTypeId;

    // Drawable resource ID for the icon
    private int iconId;

    //Accounts in a particular account type
    private ArrayList<Account> accounts=new ArrayList<Account>();



    public AccountType(String accountTypeName, int accountTypeId, int iconId) {
        this.accountTypeName = accountTypeName;
        this.accountTypeId = accountTypeId;
        this.iconId = iconId;
    }

    public AccountType(String accountTypeName, int accountTypeId, int iconId, ArrayList<Account> accounts) {
        this.accountTypeName = accountTypeName;
        this.accountTypeId = accountTypeId;
        this.iconId = iconId;
        this.accounts = accounts;
    }

    public String getAccountTypeName() {

        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
