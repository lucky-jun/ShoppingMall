package com.j.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONArray;
import com.j.dao.GoodsManageDao;
import com.j.dao.UserManageDao;
import com.j.pojo.EmpOrder;
import com.j.pojo.Goods;
import com.j.pojo.LookOrder;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.OrderHistory;
import com.j.pojo.ToCart;
import com.j.pojo.User;
import com.j.service.GoodsManageService;
import com.j.util.DateFormat;
@Service
public class GoodsManageServiceImpl implements GoodsManageService {
	
	@Autowired
	private GoodsManageDao goodsManageDao;
	@Autowired
	private UserManageDao userManageDao;
	@Autowired
	private OrderHistory orderHistory;
	@Autowired
	private EmpOrder empOrder;
	

	
//����
	//�û����ܣ�
		//�����Ʒ
	@Override
	public List<Goods> queryGoodsByUserAll() {
		return goodsManageDao.queryGoodsByUserAll();
	}
	@Override
	public List<Goods> queryGoodsByPage(int page, int number) {
		List<Goods> queryGoodsByPage = goodsManageDao.queryGoodsByPage(page, number);
		return queryGoodsByPage;
	}
		//�����Ʒ����
	@Override
	public Goods queryGoodsByID(int goo_id) {
		return goodsManageDao.queryGoodsByID(goo_id);
	}
		//ֱ����Ʒ�������������\���ﳵ����
	@Override
	public List<Goods> queryGoodsByID(List<Integer> ids) {
		
		return goodsManageDao.queryGoodsByListID(ids);
	}
//	������ﳵ
	@Override
	public List<ToCart> queryMyCartByUserAll(int userid) {
		List<ToCart> queryMyCartByUserAll = goodsManageDao.queryMyCartByUserAll(userid);
		return queryMyCartByUserAll;
	}
//	�����Ʒ�����ﳵ
	@Override
	public boolean insertGoToMyCart(MyCart mycart) {
		System.out.println("�����Ʒ�����ﳵ");
		System.out.println(mycart);
		List<MyCart> queryMyCartOneGoodsByUserID = goodsManageDao.queryMyCartOneGoodsByUserID(mycart.getCar_userid(), mycart.getCar_gooid());
//		System.out.println(queryMyCartOneGoodsByUserID);
//		System.out.println(queryMyCartOneGoodsByUserID.isEmpty());
//		Ϊ��ֱ�Ӳ���
		if(queryMyCartOneGoodsByUserID.isEmpty()) {
			System.out.println("�޸ò�Ʒ");
			int insertGoToMyCart = goodsManageDao.insertGoToMyCart(mycart);
			return insertGoToMyCart>0;
		}else {
//			��Ϊ�ռ�����Ʒ����{car_goonum} where car_userid=#{car_userid} and car_gooid=#{car_gooid}
			System.out.println("�иò�Ʒ");
			System.out.println(queryMyCartOneGoodsByUserID.get(0).getCar_goonum());
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("car_goonum", queryMyCartOneGoodsByUserID.get(0).getCar_goonum()+1);
			map.put("car_userid", mycart.getCar_userid());
			map.put("car_gooid", mycart.getCar_gooid());
			System.out.println(mycart.getCar_goonum());
			System.out.println(map);
			int updataMyCartToNumberinOne = goodsManageDao.updataMyCartToNumberinOne(map);
			return updataMyCartToNumberinOne>0;
		}
	}
//	�����ҵĶ���
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
	@Override
	public Map insertGoToMyOrder(Map map2) {
		double sumprice = 0;
		String goodsInf = "";
		System.out.println(map2);
		List list = (List) map2.get("goods");
		//����goodsid������Ʒid
	    List<Integer> goodsid = new ArrayList<Integer>();
		for(int i =0 ;i<list.size();i++) {
			System.out.println(list);
		    JSONArray jsonArray = new JSONArray(list);  
		    System.out.println(jsonArray.getJSONObject(i).get("car_gooid")==null);
		    System.out.println(jsonArray.getJSONObject(i).get("goo_id"));
		    int id = 0;
		    if(jsonArray.getJSONObject(i).get("car_gooid")!=null) {
		    	//�ӹ��ﳵ��ȡ������ƷID
		    	id =(int) jsonArray.getJSONObject(i).get("car_gooid");//�õ���һ����id 
		    }else {
		    	//����Ʒ�����ȡ������ƷID
		    	id =(int) jsonArray.getJSONObject(i).get("goo_id");//�õ���һ����id 
		    }
		    int number =(int) jsonArray.getJSONObject(i).get("number");//�õ���һ����number 
		    System.out.println("-----------------------------------------------------------------");
		    String substring = jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16);
		    System.out.println(jsonArray.getJSONObject(i).get("sump").getClass().toString());
		    System.out.println(jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16));
		    double doubleValue = 0;
		    if(substring.equals("Integer")) {
			    doubleValue = ((Integer) jsonArray.getJSONObject(i).get("sump")).doubleValue();//�õ���һ����name  
		    }else{
		    	doubleValue = ((Double) jsonArray.getJSONObject(i).get("sump")).doubleValue();//�õ���һ����name  
		    }
		    System.out.println(sumprice);
		    System.out.println(doubleValue);
		    sumprice+=doubleValue;
		    System.out.println(sumprice);
		    goodsid.add(id);
		    goodsInf+=id+"-"+number+",";
		}
		System.out.println("��ƷID��"+goodsid);
		String substring = goodsInf.substring(0,goodsInf.length()-1);
		System.out.println("��Ʒ��Ϣ��"+substring);
		System.out.println("��Ʒ�ܼۣ�"+sumprice);
