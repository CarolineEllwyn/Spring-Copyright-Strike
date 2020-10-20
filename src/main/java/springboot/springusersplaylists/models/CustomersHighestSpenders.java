package springboot.springusersplaylists.models;

//This is a model of customers sorted by total amount spent.
public class CustomersHighestSpenders {

    private double totalSpent;

    private String firstName;

    private String lastName;

    //this is an constructor with the parameters that the model required.
    public CustomersHighestSpenders(String firstName, String lastName, double totalSpent){
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSpent = totalSpent;
    }

    //this is getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
}