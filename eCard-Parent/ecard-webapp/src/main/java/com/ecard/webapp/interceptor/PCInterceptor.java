package com.ecard.webapp.interceptor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ecard.core.model.CardInfo;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.HomeService;
import com.ecard.core.service.NotificationInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserNotifyService;
import com.ecard.core.vo.NotificationList;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.NotificationOfUserVO;
import com.ecard.webapp.vo.ObjectNotification;
import com.ecard.webapp.vo.UserSearchVO;

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
	
	@Autowired
    private HomeService homeService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (!ajax) {
			try{
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
				/*List<NotificationList> listUpdate = notificationInfoService.listAllNofiticationUser(ecardUser.getUserId());
				BigInteger noticeCnt = homeService.countNotificationCard(ecardUser.getUserId());
				List<NotificationOfUserVO> notifications=new ArrayList<NotificationOfUserVO>();
				CardInfo card=null;
				for(NotificationList item:listUpdate){
					NotificationOfUserVO notification=new NotificationOfUserVO();
					notification.setContents(item.getNotify_message());
					notification.setDate(item.getNotice_date());
					notification.setId(item.getNotice_id());
					notification.setNoticeType(item.getNotice_type());
					notification.setCardId(item.getCard_id());
					try{
						card=cardInfoService.getCardInfoDetail(item.getCard_id());	
					}catch (Exception e){
						card=null;
					}
					
					notification.setImage(card!=null?card.getImageFile():"");
					notification.setRead_flg(item.getRead_flg());
					notifications.add(notification);
				}*/
					
				//notifications=UploadFileUtil.getImageFileFromSCPForNotification(notifications, scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
				ObjectNotification objectNotification=new ObjectNotification();
				//objectNotification.setNotifications(notifications);
				objectNotification.setNumberOfNotification(homeService.countNotificationCard(ecardUser.getUserId()).intValue());
				
				//remove se/ssion if isDetail is false
				HttpSession session= request.getSession();
				if (session.getAttribute("searchDetail") != null) {
					UserSearchVO u=(UserSearchVO)request.getSession().getAttribute("searchDetail");
					if(!request.getServletPath().contains("/user/home") && !request.getServletPath().contains("/user/card/details/")){
						session.setAttribute("searchDetail", null);
					}
					
					if(request.getServletPath().contains("/user/home") && !u.isDetail()){
						session.setAttribute("searchDetail", null);
					}
		        }
				request.setAttribute("objectNotification", objectNotification);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("=======================PCInterceptor fail=========================: ");
				return false;
			}
			
		}
		return true;
	}

}