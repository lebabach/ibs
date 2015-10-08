package com.ecard.webapp.vo;

import java.util.List;

public class ObjectNotification {
	private List<NotificationOfUserVO> notifications;
	private int numberOfNotification;
	public List<NotificationOfUserVO> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<NotificationOfUserVO> notifications) {
		this.notifications = notifications;
	}
	public int getNumberOfNotification() {
		return numberOfNotification;
	}
	public void setNumberOfNotification(int numberOfNotification) {
		this.numberOfNotification = numberOfNotification;
	}
}
