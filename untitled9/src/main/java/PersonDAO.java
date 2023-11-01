import java.util.List;

public interface PersonDAO {
    void createTablePersons();

    void insertPerson(Person person);

    void updatePerson(Long id, Person person);

    Person findPersonById(Long id);

    void deletePersonById(Long id);

    List<Person> findAllPersons();
}
