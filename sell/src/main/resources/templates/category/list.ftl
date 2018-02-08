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
                            <td><a href="/sell/seller/category/index?categoryId=${productCategory.categoryId}">修改</a></td>
                            <td><a href="/sell/seller/category/delete?categoryId=${productCategory.categoryId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>