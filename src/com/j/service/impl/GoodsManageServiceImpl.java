package com.j.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.j.dao.GoodsManageDao;
import com.j.pojo.Goods;
import com.j.pojo.LookOrder;
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
		System.out.println("�����Ʒ�����ﳵ");
		System.out.println(mycart);
		List<MyCart> queryMyCartOneGoodsByUserID = goodsManageDao.queryMyCartOneGoodsByUserID(mycart.getCar_userid(), mycart.getCar_gooid());
//		System.out.println(queryMyCartOneGoodsByUserID);
//		System.out.println(queryMyCartOneGoodsByUserID.isEmpty());
//		Ϊ��ֱ�Ӳ���
		if(queryMyCartOneGoodsByUserID.isEmpty()) {
			System.out.println("�޸ò�Ʒ");
			int insertGoToMyCart = goodsManageDao.insertGoToMyCart(mycart);
			return insertGoToMyCart>0;
		}else {
//			��Ϊ�ռ�����Ʒ����{car_goonum} where car_userid=#{car_userid} and car_gooid=#{car_gooid}
			System.out.println("�иò�Ʒ");
			System.out.println(queryMyCartOneGoodsByUserID.get(0).getCar_goonum());
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("car_goonum", queryMyCartOneGoodsByUserID.get(0).getCar_goonum()+1);
			map.put("car_userid", mycart.getCar_userid());
			map.put("car_gooid", mycart.getCar_gooid());
			System.out.println(mycart.getCar_goonum());
			System.out.println(map);
			int updataMyCartToNumberinOne = goodsManageDao.updataMyCartToNumberinOne(map);
			return updataMyCartToNumberinOne>0;
		}
	}
