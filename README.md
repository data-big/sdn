# 特宽SDN项目

> 基于Java实现。

## 项目内容

- [项目简介](项目简介)
- [项目架构](项目架构)

----

## 项目简介

### 项目起因

本项目主要服务于特宽SDN底层架构。

### 项目框架

`sdn`: 主工程

`sdn-api`: 接口服务模块

`sdn-cache`:缓存服务模块

`sdn-etl`:基础数据采集服务模块

`sdn-model`:数据模型模块

`sdn-util`:工具模块

`sdn-common`:通用组件

## 项目架构

1. 接口服务：采用SpringMVC+Spring+Mybatis框架

2. MYSQL：存储VPN卡数据和VPN用户数据

3. HBase：存储VPN用户上网地理位置数据

4. Redis： 缓存VPN用户上网记录数据，供ElasticSearch建索引。

## 开发人员

姓名：徐冉

Q  Q：814258

手机：13696797999