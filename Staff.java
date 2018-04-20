package RevisedGamestore;

class Staff extends UserData
{
    private String name = "";
    private int staffID;
    
    public Staff(){}
    
    public Staff(String name, String password, int staffID)
    {
        this.name = name;
        this.password = password;
        this.staffID = staffID;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setStaffID(int staffID)
    {
        this.staffID = staffID;
    }
    
    public int getStaffID()
    {
        return staffID;
    }
    
    public void printInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println("Staff ID: " + staffID);
    }
    
    @Override
    public String toString()
    {
        return ("\nStaff Name: " + name +
                "\nStaff ID: " + staffID +
                "\nPassword: " + password);
    }
}
