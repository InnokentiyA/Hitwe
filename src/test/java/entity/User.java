package entity;

public class User {
    private String name;
    private String email;
    private String gender;
    private String age;

    public User(String name, String email, String gender, String age) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }
}
