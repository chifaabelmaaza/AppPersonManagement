package ma.emsi.applicationgestionpersonne.service;

import ma.emsi.applicationgestionpersonne.dao.PersonDao;
import ma.emsi.applicationgestionpersonne.dao.impl.PersonDaoImp;
import ma.emsi.applicationgestionpersonne.entities.Person;

import java.util.List;



public class PersonService {
	private PersonDao personDao = new PersonDaoImp();

	
	public List<Person> findAll(){
		return personDao.findAll();
	}

	public void save(Person person) {
		personDao.insert(person);
		
	}
	public void update(Person person) {
		personDao.update(person);
		
	}
	public void remove(Person person) {
		personDao.deleteById(person.getId());
	}
	
	public Person SearchForPerson() {
		Person person = new Person();
		
		return person;
	}
	
	
	
}
