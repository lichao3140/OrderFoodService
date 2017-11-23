package com.lichao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.lichao.ConstantValue;
import com.lichao.bean.Category;
import com.lichao.bean.Head;
import com.lichao.bean.HomeInfo;
import com.lichao.bean.HomeItem;
import com.lichao.bean.Promotion;
import com.lichao.bean.Response;
import com.lichao.bean.Seller;
import com.lichao.utils.CommonUtil;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 获取经纬度信息，并依据经纬度信息从数据库中获取需要检索的附近商家信息（mysql自持距离范围检索）

		// 测试使用静态数据
		Response resp = new Response();
		resp.setCode("0");

		// 设置data部分数据
		// 促销信息
		ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
		for (int id = 1; id < 4; id++) {
			Promotion promotion = new Promotion();
			promotion.setId(id);
			// http://localhost:8080/TakeoutService/home/
			promotion.setPic(ConstantValue.HOST + "/OrderFoodService/" + "imgs/promotion/" + id + ".jpg");

			promotion.setInfo("promotion info...");
			promotionList.add(promotion);
		}
		// 分类信息
		String[] catetories=new String[]{"美食","甜品饮料","商店超市","早餐","果蔬","新店","下午茶","麻辣烫"};
		ArrayList<Category> categorieList = new ArrayList<Category>();
		for (int id = 1; id < 9; id++) {
			Category category = new Category();
			category.setId(id);
			category.setPic(ConstantValue.HOST + "/OrderFoodService/" + "imgs/category/" + id + ".png");
			category.setName(catetories[id-1]);
			categorieList.add(category);
		}

		// nearbySellerList
		ArrayList<Seller> nearbySellerList = new ArrayList<Seller>();
		Seller itcast = new Seller();
		itcast.setId(1);
		itcast.setPic(ConstantValue.HOST + "/OrderFoodService/" + "imgs/category/" + 1 + ".png");
		itcast.setName("黑马程序员外卖项目");
		itcast.setSendPrice("10");
		itcast.setDeliveryFee("5");
		itcast.setScore("5");

		nearbySellerList.add(itcast);

		for (int i = 2; i < 10; i++) {
			Seller seller = new Seller();
			seller.setId(i);
			seller.setName("传智播客第" + i + "家分店");
			seller.setSendPrice("10");
			seller.setDeliveryFee("5");
			nearbySellerList.add(seller);
		}

		// ortherSellerList
		ArrayList<Seller> ortherSellerList = new ArrayList<Seller>();

		for (int i = 10; i < 20; i++) {
			Seller seller = new Seller();
			seller.setId(i);
			seller.setName("传智播客第" + i + "家分店");
			seller.setSendPrice("10");
			seller.setDeliveryFee("5");
			ortherSellerList.add(seller);
		}

		List<HomeItem> body = new ArrayList<HomeItem>();
		for (Seller seller : nearbySellerList) {
			HomeItem sellerItem = new HomeItem();
			sellerItem.type = 0;
			sellerItem.seller = seller;
			body.add(sellerItem);
		}

		HomeItem item = new HomeItem();
		item.type = 1;
		item.recommendInfos = new ArrayList<String>();

		item.recommendInfos.add("黄焖鸡");
		item.recommendInfos.add("米线");
		item.recommendInfos.add("传智播客");
		item.recommendInfos.add("黑马程序员");
		item.recommendInfos.add("酷丁鱼");
		item.recommendInfos.add("博学谷");

		body.add(item);

		for (Seller seller : ortherSellerList) {
			HomeItem sellerItem = new HomeItem();
			sellerItem.type = 0;
			sellerItem.seller = seller;
			body.add(sellerItem);
		}

		HomeInfo info = new HomeInfo(new Head(promotionList, categorieList), body);
		Response res = new Response();
		res.setCode("0");
		res.setData(JSONObject.fromObject(info).toString());

		CommonUtil.renderJson(response, res);

	
	}

}
