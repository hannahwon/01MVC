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
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {

	}

	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "INSERT INTO transaction VALUES (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,SYSDATE,?)";
		System.out.println("sql은" + sql);

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("stmt은" + stmt);

		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setString(9, purchaseVO.getDivyDate().replaceAll("-", ""));

		stmt.executeUpdate();

		System.out.println(con);

		stmt.close();
		con.close();

		System.out.println("PurchaseDAO 에서 insert 완료");
	}

	public PurchaseVO findPurchase(int tranNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction t, product p, users u" + " WHERE t.prod_no = p.prod_no"
				+ " AND t.buyer_id = u.user_id" + " AND t.tran_no = ? " + " ORDER BY tran_no";

		System.out.println("PurchaseDAO 에서 sql : " + sql);

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("PurchaseDAO 에서 stmt : " + stmt);

		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();
		System.out.println("PurchaseDAO 에서 rs : " + rs);

		UserVO userVO = new UserVO();
		ProductVO productVO = new ProductVO();
		PurchaseVO purchaseVO = new PurchaseVO();

		while (rs.next()) {
			// 컬럼명으로 찾기 FIND

			userVO.setUserId(rs.getString("USER_ID"));
			userVO.setUserName(rs.getString("USER_NAME"));
			userVO.setAddr(rs.getString("ADDR"));
			userVO.setEmail(rs.getString("EMAIL"));
			userVO.setPassword(rs.getString("PASSWORD"));
			userVO.setPhone(rs.getString("CELL_PHONE"));
			userVO.setRegDate(rs.getDate("REG_DATE"));
			userVO.setRole(rs.getString("ROLE"));
			userVO.setSsn(rs.getString("SSN"));
			purchaseVO.setBuyer(userVO);
			System.out.println("setting 1 완료");

			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			purchaseVO.setPurchaseProd(productVO);
			System.out.println("setting 2 완료");

			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
			purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));
			purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
			System.out.println("setting 3 완료");

		}
		stmt.close();
		con.close();

		System.out.println("findPurchase 완료");
		return purchaseVO;

	}
	
	public PurchaseVO findPurchase2(int prodNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM transaction WHERE prod_no = ?";			
		System.out.println("PurchaseDAO 에서 findPurchase2 sql : " + sql);

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("PurchaseDAO 에서 findPurchase2 stmt : " + stmt);

		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();
		System.out.println("PurchaseDAO 에서 findPurchase2 rs : " + rs);

		PurchaseVO purchaseVO = null;

		while (rs.next()) {
			
			purchaseVO = new PurchaseVO();
			
			
			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
			purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
			purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));	
			System.out.println("setting 완료");

		}
		stmt.close();
		con.close();

		System.out.println("findPurchase2 완료");
		return purchaseVO;

	}

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {

		System.out.println("PurchaseDAO에서 getPurchaseList 호출 : " + buyerId);
		Connection con = DBUtil.getConnection();
		String sql = "SELECT" + " tran_no, buyer_id, receiver_name, receiver_phone, tran_status_code"
				+ " FROM transaction" + " WHERE buyer_id = ?";

		System.out.println("PurchaseDAO에서 getPurchaseList의 sql은" + sql);

		/*
		 * if (searchVO.getSearchCondition() != null) {
		 * if(searchVO.getSearchCondition().equals("0")) { sql +=
		 * " WHERE TRAN_NO LIKE '%"+ searchVO.getSearchKeyword()+ "%'"; } else
		 * if(searchVO.getSearchCondition().equals("1")) { sql +=
		 * " WHERE TRAN_NAME LIKE '%"+ searchVO.getSearchKeyword()+ "%'";
		 * 
		 * } else if(searchVO.getSearchCondition().equals("2")) { sql +=
		 * " WHERE PRICE LIKE '%"+ searchVO.getSearchKeyword()+ "%'";
		 * 
		 * } } sql += " order by TRAN_NO";
		 */

		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		stmt.setString(1, buyerId);
		System.out.println("sql" + sql);
		ResultSet rs = stmt.executeQuery();

		rs.last();

		int total = rs.getRow();
		System.out.println("PurchaseDAO에서 total 로우의 수 : " + total);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		PurchaseVO purchaseVO = null;
		UserService service = new UserServiceImpl();
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();

		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				purchaseVO = new PurchaseVO();
				purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
				purchaseVO.setBuyer(service.getUser(rs.getString("BUYER_ID")));
				purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));

				list.add(purchaseVO);

				if (!rs.next()) {
					break;
				}
			}
		}
		stmt.close();
		con.close();
		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());
		System.out.println("list : " + map.get("list"));

		return map;
	}
	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product";
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no LIKE '%" + searchVO.getSearchKeyword() + "%'";
			}else if(searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name LIKE '%" + searchVO.getSearchKeyword() + "%'";
			}
		}
		sql += " ORDER BY prod_no";
		
		System.out.println("PurchaseDAO에서 getSaleList sql:"+sql);
		
		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		System.out.println("sql" + sql);
		ResultSet rs = stmt.executeQuery();

		rs.last();

		int total = rs.getRow();
		System.out.println("PurchaseDAO에서 total 로우의 수 : " + total);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();

		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				if(this.findPurchase2(vo.getProdNo())!=null) {
					vo.setProTranCode(this.findPurchase2(vo.getProdNo()).getTranCode());
				}
				list.add(vo);

				if (!rs.next()) {
					break;
				}
			}
		}
		
		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());
		System.out.println("getSalelist() : " + map.get("list"));

		stmt.close();
		con.close();
		
		return map;
	}


	

	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();
		System.out.println("purchaseDAO updatePurchase connection 완료" + con);
		String sql = "UPDATE transaction SET payment_option=?, receiver_name=?, receiver_phone=?, demailaddr=?, dlvy_request=?, dlvy_date=? where tran_no=?";
		System.out.println("sql update sql은" + sql);

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("stmt update stmt : " + stmt);

		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDivyAddr());
		stmt.setString(5, purchaseVO.getDivyRequest());
		stmt.setString(6, purchaseVO.getDivyDate().replaceAll("-", ""));
		stmt.setInt(7, purchaseVO.getTranNo());

		stmt.executeUpdate();

		stmt.close();
		con.close();

		System.out.println("purchase 업데이트 실행완료");
	}
	
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		System.out.println("PurchaseDAO에서 updateTranCodeByProd() 호출 :"+purchaseVO);
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getTranNo());
		
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
		
		System.out.println("updateTranCode 완료");
		
		
	}

}
