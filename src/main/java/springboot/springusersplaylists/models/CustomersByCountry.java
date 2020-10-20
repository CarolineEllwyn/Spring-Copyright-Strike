package springboot.springusersplaylists.models;

//This is a model of customers grouped by country.
public class CustomersByCountry {
    private int numberOfCustomers;
    private String Country;

    //this is an empty constructor
    public CustomersByCountry(){

    }

    //this is an constructor with the parameters that the model required.
    public CustomersByCountry(String country, int numberOfCustomers){
        Country = country;
        this.numberOfCustomers = numberOfCustomers;
    }

    //this is getters and setters
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) { Country = country; }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }
}
