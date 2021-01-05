package com.j.service;

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
	public List<Goods> queryGoodsByID(List<Integer> ids);
		//��ӹ��ﳵ
	public boolean insertGoToMyCart(MyCart mycart);
		//������ﳵ
	public List<ToCart> queryMyCartByUserAll(int userid);
		//���ﳵ����
	
		//�����ҵĶ���
	public Map insertGoToMyOrder(Map map);
		//�ҵĶ������֧��
	public boolean updateMyOrderPayState(String ord_paystate,String ord_orderstate,String ord_id);

		//����ջ�
	
	//Ա������
		//......

}
