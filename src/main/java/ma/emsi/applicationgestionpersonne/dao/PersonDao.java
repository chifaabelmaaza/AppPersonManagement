package ma.emsi.applicationgestionpersonne.dao;

import ma.emsi.applicationgestionpersonne.entities.Person;

import java.util.List;



public interface PersonDao {
	void insert(Person person);

	void update(Person person);

	void deleteById(Integer id);

	Person findById(Integer id);

	List<Person> findAll();

	Person findByIdentity(String name);

	Person findByIdentity(Integer id);
}
