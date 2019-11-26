package com.angulartry.lexter;


import java.util.List;

public interface PersonService{

	List<Person> getAllPerson();
	
	Person getPerson(int id);

	void addPerson(Person person);

	void updatePerson(Person person);	

	void deletePerson(int id);
}