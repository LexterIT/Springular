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

import com.querydsl.core.types.Predicate;


// @RequestMapping(value="/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleSerivce;

    // @Autowired
    // private RoleConverter roleConverter;

    @GetMapping("/api/roles")
    public List<Role> getAllRoles(){
        List<Role> roles = roleSerivce.getAllRoles();
        System.out.println(roles);
        return roles;
    }

    @GetMapping("/api/roles/{id}")
    public Role getRole(@PathVariable Integer id) {
        Role role = roleSerivce.getRole(id);
        return role;
    }

    @PutMapping("/api/roles/{id}")
    public void updateRole(@PathVariable Integer id, @RequestBody Role role) {
        System.out.println(role);
        role.setId(id);
        roleSerivce.updateRole(role);

    }

    @PostMapping("/api/roles")
    public Role addRole(@RequestBody Role role) {
        System.out.println(role);
        if(roleSerivce.addRole(role)) {
            return role;
        } else
        return null;
    }

    @DeleteMapping("/api/roles/{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleSerivce.deleteRole(id);
    }

    // @RequestMapping(value="/addRole")
    // public ModelAndView addRole(RoleDTO roleDTO) {
    //     ModelAndView mv = new ModelAndView(new RedirectView("/role/roles"));
    //     Role role = roleConverter.dtoToEntity(roleDTO);
    //     roleSerivce.addRole(role);
    //     return mv;
    // }

    // @RequestMapping(value="/addRoleForm")
    // public String addRoleForm() {
    //     return "addRoleForm.jsp";
    // }

    // @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    // public ModelAndView editRoleForm(@PathVariable int id) {
    //     ModelAndView mv = new ModelAndView("editRoleForm.jsp");
    //     Role role = roleSerivce.getRole(id);
    //     RoleDTO roleDTO = roleConverter.entityToDTO(role);
    //     mv.addObject("role",roleDTO);
    //     return mv;
    // }

    // @RequestMapping(value="/edit/updateRole")
    // public ModelAndView updateRole(RoleDTO roleDTO) {
    //     ModelAndView mv = new ModelAndView(new RedirectView("/role/roles"));
    //     Role role = roleConverter.dtoToEntity(roleDTO);
    //     roleSerivce.updateRole(role);
    //     return mv;
    // }

    // @RequestMapping(value="/delete/{id}")
    // public ModelAndView deleteRoleForm(@PathVariable int id) {
    //     ModelAndView mv = new ModelAndView("deleteRoleResult.jsp");
    //     roleSerivce.deleteRole(id);
    //     mv.addObject("roleId",id);
    //     return mv;  
    // }

}