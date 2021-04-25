package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.purchaseService;
import com.model2.mvc.service.purchase.impl.purchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.purchaseVO;



public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		purchaseVO purchaseVO=new PurchaseVO();
		purchaseVO.setProdName(request.getParameter("prodName"));
		purchaseVO.setProdDetail(request.getParameter("prodDetail"));
		purchaseVO.setManuDate(request.getParameter("manuDate"));
		purchaseVO.setPrice(Integer.parseInt(request.getParameter("price")));
		purchaseVO.setFileName(request.getParameter("fileName"));

		System.out.println(purchaseVO);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addpurchase(purchaseVO);
		request.setAttribute("PurchaseVO", purchaseVO);
		
		return "forward:/purchase/addpurchase.jsp";
	}
}