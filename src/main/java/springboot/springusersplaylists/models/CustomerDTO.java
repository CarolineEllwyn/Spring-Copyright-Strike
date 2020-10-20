package springboot.springusersplaylists.models;

//This is a model of a modified customer.
public class CustomerDTO {
    private int customerId;
    private String firstName;
    private String lastName;
    private String country;
    private String postalCode;
    private String phone;

    //this is a constructor that takes in the parameters a modified customer requires
    public CustomerDTO(int customerId, String firstName, String lastName, String country, String postalCode, String phone){
        this.customerId = customerId;
        this.firstName= firstName;
        this.lastName= lastName;
        this.country= country;
        this.postalCode= postalCode;
        this.phone= phone;
    }

    //This is getters and setters for all the parameters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
