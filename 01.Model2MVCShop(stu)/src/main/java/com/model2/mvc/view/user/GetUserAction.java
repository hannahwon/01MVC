package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class GetUserAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=request.getParameter("userId");
		
		UserService service=new UserServiceImpl();
		UserVO dbVO=service.getUser(userId);
		
		request.setAttribute("vo", dbVO);

		return "forward:/user/readUser.jsp";
	}
}