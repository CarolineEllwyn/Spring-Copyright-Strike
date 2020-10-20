package springboot.springusersplaylists.models;

public class CustomersMostPopularGenre {


    private int mostPopularGenre;

    private String firstName;

    private String lastName;

    private String genreName;



    public CustomersMostPopularGenre(String firstName, String lastName, String genreName, int mostPopularGenre) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.genreName = genreName;
        this.mostPopularGenre = mostPopularGenre;


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
