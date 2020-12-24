package com.j.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.OrderHistory;

public interface GoodsManageService {
//功能
	//用户功能：
		//浏览商品
	public List<Goods> queryGoodsByUserAll();
		//浏览商品详情
	public Goods queryGoodsByID(int goo_id);
		//直接商品详情界面点击购买\购物车结算
	public List<Goods> queryGoodsByID(List<Integer> ids);
		//添加购物车
	
		//浏览购物车
	
		//购物车结算
	
		//我的订单点击支付
	
		//点击收货
	
	//员工功能
		//......

}
