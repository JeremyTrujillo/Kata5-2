package kata52;

import java.time.LocalDate;

public class Kata52 {
    
    public static void main(String[] args){
        Database database = new Database("jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Kata52\\DB_SQLite\\db.db");
        database.open();
        init(database);
        database.select();
        database.close();
    }

    private static void init(Database database) {
        database.truncate();
        database.insert(new Person ("Jeremy","Trujil.",LocalDate.of(1999, 10, 23)));
        database.insert(new Person("Luis","López",LocalDate.of(1989, 4, 6)));
        database.insert(new Person("varv","aegy",LocalDate.of(2018, 12, 1)));
    }
}
