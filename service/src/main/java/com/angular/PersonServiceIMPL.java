package com.angulartry.lexter;


import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PersonServiceIMPL implements PersonService {

	// private List<Role> roles = new ArrayList<Role>();

	@Autowired
	private PersonRepository personRepository;

	// public RoleServiceIMPL(HibernateUtil hibernateUtil) {
	// 	this.hibernateUtil = hibernateUtil;
	// }

	public List<Person> getAllPerson() {
		List<Person> people = new ArrayList<Person>();
		personRepository.findAll().forEach(person -> people.add(person));
		return people;
	}

	public Person getPerson(int id) {
		Person person = personRepository.findById(id).get();
		return person;	
	}

	public void addPerson(Person person) {
		System.out.println("Service:" + person);
		personRepository.save(person);
	}

	public void deletePerson(int id) {
		personRepository.deleteById(id);
	}

	public void updatePerson(Person person) {
		personRepository.save(person);
	}

	// public Role getRole(int id) {
	// 	// Role role = (Role) hibernateUtil.getSingleObject(Role.class, id);
	// 	Role role = roleRepository.findById(id).get();
 // 		if(role == null) {
	// 		return null;
	// 	}
	// 	return role;
	// }

	// public boolean addRole(Role role) {
	// 	List<Role> roles = getAllRoles();
	// 	System.out.println(roles);
	// 	boolean existing = roles.stream().anyMatch(r -> r.getRole().equals(role.getRole()));
	// 	System.out.println(existing);
	// 	if(role == null) {
	// 		return false;
	// 	}
	// 	if(!existing) {
	// 		roleRepository.save(role);
	// 		return true;
	// 	}
	// 	else {
	// 		return false;
	// 	}
	// }

	// public void updateRole(Role role) {
	// 	// hibernateUtil.updateObject(role);
	// 	roleRepository.save(role);
	// }

	// public void deleteRole(int id) {
	// 	// Role role = (Role) hibernateUtil.getSingleObject(Role.class, id);
	// 	// hibernateUtil.deleteObject(role);
	// 	roleRepository.deleteById(id);
	// }
}