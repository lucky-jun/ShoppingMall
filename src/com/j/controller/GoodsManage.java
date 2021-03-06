package com.j.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.ToCart;
import com.j.service.GoodsManageService;
import com.j.service.impl.GoodsManageServiceImpl;

@Controller
//报错：Access to XMLHttpRequest at 'http://localhost:8080/ShoppingMall/login.action
//跨域问题解决办法：加@CrossOrigin 
@CrossOrigin 
public class GoodsManage {

	@Autowired
	private GoodsManageService goodsManageService;
	private MyCart mycart;
	
//	首页获取商品展示
	@ResponseBody
	@RequestMapping(value="/queryGoodsPage.do",method=RequestMethod.POST)
	public String Home(@RequestBody Map<String ,Integer> map2) {
		System.out.println(map2);
//		String page = (String) map2.get("page");
//		String limit = (String) map2.get("limit");
//		public String Home(String page,String limit) {
		System.out.println(map2.get("page"));
		System.out.println(map2.get("limit"));
//		System.out.println(Integer.valueOf(page));
//		System.out.println(Integer.valueOf(limit));
//		List<Goods> queryGoodsByPage = goodsManageService.queryGoodsByPage((String) map.get("page"), (String) map.get("limit"));
//		List<Goods> queryGoodsByPage = goodsManageService.queryGoodsByPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<Goods> queryGoodsByPage = goodsManageService.queryGoodsByPage(Integer.valueOf(map2.get("page")), Integer.valueOf(map2.get("limit")));
		System.out.println(queryGoodsByPage);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", queryGoodsByPage);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	获取购物车商品展示
	@ResponseBody
	@RequestMapping(value="/queryToCart.do",method=RequestMethod.POST)
	public String queryToCart(@RequestBody Map<String,Integer> userId) {
//	public String Cart(int userId) {
		System.out.println(userId);
		List<ToCart> queryMyCartByUserAll = goodsManageService.queryMyCartByUserAll(userId.get("userID"));
		
		System.out.println(queryMyCartByUserAll);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", queryMyCartByUserAll);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	查看商品详情
	@ResponseBody
	@RequestMapping(value="/queryGoodsById.do",method=RequestMethod.POST)
	public String GoodsDetail(@RequestBody Map<String, Integer> map2) {
//	public String GoodsDetail(Integer goodsId) {
		System.out.println(map2.get("goodsId"));
		Goods queryGoodsByID = goodsManageService.queryGoodsByID(map2.get("goodsId"));
		
		System.out.println(queryGoodsByID);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", queryGoodsByID);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	添加商品到购物车
	@ResponseBody
	@RequestMapping(value="/insertToCart.do",method=RequestMethod.POST)
	public String insertToMyCart(@RequestBody Map<String,Integer> map2) {
//	public String insertToMyCart(int userId,int goodsId,int number) {
//		System.out.println(userId);
//		System.out.println(goodsId);
//		System.out.println(number);
		System.out.println(map2.get("goodsId"));
		MyCart myCart2 = new MyCart();
		myCart2.setCar_gooid(map2.get("goodsId"));
		myCart2.setCar_userid(map2.get("userId"));
		myCart2.setCar_goonum(map2.get("goodsNumber"));
		System.out.println(myCart2);
		boolean insertGoToMyCart = goodsManageService.insertGoToMyCart(myCart2);
		
		System.out.println(insertGoToMyCart);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", insertGoToMyCart);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
	
	
//	用户从购物车中删除已加入的商品
	@ResponseBody
	@RequestMapping(value="/deleteToMyCart.do",method=RequestMethod.POST)
	public String deleteToMyCartByList(@RequestBody Map<String,Integer> map2) {
		Map<String, Object> map = goodsManageService.deleteToMyCartByList(map2);
		return JSON.toJSONString(map);
	}
	
	
	
//	用户从购物车中加入收藏夹
	@ResponseBody
	@RequestMapping(value="/insertToFavorite.do",method=RequestMethod.POST)
	public String insertToFavorite(@RequestBody Map<String,Integer> map2) {
		Map<String, Object> map = goodsManageService.insertToFavorite(map2);
		return JSON.toJSONString(map);
	}
//	用户查看收藏夹
	@ResponseBody
	@RequestMapping(value="/queryFavoriteByUserAll.do",method=RequestMethod.POST)
	public String queryFavoriteByUserAll(@RequestBody Map<String,Integer> map2) {
		Map<String, Object> map = goodsManageService.queryFavoriteByUserAll(map2);
		return JSON.toJSONString(map);
	}
//	用户删除收藏夹商品
	@ResponseBody
	@RequestMapping(value="/deleteMyFavorite.do",method=RequestMethod.POST)
	public String deleteMyFavorite(@RequestBody Map<String, Object> map2) {
		Map<String, Object> map = goodsManageService.deleteMyFavorite(map2);
		return JSON.toJSONString(map);
	}
	
	
	
//	加入我的订单
	@ResponseBody
	@RequestMapping(value="/insertToMyOrder.do",method=RequestMethod.POST)
	public String insertToMyOrder(@RequestBody Map<String,Object> map2) {
//	public String insertToMyOrder(int ord_userid,String ord_goodsinf,double ord_sumprice,String ord_paystate,String ord_orderstate) {

//		System.out.println(map2);
//		//将Array中的对象值取出
//		double sumprice = 0;
//		String goodsInf = "";
//		List list = (List) map2.get("goods");
//		for(int i =0 ;i<list.size();i++) {
//		    JSONArray jsonArray = new JSONArray(list);  
//		    int id =(int) jsonArray.getJSONObject(i).get("goo_id");//得到第一个的id 
//		    int number =(int) jsonArray.getJSONObject(i).get("number");//得到第一个的number 
//		    String substring = jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16);
//		    double doubleValue = 0;
//		    if(substring.equals("Integer")) {
//			    doubleValue = ((Integer) jsonArray.getJSONObject(0).get("sump")).doubleValue();//得到第一个的name  
//		    }else{
//		    	doubleValue = ((Double) jsonArray.getJSONObject(0).get("sump")).doubleValue();//得到第一个的name  
//		    }
//		    sumprice+=doubleValue;
//		    goodsInf+=id+"-"+number+",";
//		}
//		String substring = goodsInf.substring(0,goodsInf.length()-1);
//		System.out.println("商品信息："+substring);
//		System.out.println("商品总价："+sumprice);
//		map2.get("userId").getClass()
//		Integer.valueOf((String) map2.get("userId")).getClass();
//		System.out.println(Integer.valueOf((String) map2.get("userId")).intValue());
//		MyOrder myOrder = new MyOrder(ord_userid, ord_userid, ord_goodsinf, ord_sumprice, ord_paystate, ord_orderstate, new Date());
//		System.out.println(myOrder);
		Map insertGoToMyOrder = goodsManageService.insertGoToMyOrder(map2);
//		System.out.println(insertGoToMyOrder);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("data", insertGoToMyOrder);
		System.out.println(insertGoToMyOrder);
		return JSON.toJSONString(insertGoToMyOrder);
	}
//	修改支付状态
	@ResponseBody
	@RequestMapping(value="/updateMyOrderToPay.do",method=RequestMethod.POST)
	public String updateMyOrderToPay(@RequestBody Map map2) {
//	public String insertToMyOrder(int MyOrderId,int userId,String ord_paystate) {
//		ord_paystate = "已支付";
//		String ord_orderstate = "等待发货";
		System.out.println(map2);
		System.out.println(map2.get("OrderId").getClass());
//		System.out.println(myOrder);
		boolean updateMyOrderPayState = goodsManageService.updateMyOrderPayState((String)map2.get("payState"), (String)map2.get("orderState"),String.valueOf(map2.get("OrderId")));
		System.out.println(updateMyOrderPayState);
		System.out.println(map2);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", updateMyOrderPayState);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	浏览我的订单
	@ResponseBody
	@RequestMapping(value="/queryToMyOrder.do",method=RequestMethod.POST)
	public String queryToMyOrder(@RequestBody Map map2) {
//	public String insertToMyOrder(int MyOrderId,int userId,String ord_paystate) {
//		ord_paystate = "已支付";
//		String ord_orderstate = "等待发货";
		System.out.println(map2);
		System.out.println(map2.get("userId").getClass());
		System.out.println(map2.get("page").getClass());
		System.out.println(map2.get("limit").getClass());
//		System.out.println(myOrder);
		Map<String, Object> queryMyOrderByUserID = goodsManageService.queryMyOrderByUserID(Integer.valueOf((String)map2.get("userId")),Integer.valueOf(map2.get("page").toString()), Integer.valueOf(map2.get("limit").toString()));
		System.out.println(queryMyOrderByUserID);
//		System.out.println(map2);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("flag", queryMyOrderByUserID);
//		System.out.println(map);
		return JSON.toJSONString(queryMyOrderByUserID);
	}
//	用户取消订单
	@ResponseBody
	@RequestMapping(value="/deleteToMyOrderByUser.do",method=RequestMethod.POST)
	public String deleteToMyOrderByUser(@RequestBody Map map2) {
		System.out.println(map2);
//		System.out.println(map2.get("userId").getClass());
//		System.out.println(map2.get("page").getClass());
//		System.out.println(map2.get("limit").getClass());
//		System.out.println(myOrder);
//		goodsManageService.deleteToMyOrderByUser(map2);
		Map deleteToMyOrderByUser = goodsManageService.deleteToMyOrderByUser(map2);
//		Map<String, Object> queryMyOrderByUserID = goodsManageService.queryMyOrderByUserID(Integer.valueOf((String)map2.get("userId")),Integer.valueOf(map2.get("page").toString()), Integer.valueOf(map2.get("limit").toString()));
//		System.out.println(queryMyOrderByUserID);
//		System.out.println(map2);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("flag", true);
//		System.out.println(map);
		System.out.println("deleteToMyOrderByUser:"+deleteToMyOrderByUser);
		return JSON.toJSONString(deleteToMyOrderByUser);
	}
	
	//员工订单查询
	
	@ResponseBody
	@RequestMapping(value = "/queryMyOrderAll.do" , method = RequestMethod.POST)
	public String queryMyOrderAll() {
		System.out.println("Controller界面");
		Map<String, Object> queryMyOrderAll = goodsManageService.queryMyOrderAll();
		System.out.println(queryMyOrderAll);
		return JSON.toJSONString(queryMyOrderAll);
	}
	//按名字搜索
	@ResponseBody
	@RequestMapping(value = "/queryOrderByUserName.do" , method = RequestMethod.POST)
	public String queryOrderByUserName(@RequestBody Map user) {
		System.out.println("Controller界面");
		Map<String, Object> queryOrderByUserName = goodsManageService.queryOrderByUserName((String) user.get("ord_username"));
//		System.out.println(queryOrderByUserName);
		return JSON.toJSONString(queryOrderByUserName);
	}
	
	//发货
	@ResponseBody
	@RequestMapping(value = "/updateOrderToDelivering.do" , method = RequestMethod.POST)
	public String updateOrderToDelivering(@RequestBody Map map2) {
		System.out.println("Controller界面");
		Map<String, Object> map = goodsManageService.updateOrderToDelivering(map2);
//		System.out.println(queryOrderByUserName);
		return JSON.toJSONString(map);
	}
	//取消订单
	@ResponseBody
	@RequestMapping(value = "/DeleteOrderToDelivering.do" , method = RequestMethod.POST)
	public String DeleteOrderToDelivering(@RequestBody Map map) {
		System.out.println("Controller界面");
//		System.out.println(queryOrderByUserName);
		return JSON.toJSONString(goodsManageService.deleteToMyOrderByEmp(map));
	}
	//修改订单信息
	@ResponseBody
	@RequestMapping(value = "/updateOrderToAddress.do" , method = RequestMethod.POST)
	public String updataOrderToAddress(@RequestBody Map map) {
		System.out.println("Controller界面");
//		System.out.println(queryOrderByUserName);
		return JSON.toJSONString(goodsManageService.updataOrderToAddress(map));
//		return JSON.toJSONString(goodsManageService.deleteToMyOrderByEmp(map));
	}
	
	//商品详情添加收藏夹
	@ResponseBody
	@RequestMapping(value = "/insertToFavoriteIndetail.do" , method = RequestMethod.POST)
	public String insertToFavoriteIndetail(@RequestBody Map map) {
		System.out.println("Controller界面");
		return JSON.toJSONString(goodsManageService.insertToFavoriteIndetail(map));
	}
	//用户查看历史订单
	@ResponseBody
	@RequestMapping(value = "/queryToHistoryOrderAllByUser.do" , method = RequestMethod.POST)
	public String queryToHistoryOrderAllByUser(@RequestBody Map map) {
		System.out.println("Controller界面");
		return JSON.toJSONString(goodsManageService.queryOrderHistoryByUserID(map));
	}
	
	
}
