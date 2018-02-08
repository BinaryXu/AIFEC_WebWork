<html>
  <#include "../common/header.ftl">
    <body>
        <div class="container-fluid row">
        <#--边栏-->
        <#include "../common/leftMenu.ftl">
            <div class="col-xs-12 col-md-8">
            <#--主要内容-->
                <div class="container pull-right">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>订单ID</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
							<#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.buyerAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                                <td>${orderDTO.getPayStatusEnum().getMsg()}</td>
                                <td>${orderDTO.createTime}</td>
                                <td>
                                    <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                                </td>
                                <td>
										<#if orderDTO.orderStatus==0>
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                        </#if>
                                </td>
                            </tr>
                            </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
						<#if currPage lte 1>
							<li class="disabled">
                                <a href="#">上一页</a>
                            </li>
                        <#else>
								<li>
                                    <a href="/sell/seller/order/list?page=${currPage-1}&size=${size}">上一页</a>
                                </li>
                        </#if>

						<#list 1..2 as index>
                            <#if currPage==index>
								<li class="disabled">
                                    <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            <#else>
									<li>
                                        <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                    </li>
                            </#if>
                        </#list>
						<#if currPage gte orderDTOPage.getTotalPages()>
							<li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
								<li>
                                    <a href="/sell/seller/order/list?page=${currPage+1}&size=${size}">下一页</a>
                                </li>
                        </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">
                            提示
                        </h4>
                    </div>
                    <div class="modal-body">
                        您有新的订单请注意查收！！！
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="javascript:document.getElementById('notice').pause()" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" onclick="location.reload()" class="btn btn-primary">查看详情</button>
                    </div>
                </div>

            </div>
        </div>
        <audio id="notice" loop="loop">
            <source src="/sell/mp3/123.mp3" type="audio/mpeg" />
        </audio>
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            var websocket = null;
            if('WebSocket' in window){
                websocket = new WebSocket('ws://127.0.0.1:8081/sell/webSocket');
            }else{
                alert('该浏览器不支持webSocket消息推送')
            }
            websocket.onopen = function (event) {
                console.log('连接成功！！！');
            }
            websocket.onclose = function (event) {
                console.log('连接关闭！！！');
            }
            websocket.onmessage = function (ev) {
                console.log('你有新的消息：'+ev.data)
                //弹出框-播放音乐
                $('#myModal').modal('show');
                document.getElementById('notice').play();
            }
            websocket.onerror = function (ev) {
                console.log('websocket通信发生异常！！！')
            }
            window.onbeforeunload = function(){
                websocket.close();
            }

        </script>
    </body>
</html>