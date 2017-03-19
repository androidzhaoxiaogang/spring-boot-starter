package com.xcesys.extras.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcesys.extras.framework.dao.repository.IBaseRepository;
import com.xcesys.extras.framework.dao.repository.RoleRepository;
import com.xcesys.extras.framework.entity.Role;
import com.xcesys.extras.framework.service.impl.BaseCrudService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RoleService extends BaseCrudService<Role, Long> {
	@Autowired
	private RoleRepository roleRepository;

	public int countByname(String username) {
		return roleRepository.countByName(username);
	}

	public Role findByName(String Rolename) {
		return roleRepository.findByName(Rolename);
	}

	@Override
	public IBaseRepository<Role, Long> getRepository() {
		return roleRepository;
	}

	public int countByName(String name) {
		return roleRepository.countByName(name);
	}

	public Role usersInRole(Long id) {
		return roleRepository.findUsersById(id);
	}
}