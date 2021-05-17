
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<html>
<body>


<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="demoTable">
        <div class="layui-form-item" style="margin-bottom:0px">
            <label class="layui-form-label">服务端名称：   </label>
            <div class="layui-input-block">
                <select  id="proid" name="proid" lay-filter="aihao" lay-search="">
                       <option value="">请选择服务端名称</option>
                     <c:forEach items="${prolist}" var="plist"  varStatus="p">
                         <option value="${plist.id}">${plist.projectname}</option>
                     </c:forEach>


                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">业务线名称：  </label>
            <div class="layui-input-block">
                <input type="text" name="busname" id="busname" lay-verify="title" autocomplete="off" placeholder="请输入业务线名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-sm" data-type="reload" lay-filter="demo1"><i class="layui-icon">&#xe615;</i>搜索 </button>
                <button class="layui-btn layui-btn-sm" lay-event="getCheckData" onclick=window.location.href='/busi/addBusi'>
                    <i class="layui-icon">&#xe608;</i>新增

                </button>
            </div>
        </div>


    </div>







</script>

<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-xs" lay-event="edit" ><i class="layui-icon">&#xe642;</i>编辑</a>


    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"> <i class="layui-icon">&#xe640;</i>删除</a>
</script>


<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script src="js/jquery-3.5.1.js" charset="utf-8"></script>
<script>
    layui.use('table', function(){

        var table = layui.table;

        table.render({

            elem: '#test'
            ,url:'/busi/getBusinessBySearch'
            ,toolbar: '#toolbarDemo' //开启头具栏，并为其绑定左侧模板
            ,title: '业务线表'
            ,page: true
            ,  height : 480
            ,cols: [[

                ,{type: 'checkbox',  fixed: 'left'}
                ,{field:'busid', title:'ID',  width:80, fixed: 'left', sort: true}
                ,{field:'busname', title:'业务线名称', width:450, edit: 'text', sort: true}
                ,{field:'isuse', title:'是否启用', width:300, edit: 'text', sort: true}
                ,{ title:'操作',  toolbar: '#barDemo' ,width:211}
            ]]
                ,parseData: function (res) {
                    if(res.count == 0)
                    {
                        return {
                            'code': 201, //接口状态
                            'msg': '暂无数据', //提示文本
                            'count': 0, //数据长度
                            'data': [] //数据列表，是直接填充进表格中的数组
                        }
                    }
                }
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                console.log(count);
                $(".layui-table-box").find("[data-field='busid']").css("display","none");

                $("[data-field='isuse']").children().each(function(){

                    console.log(this)
                    if($(this).text()=='1'){
                        $(this).text("是")
                    }else if($(this).text()=='0'){
                        $(this).text("否")
                    }
                });


                pageCurr=curr;
            }
            ,id: 'testReload'
        });



        var $ = layui.$, active = {
            reload: function(){
                var proid = $('#proid');
                var busname=$('#busname')

                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        proid:proid.val(),
                        busname:busname.val()
                    }
                }, 'data');
            }
        };

        $('body').on('click', '.demoTable  .layui-form-item .layui-btn',function() {
            console.log(this)
            var id = $(this).data('type');
            console.log(id)
            active[id] ? active[id].call(this) : '';
        });


        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    location.href='/busi/delBusi?busid='+data.busid
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                location.href='/busi/addBusi?busid='+data.busid

            }
        });
    });
</script>
<style>
    .layui-form-label{
        width: 100px;
        padding: 9px 0px;
    }
    .layui-form-item{
        margin-top: 10px;

    }
</style>
</body>
</html>