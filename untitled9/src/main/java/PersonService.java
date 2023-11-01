

import java.util.List;

public class PersonService {
    private final PersonDAO personDao;

    public PersonService(PersonDAO personDao) {
        this.personDao = personDao;
    }

    public void createTablePersons() {
        personDao.createTablePersons();
    }

    public void insertPerson(Person person) {
        personDao.insertPerson(person);
    }

    public void updatePerson(Long id, Person person) {
        personDao.updatePerson(id, person);
    }

    public Person findPersonById(Long id) {
        return personDao.findPersonById(id);
    }

    public void deletePersonById(Long id) {
        personDao.deletePersonById(id);
    }

    public List<Person> findAllPersons() {
        return personDao.findAllPersons();
    }
}