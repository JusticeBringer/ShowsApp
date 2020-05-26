package model.individual;

public class Person {
    private String username;
    private String password;
    private String firstName;
    private String familyName;
    private String email;
    private Integer age;

    public Person(String username, String password, String firstName, String familyName, String email, Integer age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.age = age;
    }

    public Person() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.familyName = "";
        this.email = "";
        this.age = 0;
    }

    public Person(String u, String p) {
        this.username = u;
        this.password = p;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(int id, String u, String p) {

    }

}
