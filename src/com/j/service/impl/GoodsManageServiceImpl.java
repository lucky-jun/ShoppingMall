package com.j.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j.dao.GoodsManageDao;
import com.j.pojo.Goods;
import com.j.service.GoodsManageService;
@Service
public class GoodsManageServiceImpl implements GoodsManageService {
	
	@Autowired
	private GoodsManageDao goodsManageDao;
	
//����
	//�û����ܣ�
		//�����Ʒ
	@Override
	public List<Goods> queryGoodsByUserAll() {
		return goodsManageDao.queryGoodsByUserAll();
	}
		//�����Ʒ����
	@Override
	public Goods queryGoodsByID(int goo_id) {
		return goodsManageDao.queryGoodsByID(goo_id);
	}
		//ֱ����Ʒ�������������\���ﳵ����
	@Override
	public List<Goods> queryGoodsByID(List<Integer> ids) {
		
		return goodsManageDao.queryGoodsByListID(ids);
	}

}
