package com.angulartry.lexter;


import java.util.List;

public interface RoleService{

	List<Role> getAllRoles();
	
	Role getRole(int id);

	boolean addRole(Role role);

	void updateRole(Role role) ;	

	void deleteRole(int id);
}