/*
 * CompanyController
 */
package com.ecard.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.ecard.core.model.GroupCompanyDepartment;
import com.ecard.core.model.GroupCompanyDepartmentId;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.service.CompanyInfoService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.GroupDepartmentVO;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.security.RoleType;
import com.ecard.webapp.util.CsvFileReader;
import com.ecard.webapp.util.DateTimeUtil;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.vo.CompanyDisplayVO;
import com.ecard.webapp.vo.Department;
import com.ecard.webapp.vo.GroupCompanyDepartmentVO;

@Controller
@RequestMapping("/companies/*")
public class CompanyController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Autowired
	CompanyInfoService companyInfoService;

	@Autowired
	GroupCompanyInfoService groupCompanyInfoService;

	@Autowired
	UserInfoService userInfoService;
	
    /*
     * Return screen list company.
     * If user login have role Admin then get all company.
     * Else get list company have the same groupcompanyId user login. 
     */
	@RequestMapping("list")
	public ModelAndView list(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<GroupCompanyInfo> companyInfos = null;
		List<CompanyDisplayVO> lstcompanyDisplayVO = new ArrayList<>();
		if (roles.contains(RoleType.ROLE_ADMIN.name())) {
			companyInfos = groupCompanyInfoService.getListCompany();
		} else {
			companyInfos = groupCompanyInfoService.getListCompanyOfUser(ecardUser.getGroupCompanyId());
		}
		CompanyDisplayVO companyDisplayVO = null;
		if (companyInfos != null) {
			for (GroupCompanyInfo groupCompanyInfo : companyInfos) {
				String groupCompanyInfoIndex = StringUtilsHelper.convertIdToString(groupCompanyInfo.getGroupCompanyId());
				companyDisplayVO = new CompanyDisplayVO(groupCompanyInfo.getGroupCompanyId(), groupCompanyInfoIndex,
						groupCompanyInfo.getGroupCompanyName(), groupCompanyInfo.getGroupCompanyNameKana(),
						groupCompanyInfo.getCreateDate(), groupCompanyInfo.getUpdateDate(),
						groupCompanyInfo.getOperaterId());
				lstcompanyDisplayVO.add(companyDisplayVO);
			}
		}

		return new ModelAndView("companylist", "lstcompanyDisplayVO", lstcompanyDisplayVO);
	}

	/*
	 * Return screen register company.
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView addView() {
		return new ModelAndView("addcompany", "companylistVO", null);
	}
    
	/*
	 * Save edit company.
	 */
	@RequestMapping(value = "editCompany", method = RequestMethod.POST)
	public ModelAndView add(@RequestParam(value = "groupCompanyId") int groupCompanyId,
			@RequestParam(value = "groupCompanyName") String groupCompanyName,
			@RequestParam(value = "createDate") String createDate) {
		GroupCompanyInfo groupCompanyInfo = new GroupCompanyInfo();
		groupCompanyInfo = groupCompanyInfoService.getCompanyById(groupCompanyId);
		groupCompanyInfo.setGroupCompanyName(groupCompanyName);
		groupCompanyInfo.setGroupCompanyNameKana(groupCompanyName);
		groupCompanyInfo.setCreateDate(DateTimeUtil.parseDate(createDate));
		groupCompanyInfoService.update(groupCompanyInfo);
		return new ModelAndView("redirect:list");
	}

	/*
	 * Register company.
	 */
	@RequestMapping(value = "registerCompany", method = RequestMethod.POST)
	public ModelAndView registerCompany(@RequestParam(value = "groupCompanyName") String groupCompanyName,
			@RequestParam(value = "createDate") String createDate) {
		GroupCompanyInfo groupCompanyInfo = new GroupCompanyInfo();
		groupCompanyInfo.setGroupCompanyName(groupCompanyName);
		groupCompanyInfo.setGroupCompanyNameKana(groupCompanyName);
		groupCompanyInfo.setOperaterId(0);
		groupCompanyInfo.setUpdateDate(DateTimeUtil.parseDate(createDate));
		groupCompanyInfo.setCreateDate(DateTimeUtil.parseDate(createDate));
		groupCompanyInfoService.add(groupCompanyInfo);
		return new ModelAndView("redirect:list");
	}
    /*
     * Delete company.
     */
	@RequestMapping("delete")
	@ResponseBody
	public int delete(@RequestParam(value = "companyID") int companyID) {
		try {
			groupCompanyInfoService.delete(companyID);
			List<GroupDepartmentVO> groupCompanyDepartment = null;
			groupCompanyDepartment = groupCompanyInfoService.getCompanyDepartmentById(companyID);
			if (groupCompanyDepartment != null) {
				groupCompanyInfoService.deleteDepartment(companyID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	@RequestMapping(value = "confirm/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView confirm(@PathVariable("id") int id) {
		GroupCompanyInfo groupCompanyInfo = null;
		groupCompanyInfo = groupCompanyInfoService.getCompanyById(id);
		List<GroupDepartmentVO> groupCompanyDepartment = null;
		groupCompanyDepartment = groupCompanyInfoService.getCompanyDepartmentById(id);
		GroupCompanyDepartmentVO groupCompanyDepartmentVO = new GroupCompanyDepartmentVO(groupCompanyInfo,
				groupCompanyDepartment);
		return new ModelAndView("confirmcompany", "groupCompanyDepartmentVO", groupCompanyDepartmentVO);
	}

	@RequestMapping(value = "edit/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") int id) {
		GroupCompanyInfo groupCompanyInfo = null;
		groupCompanyInfo = groupCompanyInfoService.getCompanyById(id);
		List<GroupDepartmentVO> groupCompanyDepartment = null;
		groupCompanyDepartment = groupCompanyInfoService.getCompanyDepartmentById(id);
		GroupCompanyDepartmentVO groupCompanyDepartmentVO = new GroupCompanyDepartmentVO(groupCompanyInfo,
				groupCompanyDepartment);
		return new ModelAndView("editcompany", "groupCompanyDepartmentVO", groupCompanyDepartmentVO);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public int save(@RequestParam(value = "groupCompanyId") String groupCompanyId,
			@RequestParam(value = "groupCompanyDepartments[]") ArrayList<String> groupCompanyDepartments,
			@RequestParam(value = "groupCompanyName") String groupCompanyName) {
		int companyId = Integer.parseInt(groupCompanyId);
		GroupCompanyInfo groupCompanyInfo = null;
		groupCompanyInfo = groupCompanyInfoService.getCompanyById(companyId);
		groupCompanyInfo.setGroupCompanyName(groupCompanyName);
		groupCompanyInfo.setGroupCompanyNameKana(groupCompanyName);
		groupCompanyInfoService.update(groupCompanyInfo);
		List<GroupDepartmentVO> groupCompanyDepartment = null;
		groupCompanyDepartment = groupCompanyInfoService.getCompanyDepartmentById(companyId);
		if (groupCompanyDepartment != null) {
			groupCompanyInfoService.deleteDepartment(companyId);
		}
		if (groupCompanyDepartments.size() > 0) {
			for (String department : groupCompanyDepartments) {
				GroupCompanyDepartmentId groupCompanyDepartmentId = new GroupCompanyDepartmentId();
				groupCompanyDepartmentId.setGroupCompanyDepartmentName(department);
				groupCompanyDepartmentId.setGroupCompanyId(companyId);

				GroupCompanyDepartment groupDepartment = new GroupCompanyDepartment();
				groupDepartment.setGroupCompanyInfo(groupCompanyInfo);
				groupDepartment.setId(groupCompanyDepartmentId);

				groupCompanyInfoService.addDepartment(groupDepartment);

			}
			return 0;
		}
		return 1;

	}

	@RequestMapping(value = "adddepartment/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView addDepartment(@PathVariable("id") int id) {
		GroupCompanyInfo groupCompanyInfo = null;
		groupCompanyInfo = groupCompanyInfoService.getCompanyById(id);
		List<GroupDepartmentVO> groupCompanyDepartment = groupCompanyInfoService.getCompanyDepartmentById(id);
		GroupCompanyDepartmentVO groupCompanyDepartmentVO = new GroupCompanyDepartmentVO(groupCompanyInfo,
				groupCompanyDepartment);
		return new ModelAndView("add_department", "groupCompanyDepartmentVO", groupCompanyDepartmentVO);
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file,
			@RequestParam("groupCompanyId") String groupCompanyId, @RequestParam("name") String name) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("user.dir");
				File dir = new File(rootPath + File.separator + "csv");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				String filename = serverFile.getPath();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				Department department = new Department();
				final CellProcessor[] processors = getProcessorsDepartment();
				// read csv
				List<Department> listDepartment = (List<Department>) (Object) CsvFileReader.readCsv(filename, department, processors);

				for (Department depart : listDepartment) {
					GroupCompanyDepartmentId groupCompanyDepartmentId = new GroupCompanyDepartmentId();
					groupCompanyDepartmentId.setGroupCompanyDepartmentName(depart.getDepartment());
					groupCompanyDepartmentId.setGroupCompanyId(Integer.parseInt(groupCompanyId));

					GroupCompanyDepartment groupDepartment = new GroupCompanyDepartment();
					groupDepartment.setId(groupCompanyDepartmentId);

					groupCompanyInfoService.addDepartment(groupDepartment);
				}
				logger.info("Server File Location=" + serverFile.getAbsolutePath());
				serverFile.delete();
				return new ModelAndView("redirect:list");
			} catch (Exception e) {

				return new ModelAndView("redirect:adddepartment/" + groupCompanyId);
			}
		} else {

			return new ModelAndView("redirect:adddepartment/" + groupCompanyId);
		}
	}

	private static CellProcessor[] getProcessorsDepartment() {
		return new CellProcessor[] { new NotNull() };
	}
}