//		System.out.println("userID1��"+map2.get("userId"));
//		System.out.println("userID2��"+Integer.valueOf((String) map2.get("userId")));
//		System.out.println("userID3��"+Integer.valueOf((String) map2.get("userId")).intValue());
		
		MyOrder myOrder = new MyOrder(Integer.valueOf((String) map2.get("userId")).intValue(), substring, sumprice, "����δ֧��", "�ȴ�֧��", new Date());
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			goodsManageDao.insertGoToMyOrder(myOrder);
//			goodsManageDao.deleteToMyCartByList(map)
//			int a = 1/0;
			int queryMyOrderNewByUserID = goodsManageDao.queryMyOrderNewByUserID(myOrder.getOrd_userid());
			map.put("MyOrderId", queryMyOrderNewByUserID);
			map.put("flag", true);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			map.put("flag", false);
		}
//		int insertGoToMyOrder = goodsManageDao.insertGoToMyOrder(myOrder);
//		int queryMyOrderNewByUserID = goodsManageDao.queryMyOrderNewByUserID(myOrder.getOrd_userid());
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("MyOrderId", queryMyOrderNewByUserID);
//		map.put("flag", insertGoToMyOrder>0);
		System.out.println("service:"+map);
		return map;
	}
//	�޸�֧��״̬
	@Override
	public boolean updateMyOrderPayState(String ord_paystate, String ord_orderstate,String ord_id) {
		int updateMyOrderPayState = goodsManageDao.updateMyOrderPayState(ord_paystate, ord_orderstate,ord_id);
		return updateMyOrderPayState>0;
	}
