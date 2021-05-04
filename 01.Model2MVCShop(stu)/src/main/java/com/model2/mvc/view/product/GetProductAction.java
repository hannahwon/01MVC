package com.model2.mvc.view.product;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;



public class GetProductAction extends Action{

	public GetProductAction() {
		
	}
	
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		Cookie cookie = new Cookie("history"+request.getParameter("prodNo"), request.getParameter("prodNo")); //쿠키생성
		cookie.setMaxAge(-1); //-1동안 보관 : 브라우저 열려있는 동안 
		response.addCookie(cookie); //쿠키저장 : response객체에 저장해야한다. 클라이언트에게 보낸다.
		System.out.println("GetProductAction.java에서 cookie 저장:"+cookie);
		
		int prodNo =Integer.parseInt(request.getParameter("prodNo"));
		
		System.out.println("GetProductAction에서 prodNo : "+prodNo);
		
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(prodNo);
		
		System.out.println(vo);
		request.setAttribute("vo", vo);
		System.out.println(request.getParameter("menu"));
		
		if(request.getParameter("menu").equals("manage")) {
			return "forward:/updateProductView.do";
		}
		
		return "forward:/product/getProduct.jsp";
	}
}