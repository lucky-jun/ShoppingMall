package com.j.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.OrderHistory;
import com.j.pojo.ToCart;

public interface GoodsManageService {
//功能
	//用户功能：
		//浏览商品
	public List<Goods> queryGoodsByUserAll();
	public List<Goods> queryGoodsByPage(int page,int number);
		//浏览商品详情
	public Goods queryGoodsByID(int goo_id);
		//直接商品详情界面点击购买\购物车结算
	public LinkedList<Goods> queryGoodsByID(LinkedList<Integer> ids);
		//添加购物车
	public boolean insertGoToMyCart(MyCart mycart);
		//从购物车删除商品
	public Map<String,Object> deleteToMyCartByList(Map map2);
		//从购物车加入收藏夹
	public Map<String,Object> insertToFavorite(Map map2);
		//从商品详情加入收藏夹
	public Map<String,Object> insertToFavoriteIndetail(Map map2);
		//浏览购物车
	public List<ToCart> queryMyCartByUserAll(int userid);
		//购物车结算
	
		//加入我的订单
	public Map insertGoToMyOrder(Map map);
		//我的订单点击支付
	public boolean updateMyOrderPayState(String ord_paystate,String ord_orderstate,String ord_id);
		//查询我的订单
	public Map<String,Object>queryMyOrderByUserID(int userId,int page,int limit);

		//取消订单
	public Map<String,Object> deleteToMyOrderByUser(Map<String,Object> map);
		//点击收货
	
		//浏览收藏夹
	public Map<String,Object> queryFavoriteByUserAll(Map<String,Integer> map);
		//删除收藏夹商品
	public Map<String,Object> deleteMyFavorite(Map<String,Object> map2);
	
	//员工功能
		//查询订单
	public Map<String, Object> queryMyOrderAll();
			//查询用户姓名订单
	public Map<String,Object> queryOrderByUserName(String user_name);
		//发货
	public Map<String,Object> updateOrderToDelivering(Map map2);
		//取消订单
	public Map<String,Object> deleteToMyOrderByEmp(Map map);
		//......

}
