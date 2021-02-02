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
//����
	//�û����ܣ�
		//�����Ʒ
	public List<Goods> queryGoodsByUserAll();
	public List<Goods> queryGoodsByPage(int page,int number);
		//�����Ʒ����
	public Goods queryGoodsByID(int goo_id);
		//ֱ����Ʒ�������������\���ﳵ����
	public LinkedList<Goods> queryGoodsByID(LinkedList<Integer> ids);
		//��ӹ��ﳵ
	public boolean insertGoToMyCart(MyCart mycart);
		//�ӹ��ﳵɾ����Ʒ
	public Map<String,Object> deleteToMyCartByList(Map map2);
		//�ӹ��ﳵ�����ղؼ�
	public Map<String,Object> insertToFavorite(Map map2);
		//����Ʒ��������ղؼ�
	public Map<String,Object> insertToFavoriteIndetail(Map map2);
		//������ﳵ
	public List<ToCart> queryMyCartByUserAll(int userid);
		//���ﳵ����
	
		//�����ҵĶ���
	public Map insertGoToMyOrder(Map map);
		//�ҵĶ������֧��
	public boolean updateMyOrderPayState(String ord_paystate,String ord_orderstate,String ord_id);
		//��ѯ�ҵĶ���
	public Map<String,Object>queryMyOrderByUserID(int userId,int page,int limit);

		//ȡ������
	public Map<String,Object> deleteToMyOrderByUser(Map<String,Object> map);
		//����ջ�
	
		//����ղؼ�
	public Map<String,Object> queryFavoriteByUserAll(Map<String,Integer> map);
		//ɾ���ղؼ���Ʒ
	public Map<String,Object> deleteMyFavorite(Map<String,Object> map2);
	
	//Ա������
		//��ѯ����
	public Map<String, Object> queryMyOrderAll();
			//��ѯ�û���������
	public Map<String,Object> queryOrderByUserName(String user_name);
		//����
	public Map<String,Object> updateOrderToDelivering(Map map2);
		//ȡ������
	public Map<String,Object> deleteToMyOrderByEmp(Map map);
		//......

}
