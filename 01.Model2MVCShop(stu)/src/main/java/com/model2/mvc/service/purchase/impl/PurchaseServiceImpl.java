package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceImpl implements PurchaseService {

	private ProductDAO prodDAO;
	private PurchaseDAO dao;
	
	public PurchaseServiceImpl() {
		
	}
	@Override
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception {
		return dao.insertPurchase(purchaseVO);

	}

	@Override
	public PurchaseVO getPurchase(int purchaseVO) throws Exception {
		
		return dao.findPurchase(purchaseVO);
	}

	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		
		return dao.getPurchaseList(searchVO);
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		
		return dao.getSaleList(searchVO);
	}


	@Override
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		return dao.updatePurchase(purchaseVO);
	}

	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		dao.updateTranCode(purchaseVO);

	}
	@Override
	public PurchaseVO updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
