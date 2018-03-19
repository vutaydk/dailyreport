<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:if
	test="${not empty messagePopup || not empty sessionScope.messagePopup}">
	<script type="text/javascript">
		$(window).on('load', function() {
			$('#message-modal').modal('show');
		});
	</script>
	<div class="modal fade" id="message-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<c:if test="${not empty sessionScope.messagePopup}">
					<c:out value="${sessionScope.messagePopup}" />
					<c:remove var="messagePopup" scope="session" />
				</c:if>
				<c:out value="${messagePopup}" />
				<i class="fas fa-check text-success"></i>
			</div>
		</div>
	</div>
</c:if>