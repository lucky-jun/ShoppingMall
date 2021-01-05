package com.j.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.j.dao.GoodsManageDao;
import com.j.pojo.Goods;
import com.j.pojo.MyCart;
import com.j.pojo.MyOrder;
import com.j.pojo.ToCart;
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
	@Override
	public List<Goods> queryGoodsByPage(int page, int number) {
		List<Goods> queryGoodsByPage = goodsManageDao.queryGoodsByPage(page, number);
		return queryGoodsByPage;
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
//	������ﳵ
	@Override
	public List<ToCart> queryMyCartByUserAll(int userid) {
		List<ToCart> queryMyCartByUserAll = goodsManageDao.queryMyCartByUserAll(userid);
		return queryMyCartByUserAll;
	}
//	�����Ʒ�����ﳵ
	@Override
	public boolean insertGoToMyCart(MyCart mycart) {
		int insertGoToMyCart = goodsManageDao.insertGoToMyCart(mycart);
		
		return insertGoToMyCart>0;
	}
//	�����ҵĶ���
	@Override
	public Map insertGoToMyOrder(Map map2) {

		double sumprice = 0;
		String goodsInf = "";
		List list = (List) map2.get("goods");
		for(int i =0 ;i<list.size();i++) {
		    JSONArray jsonArray = new JSONArray(list);  
		    int id =(int) jsonArray.getJSONObject(i).get("goo_id");//�õ���һ����id 
		    int number =(int) jsonArray.getJSONObject(i).get("number");//�õ���һ����number 
		    String substring = jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16);
		    double doubleValue = 0;
		    if(substring.equals("Integer")) {
			    doubleValue = ((Integer) jsonArray.getJSONObject(0).get("sump")).doubleValue();//�õ���һ����name  
		    }else{
		    	doubleValue = ((Double) jsonArray.getJSONObject(0).get("sump")).doubleValue();//�õ���һ����name  
		    }
		    sumprice+=doubleValue;
		    goodsInf+=id+"-"+number+",";
		}
		String substring = goodsInf.substring(0,goodsInf.length()-1);
		System.out.println("��Ʒ��Ϣ��"+substring);
		System.out.println("��Ʒ�ܼۣ�"+sumprice);
		System.out.println("userID1��"+map2.get("userId"));
		System.out.println("userID2��"+Integer.valueOf((String) map2.get("userId")));
		System.out.println("userID3��"+Integer.valueOf((String) map2.get("userId")).intValue());
		
		MyOrder myOrder = new MyOrder(Integer.valueOf((String) map2.get("userId")).intValue(), substring, sumprice, "����δ֧��", "�ȴ�֧��", new Date());
		
		int insertGoToMyOrder = goodsManageDao.insertGoToMyOrder(myOrder);
		int queryMyOrderNewByUserID = goodsManageDao.queryMyOrderNewByUserID(myOrder.getOrd_userid());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("MyOrderId", queryMyOrderNewByUserID);
		map.put("flag", insertGoToMyOrder>0);
		return map;
	}
//	�޸�֧��״̬
	@Override
	public boolean updateMyOrderPayState(String ord_paystate, String ord_orderstate,String ord_id) {
		int updateMyOrderPayState = goodsManageDao.updateMyOrderPayState(ord_paystate, ord_orderstate,ord_id);
		return updateMyOrderPayState>0;
	}

}
