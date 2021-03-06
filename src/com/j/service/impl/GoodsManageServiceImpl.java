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
	
	@Autowired
	private MyOrder myOrder;
	

	
//功能
	//用户功能：
		//浏览商品
	@Override
	public List<Goods> queryGoodsByUserAll() {
		return goodsManageDao.queryGoodsByUserAll();
	}
	@Override
	public List<Goods> queryGoodsByPage(int page, int number) {
		List<Goods> queryGoodsByPage = goodsManageDao.queryGoodsByPage(page, number);
		return queryGoodsByPage;
	}
		//浏览商品详情
	@Override
	public Goods queryGoodsByID(int goo_id) {
		return goodsManageDao.queryGoodsByID(goo_id);
	}
		//直接商品详情界面点击购买\购物车结算
	@Override
	public LinkedList<Goods> queryGoodsByID(LinkedList<Integer> ids) {
		
		return goodsManageDao.queryGoodsByListID(ids);
	}
//	浏览购物车
	@Override
	public List<ToCart> queryMyCartByUserAll(int userid) {
		List<ToCart> queryMyCartByUserAll = goodsManageDao.queryMyCartByUserAll(userid);
		return queryMyCartByUserAll;
	}
//	添加商品到购物车
	@Override
	public boolean insertGoToMyCart(MyCart mycart) {
		System.out.println("添加商品到购物车");
		System.out.println(mycart);
		List<MyCart> queryMyCartOneGoodsByUserID = goodsManageDao.queryMyCartOneGoodsByUserID(mycart.getCar_userid(), mycart.getCar_gooid());
//		System.out.println(queryMyCartOneGoodsByUserID);
//		System.out.println(queryMyCartOneGoodsByUserID.isEmpty());
//		为空直接插入
		if(queryMyCartOneGoodsByUserID.isEmpty()) {
			System.out.println("无该产品");
			int insertGoToMyCart = goodsManageDao.insertGoToMyCart(mycart);
			return insertGoToMyCart>0;
		}else {
//			不为空加入商品数量{car_goonum} where car_userid=#{car_userid} and car_gooid=#{car_gooid}
			System.out.println("有该产品");
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
	
	/*
	 * {goods=[{goo_buying_price=7000, goo_id=2,
	 * goo_image=//img13.360buyimg.com/n1/s450x450_jfs/t1/133141/39/19600/94797/
	 * 5fd33063Eaa6bb4fc/4da9462bf0252ecb.jpg, goo_name=Apple iPhone 11 (A2223)
	 * 128GB 紫色, goo_selling_price=7000, goo_stock=100, goo_supid=2, goo_type=电子产品,
	 * number=1, sump=7000}], userId=6}
	 */
	
//	加入我的订单
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
	@Override
	public Map insertGoToMyOrder(Map map2) {
		System.out.println("666666666666666:"+map2);
		System.out.println("666666666666666:");
		double sumprice = 0;
		String goodsInf = "";
		String deliadd = (String)map2.get("deliadd");
		System.out.println(map2);
		List list = (List) map2.get("goods");
		//创建goodsid接收商品id
	    List<Integer> goodsid = new ArrayList<Integer>();
		for(int i =0 ;i<list.size();i++) {
			System.out.println(list);
		    JSONArray jsonArray = new JSONArray(list);  
		    System.out.println(jsonArray.getJSONObject(i).get("car_gooid")==null);
		    System.out.println(jsonArray.getJSONObject(i).get("goo_id"));
		    int id = 0;
		    if(jsonArray.getJSONObject(i).get("car_gooid")!=null) {
		    	//从购物车获取订单商品ID
		    	id =(int) jsonArray.getJSONObject(i).get("car_gooid");//得到第一个的id 
		    }else {
		    	//从商品详情获取订单商品ID
		    	id =(int) jsonArray.getJSONObject(i).get("goo_id");//得到第一个的id 
		    }
		    int number =(int) jsonArray.getJSONObject(i).get("number");//得到第一个的number 
		    System.out.println("-----------------------------------------------------------------");
		    String substring = jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16);
		    System.out.println(jsonArray.getJSONObject(i).get("sump").getClass().toString());
		    System.out.println(jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16));
		    double doubleValue = 0;
		    if(substring.equals("Integer")) {
			    doubleValue = ((Integer) jsonArray.getJSONObject(i).get("sump")).doubleValue();//得到第一个的name  
		    }else{
		    	doubleValue = ((Double) jsonArray.getJSONObject(i).get("sump")).doubleValue();//得到第一个的name  
		    }
		    System.out.println(sumprice);
		    System.out.println(doubleValue);
		    sumprice+=doubleValue;
		    System.out.println(sumprice);
		    goodsid.add(id);
		    goodsInf+=id+"-"+number+",";
		}
		System.out.println("商品ID："+goodsid);
		String substring = goodsInf.substring(0,goodsInf.length()-1);
		System.out.println("商品信息："+substring);
		System.out.println("商品总价："+sumprice);
//		System.out.println("userID1："+map2.get("userId"));
//		System.out.println("userID2："+Integer.valueOf((String) map2.get("userId")));
//		System.out.println("userID3："+Integer.valueOf((String) map2.get("userId")).intValue());
		
		MyOrder myOrder = new MyOrder(Integer.valueOf((String) map2.get("userId")).intValue(),deliadd, substring, sumprice, "订单未支付", "等待支付", new Date());
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
//	修改支付状态
	@Override
	public boolean updateMyOrderPayState(String ord_paystate, String ord_orderstate,String ord_id) {
		int updateMyOrderPayState = goodsManageDao.updateMyOrderPayState(ord_paystate, ord_orderstate,ord_id);
		return updateMyOrderPayState>0;
	}
//	查询我的订单
	@Override
	public Map<String, Object> queryMyOrderByUserID(int userId, int page, int limit) {
//		获取订单中的商品
		List<MyOrder> queryMyOrderByUserID = goodsManageDao.queryMyOrderByUserID(userId, page, limit);
		System.out.println(queryMyOrderByUserID);
		System.out.println(queryMyOrderByUserID.size());
		
		List<Integer> listid = new ArrayList<Integer>();
		List<Integer> listnumber = new ArrayList<Integer>();
		List<LookOrder> listorder = new ArrayList<LookOrder>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		
			for(int i =0;i<queryMyOrderByUserID.size();i++) {
				String[] split = queryMyOrderByUserID.get(i).getOrd_goodsinf().split(",");
				System.out.println("订单编号:"+queryMyOrderByUserID.get(i).getOrd_id());
				System.out.println("split:"+split.toString());
				System.out.println("split.length:"+split.length);
				//订单IDList
				List<Integer> listOrderById = new ArrayList<Integer>();
				//相应订单商品Number
				List<Integer> listOrderBynumber = new ArrayList<Integer>();
				//将订单获取到的商品ID和商品数量分离
				for(int a=0;a<split.length;a++) {
					String[] split2 = split[a].split("-");
					listOrderById.add(Integer.valueOf(split2[0]));
					listOrderBynumber.add(Integer.valueOf(split2[1]));
				}
				System.out.println(listOrderById);
				//查询语句，获取订单中商品的相关信息
				List<LookOrder> queryGoodsLookOrderByListID = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
				System.out.println("-------------------");
				System.out.println(queryGoodsLookOrderByListID);
				System.out.println(queryGoodsLookOrderByListID.size());
				System.out.println(listOrderBynumber);
				for(int a=0;a<queryGoodsLookOrderByListID.size();a++) {
					//将购买的商品数量写入
					queryGoodsLookOrderByListID.get(a).setNumber(listOrderBynumber.get(a));
					queryGoodsLookOrderByListID.get(a).setSumprice(listOrderBynumber.get(a)*queryGoodsLookOrderByListID.get(a).getGoo_selling_price());
					queryGoodsLookOrderByListID.get(a).setPaystate(queryMyOrderByUserID.get(i).getOrd_paystate());
					queryGoodsLookOrderByListID.get(a).setOrderstate(queryMyOrderByUserID.get(i).getOrd_orderstate());
					queryGoodsLookOrderByListID.get(a).setCreattime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(queryMyOrderByUserID.get(i).getOrd_createtime()));
					queryGoodsLookOrderByListID.get(a).setOrd_id(queryMyOrderByUserID.get(i).getOrd_id());
					System.out.println(queryGoodsLookOrderByListID.get(a));
				}
				//以订单号添加入map集合
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
	//用户取消订单
	//传播行为propagation（有事务加入事务，没有则创建事务）---隔离级别isolation（SERIALIZABLE：最高级别，能防止脏读，重复读，幻读）---超时timeout（单位毫秒）---回滚rollbackFor(遇到该错误回滚)---不回滚noRollbackFor(遇到该错误不回滚)
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
//	@Transactional(rollbackFor = {ArithmeticException.class})
	@Override
	public Map<String, Object> deleteToMyOrderByUser(Map<String, Object> map) {
		System.out.println("进入service");
		//获取订单信息
		List<MyOrder> queryMyOrderByOrdid = goodsManageDao.queryMyOrderByOrdid((Integer)map.get("MyOrderId"));
		System.out.println(queryMyOrderByOrdid);
		//将获取插入历史订单表
		orderHistory.setHis_goodsinf(queryMyOrderByOrdid.get(0).getOrd_goodsinf());
		orderHistory.setHis_starttime(queryMyOrderByOrdid.get(0).getOrd_createtime());
		orderHistory.setHis_stoptime(new Date());
		orderHistory.setHis_sumprice(queryMyOrderByOrdid.get(0).getOrd_sumprice());
		orderHistory.setHis_userid(queryMyOrderByOrdid.get(0).getOrd_userid());
		orderHistory.setHis_orderstate((String)map.get("state"));
		System.out.println(orderHistory);
		Map<String,Object> map3 = new HashMap<String, Object>();
		System.out.println("0000000000000");
//		int insertGoToOrderHistory = goodsManageDao.insertGoToOrderHistory(orderHistory);
		try {
			//插入历史订单
			System.out.println("111111111111111");
			int insertGoToOrderHistory = goodsManageDao.insertGoToOrderHistory(orderHistory);
			System.out.println(insertGoToOrderHistory);
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("ord_id", map.get("MyOrderId"));
			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
			System.out.println("哈哈哈哈");
			System.out.println(map2);
			//删除我的订单
//			int i=1/0;
			int deleteToMyOrder = goodsManageDao.deleteToMyOrder(map2);
			map3.put("flag", deleteToMyOrder>0);
		} catch (Exception e) {
			//try-catch手动回滚
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
		System.out.println("事务管理map3："+map3);
		return map3;
	}
	//员工订单查询
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
			//获取商品信息并分割
			String[] split = order.get(i).getOrd_goodsinf().split(",");
			//获取用户ID
			int ord_userid = order.get(i).getOrd_userid();
			
			System.out.println("split:"+split);
			System.out.println("split:"+split.toString());
			
			//商品IDList
			List<Integer> listOrderById = new LinkedList<Integer>();
			//相应订单商品Number
			List<Integer> listOrderBynumber = new ArrayList<Integer>();
			//将订单获取到的商品ID和商品数量分离
			for(int a=0;a<split.length;a++) {
				String[] split2 = split[a].split("-");
				listOrderById.add(Integer.valueOf(split2[0]));
				listOrderBynumber.add(Integer.valueOf(split2[1]));
			}
			System.out.println("22222222222222222222222");
			System.out.println(listOrderById);
//			new LinkedList<>()
			//查询语句，获取订单中商品的相关信息
			List<LookOrder> goods = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
			System.out.println("goods:--------:"+goods);
			//获取用户信息
			User user = userManageDao.queryUserById(ord_userid);
			Map map4 = new LinkedMap();
			//将对应信息赋给实体类
			for(int a=0;a<goods.size();a++) {
				EmpOrder empOrder2 = new EmpOrder();
				System.out.println("商品名："+goods.get(a).getGoo_name());
				//设置订单ID
				empOrder2.setOrd_id(order.get(i).getOrd_id());
				empOrder2.setOrd_username(user.getUser_name());
				empOrder2.setOrd_useradd(order.get(i).getOrd_deliadd());
				empOrder2.setOrd_goodsid(goods.get(a).getGoo_id());
				empOrder2.setOrd_goodsname(goods.get(a).getGoo_name());
				empOrder2.setOrd_goodsnumber(listOrderBynumber.get(a));
				empOrder2.setOrd_sumprice(order.get(i).getOrd_sumprice());
				empOrder2.setOrd_paystate(order.get(i).getOrd_paystate());
				empOrder2.setOrd_orderstate(order.get(i).getOrd_orderstate());
				empOrder2.setOrd_createtime(order.get(i).getOrd_createtime());
//				System.out.println("--------:"+empOrder2);
				list.add(empOrder2);
//				System.out.println("yyyyyyyyy:"+list);
			}
			//以订单号添加入map集合
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
		System.out.println("员工查询："+map);
		return map;
	}
	//按用户姓名查询订单
	@Override
	public Map<String, Object> queryOrderByUserName(String user_name) {
		System.out.println(user_name);
		LinkedList<User> userlist = userManageDao.queryUseJingQueByName(user_name);
		System.out.println("判断用户："+userlist);
//		System.out.println("判断用户："+user==null);
//		System.out.println("判断用户："+user.equals(null));
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
				//获取商品信息并分割
				String[] split = order.get(i).getOrd_goodsinf().split(",");
				//获取用户ID
				int ord_userid = order.get(i).getOrd_userid();
				
				System.out.println("split:"+split);
				System.out.println("split:"+split.toString());
				
				//商品IDList
				List<Integer> listOrderById = new LinkedList<Integer>();
				//相应订单商品Number
				List<Integer> listOrderBynumber = new ArrayList<Integer>();
				//将订单获取到的商品ID和商品数量分离
				for(int a=0;a<split.length;a++) {
					String[] split2 = split[a].split("-");
					listOrderById.add(Integer.valueOf(split2[0]));
					listOrderBynumber.add(Integer.valueOf(split2[1]));
				}
				System.out.println("22222222222222222222222");
				System.out.println(listOrderById);
//				new LinkedList<>()
				//查询语句，获取订单中商品的相关信息
				List<LookOrder> goods = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
				System.out.println("goods:--------:"+goods);
//				获取用户信息
//				User user = userManageDao.queryUserById(ord_userid);
				Map map4 = new LinkedMap();
				//将对应信息赋给实体类
				for(int a=0;a<goods.size();a++) {
					EmpOrder empOrder2 = new EmpOrder();
					System.out.println("商品名："+goods.get(a).getGoo_name());
					//设置订单ID
					empOrder2.setOrd_id(order.get(i).getOrd_id());
					empOrder2.setOrd_username(userlist.get(0).getUser_name());
					empOrder2.setOrd_useradd(order.get(i).getOrd_deliadd());
					empOrder2.setOrd_goodsid(goods.get(a).getGoo_id());
					empOrder2.setOrd_goodsname(goods.get(a).getGoo_name());
					empOrder2.setOrd_goodsnumber(listOrderBynumber.get(a));
					empOrder2.setOrd_sumprice(order.get(i).getOrd_sumprice());
					empOrder2.setOrd_paystate(order.get(i).getOrd_paystate());
					empOrder2.setOrd_orderstate(order.get(i).getOrd_orderstate());
					empOrder2.setOrd_createtime(order.get(i).getOrd_createtime());
//					System.out.println("--------:"+empOrder2);
					list.add(empOrder2);
//					System.out.println("yyyyyyyyy:"+list);
				}
				//以订单号添加入map集合
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
		System.out.println("员工查询："+map);
		return map;
	}
	//发货
	@Override
	public Map<String, Object> updateOrderToDelivering(Map map2) {
		myOrder.setOrd_id((int) map2.get("orderId"));
		myOrder.setOrd_orderstate("等待收货");
		System.out.println("service");
		System.out.println(myOrder);
		int updataOrder = goodsManageDao.updateOrder(myOrder);
		System.out.println("发货按钮："+updataOrder);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", updataOrder>0);
		return map;
	}
	//员工取消订单
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
	@Override
	public Map<String, Object> deleteToMyOrderByEmp(Map map) {
		System.out.println("=====================:"+map);
		List<MyOrder> queryMyOrderByOrdid = goodsManageDao.queryMyOrderByOrdid((int) map.get("ord_id"));
		
		
		orderHistory.setHis_goodsinf(queryMyOrderByOrdid.get(0).getOrd_goodsinf());
		orderHistory.setHis_starttime(queryMyOrderByOrdid.get(0).getOrd_createtime());
		orderHistory.setHis_stoptime(new Date());
		orderHistory.setHis_sumprice(queryMyOrderByOrdid.get(0).getOrd_sumprice());
		orderHistory.setHis_userid(queryMyOrderByOrdid.get(0).getOrd_userid());
		orderHistory.setHis_orderstate("已取消");
		Map<String,Object> map3 = new HashMap<String, Object>();
//		try {
			//插入历史订单
			goodsManageDao.insertGoToOrderHistory(orderHistory);
//			Map<String,Object> map2 = new HashMap<String, Object>();
//			map2.put("ord_id", map.get("ord_id"));
//			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
//			System.out.println("哈哈哈哈");
//			int i=1/0;
			//删除我的订单
			int deleteToMyOrderByEmp = goodsManageDao.deleteToMyOrderByEmp(map);
			map3.put("flag", deleteToMyOrderByEmp>0);
//		} catch (Exception e) {
			//try-catch手动回滚
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			map3.put("flag", false);
//		}
		
//		if(insertGoToOrderHistory>0) {
//			Map<String,Object> map2 = new HashMap<String, Object>();
//			map2.put("ord_id", map.get("MyOrderId"));
//			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
//			int deleteToMyOrder = goodsManageDao.deleteToMyOrder(map2);
//			map3.put("flag", deleteToMyOrder>0);
//		}else {
//			map3.put("flag", false);
//		}
		System.out.println("事务管理map3："+map3);
		return map3;
	}
	//用户从购物车中删除已加入的商品
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout=60000)
	public Map<String, Object> deleteToMyCartByList(Map map2) {
//		map2.get("ord_id");
//		map2.get("user_id");
		System.out.println(map2);
		List<Integer> list = new ArrayList<Integer>();
		list.add((int)map2.get("car_gooid"));
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("car_gooid", list);
		map.put("car_userid", map2.get("user_id"));
		System.out.println(map);
		Map<String,Object> res = new HashMap<String, Object>();
		try {
			int deleteToMyCartByList = goodsManageDao.deleteToMyCartByList(map);
			res.put("flag", deleteToMyCartByList>0);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.put("flag", false);
		}
		System.out.println(res);
		return res;
	}
	//从购物车加入收藏夹
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE,timeout=60000)
	public Map<String, Object> insertToFavorite(Map map2) {
		System.out.println(map2);//{fav_gooid=2, fav_userid=1}
		//查询是否含有该ID商品
		Goods queryGoodsByID = goodsManageDao.queryGoodsByID((int) map2.get("fav_gooid"));
		System.out.println(queryGoodsByID);
		System.out.println(queryGoodsByID!=null);
		Map<String,Object> res = new HashMap<String, Object>();
		if(queryGoodsByID!=null) {
			List<Integer> list = new ArrayList<Integer>();
			list.add((int)map2.get("fav_gooid"));
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("car_gooid", list);
			map.put("car_userid", map2.get("fav_userid"));
			System.out.println(map);
			try {
				//查询收藏夹是否已含有相同商品
				int queryFavoriteByGooid = goodsManageDao.queryFavoriteByGooid(map2);
				System.out.println("查询到的个数："+queryFavoriteByGooid);
				if(queryFavoriteByGooid>0) {
					//删除购物车
					int deleteToMyCartByList = goodsManageDao.deleteToMyCartByList(map);
					res.put("flag", true);
					res.put("data", "已存在");
				}else {				
					//插入收藏夹
					int insertToFavorite = goodsManageDao.insertToFavorite(map2);
					//删除购物车
					int deleteToMyCartByList = goodsManageDao.deleteToMyCartByList(map);
					res.put("flag", true);
					res.put("data", "未存在");
				}
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				res.put("flag", false);
			}
		}
		return res;
	}
	//浏览收藏夹
	@Override
	public Map<String, Object> queryFavoriteByUserAll(Map<String, Integer> map) {
		System.out.println(map);
		LinkedList<Integer> linkedList = goodsManageDao.queryFavoriteByUserAll(map);
		LinkedList<Goods> queryGoodsByListID = goodsManageDao.queryGoodsByListID(linkedList);
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("data", queryGoodsByListID);
		return map2;
	}
	//用户删除收藏夹商品
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE,timeout=60000)
	public Map<String, Object> deleteMyFavorite(Map<String, Object> map2) {
		System.out.println(map2);//{fav_gooid=2, fav_userid=1}
		Map<String,Object> res = new HashMap<String, Object>();
		List<Integer> list = new ArrayList<Integer>();
		list.add((int)map2.get("fav_gooid"));
		map2.put("fav_gooid", list);
		System.out.println(map2);
		try {
			int deleteMyFavorite = goodsManageDao.deleteMyFavorite(map2);
			res.put("flag", true);
			System.out.println(deleteMyFavorite);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.put("flag", false);
		}
		return res;
	}
	//从商品详情加入收藏夹
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE,timeout=60000)
	public Map<String, Object> insertToFavoriteIndetail(Map map2) {
		System.out.println(map2);//{fav_gooid=2, fav_userid=1}{fav_gooid=3, fav_userid=1}
		//查询是否含有该ID商品
		Goods queryGoodsByID = goodsManageDao.queryGoodsByID((int) map2.get("fav_gooid"));
		System.out.println(queryGoodsByID);
//		System.out.println(queryGoodsByID!=null);
		Map<String,Object> res = new HashMap<String, Object>();
		if(queryGoodsByID!=null) {
			List<Integer> list = new ArrayList<Integer>();
			list.add((int)map2.get("fav_gooid"));
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("car_gooid", list);
			map.put("car_userid", map2.get("fav_userid"));
//			System.out.println(map);
			try {
//				//查询收藏夹是否已含有相同商品
				int queryFavoriteByGooid = goodsManageDao.queryFavoriteByGooid(map2);
				System.out.println("查询到的个数："+queryFavoriteByGooid);
				if(queryFavoriteByGooid==0) {			
//					//插入收藏夹
					int insertToFavorite = goodsManageDao.insertToFavorite(map2);
					System.out.println(insertToFavorite);
					res.put("flag", true);
					res.put("data", "未存在");
				}else {
					res.put("flag", true);
					res.put("data", "已存在");
				}
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				res.put("flag", false);
			}
		}
		return res;
	}
	//历史订单查询
	@Override
	public Map<String, Object> queryOrderHistoryByUserID(Map<String, Object> request) {
		System.out.println("历史订单查询"+request);
		List<OrderHistory> queryOrderHistoryByUserID = goodsManageDao.queryOrderHistoryByUserID(Integer.valueOf((String)request.get("userId")) );
		/*
		 * [OrderHistory 
		 * 
		 * [his_id=34, his_userid=6, his_deliadd=null,
		 * his_goodsinf=4-2,1-3, his_sumprice=2004.0, his_orderstate=已收货,
		 * his_starttime=Tue Jan 12 00:54:05 CST 2021, his_stoptime=Sat Jan 30 17:33:40
		 * CST 2021], OrderHistory 
		 * 
		 * [his_id=35, his_userid=6, his_deliadd=null,
		 * his_goodsinf=4-2,1-1,7-1,8-1,6-1, his_sumprice=2103.8, his_orderstate=已收货,
		 * his_starttime=Tue Jan 12 00:54:45 CST 2021, his_stoptime=Thu Feb 04 09:16:01
		 * CST 2021], OrderHistory 
		 * 
		 * [his_id=36, his_userid=6, his_deliadd=null,
		 * his_goodsinf=8-2,6-2,7-3, his_sumprice=237.5, his_orderstate=已收货,
		 * his_starttime=Tue Jan 12 00:53:50 CST 2021, his_stoptime=Thu Feb 04 09:19:56
		 * CST 2021], OrderHistory 
		 * 
		 * [his_id=37, his_userid=6, his_deliadd=null,
		 * his_goodsinf=1-1,6-2, his_sumprice=10.0, his_orderstate=已收货,
		 * his_starttime=Tue Jan 12 01:00:17 CST 2021, his_stoptime=Thu Feb 04 09:20:00
		 * CST 2021], OrderHistory 
		 * 
		 * [his_id=38, his_userid=6, his_deliadd=null,
		 * his_goodsinf=4-1, his_sumprice=999.0, his_orderstate=已收货, his_starttime=Sat
		 * Jan 30 16:54:55 CST 2021, his_stoptime=Thu Feb 04 09:20:03 CST 2021]]
		 */
//		System.out.println(queryOrderHistoryByUserID);
//		System.out.println(queryOrderHistoryByUserID.size());
////		System.out.println(queryMyOrderByUserID.size());
//		
//		List<Integer> listid = new ArrayList<Integer>();
//		List<Integer> listnumber = new ArrayList<Integer>();
//		List<LookOrder> listorder = new ArrayList<LookOrder>();
//		Map<String,Object> map2 = new HashMap<String, Object>();
//		
//			for(int i =0;i<queryOrderHistoryByUserID.size();i++) {
//				String[] split = queryOrderHistoryByUserID.get(i).getHis_goodsinf().split(",");
//				System.out.println("订单编号:"+queryOrderHistoryByUserID.get(i).getHis_id());
//				System.out.println("split:"+split.toString());
//				System.out.println("split.length:"+split.length);
//				//订单IDList
//				List<Integer> listOrderById = new ArrayList<Integer>();
//				//相应订单商品Number
//				List<Integer> listOrderBynumber = new ArrayList<Integer>();
//				//将订单获取到的商品ID和商品数量分离
//				for(int a=0;a<split.length;a++) {
//					String[] split2 = split[a].split("-");
//					listOrderById.add(Integer.valueOf(split2[0]));
//					listOrderBynumber.add(Integer.valueOf(split2[1]));
//				}
//				System.out.println(listOrderById);
//				//查询语句，获取订单中商品的相关信息
//				List<LookOrder> queryGoodsLookOrderByListID = goodsManageDao.queryGoodsLookOrderByListID(listOrderById);
//				System.out.println("-------------------");
//				System.out.println(queryGoodsLookOrderByListID);
//				System.out.println(queryGoodsLookOrderByListID.size());
//				System.out.println(listOrderBynumber);
//				for(int a=0;a<queryGoodsLookOrderByListID.size();a++) {
//					//将购买的商品数量写入
//					queryGoodsLookOrderByListID.get(a).setNumber(listOrderBynumber.get(a));
//					queryGoodsLookOrderByListID.get(a).setSumprice(listOrderBynumber.get(a)*queryGoodsLookOrderByListID.get(a).getGoo_selling_price());
//					queryGoodsLookOrderByListID.get(a).setPaystate(queryOrderHistoryByUserID.get(i).getHis_orderstate());
//					queryGoodsLookOrderByListID.get(a).setOrderstate(queryOrderHistoryByUserID.get(i).getHis_orderstate());
//					queryGoodsLookOrderByListID.get(a).setCreattime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(queryOrderHistoryByUserID.get(i).getOrd_createtime()));
//					queryGoodsLookOrderByListID.get(a).setOrd_id(queryMyOrderByUserID.get(i).getOrd_id());
//					System.out.println(queryGoodsLookOrderByListID.get(a));
//				}
//				//以订单号添加入map集合
//				map2.put(String.valueOf(queryMyOrderByUserID.get(i).getOrd_id()), queryGoodsLookOrderByListID);
//				
//				System.out.println("++++++++++++++++:"+map2);
//			}
//			
//		System.out.println("map2:"+map2);
//		System.out.println(queryOrderHistoryByUserID);
		return null;
	}
	//员工修改订单发货地址信息
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE,timeout=60000)
	public Map<String, Object> updataOrderToAddress(Map map2) {
		System.out.println(map2);
		myOrder.setOrd_deliadd((String) map2.get("newAddress"));
		myOrder.setOrd_id((int) map2.get("order_id"));
		Map<String,Object> map = new HashMap<String , Object>();
		try {
			int updateOrder = goodsManageDao.updateOrder(myOrder);
			System.out.println("修改："+updateOrder);
			if(updateOrder>0) {
				map.put("flag", true);
			}else {
				map.put("flag", false);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			map.put("flag", false);
		}
		return map;
	}

}
