package com.lichao.bean;

import java.util.ArrayList;

public class Seller {
	
	/**
	 * "id":1,
                    "pic":http://xxxxxxxxxx.jpg,
                    "name":"��ʮ���루�ϵص꣩",
                    
                    "score":"4.4",
                    "sale":4132,//����
                    "ensure":1,//�Ƿ���ת�ͱ�֤
                    
                    "invoice":1,//�Ƿ��ṩ��Ʊ
                    "sendPrice":20,//���ͼ۸�
                    "deliveryFee":4,//���ͷ�
                    
                    "recentVisit":1,//�Ƿ�������
                    "distance":"773m",
                    "time":"34����",
                    
                    "activityList":,[{//��б�
                       "id":1,
                       "type":1,// ����ͣ��������
                       "info":"����֧������30��8"
},{
   "id":2,
   "type":2,
   " info ":"16.9Ԫ�ؼ۳�ֵ�ײͣ�"
}]

	 */
	
	private long id;
	private String pic;
	private String name;
	
	private String score;
	private String sale;
	private String ensure;
	
	private String invoice;
	private String sendPrice;
	private String deliveryFee;
	
	private String recentVisit;
	private String distance;
	private String time;
	
	private ArrayList<ActivityInfo> activityList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getEnsure() {
		return ensure;
	}

	public void setEnsure(String ensure) {
		this.ensure = ensure;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getSendPrice() {
		return sendPrice;
	}

	public void setSendPrice(String sendPrice) {
		this.sendPrice = sendPrice;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getRecentVisit() {
		return recentVisit;
	}

	public void setRecentVisit(String recentVisit) {
		this.recentVisit = recentVisit;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<ActivityInfo> getActivityList() {
		return activityList;
	}

	public void setActivityList(ArrayList<ActivityInfo> activityList) {
		this.activityList = activityList;
	}

}
