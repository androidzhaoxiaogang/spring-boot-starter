package com.xcesys.extras.framework.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xcesys.extras.framework.core.controller.BaseCrudController;
import com.xcesys.extras.framework.core.service.ICrudService;
import com.xcesys.extras.framework.core.util.ConvertUtils;
import com.xcesys.extras.framework.entity.Role;
import com.xcesys.extras.framework.entity.User;
import com.xcesys.extras.framework.service.RoleService;
import com.xcesys.extras.framework.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseCrudController<User, Long> {
	@Autowired(required = false)
	PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
	@Autowired
	UserService service;
	@Autowired
	RoleService roleService;

	@Override
	protected ICrudService<User, Long> getCrudService() {
		return service;
	}

	@Override
	protected String getSuffix() {
		return "user";
	}

	@ModelAttribute("roles")
	public Iterable<Role> getRoles(@PathVariable(name = "id", required = false) Long id) {
		return roleService.findAll();
	}

	protected User newModel() {
		User user = new User();
		return user;
	}

	@Override
	protected void preSave(User m, HttpServletRequest request) {
		// 解决checkbox没有勾选时传值问题
		m.setEnabled(ConvertUtils.convertObjectToBoolean(request.getParameter("enabled")));
		m.getRoles().clear();
		String[] ids = request.getParameterValues("roles");
		if (ids != null && ids.length > 0) {
			Iterable<Role> roles = roleService.findByIds(ConvertUtils.convertStringArrayToLongArray(ids));
			roles.forEach(m.getRoles()::add);
		}
		super.preSave(m, request);
	}

	@ResponseBody
	@GetMapping(value = "resetpwd")
	public int resetpwd(Long[] ids) {
		if (ids != null && ids.length > 0) {
			return service.resetpwd(ids);
		}
		return -1;
	}

	@GetMapping(value = "unique")
	@ResponseBody
	public boolean unique(String username, String oldUsername) {
		if (!StringUtils.isBlank(oldUsername) && oldUsername.trim().equals(username))
			return true;
		return service.countByUsername(username) <= 0;
	}
}
