====VPN卡信息接口====

1.根据用用户真实号查询VPN卡信息
GET：http://192.168.6.10:8900/sdn/vpncard/{realNumber}
例子：http://192.168.6.10:8900/sdn/vpncard/13912345678
{realNumber}：用户真实号
Response:
{

    "id": "1",
    "realNumber": "13912345678",
    "bizIP": "192.168.1.2",
    "stopIP": "192.168.1.3",
    "specialIP": "192.168.1.4",
    "offsetBizIP": "192.168.1.5",
    "offsetStopIP": "192.168.1.6",
    "offsetSpecialIP": "192.168.1.7",
    "invalid": ​0,
    "insertDate": "2016-05-18T15:39:13.000Z"

}


2.根据一组用用户真实号查询VPN卡信息
GET：http://192.168.6.10:8900/sdn/vpncards/{realNumbers}
例子：http://192.168.6.10:8900/sdn/vpncards/13912345678&13900000000
{realNumbers}：13612345678&13800000000&13988888888&
Response:
[

{

    "id": "1",
    "realNumber": "13912345678",
    "bizIP": "192.168.1.2",
    "stopIP": "192.168.1.3",
    "specialIP": "192.168.1.4",
    "offsetBizIP": "192.168.1.5",
    "offsetStopIP": "192.168.1.6",
    "offsetSpecialIP": "192.168.1.7",
    "invalid": ​0,
    "insertDate": "2016-05-18T15:39:13.000Z"

},

    {
        "id": "2",
        "realNumber": "13900000000",
        "bizIP": "192.168.1.2",
        "stopIP": "192.168.1.3",
        "specialIP": "192.168.1.4",
        "offsetBizIP": "192.168.1.5",
        "offsetStopIP": "192.168.1.6",
        "offsetSpecialIP": "192.168.1.7",
        "invalid": ​0,
        "insertDate": "2016-05-18T15:39:13.000Z"
    }

]


3.分页查询VPN卡信息列表
GET：http://192.168.6.10:8900/sdn/vpncards/{pageNo}/{pageSize}
例子：http://192.168.6.10:8900/sdn/vpncards/1/2
{pageNo}：页码
{pageSize}：页容量
Response：
{

    "data": 

[

{

    "id": "1",
    "realNumber": "13912345678",
    "bizIP": "192.168.1.2",
    "stopIP": "192.168.1.3",
    "specialIP": "192.168.1.4",
    "offsetBizIP": "192.168.1.5",
    "offsetStopIP": "192.168.1.6",
    "offsetSpecialIP": "192.168.1.7",
    "invalid": ​0,
    "insertDate": "2016-05-18T15:39:13.000Z"

},

    {
        "id": "2",
        "realNumber": "13900000000",
        "bizIP": "192.168.1.2",
        "stopIP": "192.168.1.3",
        "specialIP": "192.168.1.4",
        "offsetBizIP": "192.168.1.5",
        "offsetStopIP": "192.168.1.6",
        "offsetSpecialIP": "192.168.1.7",
        "invalid": ​0,
        "insertDate": "2016-05-18T15:39:13.000Z"
    }

],
"page": 

    {
        "pageNo": ​1,
        "pageSize": ​2,
        "totalRecord": ​2,
        "totalPage": ​1,
        "params": { },
        "paramLists": { },
        "searchUrl": "/sdn/vpncards",
        "pageNoDisp": "1",
        "paraJson": "{}",
        "paraListJson": "{}"
    }

}

4.分页模糊查询VPN卡信息列表
GET：http://192.168.6.10:8900/sdn/vpncards/{pageNo}/{pageSize}/{vpncardJson}
例子：http://192.168.6.10:8900/sdn/vpncards/1/2/{"realNumber":1}
{pageNo}：页码
{pageSize}：页容量
Response：
{

    "data": 

[

{

    "id": "1",
    "realNumber": "13912345678",
    "bizIP": "192.168.1.2",
    "stopIP": "192.168.1.3",
    "specialIP": "192.168.1.4",
    "offsetBizIP": "192.168.1.5",
    "offsetStopIP": "192.168.1.6",
    "offsetSpecialIP": "192.168.1.7",
    "invalid": ​0,
    "insertDate": "2016-05-18T15:39:13.000Z"

},

    {
        "id": "2",
        "realNumber": "13900000000",
        "bizIP": "192.168.1.2",
        "stopIP": "192.168.1.3",
        "specialIP": "192.168.1.4",
        "offsetBizIP": "192.168.1.5",
        "offsetStopIP": "192.168.1.6",
        "offsetSpecialIP": "192.168.1.7",
        "invalid": ​0,
        "insertDate": "2016-05-18T15:39:13.000Z"
    }

],
"page": 

    {
        "pageNo": ​1,
        "pageSize": ​2,
        "totalRecord": ​2,
        "totalPage": ​1,
        "params": { },
        "paramLists": { },
        "searchUrl": "/sdn/vpncards",
        "pageNoDisp": "1",
        "paraJson": "{}",
        "paraListJson": "{}"
    }

}

