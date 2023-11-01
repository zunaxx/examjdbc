import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        PersonService personService = new PersonService(new DaoImpl());


        personService.createTablePersons();

        Person person1 = new Person();
        person1.setName("John");
        person1.setAge(30);
        person1.setGender("Male");
        person1.setEmail("john@example.com");
        personService.insertPerson(person1);

        Person person2 = new Person();
        person2.setName("Alice");
        person2.setAge(25);
        person2.setGender("Female");
        person2.setEmail("alice@example.com");
        personService.insertPerson(person2);

        List<Person> persons = personService.findAllPersons();

        // Выводим информацию о персонах на консоль
        for (Person person : persons) {
            System.out.println(person);}

        personService.deletePersonById(2L);

        // Находим персону по ID
        Person foundPerson = personService.findPersonById(2L);
        if (foundPerson != null) {
            System.out.println("Найдена человека: " + foundPerson);
        }


    }
}
