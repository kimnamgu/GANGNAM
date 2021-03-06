<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>


<script type="text/javascript">
		var id_list;
		var dgb = "${param.USER_STATUS}";
		
		$(document).ready(function(){
			fn_idApproveList(1);
			
			fn_initial_setting();
			
			$("#approve").on("click", function(e){ //글쓰기 버튼
				id_list = "";
				e.preventDefault();				
				fn_updateIdApprove();
			});
			
			$("#list").on("click", function(e){ //리스트				
				e.preventDefault();				
				fn_mainSaupList();
			});
			
			$("#all").on("click", function(e){ 
				if($("input[name=chkBox]:checkbox").is(":checked"))
					$("input[name=chkBox]:checkbox").attr("checked", false);
				else					
					$("input[name=chkBox]:checkbox").attr("checked", true);
			});
			
			
			$("#srch").on("click", function(e){ //조회버튼
				
				e.preventDefault();				
				fn_idApproveList();
			});
			
			$("input:radio[name=USER_STATUS]").click(function(){
				dgb = "";
				$("#PAGE_INDEX").val("1");
				fn_idApproveList();
			});
			
			
			$("#DISP_CNT").change(function(){				
				$("#RECORD_COUNT").val($("#DISP_CNT option:selected").val());
				$("#PAGE_INDEX").val("1");
				fn_idApproveList();
			});
		
		});
		
		function fn_initial_setting(){
			
			if(dgb == "" || dgb == null)
				$('input:radio[value=Z]').prop("checked", true);
			else				
				$('input:radio[value=' + dgb + ']').prop("checked", true);
			
		}
		
		function fn_updateIdApprove(){
			var comSubmit = new ComSubmit("form1");

			//var tcnt = $("#chkid:checked").length;
			
			$('#chkid:checked').each(function() { 
		        if(id_list != "")
		        	id_list = id_list + " , " + "'" + $(this).val() + "'";
		        else	
					id_list = "'" +  $(this).val() + "'";
		    });
	
			if(id_list.length < 1)
			{
				alert('먼저 해당 ID의 체크박스를 선택해 주시기 바랍니다.');
				return;
			}
			
			$('#ID_LIST').val(id_list); 
			
			if(confirm('선택한 아이디를 사용승인 하시겠습니까?'))
			{
				comSubmit.setUrl("<c:url value='/common/updateIdApprove.do' />");
				comSubmit.submit();
			}
		}
		
		
		
		function fn_idApproveList(pageNo){			
			var comAjax = new ComAjax();
			var user_right =  $("#USER_RIGHT").val();
			var tmp = "";
			
			comAjax.setUrl("<c:url value='/common/selectIdApproveList.do' />");
			comAjax.setCallback("fn_selectIdApproveListCallback");
			comAjax.addParam("PAGE_INDEX",$("#PAGE_INDEX").val());
			comAjax.addParam("PAGE_ROW", $("#RECORD_COUNT").val());
			
		
			tmp = $('input:radio[name=USER_STATUS]:checked').val();
			
			if(dgb == "" || dgb == null)
			{
				if( typeof tmp == 'undefined')
					tmp = "Z";
			}
			else{
				tmp = dgb;
			}
			
			comAjax.addParam("USER_STATUS", tmp);		
			comAjax.ajax();
		}
		
		
		function fn_selectIdApproveListCallback(data){			
			var total = data.TOTAL;
			var body = $("#mytable");
			var recordcnt = $("#RECORD_COUNT").val();
			
			$("#tcnt").text(comma(total));
			
			body.empty();
			if(total == 0){
				var str = "<tr>" + 
								"<td colspan=\"8\">조회된 결과가 없습니다.</td>" +
						  "</tr>";
						  
				body.append(str);
			}
			else{
				var params = {
					divId : "PAGE_NAVI",
					pageIndex : "PAGE_INDEX",
					totalCount : total,
					recordCount : recordcnt,
					eventName : "fn_idApproveList"
				};
				gfn_renderPaging(params);
				
				var str = "";
							
				$.each(data.list, function(key, value){
					
				
												
							str += "<tr>" +
					                   "<td>" + value.RNUM + "</td>" +
					                   "<td><input type='checkbox' id='chkid' name='chkBox' value='" +  value.USER_ID + "'></td>" +
					                   "<td class=\"list_center\"'>" + 
									   "<a href='#this' name='title' >" + NvlStr(value.USER_ID) + "</a>" + 									  
									   "<input type='hidden' name='title' id='UID' value=" + value.USER_ID + ">" + 
									   "</td>" +					                   
					                   "<td>" + value.USER_NAME + "</td>" + 
					                   "<td>" + value.DEPT_NAME + "</td>" + 
					                   "<td>" + value.CLASS_NAME  + "</td>" +
									   "<td>" + NvlStr(value.USER_TEL) +  "</td>" +
									   "<td>" + NvlStr(value.APPLY_REASON) +  "</td>" +									   
									   "</tr>";
														
				});
				body.append(str);
				
				$("a[name='title']").on("click", function(e){ //제목 
					e.preventDefault();
					fn_idApproveListDetail($(this));
				});
			}
		}
		
		
		
		function fn_mainSaupList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/daejang/mainSaupList.do' />");			
			comSubmit.submit();			
		}	
			
		function fn_idApproveListDetail(obj){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/common/idApproveListDetail.do' />");
			comSubmit.addParam("USER_ID", obj.parent().find("#UID").val());
			comSubmit.addParam("USER_STATUS", $('input:radio[name=USER_STATUS]:checked').val());
			comSubmit.submit();
		}
	</script>
