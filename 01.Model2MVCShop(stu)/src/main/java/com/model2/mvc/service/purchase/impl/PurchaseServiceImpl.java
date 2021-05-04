package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseServiceImpl implements PurchaseService {

	private ProductDAO prodDAO;
	private PurchaseDAO dao;
	
	public PurchaseServiceImpl() {
		dao = new PurchaseDAO();
	}
	@Override
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		dao.insertPurchase(purchaseVO);

	}

	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		
		return dao.findPurchase(tranNo);
	}
	
	@Override
	public PurchaseVO getPurchase2(int prodNo) throws Exception {
		
		return dao.findPurchase2(prodNo);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		
		return dao.getPurchaseList(searchVO, buyerId);
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		
		return dao.getSaleList(searchVO);
	}


	@Override
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		dao.updatePurchase(purchaseVO);	
	}


	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		dao.updateTranCode(purchaseVO);
	}
	

}
