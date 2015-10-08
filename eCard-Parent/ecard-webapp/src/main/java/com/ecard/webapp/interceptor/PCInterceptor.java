package com.ecard.webapp.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.NotificationInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserNotifyService;
import com.ecard.core.vo.NotificationList;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.NotificationOfUserVO;
import com.ecard.webapp.vo.ObjectNotification;

@Component
public class PCInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
    NotificationInfoService notificationInfoService;
	
	@Autowired
    CardInfoService cardInfoService;
	
	@Value("${scp.hostname}")
	private String scpHostName;
	    
	@Value("${scp.user}")
    private String scpUser;
	    
	@Value("${scp.password}")
	private String scpPassword;
	    
	@Value("${scp.port}")
	private String scpPort;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (!ajax) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			List<NotificationList> listUpdate = notificationInfoService.listAllNofiticationUser(ecardUser.getUserId());
			List<NotificationOfUserVO> notifications=new ArrayList<NotificationOfUserVO>();
			for(NotificationList item:listUpdate){
				NotificationOfUserVO notification=new NotificationOfUserVO();
				notification.setContents(item.getNotify_message());
				notification.setDate(item.getNotice_date());
				notification.setId(item.getNotice_id());
				notification.setImage(cardInfoService.getCardInfoDetail(item.getCard_id()).getImageFile());
				notifications.add(notification);
			}
				
			notifications=UploadFileUtil.getImageFileFromSCPForNotification(notifications, scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
			ObjectNotification objectNotification=new ObjectNotification();
			objectNotification.setNotifications(notifications);
			objectNotification.setNumberOfNotification(listUpdate.stream().filter(x->x.getRead_flg()==0).collect(Collectors.toList()).size());
			request.setAttribute("objectNotification", objectNotification);
		}
		return true;
	}

}