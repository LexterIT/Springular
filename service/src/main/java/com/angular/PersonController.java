package com.angulartry.lexter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import com.querydsl.core.types.Predicate;


// @RequestMapping(value="/role")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleSerivce;

    // @Autowired
    // private RoleConverter roleConverter;

    @GetMapping("/api/people")
    public List<Person> getAllPerson() {
        List<Person> people = personService.getAllPerson();
        System.out.println(people);
        return people;
    }

    @GetMapping("/api/people/{id}")
    public Person getPerson(@PathVariable Integer id) {
        System.out.println(id);
        Person person = personService.getPerson(id);
        System.out.println(person);
        return person;
    }

    @PostMapping("/api/people")
    public void addPerson(@RequestBody Person person) {
        System.out.println("Controller:" + person);
        personService.addPerson(person);
    }

    @DeleteMapping("/api/people/{id}")
    public void deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
    }

    @PutMapping("/api/people/{id}")
    public void updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        System.out.println(id);
        System.out.println("Update before setId:" + person);
        person.setId(id);
        Person tempPerson = personService.getPerson(id);
        person.setContactInfo(tempPerson.getContactInfo());
        person.setRoles(tempPerson.getRoles());
        System.out.println("Update after setId:" + person);
        personService.updatePerson(person);
    }

    @GetMapping("/api/people/roles/{id}")
    public List<Role> updatePersonRoles(@PathVariable Integer id) {
        Person person = personService.getPerson(id);
        List<Role> availableRoles = roleSerivce.getAllRoles();
        System.out.println("Before Removal: " + availableRoles);
        List<Role> tempRoles = new ArrayList<Role>(availableRoles);
        System.out.println("Person Roles:" + person.getRoles());
        for(Role role: tempRoles) {
            if(person.getRoles().contains(role)) {
                availableRoles.remove(role);
            }
        }
        System.out.println("After removal" + availableRoles);
        return availableRoles;
    }

    @PostMapping("/api/people/roles/{id}/{roleId}")
    public void updatePersonRoleAdd(@PathVariable Integer id, @PathVariable Integer roleId) {
        Role role = roleSerivce.getRole(roleId);
        Person person = personService.getPerson(id);
        // System.out.println("roles before add:"+person.getRoles());
        person.getRoles().add(role);
        // System.out.println("roles after add:"+person.getRoles());
        personService.updatePerson(person);
    }

    @DeleteMapping("/api/people/roles/{id}/{roleId}")
    public void updatePersonRoleDelete(@PathVariable Integer id, @PathVariable Integer roleId) {
        Role role = roleSerivce.getRole(roleId);
        Person person = personService.getPerson(id);
        // System.out.println("roles before removal:" + person.getRoles());
        person.getRoles().remove(role);
        // System.out.println("roles after removal:" + person.getRoles());
        personService.updatePerson(person);
    }

    @GetMapping("/api/people/contacts/{id}")
    public List<ContactInfo> personContacts(@PathVariable Integer id) {
        Person person = personService.getPerson(id);
        return person.getContactInfo();
    }

    @PostMapping("/api/people/contacts/{id}")
    public ContactInfo updatePersonContactAdd(@PathVariable Integer id, @RequestBody ContactInfo contactInfo) {
        System.out.println(contactInfo);
        Person person = personService.getPerson(id);
        int newContactIndex = person.getContactInfo().size();
        List<ContactInfo> contactInfos = person.getContactInfo();
        System.out.println("before Add:" + contactInfos);
        contactInfos.add(contactInfo);
        System.out.println("after Add:" + contactInfos);
        person.setContactInfo(contactInfos);
        personService.updatePerson(person);
        contactInfo.setIndex(newContactIndex);
        return contactInfo;
    }

    @DeleteMapping("/api/people/contacts/{id}/{contactIndex}")
    public void updatePersonContactDelete(@PathVariable Integer id, @PathVariable Integer contactIndex) {
        Person person = personService.getPerson(id);
        ContactInfo contactInfo = person.getContactInfo().stream()
                                    .filter( c -> c.getIndex() == contactIndex)
                                    .findFirst()
                                    .get();
        System.out.println(contactInfo);

        person.getContactInfo().remove(contactInfo);
        // person.getContactInfo().stream()
        // .filter(c -> c.getId() != contactInfo.getId());
        System.out.println(person);
        personService.updatePerson(person);
    }

    // @GetMapping("/api/roles")
    // public List<Role> getAllRoles(){
    //     List<Role> roles = roleSerivce.getAllRoles();
    //     System.out.println(roles);
    //     return roles;
    // }

    // @GetMapping("/api/roles/{id}")
    // public Role getRole(@PathVariable Integer id) {
    //     Role role = roleSerivce.getRole(id);
    //     return role;
    // }

    // @PutMapping("/api/roles/{id}")
    // public void updateRole(@PathVariable Integer id, @RequestBody Role role) {
    //     System.out.println(role);
    //     role.setId(id);
    //     roleSerivce.updateRole(role);

    // }

    // @PostMapping("/api/roles")
    // public Role addRole(@RequestBody Role role) {
    //     System.out.println(role);
    //     if(roleSerivce.addRole(role)) {
    //         return role;
    //     } else
    //     return null;
    // }

    // @DeleteMapping("/api/roles/{id}")
    // public void deleteRole(@PathVariable Integer id) {
    //     roleSerivce.deleteRole(id);
    // }
}