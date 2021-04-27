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
	public PurchaseVO getPurchase(int purchaseVO) throws Exception {
		
		return null;
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		
		return null;
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		
		return null;
	}


	@Override
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
				
	}


	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		

	}
	

}