</head>
<body bgcolor="#FFFFFF">
<form id="form1" name="form1">
<input type="hidden" id="USER_ID" name="USER_ID" value="${sessionScope.userinfo.userId}">
<input type="hidden" id="DEPT_ID" name="DEPT_ID" value="${sessionScope.userinfo.deptId}">
<input type="hidden" id="USER_RIGHT" name="USER_RIGHT" value="${sessionScope.userinfo.userright}">
<input type="hidden" id="ID_LIST" name="ID_LIST" value="">


<!-- 타이틀 -->
<div style="background-color: #4272d7;color:#ffffff; margin-bottom:10px; width: 100%;">
	<img src="/service/images/popup_title.gif" align="absmiddle">사용자 승인
</div>

<table  border="0" cellspacing="0" cellpadding="0" class="bodyframe_full">
  <tr>
    <td class="pupup_frame" style="padding-right:12px">	
    
    
   <table width="720" border="0" cellspacing="1" cellpadding="0" class="tbl" id="divMemoMainBody1">
	  <tr>
           <td width="100" class="tbl_field">구분</td>
           <td colspan="3" width="660" class="tbl_list_left">           	                 
               <input type="radio" id="USER_STATUS1" name="USER_STATUS" value="Z" class="input" onfocus="this.blur()"> 미승인 &nbsp; <input type="radio" id="USER_STATUS2" name="USER_STATUS" value="0" class="input" onfocus="this.blur()"> 승인                	                	               
           </td>
      </tr>	 
      </table>
    
    
    
 <!-- -------------- 버튼 시작 --------------  -->
   <table width="100%" height="40" border="0" cellspacing="0" cellpadding="0" id="divMemoMainBody2">
     <tr>       
       <td align="left"><b>Total : <span id="tcnt"></span></b> &nbsp;&nbsp; 
       <select id="DISP_CNT" name="DISP_CNT" class="input">    
       <option value="10">10</option>
       <option value="20">20</option>
       <option value="30">30</option>
       <option value="40">40</option>
       <option value="50">50</option>
   	   </select>
       </td>       
       <td align="right">
       <table border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td>
				<a href="/service/docIssueReserveList.do" class="btn btn-info"><i class="fa fa-mail-reply-all"></i> 사용자화면</a>
           </td>
           <td>
				&nbsp;&nbsp;<a href="#this" class="btn btn-success" id="approve"><i class="fa fa-chevron-down"></i> 승인</a>            
           </td>
           <!-- 
           <td>
           <table border="0" cellspacing="0" cellpadding="0" class="btn" id="tblAddMemo21" style="filter:none;">
             <tr>
               <td><img src="/service/images/btn_type0_head.gif"></td>
               <td background="/service/images/btn_type0_bg.gif" class="btn_type1" nowrap><a href="#this" class="btn" id="srch"><font color="white">조회</font></a></td>
               <td><img src="/service/images/btn_type0_end.gif"></td>
             </tr>
           </table>                
           </td>
           -->		
         </tr>
       </table>
       </td>
     </tr>
   </table>
   <!-- -------------- 버튼 끝 ---------------->	
    	
		<table class="tbl1">
			<thead>			
			<tr>
				<th>번호</th>
				<th><div id="all"><u>all</u></div></th>
				<th>아이디</th>
				<th>이름</th>
				<th>부서</th>
				<th>직급</th>
				<th>전화번호</th>
				<th>신청사유</th>			
			</tr>
			</thead>
		<tbody id='mytable'>			
		</tbody>
		</table>	
	</td>
	</tr>
</table>
</form>
	<div id="PAGE_NAVI" align='center'></div>
	<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX"/>
	<input type="hidden" id="RECORD_COUNT" name="RECORD_COUNT" value="10"/>	
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
		
</body>
</html>