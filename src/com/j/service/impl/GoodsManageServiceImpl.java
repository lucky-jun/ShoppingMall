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
	
//功能
	//用户功能：
		//浏览商品
	@Override
	public List<Goods> queryGoodsByUserAll() {
		return goodsManageDao.queryGoodsByUserAll();
	}
		//浏览商品详情
	@Override
	public Goods queryGoodsByID(int goo_id) {
		return goodsManageDao.queryGoodsByID(goo_id);
	}
		//直接商品详情界面点击购买\购物车结算
	@Override
	public List<Goods> queryGoodsByID(List<Integer> ids) {
		
		return goodsManageDao.queryGoodsByListID(ids);
	}

}
