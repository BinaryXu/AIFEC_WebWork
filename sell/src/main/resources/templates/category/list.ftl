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
                            <th>类目ID</th>
                            <th>名称</th>
                            <th>类目类型</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productCategoryList as productCategory>
                        <tr>
                            <td>${productCategory.categoryId}</td>
                            <td>${productCategory.categoryName}</td>
                            <td>${(productCategory.categoryType)}</td>
                            <td>${productCategory.createTime}</td>
                            <td>${(productCategory.updateTime)!''}</td>
                            <td><a href="/sell/category/index?categoryId=${productCategory.categoryId}">修改</a></td>
                            <td><a href="/sell/category/delete?categoryId=${productCategory.categoryId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>