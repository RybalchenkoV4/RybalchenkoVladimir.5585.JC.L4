public class Employee {
    enum Genders{MALE, FEMALE}
    String name;
    String midName;
    String surName;
    String phone;
    String position;
    int salary;
    int birth;
    Genders gender;

    public Genders getGender(){
        return gender;
    }

    public void setGender(Genders gender){
        this.gender = gender;
    }
}
