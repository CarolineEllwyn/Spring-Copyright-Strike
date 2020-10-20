package springboot.springusersplaylists.models;

//This is a model of customers sorted by highest spending on tracks grouped by genre.
public class CustomersMostPopularGenre {

    private int mostPopularGenre;

    private String firstName;

    private String lastName;

    private String genreName;

    //this is an constructor with the parameters that the model required.
    public CustomersMostPopularGenre(String firstName, String lastName, String genreName, int mostPopularGenre) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genreName = genreName;
        this.mostPopularGenre = mostPopularGenre;
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

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public int getMostPopularGenre() {
        return mostPopularGenre;
    }

    public void setMostPopularGenre(int mostPopularGenre) {
        this.mostPopularGenre = mostPopularGenre;
    }


}
