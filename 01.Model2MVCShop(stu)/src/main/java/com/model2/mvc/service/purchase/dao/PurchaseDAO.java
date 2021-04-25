package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseDAO {

	public PurchaseDAO() {
		
	}
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO TRANSACION VALUES(seq_transaction_tran_no.nextval,seq_product_prod_no,seq_transaction_tran_buyer_id,?,?,?,?,?,SYSDATE,?)";
				
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverPhone());
		stmt.setString(3, purchaseVO.getDivyAddr());
		stmt.setString(4, purchaseVO.getDivyRequest());
		stmt.setString(5, purchaseVO.getTranCode());
		stmt.setString(6, purchaseVO.getDivyDate());
	
		stmt.executeUpdate();
		
		System.out.println("insert :"+stmt+"완료");
		System.out.println(con);
		
		con.close();
		
	}
	
	public PurchaseVO findPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM TRANSACTION WHERE TRAN_NO =?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		//stmt.setInt(1, purchaseVO);
		
		ResultSet rs = stmt.executeQuery();
		
		
	/*	
		PurchaseVO purchaseVO = null;
		while(rs.next()) {
			purchaseVO = new PurchaseVO();
			purchaseVO.setTranCode(rs.getInt("tran_no"));
			purchaseVO.setProdName(rs.getInt("prod_no"));
			userVO.setBuyer(rs.getString("buyer_id"));
			purchaseVO.setManuDate(rs.getString("payment_option"));
			purchaseVO.setPrice(rs.getString("receiver_name"));
			purchaseVO.setFileName(rs.getString("receiver_phone"));
			purchaseVO.setRegDate(rs.getString("demailaddr"));
			purchaseVO.setRegDate(rs.getString("dlvy_request"));
			purchaseVO.setRegDate(rs.getString("tran_status_code"));
			purchaseVO.setRegDate(rs.getDate("order_data"));
			purchaseVO.setRegDate(rs.getDate("dlvy_date"));
			
			
		}*/
		con.close();
		
		return purchaseVO;
		
	}
		
	
	public HashMap<String,Object> getPurcahse(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM TRANSACTION";
		if (searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE TRAN_NO LIKE '%"+ searchVO.getSearchKeyword()+ "%'";
			} else if(searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE TRAN_NAME LIKE '%"+ searchVO.getSearchKeyword()+ "%'";
	
			} else if(searchVO.getSearchCondition().equals("2")) {
				sql += " WHERE PRICE LIKE '%"+ searchVO.getSearchKeyword()+ "%'";
	
			}
		}
		sql += " order by TRAN_NO";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				vo.setPrice(rs.getInt("PRICE"));

				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}
	
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		System.out.println("purchase connection 완료");
		String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getProdName());
		stmt.setString(2, purchaseVO.getProdDetail());
		stmt.setString(3, purchaseVO.getManuDate().replaceAll("-", ""));
		stmt.setInt(4, purchaseVO.getPrice());
		stmt.setString(5, purchaseVO.getFileName());
		stmt.setInt(6, purchaseVO.getProdNo());
		stmt.executeUpdate();
		System.out.println("업데이트실행완료");
		con.close();
	}
	
}
