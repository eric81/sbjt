package com.eudemon.taurus.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eudemon.taurus.app.entities.OperResult;
import com.eudemon.taurus.app.entities.User;
import com.eudemon.taurus.app.service.UserService;
import com.eudemon.taurus.app.utils.JasonUtils;

/**
 * 使用自己封装的方法（fastjson）返回json及jsonp格式的数据
 * @author eric
 *
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserApiAction {
	private Logger logger = LoggerFactory.getLogger(UserApiAction.class);
	@Autowired
	private UserService sv;

	@GetMapping(value = "/{id}")
	public void get(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("[api user get] id=" + id);

		User user = this.sv.getUser(id);

		JasonUtils.writeJasonP(request, response, user);
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
	public void list(@PageableDefault(value = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("[api user list] pageable=" + pageable);

		Page<User> rs = this.sv.getUserList(pageable);

		JasonUtils.writeJasonP(request, response, rs);
	}

	@RequestMapping(value = "/add")
	public void add(User user, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("[api user add] user=" + user);
		OperResult or = new OperResult();

		sv.addUser(user);

		or.setData(user);
		JasonUtils.writeJasonP(request, response, or);
	}

	@GetMapping(value = "/delete/{id}")
	public void delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("[api user delete] id=" + id);
		OperResult or = new OperResult();

		this.sv.delete(id);

		JasonUtils.writeJasonP(request, response, or);
	}

	@RequestMapping(value = "/modify")
	public void modify(User user, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("[api user modify] user=" + user);
		OperResult or = new OperResult();

		this.sv.modify(user);

		JasonUtils.writeJasonP(request, response, or);
	}
}