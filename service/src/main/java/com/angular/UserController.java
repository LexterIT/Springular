// package com.angulartry.lexter;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestBody;
// import java.util.List;
// import java.util.ArrayList;
// import org.springframework.web.bind.annotation.CrossOrigin;

// import com.querydsl.core.types.Predicate;

// @CrossOrigin(origins = "*")
// @RestController
// public class UserController {

//     @Autowired
//     private UserRepository userRepository;

//     @GetMapping("/users/{id}")
//     public User findUserById(@PathVariable("id") long id) {
//         User user = userRepository.findById(id).get();
//         System.out.println(user);
//         return user;
//     }

//     @PostMapping("/users")
//     public void addUser(@RequestBody User user) {
//         System.out.println(user);
//         userRepository.save(user);
//     }

//     @PutMapping("/users/{id}")
//     public void updateUser(@RequestBody User user, @PathVariable int id) {
//         user.setId(id);
//         System.out.println(user);
//         userRepository.save(user);
//     }

//     @DeleteMapping("/users/{id}")
//     public void deleteUser(@PathVariable long id) {
//         userRepository.deleteById(id);
//     }

//     // @GetMapping("/users")
//     // public Page<User> findAllUsers(Pageable pageable) {
//     //     return userRepository.findAll(pageable);
//     // }

//     @GetMapping("/users")
//     public List<User> findAllUsers() {
//         List<User> listUser = new ArrayList<User>();
//         for(User user : userRepository.findAll()) {
//             listUser.add(user);
//         }
//         // userRepository.findAll().stream().forEach(u -> listUser.add(u));
//         System.out.println(listUser);
//         return listUser;
//     }

//     @GetMapping("/sortedusers")
//     public Page<User> findAllUsersSortedByName() {
//         Pageable pageable = PageRequest.of(0, 5, Sort.by("name"));
//         return userRepository.findAll(pageable);
//     }

//     @GetMapping("/filteredusers")
//     public Iterable<User> getUsersByQuerydslPredicate(@QuerydslPredicate(root = User.class) Predicate predicate) {
//         return userRepository.findAll(predicate);
//     }
// }