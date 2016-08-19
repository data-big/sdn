##接口服务启动命令
```
sdn-api/bin/ctl.sh start jettyServer&
默认端口：8900
```
##接口文档
###1.根据用用户真实号查询VPN卡信息
```
接口：http://192.168.6.10:8900/sdn/vpncard/{realNumber}
类型：GET
参数：{realNumber}：用户真实号
请求：http://192.168.6.10:8900/sdn/vpncard/13900000001
响应：
{

    "id": "d6f1d0f2dce8e2e9d446d348c03fb2b9",
    "realNumber": "13900000001",
    "bizIP": "192.168.0.1",
    "stopIP": "192.168.0.2",
    "specialIP": "192.168.0.3",
    "offsetBizIP": "192.168.0.4",
    "offsetStopIP": "192.168.0.5",
    "offsetSpecialIP": "192.168.0.6",
    "invalid": 0,
    "insertDate": "2016-06-08 15:00:00.0"

}
```
###2.根据一组用用户真实号查询VPN卡信息
```
接口：http://192.168.6.10:8900/sdn/vpncards/{realNumbers}
类型：GET
参数：{realNumbers}：13900000001&13900000002&13900000003
请求：http://192.168.6.10:8900/sdn/vpncards/13900000001&13900000002&13900000003
响应：
[

    {
        "id": "87928d0cbe51721245a46f2827416af3",
        "realNumber": "13900000002",
        "bizIP": "192.168.0.7",
        "stopIP": "192.168.0.8",
        "specialIP": "192.168.0.9",
        "offsetBizIP": "192.168.0.10",
        "offsetStopIP": "192.168.0.11",
        "offsetSpecialIP": "192.168.0.12",
        "invalid": 0,
        "insertDate": "2016-06-08 15:00:00.0"
    },
    {
        "id": "d6f1d0f2dce8e2e9d446d348c03fb2b9",
        "realNumber": "13900000001",
        "bizIP": "192.168.0.1",
        "stopIP": "192.168.0.2",
        "specialIP": "192.168.0.3",
        "offsetBizIP": "192.168.0.4",
        "offsetStopIP": "192.168.0.5",
        "offsetSpecialIP": "192.168.0.6",
        "invalid": 0,
        "insertDate": "2016-06-08 15:00:00.0"
    },
    {
        "id": "d7a211925df65e4d791469c713c9a8bd",
        "realNumber": "13900000003",
        "bizIP": "192.168.0.13",
        "stopIP": "192.168.0.14",
        "specialIP": "192.168.0.15",
        "offsetBizIP": "192.168.0.16",
        "offsetStopIP": "192.168.0.17",
        "offsetSpecialIP": "192.168.0.18",
        "invalid": 0,
        "insertDate": "2016-06-08 15:00:00.0"
    }

]
```
###3.分页查询VPN卡信息列表
```
接口：http://192.168.6.10:8900/sdn/vpncards/{pageNo}/{pageSize}
类型：GET
参数：{pageNo}：页码，{pageSize}：页容量
例子：http://192.168.6.10:8900/sdn/vpncards/1/2
响应：
{

    "data": [
        {
            "id": "1",
            "realNumber": "13912345678",
            "bizIP": "192.168.1.1",
            "stopIP": "192.168.1.2",
            "specialIP": "192.168.1.3",
            "offsetBizIP": "192.168.1.4",
            "offsetStopIP": "192.168.1.5",
            "offsetSpecialIP": "192.168.1.6",
            "invalid": 1,
            "insertDate": "2016-05-18 15:39:13.0"
        },
        {
            "id": "2",
            "realNumber": "13088888888",
            "bizIP": "192.168.1.7",
            "stopIP": "192.168.1.8",
            "specialIP": "192.168.1.9",
            "offsetBizIP": "192.168.1.10",
            "offsetStopIP": "192.168.1.11",
            "offsetSpecialIP": "192.168.1.12",
            "invalid": 0,
            "insertDate": "2016-05-19 15:39:13.0"
        }
    ],
    "page": {
        "pageNo": 1,
        "pageSize": 2,
        "totalRecord": 188,
        "totalPage": 94,
        "param": { },
        "searchUrl": "/sdn/vpncards",
        "pageNoDisp": "1|2|3|4|5|6|7|8|0|93|94",
        "paramJson": "{}"
    }

}
```
###4.分页模糊查询并支持按时间区间查询VPN卡信息列表
```
接口：http://192.168.6.10:8900/sdn/vpncards/{pageNo}/{pageSize}/{vpncardJson}
类型：GET
参数：{pageNo}：页码，{pageSize}：页容量，startTime：insertDate开始时间，endTime：insertDate结束时间
例子：http://192.168.6.10:8900/sdn/vpncards/1/2/ {"startTime":"2016-06-08","endTime":"2016-06-09"}
响应：
{

    "data": [
        {
            "id": "c12dddfa0fd3755d5486272ae8bd4a45",
            "realNumber": "13900000075",
            "bizIP": "192.168.0.380",
            "stopIP": "192.168.0.381",
            "specialIP": "192.168.0.382",
            "offsetBizIP": "192.168.0.383",
            "offsetStopIP": "192.168.0.384",
            "offsetSpecialIP": "192.168.0.385",
            "invalid": 0,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "ac036917345b5654bc9a6ee4d36a11f2",
            "realNumber": "13900000009",
            "bizIP": "192.168.0.49",
            "stopIP": "192.168.0.50",
            "specialIP": "192.168.0.51",
            "offsetBizIP": "192.168.0.52",
            "offsetStopIP": "192.168.0.53",
            "offsetSpecialIP": "192.168.0.54",
            "invalid": 1,
            "insertDate": "2016-06-08 15:00:00.0"
        }
    ],
    "page": {
        "pageNo": 1,
        "pageSize": 2,
        "totalRecord": 172,
        "totalPage": 86,
        "param": {
            "startTime": "2016-06-08",
            "endTime": "2016-06-09"
        },
        "searchUrl": "/sdn/vpncards",
        "pageNoDisp": "1|2|3|4|5|6|7|8|0|85|86",
        "paramJson": "{\"startTime\":\"2016-06-08\",\"endTime\":\"2016-06-09\"}"
    }

}
```
###5.根据用用户真实号查询VPN用户信息
```
接口：http://192.168.6.10:8900/sdn/vpnuser/{realNumber}
类型：GET
参数：{realNumber}：用户真实号
请求：http://192.168.6.10:8900/sdn/vpnuser/13900000001
响应：
{

    "id": "020c3e064f3b9ce1fb0390a6d54fa470",
    "realNumber": "13900000001",
    "sponsorNumber": "13888888888",
    "sponsorName": "李四1",
    "sponsorIDType": 1,
    "sponsorIDNumber": "342622198808088888",
    "userNumber": "13900000001",
    "userName": "张三1",
    "userIDType": 1,
    "userIDNumber": "342622199909099999",
    "registerDate": "2016-06-08 15:00:00.0",
    "cancelDate": "2016-07-07 15:00:00.0",
    "registerAgent": "王五1",
    "modifyDate": "2016-06-08 15:00:00.0",
    "invalid": 0

}
```
###6.根据一组用用户真实号查询VPN用户信息
```
接口：http://192.168.6.10:8900/sdn/vpnusers/{realNumbers}
类型：GET
参数：{realNumbers}：13900000001&13900000002&13900000003
请求：http://192.168.6.10:8900/sdn/vpnusers/13900000001&13900000002&13900000003
响应：
[

    {
        "id": "020c3e064f3b9ce1fb0390a6d54fa470",
        "realNumber": "13900000001",
        "sponsorNumber": "13888888888",
        "sponsorName": "李四1",
        "sponsorIDType": 1,
        "sponsorIDNumber": "342622198808088888",
        "userNumber": "13900000001",
        "userName": "张三1",
        "userIDType": 1,
        "userIDNumber": "342622199909099999",
        "registerDate": "2016-06-08 15:00:00.0",
        "cancelDate": "2016-07-07 15:00:00.0",
        "registerAgent": "王五1",
        "modifyDate": "2016-06-08 15:00:00.0",
        "invalid": 0
    },
    {
        "id": "3709c782d3b7412797e1100b565781ea",
        "realNumber": "13900000002",
        "sponsorNumber": "13888888888",
        "sponsorName": "李四2",
        "sponsorIDType": 1,
        "sponsorIDNumber": "342622198808088888",
        "userNumber": "13900000002",
        "userName": "张三2",
        "userIDType": 1,
        "userIDNumber": "342622199909099999",
        "registerDate": "2016-06-08 15:00:00.0",
        "cancelDate": "2016-07-07 15:00:00.0",
        "registerAgent": "王五2",
        "modifyDate": "2016-06-08 15:00:00.0",
        "invalid": 0
    },
    {
        "id": "d8e80a67f4b81f37988b070219bf790a",
        "realNumber": "13900000003",
        "sponsorNumber": "13888888888",
        "sponsorName": "李四3",
        "sponsorIDType": 1,
        "sponsorIDNumber": "342622198808088888",
        "userNumber": "13900000003",
        "userName": "张三3",
        "userIDType": 1,
        "userIDNumber": "342622199909099999",
        "registerDate": "2016-06-08 15:00:00.0",
        "cancelDate": "2016-07-07 15:00:00.0",
        "registerAgent": "王五3",
        "modifyDate": "2016-06-08 15:00:00.0",
        "invalid": 0
    }

]
```
###7.分页查询VPN用户信息列表
```
接口：http://192.168.6.10:8900/sdn/vpnusers/{pageNo}/{pageSize}
类型：GET
参数：{pageNo}：页码，{pageSize}：页容量
例子：http://192.168.6.10:8900/sdn/vpnusers/1/2
响应：
{

    "data": [
        {
            "id": "2",
            "realNumber": "13088888888",
            "sponsorNumber": "13500001111",
            "sponsorName": "李磊",
            "sponsorIDType": 1,
            "sponsorIDNumber": "342622199900001111",
            "userNumber": "13522223333",
            "userName": "韩梅梅",
            "userIDType": 1,
            "userIDNumber": "342622198800001111",
            "registerDate": "2016-05-18 09:49:36.0",
            "cancelDate": "2016-05-25 09:49:43.0",
            "registerAgent": "1",
            "modifyDate": "2016-05-19 09:49:57.0",
            "invalid": 0
        },
        {
            "id": "2694179db5ddea9f54398b098fd6e8bf",
            "realNumber": "13912345678",
            "sponsorNumber": "13500001111",
            "sponsorName": "张三",
            "sponsorIDType": 1,
            "sponsorIDNumber": "342622199900001111",
            "userNumber": "13566666666",
            "userName": "测试",
            "userIDType": 1,
            "userIDNumber": "342622198800001111",
            "registerDate": "2016-05-19 00:00:00.0",
            "cancelDate": "2016-05-26 09:49:43.0",
            "registerAgent": "1",
            "modifyDate": "2016-05-19 09:49:57.0",
            "invalid": 0
        }
    ],
    "page": {
        "pageNo": 1,
        "pageSize": 2,
        "totalRecord": 188,
        "totalPage": 94,
        "param": { },
        "searchUrl": "/sdn/vpnusers",
        "pageNoDisp": "1|2|3|4|5|6|7|8|0|93|94",
        "paramJson": "{}"
    }

}
```
###8.分页模糊查询并支持按时间区间查询VPN用户信息列表
```
接口：http://192.168.6.10:8900/sdn/vpnusers/{pageNo}/{pageSize}/{vpncardJson}
类型：GET
参数：{pageNo}：页码，{pageSize}：页容量，startTime：registerDate开始时间，endTime：registerDate结束时间
例子：http://192.168.6.10:8900/sdn/vpnusers/1/2/ {"startTime":"2016-06-08","endTime":"2016-06-09"}
响应：
{

    "data": [
        {
            "id": "8865eab2cd3748a873935e7e6e4ee3e5",
            "realNumber": "13900000025",
            "sponsorNumber": "13888888888",
            "sponsorName": "李四25",
            "sponsorIDType": 1,
            "sponsorIDNumber": "342622198808088888",
            "userNumber": "13900000025",
            "userName": "张三25",
            "userIDType": 1,
            "userIDNumber": "342622199909099999",
            "registerDate": "2016-06-08 15:00:00.0",
            "cancelDate": "2016-07-07 15:00:00.0",
            "registerAgent": "王五25",
            "modifyDate": "2016-06-08 15:00:00.0",
            "invalid": 0
        },
        {
            "id": "be9ec11048d1238c82b046696d5f6eed",
            "realNumber": "13900000003",
            "sponsorNumber": "13888888888",
            "sponsorName": "李四3",
            "sponsorIDType": 1,
            "sponsorIDNumber": "342622198808088888",
            "userNumber": "13900000003",
            "userName": "张三3",
            "userIDType": 1,
            "userIDNumber": "342622199909099999",
            "registerDate": "2016-06-08 15:00:00.0",
            "cancelDate": "2016-07-07 15:00:00.0",
            "registerAgent": "王五3",
            "modifyDate": "2016-06-08 15:00:00.0",
            "invalid": 1
        }
    ],
    "page": {
        "pageNo": 1,
        "pageSize": 2,
        "totalRecord": 162,
        "totalPage": 81,
        "param": {
            "startTime": "2016-06-08",
            "endTime": "2016-06-09"
        },
        "searchUrl": "/sdn/vpnusers",
        "pageNoDisp": "1|2|3|4|5|6|7|8|0|80|81",
        "paramJson": "{\"startTime\":\"2016-06-08\",\"endTime\":\"2016-06-09\"}"
    }

}
```
###9.根据用户真实号和时间区间查询VPN用户地理位置信息接口
```
接口：http://192.168.6.10:8900/sdn/vpnpostion/{realNumuber}/{start}/{end}
类型：GET
参数：{realNumuber}：用户真实号，{start}：开始时间，{end}：结束时间
例子：http://192.168.6.10:8900/sdn/vpnpostion/13900000001/2016-06-08 00:00:00/2016-06-08 23:59:00
响应：
[

    {
        "realNumber": "13900000001",
        "bizIP": "192.168.0.1",
        "sac": "10631",
        "lac": "54537",
        "time": "2016-06-08 15:00:03",
        "baseStation": {
            "id": "00000000000000000000000000009476",
            "cell": "134818183",
            "sac": "10631",
            "lac": "54537",
            "lat": "31.88885498",
            "lng": "117.26963806",
            "address": "安徽省合肥市庐阳区建工巷"
        }
    },
    {
        "realNumber": "13900000001",
        "bizIP": "192.168.0.1",
        "sac": "20403",
        "lac": "54533",
        "time": "2016-06-08 15:00:02",
        "baseStation": {
            "id": "00000000000000000000000000005607",
            "cell": "134827955",
            "sac": "20403",
            "lac": "54533",
            "lat": "31.87360382",
            "lng": "117.25714111",
            "address": "安徽省合肥市庐阳区亳州路街道长丰路169号"
        }
    },
    {
        "realNumber": "13900000001",
        "bizIP": "192.168.0.1",
        "sac": "10751",
        "lac": "54528",
        "time": "2016-06-08 15:00:01",
        "baseStation": {
            "id": "00000000000000000000000000002084",
            "cell": "10751",
            "sac": "10751",
            "lac": "54528",
            "lat": "31.86564445",
            "lng": "117.27584076",
            "address": "安徽省合肥市庐阳区安庆路街道安庆路144号"
        }
    }

]
```
###10.根据时间分组并按照时间区间统计每日VPN用户总量和增量
```
接口：http://192.168.6.10:8900/sdn/statistics/vpnuser/{start}/{end}
类型：GET
参数：{start}开始时间（yyyy-MM-dd），{end}结束时间（yyyy-MM-dd）
例子：http://192.168.6.10:8900/sdn/statistics/vpnuser/2016-05-18/2016-05-20
响应：
[

    {
        "count": {
            "total": 3,
            "increment": 2
        },
        "date": "2016-05-19"
    },
    {
        "count": {
            "total": 1,
            "increment": 0
        },
        "date": "2016-05-18"
    }

]
```
###11.根据用户证件类型分组并按照时间区间统计VPN用户总量
```
接口：http://192.168.6.10:8900/sdn/statistics/vpnuser/idtype/{start}/{end}
类型：GET
参数：{start}开始时间（yyyy-MM-dd），{end}结束时间（yyyy-MM-dd）
例子：http://192.168.6.10:8900/sdn/statistics/vpnuser/idtype/2016-05-18/2016-05-20
响应：
[

    {
        "name": "1",
        "value": 4
    }

]
```
###12.根据时间分组并按照时间区间统计每日VPN卡总量和增量
```
接口：http://192.168.6.10:8900/sdn/statistics/vpncard/{start}/{end}
类型:GET
参数：{start}开始时间（yyyy-MM-dd），{end}结束时间（yyyy-MM-dd）
例子：http://192.168.6.10:8900/sdn/statistics/vpncard/2016-05-18/2016-05-20
响应：
[

    {
        "count": {
            "total": 1,
            "increment": 0
        },
        "date": "2016-05-20"
    },
    {
        "count": {
            "total": 1,
            "increment": 0
        },
        "date": "2016-05-19"
    }

]
```
###13.根据VPN卡状态分组统计VPN卡总量
```
接口：http://192.168.6.10:8900/sdn/statistics/vpncard/status
类型:GET
例子：http://192.168.6.10:8900/sdn/statistics/vpncard/status
响应：
[

    {
        "name": "0",
        "value": 5
    },
    {
        "name": "1",
        "value": 10
    }

]
```
###14.省范围查询上网用户分布接口
```
接口：http://192.168.6.10:8900/sdn/statistics/vpnpostion/{region}/{timeType}
类型：GET
参数：{region}：省份名称，{timeType}：hour,yesterday,week,month
例子：http://192.168.6.10:8900/sdn/statistics/vpnpostion/安徽/hour
响应：
{

    "min": 4,
    "data": [
        {
            "name": "合肥",
            "value": 458
        },
        {
            "name": "芜湖",
            "value": 273
        },
        {
            "name": "黄山",
            "value": 29
        },
        {
            "name": "蚌埠",
            "value": 568
        },
        {
            "name": "滁州",
            "value": 4
        },
        {
            "name": "淮北",
            "value": 149
        }
    ],
    "max": 568

}
```
###15.根据VPN卡业务IP查询用户真实号接口
```
接口：http://192.168.6.10:8900/sdn/vpncard/realnumber/{bizIP}
类型：GET
参数：{bizIP} ：业务IP地址
例子：http://192.168.6.10:8900/sdn/vpncard/realnumber/192.168.8.1
响应：{"realNumber": "13912345678"}
```
###16.根据用户真实号查询VPN卡信息接口
```
接口：http://192.168.6.10:8900/sdn/vpncard/{realNumber}
类型：GET
参数：{realNumber} ：用户真实号
例子：http://192.168.6.10:8900/sdn/vpncard/13912345678
响应：{"id":"6e6e3bfddd5a00b010f8ddd9f47ee694","realNumber":"13912345678","bizIP":"192.168.8.1","stopIP":"192.168.8.2","specialIP":"192.168.8.3","offsetBizIP":"192.168.8.4","offsetStopIP":"192.168.8.5","offsetSpecialIP":"192.168.8.6","invalid":0,"insertDate":"2016-05-25 16:55:50.0"}
```
###17.根据用户真实号查询VPN用户信息接口
```
接口：http://192.168.6.10:8900/sdn/vpnuser/{realNumber}
类型：GET
参数：{realNumber} ：用户真实号
例子：http://192.168.6.10:8900/sdn/vpnuser/13912345678
响应：
{"id":"267c55434b31cd4f12f911915086a056","realNumber":"13912345678","sponsorNumber":"13500001111","sponsorName":"张三","sponsorIDType":1,"sponsorIDNumber":"342622199900001111","userNumber":"13522223333","userName":"李四","userIDType":1,"userIDNumber":"342622198800001111","registerDate":"2016-05-19 09:49:36.0","cancelDate":"2016-05-26 09:49:43.0","registerAgent":"1","modifyType":1,"modifyDate":"2016-05-19 09:49:57.0","invalid":0}
```
###18.上网记录索引信息缓存Redis接口
```
接口：http://192.168.6.10:8900/sdn/cache/index
类型：POST
数据类型：Json
CURL请求示例：
curl -X POST --header 'Content-Type:application/json' --header 'Accept:application/json' http://192.168.6.10:8900/sdn/cache/index --data '{"id":"1","username":null,"identity_id":null,"phone_num":null,"timestamp":null,"src_ip":null,"des_ip":null,"src_port":null,"des_port":null,"protocol_type":null,"header":null,"url":null,"flow_type":null,"resource_type":null,"domain_name":null,"size":null,"content":null,"title":null,"iccid":null}'
响应信息
{"errorCode":0,"errorMessage":"1"}
errorCode：0表示处理成功，其它数字标识处理失败。
errorMessage：一般未处理成功后的数据库主键ID信息。
```
###MySQL脚本目录
```
/sdn-api/sql/sdn.sql
```
###Redis相关：
```
master
redis.master.host=192.168.6.10
redis.master.port=6379
redis.master.password=zxsoft

slave
redis.slave.host=192.168.6.10
redis.slave.port=6379
redis.slave.password=zxsoft
```
###上网记录索引缓存键
```
sdn.cache.internetinfo=sdn.cache.internetinfo
```