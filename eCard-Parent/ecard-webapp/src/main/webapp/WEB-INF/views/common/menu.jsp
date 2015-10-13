
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="java.util.List"%>


<div id="left-side" class="col-sm-2">
	<!-- Menu -->
	<div class="row box-shadow bg-white menu-top-header">
		<h4 class="h4-header">
			<fmt:message key="menu.heading" />
		</h4>
	</div>

	<div class="row box-shadow bg-white box-marginTop5">
		<div class="panel-group" id="accordion">
			<!-- below is if role diff admin then display  -->

			<c:if
				test="${pageContext.request.isUserInRole('ROLE_SUPERVISOR') or pageContext.request.isUserInRole('ROLE_LEADER') or pageContext.request.isUserInRole('ROLE_OPERATOR') or pageContext.request.isUserInRole('ROLE_USER')}">
				<c:if test="${pageContext.request.isUserInRole('ROLE_OPERATOR') }">
					<!-- panel 1 -->
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" href="<c:url value='/cards/list'/>">
									<fmt:message key="menu.moveOperator" />
								</a>
							</h4>
						</div>
					</div>
					<!-- end panel 1 -->
					<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
						<!-- panel 4 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/companies/list'/>"> <fmt:message
											key="menu.companyList" />
									</a>
								</h4>
							</div>
						</div>

						<!-- end panel 4 -->


						<!-- panel 5 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/operators/list'/>"> <fmt:message
											key="menu.userList" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 5 -->

						<!-- panel 6 -->
						<%-- <div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/download/list'/>"> <fmt:message
											key="menu.gecognitionDownload" />
									</a>
								</h4>
							</div>
						</div> --%>
						<!-- end panel 6 -->

						<!-- panel 7 -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/backupdatas/backup'/>"> <fmt:message
											key="menu.backup" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 7 -->



						<!-- panel 8 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/logs/listlog'/>" id="confirm_log"> <fmt:message
											key="menu.logConfirm" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 8 -->

						<!-- panel 9 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/contacts/listcontact'/>"> <fmt:message
											key="menu.help" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 9 -->


						<!-- panel 10 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/mails/displayMail'/>"> <fmt:message
											key="menu.sendMail" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 10-->

						<!--  panel 12 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importOperatorByCSV'/>"> <fmt:message
											key="menu.import.operator.CSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 12-->

						<!--  panel 13 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importCardCSV'/>"> <fmt:message
											key="menu.importCSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 13-->
					</c:if>
					<!-- end if 37  -->
					<c:if
						test="${pageContext.request.isUserInRole('ROLE_OPERATOR_MANAGER') }">
						<!-- panel 7 -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/backupdatas/backup'/>"> <fmt:message
											key="menu.backup" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 7 -->

						<!-- panel 8 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/logs/listlog'/>" id="confirm_log"> <fmt:message
											key="menu.logConfirm" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 8 -->

						<!-- panel 9 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/contacts/listcontact'/>"> <fmt:message
											key="menu.help" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 9 -->


						<!-- panel 10 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/mails/displayMail'/>"> <fmt:message
											key="menu.sendMail" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 10-->

						<!--  panel 12 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importOperatorByCSV'/>"> <fmt:message
											key="menu.import.operator.CSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 12-->

						<!--  panel 13 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importCardCSV'/>"> <fmt:message
											key="menu.importCSV" />
									</a>
								</h4>
							</div>
						</div>
					</c:if>
					<!-- end if 153 -->
					<c:if
						test="${pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') }">
						<!-- panel 4 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/companies/list'/>"> <fmt:message
											key="menu.companyList" />
									</a>
								</h4>
							</div>
						</div>

						<!-- end panel 4 -->


						<!-- panel 5 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/operators/list'/>"> <fmt:message
											key="menu.userList" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 5 -->

						<!-- panel 6 -->
						<%-- <div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/download/list'/>"> <fmt:message
											key="menu.gecognitionDownload" />
									</a>
								</h4>
							</div>
						</div> --%>
						<!-- end panel 6 -->



					</c:if>
					<!-- end if 228  -->
				</c:if>
				<!--  end if 24 -->

				<c:if
					test="${pageContext.request.isUserInRole('ROLE_SUPERVISOR') or pageContext.request.isUserInRole('ROLE_LEADER') }">
					<!-- panel 1 -->
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" href="<c:url value='/cards/list'/>">
									<fmt:message key="menu.moveOperator" />
								</a>
							</h4>
						</div>
					</div>
					<!-- end panel 1 -->
					<!-- panel 2 -->
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-parent="#accordion"
									href="<c:url value='/teams/list'/>"> <fmt:message
										key="menu.teamList" />
								</a>
							</h4>
						</div>
					</div>

					<!--end panel 2 -->

					<!-- panel 3 -->
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a class="accordion-toggle" data-parent="#accordion"
									href="<c:url value='/teams/allocation'/>"> <fmt:message
										key="menu.allocation" />
								</a>
							</h4>
						</div>
					</div>


					<!--emd panel 3 -->
					<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
						<!-- panel 4 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/companies/list'/>"> <fmt:message
											key="menu.companyList" />
									</a>
								</h4>
							</div>
						</div>

						<!-- end panel 4 -->


						<!-- panel 5 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/operators/list'/>"> <fmt:message
											key="menu.userList" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 5 -->

						<!-- panel 6 -->
						<%-- <div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/download/list'/>"> <fmt:message
											key="menu.gecognitionDownload" />
									</a>
								</h4>
							</div>
						</div> --%>
						<!-- end panel 6 -->

						<!-- panel 7 -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/backupdatas/backup'/>"> <fmt:message
											key="menu.backup" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 7 -->



						<!-- panel 8 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/logs/listlog'/>" id="confirm_log"> <fmt:message
											key="menu.logConfirm" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 8 -->

						<!-- panel 9 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/contacts/listcontact'/>"> <fmt:message
											key="menu.help" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 9 -->


						<!-- panel 10 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/mails/displayMail'/>"> <fmt:message
											key="menu.sendMail" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 10-->

						<!--  panel 12 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importOperatorByCSV'/>"> <fmt:message
											key="menu.import.operator.CSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 12-->

						<!--  panel 13 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importCardCSV'/>"> <fmt:message
											key="menu.importCSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 13-->
					</c:if>
					<!-- end line 312  -->
					<c:if
						test="${pageContext.request.isUserInRole('ROLE_OPERATOR_MANAGER') }">

						<!-- panel 7 -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/backupdatas/backup'/>"> <fmt:message
											key="menu.backup" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 7 -->



						<!-- panel 8 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/logs/listlog'/>" id="confirm_log"> <fmt:message
											key="menu.logConfirm" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 8 -->

						<!-- panel 9 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/contacts/listcontact'/>"> <fmt:message
											key="menu.help" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 9 -->


						<!-- panel 10 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/mails/displayMail'/>"> <fmt:message
											key="menu.sendMail" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 10-->

						<!--  panel 12 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importOperatorByCSV'/>"> <fmt:message
											key="menu.import.operator.CSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 12-->

						<!--  panel 13 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importCardCSV'/>"> <fmt:message
											key="menu.importCSV" />
									</a>
								</h4>
							</div>
						</div>
					</c:if>
					<!-- end if 428 -->
					<c:if
						test="${pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') }">
						<!-- panel 4 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/companies/list'/>"> <fmt:message
											key="menu.companyList" />
									</a>
								</h4>
							</div>
						</div>

						<!-- end panel 4 -->


						<!-- panel 5 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/operators/list'/>"> <fmt:message
											key="menu.userList" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 5 -->

						<!-- panel 6 -->
						<%-- <div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/download/list'/>"> <fmt:message
											key="menu.gecognitionDownload" />
									</a>
								</h4>
							</div>
						</div> --%>
						<!-- end panel 6 -->

					</c:if>
					<!-- end if 506  -->
				</c:if>
				<!-- end if line 273  -->

				<c:if test="${pageContext.request.isUserInRole('ROLE_USER') }">

					<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
						<!-- panel 4 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/companies/list'/>"> <fmt:message
											key="menu.companyList" />
									</a>
								</h4>
							</div>
						</div>

						<!-- end panel 4 -->


						<!-- panel 5 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/operators/list'/>"> <fmt:message
											key="menu.userList" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 5 -->

						<!-- panel 6 -->
						<%-- <div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/download/list'/>"> <fmt:message
											key="menu.gecognitionDownload" />
									</a>
								</h4>
							</div>
						</div> --%>
						<!-- end panel 6 -->

						<!-- panel 7 -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/backupdatas/backup'/>"> <fmt:message
											key="menu.backup" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 7 -->



						<!-- panel 8 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/logs/listlog'/>" id="confirm_log"> <fmt:message
											key="menu.logConfirm" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 8 -->

						<!-- panel 9 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/contacts/listcontact'/>"> <fmt:message
											key="menu.help" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 9 -->


						<!-- panel 10 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/mails/displayMail'/>"> <fmt:message
											key="menu.sendMail" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 10-->

						<!--  panel 12 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importOperatorByCSV'/>"> <fmt:message
											key="menu.import.operator.CSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 12-->

						<!--  panel 13 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importCardCSV'/>"> <fmt:message
											key="menu.importCSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 13-->
					</c:if>
					<!-- end line 312  -->
					<c:if
						test="${pageContext.request.isUserInRole('ROLE_OPERATOR_MANAGER') }">

						<!-- panel 7 -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/backupdatas/backup'/>"> <fmt:message
											key="menu.backup" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 7 -->



						<!-- panel 8 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/logs/listlog'/>" id="confirm_log"> <fmt:message
											key="menu.logConfirm" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 8 -->

						<!-- panel 9 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/contacts/listcontact'/>"> <fmt:message
											key="menu.help" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 9 -->


						<!-- panel 10 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/mails/displayMail'/>"> <fmt:message
											key="menu.sendMail" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 10-->

						<!--  panel 12 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importOperatorByCSV'/>"> <fmt:message
											key="menu.import.operator.CSV" />
									</a>
								</h4>
							</div>
						</div>
						<!--  end panel 12-->

						<!--  panel 13 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/data/importCardCSV'/>"> <fmt:message
											key="menu.importCSV" />
									</a>
								</h4>
							</div>
						</div>
					</c:if>
					<!-- end if 428 -->
					<c:if
						test="${pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') }">
						<!-- panel 4 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/companies/list'/>"> <fmt:message
											key="menu.companyList" />
									</a>
								</h4>
							</div>
						</div>

						<!-- end panel 4 -->


						<!-- panel 5 -->
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle" data-parent="#accordion"
										href="<c:url value='/operators/list'/>"> <fmt:message
											key="menu.userList" />
									</a>
								</h4>
							</div>
						</div>
						<!-- end panel 5 -->

						<!-- panel 6 -->
						<%-- <div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle"
										href="<c:url value='/download/list'/>"> <fmt:message
											key="menu.gecognitionDownload" />
									</a>
								</h4>
							</div>
						</div> --%>
						<!-- end panel 6 -->

					</c:if>
					<!-- end if 506  -->

				</c:if>
				<!-- end if line 538  -->

			</c:if>

		</div>
	</div>
	<!-- END MENU -->
</div>