====VPN用户信息接口====

1.根据用用户真实号查询VPN用户信息
GET：http://192.168.6.10:8900/sdn/vpnuser/{realNumber}
例子：http://192.168.6.10:8900/sdn/vpnuser/13522223333
{realNumber}：用户真实号
Response：
{

    "id": "1",
    "realNumber": "13522223333",
    "sponsorNumber": "13500001111",
    "sponsorName": "张三",
    "sponsorIDType": ​1,
    "sponsorIDNumber": "342622199900001111",
    "userNumber": "13522223333",
    "userName": "李四",
    "userIDType": ​1,
    "userIDNumber": "342622198800001111",
    "registerDate": "2016-05-19T09:49:36.000Z",
    "cancelDate": "2016-05-26T09:49:43.000Z",
    "registerAgent": "1",
    "modifyType": ​1,
    "modifyDate": "2016-05-19T09:49:57.000Z"

}

2.根据一组用用户真实号查询VPN用户信息
GET：http://192.168.6.10:8900/sdn/vpnusers/{realNumbers}
例子：http://192.168.6.10:8900/sdn/vpnusers/13522223333&13577778888
{realNumbers}：13612345678&13800000000&13988888888&
Response:
[

{

    "id": "2",
    "realNumber": "13577778888",
    "sponsorNumber": "13500001111",
    "sponsorName": "李磊",
    "sponsorIDType": ​1,
    "sponsorIDNumber": "342622199900001111",
    "userNumber": "13522223333",
    "userName": "韩梅梅",
    "userIDType": ​1,
    "userIDNumber": "342622198800001111",
    "registerDate": "2016-05-18T09:49:36.000Z",
    "cancelDate": "2016-05-25T09:49:43.000Z",
    "registerAgent": "1",
    "modifyType": ​1,
    "modifyDate": "2016-05-19T09:49:57.000Z"

},

    {
        "id": "1",
        "realNumber": "13522223333",
        "sponsorNumber": "13500001111",
        "sponsorName": "张三",
        "sponsorIDType": ​1,
        "sponsorIDNumber": "342622199900001111",
        "userNumber": "13522223333",
        "userName": "李四",
        "userIDType": ​1,
        "userIDNumber": "342622198800001111",
        "registerDate": "2016-05-19T09:49:36.000Z",
        "cancelDate": "2016-05-26T09:49:43.000Z",
        "registerAgent": "1",
        "modifyType": ​1,
        "modifyDate": "2016-05-19T09:49:57.000Z"
    }

]

3.分页查询VPN用户信息列表
GET：http://192.168.6.10:8900/sdn/vpnusers/{pageNo}/{pageSize}
例子：http://192.168.6.10:8900/sdn/vpnusers/1/2
{pageNo}：页码
{pageSize}：页容量
Response：
{

    "data": 

[

{

    "id": "2",
    "realNumber": "13577778888",
    "sponsorNumber": "13500001111",
    "sponsorName": "李磊",
    "sponsorIDType": ​1,
    "sponsorIDNumber": "342622199900001111",
    "userNumber": "13522223333",
    "userName": "韩梅梅",
    "userIDType": ​1,
    "userIDNumber": "342622198800001111",
    "registerDate": "2016-05-18T09:49:36.000Z",
    "cancelDate": "2016-05-25T09:49:43.000Z",
    "registerAgent": "1",
    "modifyType": ​1,
    "modifyDate": "2016-05-19T09:49:57.000Z"

},

    {
        "id": "1",
        "realNumber": "13522223333",
        "sponsorNumber": "13500001111",
        "sponsorName": "张三",
        "sponsorIDType": ​1,
        "sponsorIDNumber": "342622199900001111",
        "userNumber": "13522223333",
        "userName": "李四",
        "userIDType": ​1,
        "userIDNumber": "342622198800001111",
        "registerDate": "2016-05-19T09:49:36.000Z",
        "cancelDate": "2016-05-26T09:49:43.000Z",
        "registerAgent": "1",
        "modifyType": ​1,
        "modifyDate": "2016-05-19T09:49:57.000Z"
    }

],
"page": 

    {
        "pageNo": ​1,
        "pageSize": ​2,
        "totalRecord": ​2,
        "totalPage": ​1,
        "params": { },
        "paramLists": { },
        "searchUrl": "/sdn/vpnusers",
        "pageNoDisp": "1",
        "paraJson": "{}",
        "paraListJson": "{}"
    }

}

