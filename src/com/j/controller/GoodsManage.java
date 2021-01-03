package com.j.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.ToCart;
import com.j.service.impl.GoodsManageServiceImpl;

@Controller
//报错：Access to XMLHttpRequest at 'http://localhost:8080/ShoppingMall/login.action
//跨域问题解决办法：加@CrossOrigin 
@CrossOrigin 
public class GoodsManage {

	@Autowired
	private GoodsManageServiceImpl goodsManageService;
	private MyCart mycart;
	
//	首页获取商品展示
	@ResponseBody
	@RequestMapping(value="/queryGoodsPage.do",method=RequestMethod.POST)
//	public List<Goods> Home(@RequestBody Map map) {
		public String Home(String page,String limit) {
		System.out.println(Integer.valueOf(page));
		System.out.println(Integer.valueOf(limit));
//		List<Goods> queryGoodsByPage = goodsManageService.queryGoodsByPage((String) map.get("page"), (String) map.get("limit"));
		List<Goods> queryGoodsByPage = goodsManageService.queryGoodsByPage(Integer.valueOf(page), Integer.valueOf(limit));
		System.out.println(queryGoodsByPage);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", queryGoodsByPage);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	获取购物车商品展示
	@ResponseBody
	@RequestMapping(value="/queryToCart.do",method=RequestMethod.POST)
//	public List<Goods> Home(@RequestBody int userId) {
	public String Cart(int userId) {
		System.out.println(userId);
		List<ToCart> queryMyCartByUserAll = goodsManageService.queryMyCartByUserAll(userId);
		
		System.out.println(queryMyCartByUserAll);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", queryMyCartByUserAll);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	查看商品详情
	@ResponseBody
	@RequestMapping(value="/queryGoodsById.do",method=RequestMethod.POST)
//	public List<Goods> Home(@RequestBody int goodsId) {
	public String GoodsDetail(int goodsId) {
		System.out.println(goodsId);
		Goods queryGoodsByID = goodsManageService.queryGoodsByID(goodsId);
		
		System.out.println(queryGoodsByID);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", queryGoodsByID);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	添加商品到购物车
	@ResponseBody
	@RequestMapping(value="/insertToCart.do",method=RequestMethod.POST)
//	public List<Goods> Home(@RequestBody Map map) {
	public String insertToMyCart(int userId,int goodsId,int number) {
		System.out.println(userId);
		System.out.println(goodsId);
		System.out.println(number);
		MyCart myCart2 = new MyCart();
		myCart2.setCar_gooid(goodsId);
		myCart2.setCar_userid(userId);
		myCart2.setCar_goonum(number);
		boolean insertGoToMyCart = goodsManageService.insertGoToMyCart(myCart2);
		
		System.out.println(insertGoToMyCart);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", insertGoToMyCart);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
//	加入我的订单
	@ResponseBody
	@RequestMapping(value="/insertToMyOrder.do",method=RequestMethod.POST)
//	public List<Goods> Home(@RequestBody Map map) {
	public String insertToMyOrder(int ord_userid,String ord_goodsinf,double ord_sumprice,String ord_paystate,String ord_orderstate) {
		
		MyOrder myOrder = new MyOrder(ord_userid, ord_userid, ord_goodsinf, ord_sumprice, ord_paystate, ord_orderstate, new Date());
		System.out.println(myOrder);
		Map insertGoToMyOrder = goodsManageService.insertGoToMyOrder(myOrder);
		System.out.println(insertGoToMyOrder);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("flag", insertGoToMyOrder);
//		System.out.println(map);
		return JSON.toJSONString(insertGoToMyOrder);
	}
//	修改支付状态
	@ResponseBody
	@RequestMapping(value="/updateMyOrderToPay.do",method=RequestMethod.POST)
//	public List<Goods> Home(@RequestBody Map map) {
	public String insertToMyOrder(int MyOrderId,int userId,String ord_paystate) {
		ord_paystate = "已支付";
		String ord_orderstate = "等待发货";
//		System.out.println(myOrder);
		boolean updateMyOrderPayState = goodsManageService.updateMyOrderPayState(ord_paystate, ord_orderstate,String.valueOf(MyOrderId));
		System.out.println(updateMyOrderPayState);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flag", updateMyOrderPayState);
		System.out.println(map);
		return JSON.toJSONString(map);
	}
}
