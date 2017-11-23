package com.lichao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lichao.ConstantValue;
import com.lichao.bean.Distribution;
import com.lichao.bean.GoodsInfo;
import com.lichao.bean.Location;
import com.lichao.bean.Order;
import com.lichao.bean.OrderDetail;
import com.lichao.bean.OrderOverview;
import com.lichao.bean.OrderOverviewResponse;
import com.lichao.bean.Payment;
import com.lichao.bean.Response;
import com.lichao.bean.Rider;
import com.lichao.bean.Seller;
import com.lichao.utils.CommonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<String, OrderOverview> map;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		mapManager();
	}

	private void mapManager() {
		if (map == null) {
			map = new HashMap<String, OrderOverview>();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = 0;
		try {
			userId = Integer.parseInt(request.getParameter("userId"));
		} catch (Exception e) {
		}

		Response res = new Response();

		if (userId != 0) {

			List<Order> orderList = new ArrayList<Order>();

			for (int i = 9; i > 0; i--) {
				Order order = new Order();
				setOrder(order, i);

				orderList.add(order);
			}

			
			res.setCode("0");
			res.setData(JSONArray.fromObject(orderList).toString());

			
		}else{
			res.setCode("-1");
			res.setData("");
		}
		CommonUtil.renderJson(response, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Response res = new Response();

		Random random = new Random();
		int num = random.nextInt(9999);

		String orderOverview = request.getParameter("orderOverview");
		if (orderOverview != null) {
			res.setCode("0");
			// 将用户订单保存到数据库中
			OrderOverview overview = JSON.parseObject(orderOverview, OrderOverview.class);
			overview.id = "1010 8027 3652 5688 " + num;
			map.put(overview.id, overview);

			res.setData(overview.id);

		} else {
			res.setCode("-1");
			res.setData("");
		}

		CommonUtil.renderJson(response, res);
	}

	private void setOrder(Order order, int i) {

		order.setId("1010 8027 3652 5689 3" + i);
		if (i == 9) {
			order.setType("10");
		} else {
			order.setType("50");
		}

		List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setName("红烧肉");
		goodsInfo.setNewPrice(25);
		goodsInfos.add(goodsInfo);

		GoodsInfo goodsInfo1 = new GoodsInfo();
		goodsInfo1.setName("米饭");
		goodsInfo1.setNewPrice(2);
		goodsInfos.add(goodsInfo1);

		GoodsInfo goodsInfo2 = new GoodsInfo();
		goodsInfo2.setName("雪碧");
		goodsInfo2.setNewPrice(4);
		goodsInfos.add(goodsInfo2);
		order.setGoodsInfos(goodsInfos);

		Rider rider = new Rider();
		rider.setId(100);
		rider.setName("张三");
		rider.setPhone("13200000000");
		Location location = new Location();
		location.setLatitude("106.23");
		location.setLongitude("43.123");
		rider.setLocation(location);
		order.setRider(rider);

		Distribution distribution = new Distribution();
		distribution.setDes("黑马配送");
		distribution.setType("1");
		order.setDistribution(distribution);

		OrderDetail detail = new OrderDetail();
		detail.setAddress("修正校区");
		detail.setPay("在线支付");
		detail.setPhone("135000000000");
		detail.setTime("2020-10-10 10:10:10");
		detail.setUsername("老黑");
		order.setDetail(detail);

		Seller itcast = new Seller();
		itcast.setId(1);
		itcast.setPic(ConstantValue.HOST + "/OrderFoodService/" + "imgs/category/" + 1 + ".png");
		itcast.setName("黑马程序员外卖项目");
		itcast.setScore("5");
		order.setSeller(itcast);

	}

}