4.分页并支持模糊查询VPN用户信息列表
GET：http://192.168.6.10:8900/sdn/vpnusers/{pageNo}/{pageSize}/{"realNumber":1}
例子：http://192.168.6.10:8900/sdn/vpnusers/1/2/{"realNumber":1}
{pageNo}：页码
{pageSize}：页容量
Response：
{

    "data": 

[

{

    "id": "2",
    "realNumber": "13577778888",
    "sponsorNumber": "13500001111",
    "sponsorName": "李磊",
    "sponsorIDType": ​1,
    "sponsorIDNumber": "342622199900001111",
    "userNumber": "13522223333",
    "userName": "韩梅梅",
    "userIDType": ​1,
    "userIDNumber": "342622198800001111",
    "registerDate": "2016-05-18T09:49:36.000Z",
    "cancelDate": "2016-05-25T09:49:43.000Z",
    "registerAgent": "1",
    "modifyType": ​1,
    "modifyDate": "2016-05-19T09:49:57.000Z"

},

    {
        "id": "1",
        "realNumber": "13522223333",
        "sponsorNumber": "13500001111",
        "sponsorName": "张三",
        "sponsorIDType": ​1,
        "sponsorIDNumber": "342622199900001111",
        "userNumber": "13522223333",
        "userName": "李四",
        "userIDType": ​1,
        "userIDNumber": "342622198800001111",
        "registerDate": "2016-05-19T09:49:36.000Z",
        "cancelDate": "2016-05-26T09:49:43.000Z",
        "registerAgent": "1",
        "modifyType": ​1,
        "modifyDate": "2016-05-19T09:49:57.000Z"
    }

],
"page": 

    {
        "pageNo": ​1,
        "pageSize": ​2,
        "totalRecord": ​2,
        "totalPage": ​1,
        "params": { },
        "paramLists": { },
        "searchUrl": "/sdn/vpnusers",
        "pageNoDisp": "1",
        "paraJson": "{}",
        "paraListJson": "{}"
    }

}

上网用户地理位置接口：
http://192.168.6.10:8900/sdn/vpnpostion/13912345678/2016-01-01%2000:00:00/2016-01-01%2023:59:00

上网用户分布接口：
http://192.168.6.10:8900/sdn/vpnpostion/2016-01-01%2000:00:00/2016-01-01%2023:59:00



1.VPN卡信息存入MYSQL接口
接口地址：http://192.168.6.10:8900/sdn/vpncard
请求类型：POST
数据类型：JSON

CURL请求示例
curl -X POST --header 'Content-Type:application/json' --header 'Accept:application/json' http://192.168.6.10:8900/sdn/vpncard --data '{"realNumber":"13912345678","bizIP":"192.168.1.1","stopIP":"192.168.1.2","specialIP":"192.168.1.3","offsetBizIP":"192.168.1.4","offsetStopIP":"192.168.1.5","offsetSpecialIP":"192.168.1.6","insertDate":"2016-05-25 16:55:50.0"}'
响应信息
{"errorCode":0,"errorMessage":"12345678"}
errorCode：0表示处理成功，其它数字标识处理失败。
errorMessage：一般未处理成功后的数据库主键ID信息。

2.VPN用户信息存入MYSQL接口
接口地址：http://192.168.6.10:8900/sdn/vpnuser
请求类型：POST
数据类型：JSON

CURL请求示例
curl -X POST --header 'Content-Type:application/json' --header 'Accept:application/json' http://192.168.6.10:8900/sdn/vpnuser --data '{"realNumber":"13912345678","sponsorNumber":"13500001111","sponsorName":"张三","sponsorIDType":1,"sponsorIDNumber":"342622199900001111","userNumber":"13522223333","userName":"李四","userIDType":1,"userIDNumber":"342622198800001111","registerDate":"2016-05-19 09:49:36.0","cancelDate":"2016-05-26 09:49:43.0","registerAgent":"1","modifyType":1,"modifyDate":"2016-05-19 09:49:57.0"}'
响应信息
{"errorCode":0,"errorMessage":"12345678"}
errorCode：0表示处理成功，其它数字标识处理失败。
errorMessage：一般未处理成功后的数据库主键ID信息。

