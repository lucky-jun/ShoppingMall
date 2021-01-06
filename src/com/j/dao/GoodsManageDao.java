package com.j.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.OrderHistory;
import com.j.pojo.ToCart;

public interface GoodsManageDao {
//��
	//�����Ʒ��Goods 
	public int insertGoToGoods(Goods goods);
	//�����Ʒ��MyCart
	public int insertGoToMyCart(MyCart myCart);
	//�����Ʒ��MyOrder
	public int insertGoToMyOrder(MyOrder myOrder);
	//�����Ʒ��OrderHistory 
	public int insertGoToOrderHistory(OrderHistory orderHistory);
	
//ɾ
	//���ﳵ����ɾ��,��������Ƴ�:��map������ƷID��list���û�ID
	public int deleteToMyCartByList(Map<String ,Object> map);
	//ɾ����Ʒ������ȡ�����ߵ���ջ�:MyOrder ����map��ʽ�����ҵĶ����еĶ���ID���û�ID
	public int deleteToMyOrder(Map<String ,Object> map);
//��
	// ���̲�ѯ ��Supplier
	// ��Ʒ��ѯ :Goods
		//ȫ����Ϣ��ѯ
			//��ͨ�û�ȫ����Ʒ��Ϣ��ѯ   yes
	public List<Goods> queryGoodsByUserAll();
	public List<Goods> queryGoodsByPage(@Param("page")int page,@Param("number")int number);
			//Ա��ȫ����Ʒ��Ϣ��ѯ
	public List<Goods> queryGoodsAll();
		//���Ʋ�ѯ
	public Goods queryGoodsByName(@Param("goo_name")String goo_name);
		//ID��ѯ  yes
	public Goods queryGoodsByID(@Param("goo_id")int goo_id);
		//����ID��ѯ��������Ʒ��ʹ�� ,����List����
	public List<Goods> queryGoodsByListID(List<Integer> goodsid);
	// ���ﳵ��ѯ:MyCart
		// �û����ﳵ��ѯ��int �����û�ID
	public List<ToCart> queryMyCartByUserAll(@Param("car_userid")int car_userid);
	public List<MyCart> queryMyCartOneGoodsByUserID(@Param("car_userid")int car_userid,@Param("car_gooid")int car_gooid);
		// Ա����ѯ 
	public List<MyCart> queryMyCartAll();
	// ������ѯ ��MyOrder
		//�û�������ѯ 
	public List<MyOrder> queryMyOrderByUserID(@Param("ord_userid")int ord_userid);
		//��ѯ�û����¶���
	public int queryMyOrderNewByUserID(@Param("ord_userid")int ord_userid);
		// Ա��������ѯ
			// ��ѯȫ��
	public List<MyOrder> queryMyOrderAll();
			//����״̬��ѯ
	public List<MyOrder> queryMyOrderByOrderstate(@Param("ord_orderstate")int ord_orderstate);
			// ������Ų�ѯ���� 
	public List<MyOrder> queryMyOrderByOrdid(@Param("ord_id")int ord_id);
	// ��ʷ������ѯ:OrderHistory
		// �û���ѯ 
	public List<OrderHistory> queryOrderHistoryByUserID(@Param("his_userid")int his_userid);
		// Ա����ѯ 
	public List<OrderHistory> queryOrderHistoryAll();
//��
	// �û����ﳵ���ӹ�������:MyCart
		// ʹ��Map��ֵ
	public int updateMyCartToNumberByUidGid(Map<String ,Object> map);
	public int updateMyOrderPayState(@Param("ord_paystate") String ord_paystate,@Param("ord_orderstate") String ord_orderstate,@Param("ord_id") String ord_id);
	public int updataMyCartToNumberinOne(Map<String ,Object> map);
	
	
}
