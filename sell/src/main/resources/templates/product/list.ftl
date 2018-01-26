<html>
    <head>
        <meta charset="utf-8">
        <title>卖家后台管理系统</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>商品ID</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td>${productInfo.productIcon}</td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime}</td>
                            <td><a href="/sell/product/save?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.productStatus == 0>
                                  <a href="/sell/product/off?productId=${productInfo.productId}">下架</a>
                                  <#else>
                                  <a href="/sell/product/off?productId=${productInfo.productId}">上架</a>
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
                                <a href="/sell/product/list?page=${currPage-1}&size=${size}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..productInfoPage.getTotalPages() as index>
                           <#if currPage == index>
                                <li class="disabled">
                                    <a href="/sell/product/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/sell/product/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currPage gte productInfoPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else>
                             <li>
                                 <a href="/sell/product/list?page=${currPage+1}&size=${size}">下一页</a>
                             </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>