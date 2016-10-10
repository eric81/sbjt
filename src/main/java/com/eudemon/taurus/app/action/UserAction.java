package com.eudemon.taurus.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eudemon.taurus.app.entities.OperResult;
import com.eudemon.taurus.app.entities.User;
import com.eudemon.taurus.app.service.UserService;

/**
 * 用户管理Action
 * 
 * @author calvin
 */
@RestController
@RequestMapping(value = "/user")
public class UserAction {
	private Logger logger = LoggerFactory.getLogger(UserAction.class);
	@Autowired
	private UserService sv;

	@GetMapping(value = "/{id}")
	public User get(@PathVariable int id) {
		logger.debug("[user get] id=" + id);
		User user = this.sv.getUser(id);
		return user;
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
	public Page<User> list(@PageableDefault(value = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
		logger.info("[user list] pageable=" + pageable);
		Page<User> rs = this.sv.getUserList(pageable);
		return rs;
	}
	
	@PostMapping(value = "/add")
	public OperResult add(User user) {
		logger.debug("[user add] user=" + user);
		OperResult or = new OperResult();

		sv.addUser(user);
		
		or.setData(user);
		return or;
	}
	
	@GetMapping(value = "/delete/{id}")
	public OperResult delete(@PathVariable int id) {
		logger.debug("[user delete] id=" + id);
		OperResult or = new OperResult();

		this.sv.delete(id);

		return or;
	}
	
	@PostMapping(value = "/modify/{id}")
	public OperResult modify(@PathVariable int id, @RequestParam String role, @RequestParam String permissions) {
		logger.debug("[user modify] role=" + role);
		OperResult or = new OperResult();

		this.sv.modify(id, role, permissions);

		return or;
	}
}
