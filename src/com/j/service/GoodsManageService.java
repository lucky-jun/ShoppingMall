package com.j.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.OrderHistory;

public interface GoodsManageService {
//����
	//�û����ܣ�
		//�����Ʒ
	public List<Goods> queryGoodsByUserAll();
		//�����Ʒ����
	public Goods queryGoodsByID(int goo_id);
		//ֱ����Ʒ�������������\���ﳵ����
	public List<Goods> queryGoodsByID(List<Integer> ids);
		//��ӹ��ﳵ
	
		//������ﳵ
	
		//���ﳵ����
	
		//�ҵĶ������֧��
	
		//����ջ�
	
	//Ա������
		//......

}
