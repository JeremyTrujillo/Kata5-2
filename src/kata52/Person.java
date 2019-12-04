package kata52;

import java.time.LocalDate;
import java.time.Period;

class Person {
    private final String name;
    private final String surname;
    private final LocalDate birthday;

    public Person(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    
    public int getAge(){
        return Period.between(birthday,LocalDate.now()).getYears();
    }

}
