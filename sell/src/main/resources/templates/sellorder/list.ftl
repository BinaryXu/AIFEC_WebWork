<html>
    <head>
        <meta charset="utf-8">
        <title>卖家端订单信息</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
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
                            <td><a href="/sell/sell/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <td>
                                <#if orderDTO.orderStatus == 0>
                                  <a href="/sell/sell/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                                <a href="/sell/sell/order/list?page=${currPage-1}&size=${size}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..2 as index>
                           <#if currPage == index>
                                <li class="disabled">
                                    <a href="/sell/sell/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/sell/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                             <li>
                                 <a href="/sell/sell/order/list?page=${currPage+1}&size=${size}">下一页</a>
                             </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>