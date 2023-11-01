import java.sql.*;
import java.util.List;

public class DaoImpl implements PersonDAO{

    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public void createTablePersons() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/postgres", "postgres", "1234");
            statement = connection.createStatement();


            String sql = "CREATE TABLE persons ("
                    + "id INT PRIMARY KEY serial primary key,"
                    + "name VARCHAR(255),"
                    + "age INT,"
                    + "city VARCHAR(255)"
                    + ")";

            statement.executeUpdate(sql);
            System.out.println("Таблица Persons успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void insertPerson(Person person) {
        try {
            String query = "INSERT INTO persons (name, age, gender, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getGender());
            statement.setString(4, person.getEmail());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePerson(Long id, Person person) {
        try {
            String query = "UPDATE persons SET name=?, age=?, gender=?, email=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getGender());
            statement.setString(4, person.getEmail());
            statement.setLong(5, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Person findPersonById(Long id) {
        try {
            String query = "SELECT * FROM persons WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(((ResultSet) resultSet).getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setGender(resultSet.getString("gender"));
                person.setEmail(resultSet.getString("email"));

                System.out.println(person.toString());
            } else {
                System.out.println("Person not found.");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deletePersonById(Long id) {
        try {
            String query = "DELETE FROM persons WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Person> findAllPersons() {
        try {
            String query = "SELECT * FROM persons";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setGender(resultSet.getString("gender"));
                person.setEmail(resultSet.getString("email"));

                System.out.println(person.toString());
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
