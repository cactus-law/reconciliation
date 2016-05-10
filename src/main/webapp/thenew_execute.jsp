<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>方付通对账平台管理系统</title>

    <link rel="stylesheet" type="text/css"
          href="js/jquery/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css"/>

    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" language="javascript"
            src="js/datatables-1.9.4/js/jquery.dataTables.js"></script>

    <style type="text/css" title="currentStyle">
        @import "js/datatables-1.9.4/css/demo_page.css";
        @import "js/datatables-1.9.4/css/demo_table.css";
        @import "js/datatables-1.9.4/css/jquery.dataTables.css";
    </style>

    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            list-style-type: none;
        }

        body {
            font-family: Arial;
            background: #fff url(images/bg.png) no-repeat top left;
        }

        /* navigation */
        .navigation {
            margin: 0px auto;
            font-family: "Trebuchet MS", sans-serif;
            font-size: 24px;
            font-style: normal;
            font-weight: bold;
            letter-spacing: 1.4px;
        }

        .navigation .item {
            position: absolute;
        }

        .navigation .home {
            top: 40px;
            left: 40px;
        }

        .navigation a.icon {
            width: 52px;
            height: 52px;
            position: absolute;
            top: 0px;
            left: 0px;
            cursor: pointer;
        }

        .navigation .home a.icon {
            background: url(images/home.png) no-repeat 0px 0px;
        }

        .navigation .item a.active {
            background-position: 0px -52px;
        }

        .navigation .item img.circle {
            position: absolute;
            top: 0px;
            left: 0px;
            width: 52px;
            height: 52px;
            opacity: 0.1;
        }

        .navigation .item h2 {
            position: absolute;
            width: 147px;
            height: 52px;
            color: #222;
            font-size: 18px;
            top: -50px;
            left: -50px;
            text-indent: 10px;
            line-height: 52px;
            text-shadow: 1px 1px 1px #fff;
            text-transform: uppercase;
        }

        .navigation .item h2.active {
            color: #fff;
            text-shadow: 1px 0px 1px #555;
        }
    </style>

    <style>
        .span {
            float: left;
            text-align: right;
            width: 100px;
            padding-top: 5px;
        }

        .submit {
            font: 100% arial;
            border: 1px solid;
            float: right;
            width: 99px;
            height: 30px;
            padding: 2px 0 3px 0;
            cursor: pointer;
            background: #263C56;
            color: #FFF;
        }

        .h1 {
            font-size: 1.3em;
            font-weight: bold;
            color: #4E6CA3;
        }
    </style>
</head>

<body class="easyui-layout">

<div region="west" style="width: 130px; padding: 10px; border: 0;">
    <div class="navigation" id="nav">
        <div class="item home">
            <img src="images/bg_home.png" alt="" width="125" height="125"
                 class="circle"/> <a
                href="javascript:window.location.href='index.html';"
                class="icon"></a>
            <!--
                <h2 style="padding-top:15px;padding-left:45px;">主页</h2>
                -->
        </div>
    </div>
</div>

