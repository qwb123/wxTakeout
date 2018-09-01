<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productInfoPage.list as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.categoryType}</td>
                            <td>${productInfo.createTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                            <td>${productInfo.updateTime?string('yyyy-MM-dd hh:mm:ss')}</td>
                            <td><a href="/sell/back/product/update?productId=${productInfo.productId}">修改</a></td>
                            <td>
                                <#if productInfo.getProductStatusEnum().code== 0>
                                    <a href="/sell/back/product/off_sale?productId=${productInfo.productId}">下架</a>
                                <#else>
                                    <a href="/sell/back/product/on_sale?productId=${productInfo.productId}">上架</a>
                                </#if>
                            </td>

                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if productInfoPage.pageNum lte 1>
                            <li class="disabled">
                                <a>上一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/sell/back/product/list?pageNum=${productInfoPage.pageNum-1}&pageSize=${productInfoPage.pageSize}"> 上一页</a>
                            </li>
                        </#if>

                        <#list   1..productInfoPage.pages as index >
                            <#if productInfoPage.pageNum==index>
                                <li class="disabled">
                                    <a>${index}</a>
                                </li>
                            <#else >
                                <li>
                                    <a href="/sell/back/product/list?pageNum=${index}&pageSize=${productInfoPage.pageSize}">${index}</a>
                                </li>
                            </#if>

                        </#list>

                        <#if productInfoPage.pageNum gte productInfoPage.total>
                            <li class="disabled">
                                <a>下一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/sell/back/product/list?pageNum=${productInfoPage.pageNum+1}&pageSize=${productInfoPage.pageSize}"> 下一页</a>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>