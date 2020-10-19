package springboot.springusersplaylists.models;

public class CustomersHighestSpenders {

    private double totalSpent;

    private String firstName;

    private String lastName;

    public CustomersHighestSpenders(String firstName, String lastName, double totalSpent){

        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSpent = totalSpent;


    }

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