<div region="center" style="border: 0;">
    <form action="">

        <!-- 分割线
			<div style="width:90%;border-bottom: 1px solid #B0BED9;MARGIN: auto;padding-top:10px; " >
				<h1 class="h1">
					<%
					    String tip=(String)request.getAttribute("tip");
						if (tip==null ||!(tip!=null && tip.equals("select"))){
					%>
					     当前业务-执行清算
					<%
						}else {
							
					%>
						 当前业务-查看历史
					<%		
						}
					%>
				</h1>
			</div>-->
        <!-- 查询条件 -->
        <div
                style="width: 90%; MARGIN: auto; padding: 30px 0 10px 0; height: 10px;">
            <div
                    style="width: 80%; height: auto; float: left; display: inline; font-weight: bold; font-size: 14px; text-align: center;">
                <div
                        style="width: 34%; height: auto; float: left; display: inline; font-weight: bold; font-size: 14px;">
                    <span class="span">对账日期</span> <input class="easyui-datebox"
                                                          value="${cleardate}" id="cleardate" name="cleardate"
                                                          formatter="$.fn.datebox.defaults.formatter" readOnly="true"/>
                </div>
                <div
                        style="width: 33%; height: auto; float: left; display: inline; font-weight: bold; font-size: 14px;">
                    <span class="span">对账类型</span> <select class="easyui-combobox"
                                                           id="rcktype" name="rcktype" editable="false">
                    <%--<option value="RQ"
                            <%if(request.getAttribute("rcktype")!=null&&request.getAttribute("rcktype").equals("RQ")){%>
                            selected <%}%>>日切</option>
                    <option value="FS" <%if(request.getAttribute("rcktype")==null){%>
                            selected
                            <%} else if(request.getAttribute("rcktype").equals("FS")){%>
                            selected <%}%>>非税</option>--%>

                    <option value="RECON"
                            <%if (request.getAttribute("rcktype") == null || request.getAttribute("rcktype").equals("RECON")) {%>
                            selected="selected" <%}%>>自动对账
                    </option>
                </select>
                </div>
                
                <div
                        style="width: 33%; height: auto; float: right; display: inline; font-weight: bold; font-size: 14px;">
                    <span class="span">对账模式</span> <select class="easyui-combobox"
                                                           id="reconType" name="reconType" editable="false">
                    <option value="0"
                            <%if (request.getAttribute("reconType") == null || request.getAttribute("reconType").equals("0")) {%>
                            selected="selected" <%}%>>全部对账执行
                    </option>
                    <option value="1"
                            <%if ("1".equals(request.getAttribute("reconType")) ) {%>
                            selected="selected" <%}%>> 未对账执行
                    </option>
                </select>
                </div>
            </div>
            <div
                    style="width: 20%; float: right; display: inline; font-weight: bold; font-size: 14px;">
                <input type="button" value="查看历史" onclick="execute('history')"
                       class="submit"/> <input type="button" value="执行对账"
                                               onclick="execute('run')" class="submit"/>
            </div>
        </div>
        <!-- 分割线 -->
        <!--
        <div style="width:90%;border-bottom: 1px solid #B0BED9;MARGIN: auto;padding-top:15px; " >
        </div>-->
        <!-- 数据表格 -->


        <!--
            备注：     第一个if表示了是：“执行清算”还是“历史查看”， 进入页面默认为“执行清算”。

                       嵌套if表示了下拉列表选项为：“清算”还是“非税、理财、并账”， 进入页面默认为清算。

                       目的是为了把“老版本”中的两个页面合并成了当前一个页面。
         -->

        <!-- 执行清算 -->

        <!-- 执行清算----属于{非税、理财、并账}下拉列表 -->
        <%-- <input type="hidden" id="rckorder" name="rckorder" value="">
        <table cellpadding="0" width="90%" style="padding-top:8px;" cellspacing="0" border="0" class="display dataTable" >
                <!-- 属于清算表格 -->
                <thead>
                    <tr align="center">
                        <th>步骤</th>
                        <th>模块名称</th>
                        <th>模块状态</th>
                        <th>处理总数</th>
                        <th>处理时间</th>
                        <th>异常信息</th>
                        <th>是否执行</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${detailList}" var="list">
                        <tr class="gradeA" align="center">
                            <td>${list.id.rckorder}</td>
                            <td>${list.flowname}</td>
                            <td>
                                <c:if test="${list.flowstate eq 'N'}">未运行</c:if>
                                <c:if test="${list.flowstate eq 'R'}">正在运行</c:if>
                                <c:if test="${list.flowstate eq 'C'}">运行完成</c:if>
                                <c:if test="${list.flowstate eq 'E'}">运行异常</c:if>
                            </td>
                            <td>${list.dealcount}</td>
                            <td>${list.dealdatetime}</td>
                            <td>
                            <c:if test="${not empty list.exceptiondesc}">
                                    <input type="button" value="异常信息" size="8" title="${list.exceptiondesc}" onclick="showMsg('${list.exceptiondesc}')"/>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${list.isexecute eq 'N' }">
                                    <input type="button" onclick="nonChangeExecute('${cleardate}','${rcktype}','${list.id.rckorder}')" value="不执行" title="点击切换" />
                                </c:if>
                                <c:if test="${list.isexecute eq 'Y' }">
                                    <input type="button" onclick="nonChangeExecute('${cleardate}','${rcktype}','${list.id.rckorder}')" value="执行" title="点击切换" />
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
        </table> --%>
        <!-- 查看历史 -->
        <!-- 执行清算----属于{非税、理财、并账}下拉列表 -->
        <input type="hidden" id="rckorder" name="rckorder" value="">
        <table cellpadding="0" width="90%" style="padding-top: 8px;"
               cellspacing="0" border="0" class="display dataTable">
            <!-- 属于清算表格 -->
            <thead>
            <tr align="center">
                <th>步骤</th>
                <th>模块名称</th>
                <th>模块状态</th>
                <th>处理总数</th>
                <th>处理时间</th>
                <th>异常信息</th>
                <th>是否执行</th>
                <th>单独执行</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${detailList}" var="list">
                <tr class="gradeA" align="center">
                    <td>${list.id.rckorder}</td>
                    <td>${list.flowname}</td>
                    <td><c:if test="${list.flowstate eq 'N'}">未运行</c:if> <c:if
                            test="${list.flowstate eq 'R'}">正在运行</c:if> <c:if
                            test="${list.flowstate eq 'C'}">运行完成</c:if> <c:if
                            test="${list.flowstate eq 'E'}">运行异常</c:if></td>
                    <td>${list.dealcount}</td>
                    <td>${list.dealdatetime}</td>
                    <td><c:if test="${not empty list.exceptiondesc}">
                        <input type="button" value="异常信息" size="8"
                               title="${list.exceptiondesc}"
                               onclick="showMsg('${list.exceptiondesc}')"/>
                    </c:if></td>
                    <td><c:if test="${list.isexecute eq 'N' }">
                        <input type="button"
                               onclick="nonChangeExecute('${cleardate}','${rcktype}','${list.id.rckorder}')"
                               value="不执行" title="点击切换"/>
                    </c:if> <c:if test="${list.isexecute eq 'Y' }">
                        <input type="button"
                               onclick="nonChangeExecute('${cleardate}','${rcktype}','${list.id.rckorder}')"
                               value="执行" title="点击切换"/>
                    </c:if></td>
                    <td><input type="button"
                               onclick="singleExt('${cleardate}','${rcktype}','${list.id.rckorder}')"
                               value="单独执行"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="text" name="tip" id="tip" style="display: none"/>
    </form>