//	��ѯ�ҵĶ���
	@Override
	public Map<String, Object> queryMyOrderByUserID(int userId, int page, int limit) {
//		��ȡ�����е���Ʒ
		List<MyOrder> queryMyOrderByUserID = goodsManageDao.queryMyOrderByUserID(userId, page, limit);
		System.out.println(queryMyOrderByUserID);
		System.out.println(queryMyOrderByUserID.size());
		
		List<Integer> listid = new ArrayList<Integer>();
		List<Integer> listnumber = new ArrayList<Integer>();
		List<LookOrder> listorder = new ArrayList<LookOrder>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		
			for(int i =0;i<queryMyOrderByUserID.size();i++) {
				String[] split = queryMyOrderByUserID.get(i).getOrd_goodsinf().split(",");
				System.out.println("�������:"+queryMyOrderByUserID.get(i).getOrd_id());
				System.out.println("split:"+split.toString());
				System.out.println("split.length:"+split.length);
				//����IDList
				List<Integer> listOrderById = new ArrayList<Integer>();
				//��Ӧ������ƷNumber
				List<Integer> listOrderBynumber = new ArrayList<Integer>();
				//��������ȡ������ƷID����Ʒ��������
				for(int a=0;a<split.length;a++) {
					String[] split2 = split[a].split("-");
					listOrderById.add(Integer.valueOf(split2[0]));
					listOrderBynumber.add(Integer.valueOf(split2[1]));
				}
				System.out.println(listOrderById);
				//��ѯ��䣬��ȡ��������Ʒ�������Ϣ
				List<LookOrder> queryGoodsLookOrderByListID = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
				System.out.println("-------------------");
				System.out.println(queryGoodsLookOrderByListID);
				System.out.println(queryGoodsLookOrderByListID.size());
				System.out.println(listOrderBynumber);
				for(int a=0;a<queryGoodsLookOrderByListID.size();a++) {
					//���������Ʒ����д��
					queryGoodsLookOrderByListID.get(a).setNumber(listOrderBynumber.get(a));
					queryGoodsLookOrderByListID.get(a).setSumprice(listOrderBynumber.get(a)*queryGoodsLookOrderByListID.get(a).getGoo_selling_price());
					queryGoodsLookOrderByListID.get(a).setPaystate(queryMyOrderByUserID.get(i).getOrd_paystate());
					queryGoodsLookOrderByListID.get(a).setOrderstate(queryMyOrderByUserID.get(i).getOrd_orderstate());
					queryGoodsLookOrderByListID.get(a).setCreattime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(queryMyOrderByUserID.get(i).getOrd_createtime()));
					queryGoodsLookOrderByListID.get(a).setOrd_id(queryMyOrderByUserID.get(i).getOrd_id());
					System.out.println(queryGoodsLookOrderByListID.get(a));
				}
				//�Զ����������map����
				map2.put(String.valueOf(queryMyOrderByUserID.get(i).getOrd_id()), queryGoodsLookOrderByListID);
				
				System.out.println("++++++++++++++++:"+map2);
			}
			
		System.out.println("map2:"+map2);
		Map<String,Object> map = new HashMap<String, Object>();
		if(queryMyOrderByUserID.isEmpty()) {
			map.put("flag", false);
		}else {
			map.put("data", map2);
			map.put("flag", true);
		}
		return map;
	}
	//�û�ȡ������
	//������Ϊpropagation���������������û���򴴽�����---���뼶��isolation��SERIALIZABLE����߼����ܷ�ֹ������ظ������ö���---��ʱtimeout����λ���룩---�ع�rollbackFor(�����ô���ع�)---���ع�noRollbackFor(�����ô��󲻻ع�)
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
//	@Transactional(rollbackFor = {ArithmeticException.class})
	@Override
	public Map<String, Object> deleteToMyOrderByUser(Map<String, Object> map) {
		System.out.println("����service");
		//��ȡ������Ϣ
		List<MyOrder> queryMyOrderByOrdid = goodsManageDao.queryMyOrderByOrdid((Integer)map.get("MyOrderId"));
		//����ȡ������ʷ������
		orderHistory.setHis_goodsinf(queryMyOrderByOrdid.get(0).getOrd_goodsinf());
		orderHistory.setHis_starttime(queryMyOrderByOrdid.get(0).getOrd_createtime());
		orderHistory.setHis_stoptime(new Date());
		orderHistory.setHis_sumprice(queryMyOrderByOrdid.get(0).getOrd_sumprice());
		orderHistory.setHis_userid(queryMyOrderByOrdid.get(0).getOrd_userid());
		orderHistory.setHis_orderstate((String)map.get("state"));
		Map<String,Object> map3 = new HashMap<String, Object>();
		try {
			//������ʷ����
			goodsManageDao.insertGoToOrderHistory(orderHistory);
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("ord_id", map.get("MyOrderId"));
			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
			System.out.println("��������");
			//ɾ���ҵĶ���
//			int i=1/0;
			int deleteToMyOrder = goodsManageDao.deleteToMyOrder(map2);
			map3.put("flag", deleteToMyOrder>0);
		} catch (Exception e) {
			//try-catch�ֶ��ع�
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			map3.put("flag", false);
		}
		
//		if(insertGoToOrderHistory>0) {
//			Map<String,Object> map2 = new HashMap<String, Object>();
//			map2.put("ord_id", map.get("MyOrderId"));
//			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
//			int deleteToMyOrder = goodsManageDao.deleteToMyOrder(map2);
//			map3.put("flag", deleteToMyOrder>0);
//		}else {
//			map3.put("flag", false);
//		}
		System.out.println("�������map3��"+map3);
		return map3;
	}
	//Ա��������ѯ
	@Override
	public Map<String, Object> queryMyOrderAll() {
		List<MyOrder> order = goodsManageDao.queryMyOrderAll();
		
		System.out.println("==================================");
		System.out.println(order);
		LinkedMap map2 = new LinkedMap();
//		Map<String, List<EmpOrder>> map3 = new LinkedMap();
		LinkedList<EmpOrder> list = new LinkedList<EmpOrder>();
		for(int i =0;i<order.size();i++) {
			System.out.println("===========:"+list);
			//��ȡ��Ʒ��Ϣ���ָ�
			String[] split = order.get(i).getOrd_goodsinf().split(",");
			//��ȡ�û�ID
			int ord_userid = order.get(i).getOrd_userid();
			
			System.out.println("split:"+split);
			System.out.println("split:"+split.toString());
			
			//��ƷIDList
			List<Integer> listOrderById = new LinkedList<Integer>();
			//��Ӧ������ƷNumber
			List<Integer> listOrderBynumber = new ArrayList<Integer>();
			//��������ȡ������ƷID����Ʒ��������
			for(int a=0;a<split.length;a++) {
				String[] split2 = split[a].split("-");
				listOrderById.add(Integer.valueOf(split2[0]));
				listOrderBynumber.add(Integer.valueOf(split2[1]));
			}
			System.out.println("22222222222222222222222");
			System.out.println(listOrderById);
//			new LinkedList<>()
			//��ѯ��䣬��ȡ��������Ʒ�������Ϣ
			List<LookOrder> goods = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
			System.out.println("goods:--------:"+goods);
			//��ȡ�û���Ϣ
			User user = userManageDao.queryUserById(ord_userid);
			Map map4 = new LinkedMap();
			//����Ӧ��Ϣ����ʵ����
			for(int a=0;a<goods.size();a++) {
				EmpOrder empOrder2 = new EmpOrder();
				System.out.println("��Ʒ����"+goods.get(a).getGoo_name());
				//���ö���ID
				empOrder2.setOrd_id(order.get(i).getOrd_id());
				empOrder2.setOrd_username(user.getUser_name());
				empOrder2.setOrd_useradd(user.getUser_address());
				empOrder2.setOrd_goodsid(goods.get(a).getGoo_id());
				empOrder2.setOrd_goodsname(goods.get(a).getGoo_name());
				empOrder2.setOrd_goodsnumber(listOrderBynumber.get(a));
				empOrder2.setOrd_sumprice(order.get(i).getOrd_sumprice());
				empOrder2.setOrd_paystate(order.get(i).getOrd_orderstate());
				empOrder2.setOrd_createtime(order.get(i).getOrd_createtime());
//				System.out.println("--------:"+empOrder2);
				list.add(empOrder2);
//				System.out.println("yyyyyyyyy:"+list);
			}
			//�Զ����������map����
//			map2.put(String.valueOf(order.get(i).getOrd_id()), list);
		}
		System.out.println("++++++++++++++++:"+map2);
		System.out.println("++++++++List:"+list);
		
		
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("data", map2);
		map.put("data", list);
		System.out.println(map.size());
		if(map.size()>0) {
			map.put("flag", true);
		}else {
			map.put("flag", false);
		}
		System.out.println("Ա����ѯ��"+map);
		return map;
	}
	//���û�������ѯ����
	@Override
	public Map<String, Object> queryOrderByUserName(String user_name) {
		System.out.println(user_name);
		LinkedList<User> userlist = userManageDao.queryUseJingQueByName(user_name);
		System.out.println("�ж��û���"+userlist);
//		System.out.println("�ж��û���"+user==null);
//		System.out.println("�ж��û���"+user.equals(null));
		Map<String,Object> map = new HashMap<String, Object>();
		if(!userlist.isEmpty()) {
			List<MyOrder> order = goodsManageDao.queryOrderByUserName(userlist.get(0).getUser_id());
			//
			System.out.println("==================================");
			System.out.println(order);
			LinkedMap map2 = new LinkedMap();
//			Map<String, List<EmpOrder>> map3 = new LinkedMap();
			LinkedList<EmpOrder> list = new LinkedList<EmpOrder>();
			for(int i =0;i<order.size();i++) {
				System.out.println("===========:"+list);
				//��ȡ��Ʒ��Ϣ���ָ�
				String[] split = order.get(i).getOrd_goodsinf().split(",");
				//��ȡ�û�ID
				int ord_userid = order.get(i).getOrd_userid();
				
				System.out.println("split:"+split);
				System.out.println("split:"+split.toString());
				
				//��ƷIDList
				List<Integer> listOrderById = new LinkedList<Integer>();
				//��Ӧ������ƷNumber
				List<Integer> listOrderBynumber = new ArrayList<Integer>();
				//��������ȡ������ƷID����Ʒ��������
				for(int a=0;a<split.length;a++) {
					String[] split2 = split[a].split("-");
					listOrderById.add(Integer.valueOf(split2[0]));
					listOrderBynumber.add(Integer.valueOf(split2[1]));
				}
				System.out.println("22222222222222222222222");
				System.out.println(listOrderById);
//				new LinkedList<>()
				//��ѯ��䣬��ȡ��������Ʒ�������Ϣ
				List<LookOrder> goods = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
				System.out.println("goods:--------:"+goods);
//				��ȡ�û���Ϣ
//				User user = userManageDao.queryUserById(ord_userid);
				Map map4 = new LinkedMap();
				//����Ӧ��Ϣ����ʵ����
				for(int a=0;a<goods.size();a++) {
					EmpOrder empOrder2 = new EmpOrder();
					System.out.println("��Ʒ����"+goods.get(a).getGoo_name());
					//���ö���ID
					empOrder2.setOrd_id(order.get(i).getOrd_id());
					empOrder2.setOrd_username(userlist.get(0).getUser_name());
					empOrder2.setOrd_useradd(userlist.get(0).getUser_address());
					empOrder2.setOrd_goodsid(goods.get(a).getGoo_id());
					empOrder2.setOrd_goodsname(goods.get(a).getGoo_name());
					empOrder2.setOrd_goodsnumber(listOrderBynumber.get(a));
					empOrder2.setOrd_sumprice(order.get(i).getOrd_sumprice());
					empOrder2.setOrd_paystate(order.get(i).getOrd_orderstate());
					empOrder2.setOrd_createtime(order.get(i).getOrd_createtime());
//					System.out.println("--------:"+empOrder2);
					list.add(empOrder2);
//					System.out.println("yyyyyyyyy:"+list);
				}
				//�Զ����������map����
//				map2.put(String.valueOf(order.get(i).getOrd_id()), list);
			}
			System.out.println("++++++++++++++++:"+map2);
			System.out.println("++++++++List:"+list);
			
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("data", map2);
			map.put("data", list);
			System.out.println(map.size());
		}

		if(map.size()>0) {
			map.put("flag", true);
		}else {
			map.put("flag", false);
		}
		System.out.println("Ա����ѯ��"+map);
		return map;
	}
	//����
	@Override
	public Map<String, Object> updateOrderToDelivering(MyOrder myOrder) {
		myOrder.setOrd_orderstate("�ȴ��ջ�");
		System.out.println("service");
		System.out.println(myOrder);
		int updataOrder = goodsManageDao.updateOrder(myOrder);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", updataOrder);
		return map;
	}
	//Ա��ȡ������
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
	@Override
	public Map<String, Object> deleteToMyOrderByEmp(Map map) {
		List<MyOrder> queryMyOrderByOrdid = goodsManageDao.queryMyOrderByOrdid((int) map.get("ord_id"));
		
		
		orderHistory.setHis_goodsinf(queryMyOrderByOrdid.get(0).getOrd_goodsinf());
		orderHistory.setHis_starttime(queryMyOrderByOrdid.get(0).getOrd_createtime());
		orderHistory.setHis_stoptime(new Date());
		orderHistory.setHis_sumprice(queryMyOrderByOrdid.get(0).getOrd_sumprice());
		orderHistory.setHis_userid(queryMyOrderByOrdid.get(0).getOrd_userid());
		orderHistory.setHis_orderstate("��ȡ��");
		Map<String,Object> map3 = new HashMap<String, Object>();
		try {
			//������ʷ����
			goodsManageDao.insertGoToOrderHistory(orderHistory);
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("ord_id", map.get("ord_id"));
			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
//			System.out.println("��������");
//			int i=1/0;
			//ɾ���ҵĶ���
			int deleteToMyOrderByEmp = goodsManageDao.deleteToMyOrderByEmp(map);
			map3.put("flag", deleteToMyOrderByEmp>0);
		} catch (Exception e) {
			//try-catch�ֶ��ع�
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			map3.put("flag", false);
		}
		
//		if(insertGoToOrderHistory>0) {
//			Map<String,Object> map2 = new HashMap<String, Object>();
//			map2.put("ord_id", map.get("MyOrderId"));
//			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
//			int deleteToMyOrder = goodsManageDao.deleteToMyOrder(map2);
//			map3.put("flag", deleteToMyOrder>0);
//		}else {
//			map3.put("flag", false);
//		}
		System.out.println("�������map3��"+map3);
		return map3;
	}

}
