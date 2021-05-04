package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;




public class AddPurchaseAction extends Action {

	
	public AddPurchaseAction() {
		
	}
	
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		PurchaseVO purchaseVO=new PurchaseVO();
		
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setTranCode("1");
		
		ProductService pservice = new ProductServiceImpl();
		ProductVO product = pservice.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		purchaseVO.setPurchaseProd(product);
		
		HttpSession session = request.getSession();
		purchaseVO.setBuyer((UserVO)session.getAttribute("user"));

		System.out.println("AddPurchaseAction.java에서 purchaseVO : "+purchaseVO);
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}
}