//	�����ҵĶ���
	@Override
	public Map insertGoToMyOrder(Map map2) {

		double sumprice = 0;
		String goodsInf = "";
		System.out.println(map2);
		List list = (List) map2.get("goods");
		for(int i =0 ;i<list.size();i++) {
			System.out.println(list);
		    JSONArray jsonArray = new JSONArray(list);  
		    System.out.println(jsonArray.getJSONObject(i).get("car_gooid")==null);
		    System.out.println(jsonArray.getJSONObject(i).get("goo_id"));
		    int id = 0;
		    if(jsonArray.getJSONObject(i).get("car_gooid")!=null) {
		    	//�ӹ��ﳵ��ȡ������ƷID
		    	id =(int) jsonArray.getJSONObject(i).get("car_gooid");//�õ���һ����id 
		    }else {
		    	//����Ʒ�����ȡ������ƷID
		    	id =(int) jsonArray.getJSONObject(i).get("goo_id");//�õ���һ����id 
		    }
		    int number =(int) jsonArray.getJSONObject(i).get("number");//�õ���һ����number 
		    System.out.println("-----------------------------------------------------------------");
		    String substring = jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16);
		    System.out.println(jsonArray.getJSONObject(i).get("sump").getClass().toString());
		    System.out.println(jsonArray.getJSONObject(i).get("sump").getClass().toString().substring(16));
		    double doubleValue = 0;
		    if(substring.equals("Integer")) {
			    doubleValue = ((Integer) jsonArray.getJSONObject(i).get("sump")).doubleValue();//�õ���һ����name  
		    }else{
		    	doubleValue = ((Double) jsonArray.getJSONObject(i).get("sump")).doubleValue();//�õ���һ����name  
		    }
		    System.out.println(sumprice);
		    System.out.println(doubleValue);
		    sumprice+=doubleValue;
		    System.out.println(sumprice);
		    
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
//	��ѯ�ҵĶ���
	@Override
	public Map<String, Object> queryMyOrderByUserID(int userId, int page, int limit) {
//		��ȡ�����е���Ʒ
		List<MyOrder> queryMyOrderByUserID = goodsManageDao.queryMyOrderByUserID(userId, page, limit);
		System.out.println(queryMyOrderByUserID);
		System.out.println(queryMyOrderByUserID.size());
		
		List<Integer> listid = new ArrayList<Integer>();
		List<Integer> listnumber = new ArrayList<Integer>();
		List<LookOrder> listorder = new ArrayList<LookOrder>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		
//		for(MyOrder list: queryMyOrderByUserID) {
			for(int i =0;i<queryMyOrderByUserID.size();i++) {
				String[] split = queryMyOrderByUserID.get(i).getOrd_goodsinf().split(",");
				System.out.println("�������:"+queryMyOrderByUserID.get(i).getOrd_id());
				System.out.println("split:"+split.toString());
				System.out.println("split.length:"+split.length);
				List<Integer> listOrderById = new ArrayList<Integer>();
				
				for(int a=0;a<split.length;a++) {
//					System.out.println("split[i]:"+split[a]);
					String[] split2 = split[a].split("-");
					listOrderById.add(Integer.valueOf(split2[0]));
				}
				System.out.println(listOrderById);
				//��ѯ���
				List<Goods> queryGoodsByListID = goodsManageDao.queryGoodsByListID(listOrderById);
				map2.put(String.valueOf(queryMyOrderByUserID.get(i).getOrd_id()), queryGoodsByListID);
//				listid.addAll(listOrderById);
//				listnumber.add(Integer.valueOf(split2[1]));
			}
		/*map2:{
		 * {1=[[goo_id=5, goo_name=������, goo_stock=100, goo_buying_price=1.0, goo_selling_price=2.5, goo_supid=3, goo_type=����Ʒ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]],
		 *  2=[[goo_id=4, goo_name=������X02, goo_stock=50, goo_buying_price=899.0, goo_selling_price=999.0, goo_supid=5, goo_type=���ڷ�װ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]],
		 *  3=[[goo_id=6, goo_name=�ٲ�ζ����, goo_stock=100, goo_buying_price=2.5, goo_selling_price=4.0, goo_supid=3, goo_type=����Ʒ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]],
		 *  4=[[goo_id=4, goo_name=������X02, goo_stock=50, goo_buying_price=899.0, goo_selling_price=999.0, goo_supid=5, goo_type=���ڷ�װ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]],
		 *  15=[[goo_id=5, goo_name=������, goo_stock=100, goo_buying_price=1.0, goo_selling_price=2.5, goo_supid=3, goo_type=����Ʒ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]], 5=[[goo_id=4, goo_name=������X02, goo_stock=50, goo_buying_price=899.0, goo_selling_price=999.0, goo_supid=5, goo_type=���ڷ�װ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]], 16=[[goo_id=2, goo_name=���ܲ�, goo_stock=100, goo_buying_price=1.0, goo_selling_price=2.0, goo_supid=2, goo_type=����, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null], [goo_id=5, goo_name=������, goo_stock=100, goo_buying_price=1.0, goo_selling_price=2.5, goo_supid=3, goo_type=����Ʒ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null], [goo_id=6, goo_name=�ٲ�ζ����, goo_stock=100, goo_buying_price=2.5, goo_selling_price=4.0, goo_supid=3, goo_type=����Ʒ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null], [goo_id=8, goo_name=������Ѽ, goo_stock=100, goo_buying_price=39.9, goo_selling_price=69.9, goo_supid=1, goo_type=����Ʒ, goo_image=//img10.360buyimg.com/seckillcms/s250x250_jfs/t1/149610/13/17555/80183/5fd06d77Ef92040f1/757f488b6a26215a.jpg, goo_text=null, goo_details=null]]}
		 *    }
		 *    
		 *    
		 *    
		 *    
		 *    
		 *    
		 *    
*/
			
			System.out.println("map2:"+map2);
//			System.out.println(listid);
//			System.out.println(listnumber);
//			List<Goods> queryGoodsByListID = goodsManageDao.queryGoodsByListID(listid);
//			System.out.println(queryGoodsByListID);
//			for(int i=0;i<queryGoodsByListID.size();i++) {
//				LookOrder lookOrder = new LookOrder();
//				System.out.println("-----:"+queryGoodsByListID.get(i).getGoo_id());
//				System.out.println("-----:"+queryGoodsByListID.get(i).getGoo_name());
//				lookOrder.setGoo_id(queryGoodsByListID.get(i).getGoo_id());
//				lookOrder.setGoo_name(queryGoodsByListID.get(i).getGoo_name());
//				lookOrder.setGoo_image(queryGoodsByListID.get(i).getGoo_image());
//				lookOrder.setGoo_selling_price(queryGoodsByListID.get(i).getGoo_selling_price());
//				lookOrder.setNumber(listnumber.get(i));
//				lookOrder.setSumprice(queryGoodsByListID.get(i).getGoo_selling_price()*listnumber.get(i));
//				listorder.add(lookOrder);
//				System.out.println("listorder:"+listorder);
//			}
//			System.out.println();
//			System.out.println("listorder:"+listorder);
//		}
		Map<String,Object> map = new HashMap<String, Object>();
		if(queryMyOrderByUserID.isEmpty()) {
			map.put("flag", false);
		}else {
//			map.put("data", listorder);
			map.put("flag", true);
		}
		return map;
	}

}
