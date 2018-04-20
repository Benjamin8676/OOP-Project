package RevisedGamestore;

class Members extends UserData
{
    private String username;
    private int accountID;
    
    public Members(){}
    
    public Members(String username, String password, int accountID)
    {
        this.username = username;
        this.password = password;
        this.accountID = accountID;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setAccountID(int accountID)
    {
        this.accountID = accountID;
    }
    
    public int getAccountID()
    {
        return accountID;
    }
    
    public void printInfo()
    {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Account ID: " + accountID);
    }
    
    @Override
    public String toString()
    {
        return ("\nUsername: " + username +
                "\nPassword: " + password +
                "\nAccount ID: " + accountID);
    }
}