3.根据VPN卡业务IP查询用户真实号接口
接口地址：http://192.168.6.10:8900/sdn/vpncard/realnumber/{bizIP}
请求类型：GET
请求参数：{bizIP} 业务IP地址
请求示例：http://192.168.6.10:8900/sdn/vpncard/realnumber/192.168.1.1
响应数据：{"realNumber": "13912345678"}

4.根据用户真实号查询VPN卡信息接口
接口地址：http://192.168.6.10:8900/sdn/vpncard/{realNumber}
请求类型：GET
请求参数：{realNumber} 用户真实号
请求示例：http://192.168.6.10:8900/sdn/vpncard/13912345678
响应数据：{"id":"6e6e3bfddd5a00b010f8ddd9f47ee694","realNumber":"13912345678","bizIP":"192.168.1.1","stopIP":"192.168.1.2","specialIP":"192.168.1.3","offsetBizIP":"192.168.1.4","offsetStopIP":"192.168.1.5","offsetSpecialIP":"192.168.1.6","invalid":0,"insertDate":"2016-05-25 16:55:50.0"}

5.根据用户真实号查询VPN用户信息接口
接口地址：http://192.168.6.10:8900/sdn/vpnuser/{realNumber}
请求类型：GET
请求参数：{realNumber} 用户真实号
请求示例：http://192.168.6.10:8900/sdn/vpnuser/13912345678
响应数据：{"id":"267c55434b31cd4f12f911915086a056","realNumber":"13912345678","sponsorNumber":"13500001111","sponsorName":"张三","sponsorIDType":1,"sponsorIDNumber":"342622199900001111","userNumber":"13522223333","userName":"李四","userIDType":1,"userIDNumber":"342622198800001111","registerDate":"2016-05-19 09:49:36.0","cancelDate":"2016-05-26 09:49:43.0","registerAgent":"1","modifyType":1,"modifyDate":"2016-05-19 09:49:57.0","invalid":0}

6.上网记录索引信息缓存Redis接口
接口地址：http://192.168.6.10:8900/sdn/cache/index
请求类型：POST
数据类型：JSON

CURL请求示例
curl -X POST --header 'Content-Type:application/json' --header 'Accept:application/json' http://192.168.6.10:8900/sdn/cache/index --data '{"id":"1","username":null,"identity_id":null,"phone_num":null,"timestamp":null,"src_ip":null,"des_ip":null,"src_port":null,"des_port":null,"protocol_type":null,"header":null,"url":null,"flow_type":null,"resource_type":null,"domain_name":null,"size":null,"content":null,"title":null,"iccid":null}'
响应信息
{"errorCode":0,"errorMessage":"1"}
errorCode：0表示处理成功，其它数字标识处理失败。
errorMessage：一般未处理成功后的数据库主键ID信息。

7.Redis连接信息
#master
redis.master.host=192.168.6.10
redis.master.port=6379
redis.master.password=zxsoft

#slave
redis.slave.host=192.168.6.10
redis.slave.port=6379
redis.slave.password=zxsoft

#上网记录信息索引缓存键
sdn.cache.internetinfo=sdn.cache.internetinfo


1.根据时间分组并按照时间区间统计每日VPN用户总量和增量
接口地址：http://192.168.6.10:8900/sdn/statistics/vpnuser/{start}/{end}
参数：{start}开始时间（yyyy-MM-dd） {end}结束时间（yyyy-MM-dd）
接口类型:GET
示例：http://192.168.6.10:8900/sdn/statistics/vpnuser/2016-05-18/2016-05-20
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

2.根据用户证件类型分组并按照时间区间统计VPN用户总量
接口地址：http://192.168.6.10:8900/sdn/statistics/vpnuser/idtype/{start}/{end}
参数：{start}开始时间（yyyy-MM-dd） {end}结束时间（yyyy-MM-dd）
接口类型:GET
示例：http://192.168.6.10:8900/sdn/statistics/vpnuser/idtype/2016-05-18/2016-05-20
响应：
[

    {
        "name": "1",
        "value": 4
    }

]

3.根据时间分组并按照时间区间统计每日VPN卡总量和增量
接口地址：http://192.168.6.10:8900/sdn/statistics/vpncard/{start}/{end}
参数：{start}开始时间（yyyy-MM-dd） {end}结束时间（yyyy-MM-dd）
接口类型:GET
示例：http://192.168.6.10:8900/sdn/statistics/vpncard/2016-05-18/2016-05-20
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

4.根据VPN卡状态分组统计VPN卡总量
接口地址：http://192.168.6.10:8900/sdn/statistics/vpncard/status
接口类型:GET
示例：http://192.168.6.10:8900/sdn/statistics/vpncard/status
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





