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
	public List<Goods> queryGoodsByID(List<Integer> ids) {
		
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
//	加入我的订单
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
	@Override
	public Map insertGoToMyOrder(Map map2) {
		double sumprice = 0;
		String goodsInf = "";
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
		
		MyOrder myOrder = new MyOrder(Integer.valueOf((String) map2.get("userId")).intValue(), substring, sumprice, "订单未支付", "等待支付", new Date());
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
		//将获取插入历史订单表
		orderHistory.setHis_goodsinf(queryMyOrderByOrdid.get(0).getOrd_goodsinf());
		orderHistory.setHis_starttime(queryMyOrderByOrdid.get(0).getOrd_createtime());
		orderHistory.setHis_stoptime(new Date());
		orderHistory.setHis_sumprice(queryMyOrderByOrdid.get(0).getOrd_sumprice());
		orderHistory.setHis_userid(queryMyOrderByOrdid.get(0).getOrd_userid());
		orderHistory.setHis_orderstate((String)map.get("state"));
		Map<String,Object> map3 = new HashMap<String, Object>();
		try {
			//插入历史订单
			goodsManageDao.insertGoToOrderHistory(orderHistory);
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("ord_id", map.get("MyOrderId"));
			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
			System.out.println("哈哈哈哈");
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
	public Map<String, Object> updateOrderToDelivering(MyOrder myOrder) {
		myOrder.setOrd_orderstate("等待收货");
		System.out.println("service");
		System.out.println(myOrder);
		int updataOrder = goodsManageDao.updateOrder(myOrder);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", updataOrder);
		return map;
	}
	//员工取消订单
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,timeout = 60000)
	@Override
	public Map<String, Object> deleteToMyOrderByEmp(Map map) {
		List<MyOrder> queryMyOrderByOrdid = goodsManageDao.queryMyOrderByOrdid((int) map.get("ord_id"));
		
		
		orderHistory.setHis_goodsinf(queryMyOrderByOrdid.get(0).getOrd_goodsinf());
		orderHistory.setHis_starttime(queryMyOrderByOrdid.get(0).getOrd_createtime());
		orderHistory.setHis_stoptime(new Date());
		orderHistory.setHis_sumprice(queryMyOrderByOrdid.get(0).getOrd_sumprice());
		orderHistory.setHis_userid(queryMyOrderByOrdid.get(0).getOrd_userid());
		orderHistory.setHis_orderstate("已取消");
		Map<String,Object> map3 = new HashMap<String, Object>();
		try {
			//插入历史订单
			goodsManageDao.insertGoToOrderHistory(orderHistory);
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("ord_id", map.get("ord_id"));
			map2.put("ord_userid", Integer.valueOf((String)map.get("UserId")));
//			System.out.println("哈哈哈哈");
//			int i=1/0;
			//删除我的订单
			int deleteToMyOrderByEmp = goodsManageDao.deleteToMyOrderByEmp(map);
			map3.put("flag", deleteToMyOrderByEmp>0);
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

}
