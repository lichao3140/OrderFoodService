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

		// ��ȡ��γ����Ϣ�������ݾ�γ����Ϣ�����ݿ��л�ȡ��Ҫ�����ĸ����̼���Ϣ��mysql�Գ־��뷶Χ������

		// ����ʹ�þ�̬����
		Response resp = new Response();
		resp.setCode("0");

		// ����data��������
		// ������Ϣ
		ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
		for (int id = 1; id < 4; id++) {
			Promotion promotion = new Promotion();
			promotion.setId(id);
			// http://localhost:8080/TakeoutService/home/
			promotion.setPic(ConstantValue.HOST + "/OrderFoodService/" + "imgs/promotion/" + id + ".jpg");

			promotion.setInfo("promotion info...");
			promotionList.add(promotion);
		}
		// ������Ϣ
		String[] catetories=new String[]{"��ʳ","��Ʒ����","�̵곬��","���","����","�µ�","�����","������"};
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
		itcast.setName("�������Ա������Ŀ");
		itcast.setSendPrice("10");
		itcast.setDeliveryFee("5");
		itcast.setScore("5");

		nearbySellerList.add(itcast);

		for (int i = 2; i < 10; i++) {
			Seller seller = new Seller();
			seller.setId(i);
			seller.setName("���ǲ��͵�" + i + "�ҷֵ�");
			seller.setSendPrice("10");
			seller.setDeliveryFee("5");
			nearbySellerList.add(seller);
		}

		// ortherSellerList
		ArrayList<Seller> ortherSellerList = new ArrayList<Seller>();

		for (int i = 10; i < 20; i++) {
			Seller seller = new Seller();
			seller.setId(i);
			seller.setName("���ǲ��͵�" + i + "�ҷֵ�");
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

		item.recommendInfos.add("���˼�");
		item.recommendInfos.add("����");
		item.recommendInfos.add("���ǲ���");
		item.recommendInfos.add("�������Ա");
		item.recommendInfos.add("�ᶡ��");
		item.recommendInfos.add("��ѧ��");

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
