package com.eudemon.taurus.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eudemon.taurus.app.entities.OperResult;
import com.eudemon.taurus.app.entities.User;
import com.eudemon.taurus.app.service.UserService;

/**
 * 使用普通Controller跳转Thymeleaf模板页面
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/user")
public class UserAction {
	@Autowired
	private UserService sv;

	@GetMapping(value = "/{id}")
	public String get(@PathVariable int id, ModelMap model) {
		User user = this.sv.getUser(id);
		model.addAttribute("user", user);
		return "user/detail";
	}

	/**
	 * 分页查询 <br/>
	 * 请求样例：/user/list?page=0&size=10&sort=id,desc&sort=name,asc
	 * 
	 * @param pageable
	 *            page: 第几页，从0开始，默认为第0页 <br/>
	 *            size: 每一页的大小，默认为10 <br/>
	 *            sort: 例如sort=id,desc&sort=lastname,asc表示在按id倒序排列基础上按name正序排列
	 * @return
	 */
	@GetMapping(value = "/list")
	public String list(@PageableDefault(value = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, ModelMap model) {
		Page<User> rs = this.sv.getUserList(pageable);
		model.addAttribute("rs", rs);
		return "user/list";
	}
	
	@PostMapping(value = "/add")
	public OperResult add(User user) {
		OperResult or = new OperResult();

		sv.addUser(user);
		
		or.setData(user);
		return or;
	}
	
	@GetMapping(value = "/delete/{id}")
	public OperResult delete(@PathVariable int id) {
		OperResult or = new OperResult();

		this.sv.delete(id);

		return or;
	}
	
	@PostMapping(value = "/modify/{id}")
	public OperResult modify(@PathVariable int id, @RequestParam String role, @RequestParam String permissions) {
		OperResult or = new OperResult();

		this.sv.modify(id, role, permissions);

		return or;
	}
}
