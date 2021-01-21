package com.j.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.EmpOrder;
import com.j.pojo.Goods;
import com.j.pojo.LookOrder;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.OrderHistory;
import com.j.pojo.ToCart;

public interface GoodsManageDao {
//增
	//添加商品到Goods 
	public int insertGoToGoods(Goods goods);
	//添加商品到MyCart
	public int insertGoToMyCart(MyCart myCart);
	//添加商品到MyOrder
	public int insertGoToMyOrder(MyOrder myOrder);
	//添加商品到OrderHistory 
	public int insertGoToOrderHistory(OrderHistory orderHistory);
	
//删
	//购物车批量删除,购买或者移除:以map传入商品ID的list和用户ID
	public int deleteToMyCartByList(Map<String ,Object> map);
	//删除商品订单中取消或者点击收货:MyOrder ，以map形式传入我的订单中的订单ID和用户ID
	public int deleteToMyOrder(Map<String ,Object> map);
//查
	// 厂商查询 ：Supplier
	// 商品查询 :Goods
		//全部信息查询
			//普通用户全部商品信息查询   yes
	public List<Goods> queryGoodsByUserAll();
	public List<Goods> queryGoodsByPage(@Param("page")int page,@Param("number")int number);
			//员工全部商品信息查询
	public List<Goods> queryGoodsAll();
		//名称查询
	public Goods queryGoodsByName(@Param("goo_name")String goo_name);
		//ID查询  yes
	public Goods queryGoodsByID(@Param("goo_id")int goo_id);
		//批量ID查询：购买商品等使用 ,传入List类型
	public List<Goods> queryGoodsByListID(List<Integer> goodsid);
	public List<LookOrder> queryGoodsLookOrderByListID(List<Integer> goodsid);
	// 购物车查询:MyCart
		// 用户购物车查询，int 传入用户ID
	public List<ToCart> queryMyCartByUserAll(@Param("car_userid")int car_userid);
	public List<MyCart> queryMyCartOneGoodsByUserID(@Param("car_userid")int car_userid,@Param("car_gooid")int car_gooid);
		// 员工查询 
	public List<MyCart> queryMyCartAll();
	// 订单查询 ：MyOrder
		//用户订单查询 
	public List<MyOrder> queryMyOrderByUserID(@Param("ord_userid")int ord_userid,@Param("page")int page,@Param("number")int number);
		//查询用户最新订单
	public int queryMyOrderNewByUserID(@Param("ord_userid")int ord_userid);
		// 员工订单查询
			// 查询全部
	public List<MyOrder> queryMyOrderAll();
			//订单状态查询
	public List<MyOrder> queryMyOrderByOrderstate(@Param("ord_orderstate")int ord_orderstate);
			// 订单编号查询订单 
	public List<MyOrder> queryMyOrderByOrdid(@Param("ord_id")int ord_id);
	// 历史订单查询:OrderHistory
		// 用户查询 
	public List<OrderHistory> queryOrderHistoryByUserID(@Param("his_userid")int his_userid);
		// 员工查询 
	public List<OrderHistory> queryOrderHistoryAll();
//改
	// 用户购物车增加购买数量:MyCart
		// 使用Map传值
	public int updateMyCartToNumberByUidGid(Map<String ,Object> map);
	public int updateMyOrderPayState(@Param("ord_paystate") String ord_paystate,@Param("ord_orderstate") String ord_orderstate,@Param("ord_id") String ord_id);
	public int updataMyCartToNumberinOne(Map<String ,Object> map);
	
	
}
