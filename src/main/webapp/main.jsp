<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title> 
<%if(session.getAttribute("currentMemberShip") == null) {
	response.sendRedirect("login.jsp");
	}
%> 
</head>  
<body class="easyui-layout">  
<div region="north" style="height: 78px; background-color: #D9EEF5">
  	<table style="padding: 5px" width="100%">
  		<tr>
  			<td width="50%">
  				<img alt="logo" src="">
  			</td>
  			<td valign="bottom" align="right" width="50%">
  				<font size="3">&nbsp;&nbsp; <strong>欢迎: </strong>${currentMemberShip.user.id }(${currentMemberShip.user.firstName }${currentMemberShip.user.lastName }) 「${currentMemberShip.group.name }」</font>
  			</td>
  		</tr>
  	</table>
</div>

<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px">欢迎使用</div>
		</div>
	</div>
</div>

<div region="west" title="导航菜单" split="true" style="width: 200px">
	<div class="easyui-accordion" data-options="fit:true, border:false">
		 <c:if test="${currentMemberShip.group.name=='管理员' }">
			<div title="基础数据管理" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">
				<a href="javascript:openTab('用户管理','userManage.jsp','icon-user')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user'" style="width: 150px">用户管理</a>
				<a href="javascript:openTab('角色管理','groupManage.jsp','icon-role')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-role'" style="width: 150px">角色管理</a>
				<a href="javascript:openTab('用户权限管理','authManage.jsp','icon-power')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-power'" style="width: 150px">用户权限管理</a>
			</div>
			<div title="流程管理"  data-options="iconCls:'icon-flow'" style="padding:10px;">
				<a href="javascript:openTab('流程部署管理','deployManage.jsp','icon-deploy')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-deploy'" style="width: 150px;">流程部署管理</a>
				<a href="javascript:openTab('流程定义管理','processDefinitionManage.jsp','icon-definition')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-definition'" style="width: 150px;">流程定义管理</a>
			</div>
	    </c:if>
		<div title="任务管理" data-options="iconCls:'icon-task'" style="padding:10px">
			<a href="javascript:openTab('待办任务管理','daibanManage.jsp','icon-daiban')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-daiban'" style="width: 150px;">待办任务管理</a>
			<a href="javascript:openTab('已办任务管理','yibanManage.jsp','icon-yiban')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-yiban'" style="width: 150px;">已办任务管理</a>
			<a href="javascript:openTab('历史任务管理','lishiManage.jsp','icon-lishi')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-lishi'" style="width: 150px;">历史任务管理</a>
		</div>
		<c:if test="${currentMemberShip.group.name=='学生' }">
			<div title="业务管理"  data-options="iconCls:'icon-yewu'" style="padding:10px">
				<a href="javascript:openTab('请假申请','leaveManage.jsp','icon-apply')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-apply'" style="width: 150px">请假申请</a>
			</div>
		</c:if>
		<div title="系统管理"  data-options="iconCls:'icon-system'" style="padding:10px">
			<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
		</div>
	</div>
</div>

<div region="south" style="height: 26px; padding: 5px" align="center">
	版权信息
</div>

<script type="text/javascript">
	function openTab(text, url, iconCls){
		if($('#tabs').tabs('exists', text)){
			$('#tabs').tabs('select', text);
		} else {
			var content = "<iframe frameborder=0 scrolling='auto' style='width:100%; height=100%' src='${path}/page/"+url+"'></iframe>"
			$('#tabs').tabs('add', {
				title : text, 
				iconCls : iconCls,
				closable : true,
				content : content
			});
		}
	}
</script>

</body>  
</html>  