</div>
<script type="text/javascript">
    $(function () {

        $('#nav > div').hover(function () {
            var $this = $(this);
            $this.find('img').stop().animate({
                'width': '125px',
                'height': '125px',
                'top': '-37px',
                'left': '-37px',
                'opacity': '1.0'
            }, 500, 'easeOutBack', function () {
                $(this).parent().find('ul').fadeIn(700);
            });
            $this.find('a:first,h2').addClass('active');
        }, function () {
            var $this = $(this);
            $this.find('ul').fadeOut(500);
            $this.find('img').stop().animate({
                'width': '52px',
                'height': '52px',
                'top': '0px',
                'left': '0px',
                'opacity': '0.1'
            }, 5000, 'easeOutBack');
            $this.find('a:first,h2').removeClass('active');
        });

    });

    $(document).ready(function () {
        $('.dataTable').dataTable({
            bFilter: false,
            bPaginate: false,
            sScrollY: window.document.body.clientHeight * 0.77,
            bJQueryUI: true,
            bSort: false
        });
    });

    $.fn.datebox.defaults.formatter = function (date) {
        var y = date.getFullYear();

        var m = date.getMonth() + 1;
        m = m < 10 ? '0' + m : m;

        var d = date.getDate();
        d = d < 10 ? '0' + d : d;

        return y + '' + m + '' + d;
    }

    function execute(tip) {
        var cleardate = $('#cleardate').datebox('getValue');
        if (cleardate.length != 8) {
            $.messager.alert('消息提示', '<br><center>对账日期不能为空，或格式错误！<br></center>');
            return;
        }

        var rcktypercktype = $('#rcktype').combobox('getValue');
        if (rcktypercktype == '') {
            $.messager.alert('消息提示', '<br><center>对账类型不能为空，或格式错误！<br></center>');
            return;
        }
        if (tip == 'run') {
            document.forms[0].action = "execute_extendsNontaxAction";
            document.forms[0].target = "_self";
            document.forms[0].submit();
        } else {
            document.getElementById("tip").value = "select";
            document.forms[0].action = "execute_extendsNontaxAction";
            document.forms[0].target = "_self";
            document.forms[0].submit();
        }
    }
</script>

<script type="text/javascript">
    function stepExecute(module) {
        document.getElementById('module').value = module;
        document.forms[0].action = "<c:url value='/extendsReconcileAction!setpExecute'/>";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    }
    function changeRedownload(module) {
        document.getElementById('module').value = module;
        document.forms[0].action = "<c:url value='/extendsReconcileAction!changeRedownload'/>";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    }
    function changeExecute(module) {
        document.getElementById('module').value = module;
        document.forms[0].action = "<c:url value='/extendsReconcileAction!changeExecute'/>";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    }

    /***
     * 非清算系列
     */
    function nonChangeExecute(cleadate, rcktype, rckorder) {
        document.getElementById('rckorder').value = rckorder;
        document.forms[0].action = "changeExecute_extendsNontaxAction";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    }
    /***
     * 单独执行
     */
    function singleExt(cleadate, rcktype, rckorder) {
        document.getElementById('rckorder').value = rckorder;
        document.forms[0].action = "singleExt_extendsNontaxAction";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    }

    function showMsg(msg) {
        if (msg != null && msg != '') {
            $.messager.alert('消息提示', msg);
            return;
        } else {
            $.messager.alert('消息提示', '<br><center>没有可用于显示的消息！<br></center>');
            return;
        }
    }

    //如果等于非税，则每次刷新列表
    <%-- <%if(request.getAttribute("cleardate")!=null&&!request.getAttribute("cleardate").equals("")){
    %>
    setTimeout("refreshList()",5000);
    function refreshList() {
        document.getElementById("tip").value="select";
        document.forms[0].action = "execute_extendsNontaxAction";
        document.forms[0].target="_self";
        document.forms[0].submit();
    }
    <%
    }%> --%>
</script>
</body>
</html>