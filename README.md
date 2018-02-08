# AIFEC_WebWork
可能是目前最好最全的StringBootDemo

一.微信点餐系统服务端已经开发完成。

目前存在的问题：
    
    1.引入websocket后，项目进行单元测试时会报javax.websocket.server.ServerContainer not available
    2.扫码登录功能，需要等扫码服务端完成后增加（已经增加完成可以与“扫码登录服务端”连通）
    3.支付功能，暂不支持

二.扫码登录服务端

开发进度
    
    1.已完成展示二维码功能
    2.已完成获取code接口
    3.已完成获取AccessToken接口