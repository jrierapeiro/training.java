package common;

public class Person {
    private String Name;
    private Integer Age;
    private String Gender;

    public Person(String name, String gender, Integer age){
        this.Name = name;
        this.Gender = gender;
        this.Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String toString(){
        return  String.join(" ","Person: ", this.Name, this.Gender, this.Age.toString());
    }
}
