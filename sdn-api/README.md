1.根据用用户真实号查询VPN卡信息
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

2.根据一组用用户真实号查询VPN卡信息
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

3.分页查询VPN卡信息列表
接口：http://192.168.6.10:8900/sdn/vpncards/{pageNo}/{pageSize}
类型：GET
参数：{pageNo}：页码{pageSize}：页容量
例子：http://192.168.6.10:8900/sdn/vpncards/1/10
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
        },
        {
            "id": "3",
            "realNumber": "13911111111",
            "bizIP": "192.168.1.13",
            "stopIP": "192.168.1.14",
            "specialIP": "192.168.1.15",
            "offsetBizIP": "192.168.1.16",
            "offsetStopIP": "192.168.1.17",
            "offsetSpecialIP": "192.168.1.18",
            "invalid": 0,
            "insertDate": "2016-05-20 15:39:13.0"
        },
        {
            "id": "4",
            "realNumber": "13922222222",
            "bizIP": "192.168.1.19",
            "stopIP": "192.168.1.20",
            "specialIP": "192.168.1.21",
            "offsetBizIP": "192.168.1.22",
            "offsetStopIP": "192.168.1.23",
            "offsetSpecialIP": "192.168.1.24",
            "invalid": 0,
            "insertDate": "2016-05-21 15:39:13.0"
        },
        {
            "id": "5",
            "realNumber": "13933333333",
            "bizIP": "192.168.1.25",
            "stopIP": "192.168.1.26",
            "specialIP": "192.168.1.27",
            "offsetBizIP": "192.168.1.28",
            "offsetStopIP": "192.168.1.29",
            "offsetSpecialIP": "192.168.1.30",
            "invalid": 0,
            "insertDate": "2016-05-22 15:39:13.0"
        },
        {
            "id": "6",
            "realNumber": "13912345678",
            "bizIP": "192.168.1.1",
            "stopIP": "192.168.1.2",
            "specialIP": "192.168.1.3",
            "offsetBizIP": "192.168.1.4",
            "offsetStopIP": "192.168.1.5",
            "offsetSpecialIP": "192.168.1.6",
            "invalid": 1,
            "insertDate": "2016-05-23 15:39:13.0"
        },
        {
            "id": "a2525a876a2dcb0c748ea46c4d16a564",
            "realNumber": "13912345678",
            "bizIP": "192.168.1.1",
            "stopIP": "192.168.1.2",
            "specialIP": "192.168.1.3",
            "offsetBizIP": "192.168.1.4",
            "offsetStopIP": "192.168.1.5",
            "offsetSpecialIP": "192.168.1.6",
            "invalid": 1,
            "insertDate": "2016-05-25 16:49:41.0"
        },
        {
            "id": "8afc646d2c74af7b49529e2af631576a",
            "realNumber": "13912345678",
            "bizIP": "192.168.1.1",
            "stopIP": "192.168.1.2",
            "specialIP": "192.168.1.3",
            "offsetBizIP": "192.168.1.4",
            "offsetStopIP": "192.168.1.5",
            "offsetSpecialIP": "192.168.1.6",
            "invalid": 1,
            "insertDate": "2016-05-25 16:50:04.0"
        },
        {
            "id": "e2123c41f03165525d8352ab5b17f863",
            "realNumber": "13912345678",
            "bizIP": "192.168.1.1",
            "stopIP": "192.168.1.2",
            "specialIP": "192.168.1.3",
            "offsetBizIP": "192.168.1.4",
            "offsetStopIP": "192.168.1.5",
            "offsetSpecialIP": "192.168.1.6",
            "invalid": 1,
            "insertDate": "2016-05-25 16:55:50.0"
        },
        {
            "id": "dcd0c0eeee552b09a638b32809ad5bc9",
            "realNumber": "13912345678",
            "bizIP": "192.168.1.1",
            "stopIP": "192.168.1.2",
            "specialIP": "192.168.1.3",
            "offsetBizIP": "192.168.1.4",
            "offsetStopIP": "192.168.1.5",
            "offsetSpecialIP": "192.168.1.6",
            "invalid": 1,
            "insertDate": "2016-05-25 16:55:50.0"
        }
    ],
    "page": {
        "pageNo": 1,
        "pageSize": 10,
        "totalRecord": 188,
        "totalPage": 19,
        "param": { },
        "searchUrl": "/sdn/vpncards",
        "pageNoDisp": "1|2|3|4|5|6|7|8|0|18|19",
        "paramJson": "{}"
    }

}

4.分页模糊查询并支持按时间区间查询VPN卡信息列表
GET：http://192.168.6.10:8900/sdn/vpncards/{pageNo}/{pageSize}/{vpncardJson}
例子：http://192.168.6.10:8900/sdn/vpncards/1/10/ {"startTime":"2016-06-08","endTime":"2016-06-09"}
{pageNo}：页码
{pageSize}：页容量
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
        },
        {
            "id": "ab78be8a992b729ab6eb96049678771c",
            "realNumber": "13900000037",
            "bizIP": "192.168.0.190",
            "stopIP": "192.168.0.191",
            "specialIP": "192.168.0.192",
            "offsetBizIP": "192.168.0.193",
            "offsetStopIP": "192.168.0.194",
            "offsetSpecialIP": "192.168.0.195",
            "invalid": 0,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "aa7c6a1a8d1da95a0607c9c50e63cae4",
            "realNumber": "13900000002",
            "bizIP": "192.168.0.7",
            "stopIP": "192.168.0.8",
            "specialIP": "192.168.0.9",
            "offsetBizIP": "192.168.0.10",
            "offsetStopIP": "192.168.0.11",
            "offsetSpecialIP": "192.168.0.12",
            "invalid": 1,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "a84febdcfd0102e84fa24c2e9d88e93b",
            "realNumber": "13900000002",
            "bizIP": "192.168.0.7",
            "stopIP": "192.168.0.8",
            "specialIP": "192.168.0.9",
            "offsetBizIP": "192.168.0.10",
            "offsetStopIP": "192.168.0.11",
            "offsetSpecialIP": "192.168.0.12",
            "invalid": 1,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "a729776a91321a40f0c726c79055e282",
            "realNumber": "13900000034",
            "bizIP": "192.168.0.175",
            "stopIP": "192.168.0.176",
            "specialIP": "192.168.0.177",
            "offsetBizIP": "192.168.0.178",
            "offsetStopIP": "192.168.0.179",
            "offsetSpecialIP": "192.168.0.180",
            "invalid": 0,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "a4295f583388303a80262ff6a6e314e5",
            "realNumber": "13900000036",
            "bizIP": "192.168.0.185",
            "stopIP": "192.168.0.186",
            "specialIP": "192.168.0.187",
            "offsetBizIP": "192.168.0.188",
            "offsetStopIP": "192.168.0.189",
            "offsetSpecialIP": "192.168.0.190",
            "invalid": 0,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "a312ad6a7cad3ecc08eabed27f5cc91c",
            "realNumber": "13900000024",
            "bizIP": "192.168.0.125",
            "stopIP": "192.168.0.126",
            "specialIP": "192.168.0.127",
            "offsetBizIP": "192.168.0.128",
            "offsetStopIP": "192.168.0.129",
            "offsetSpecialIP": "192.168.0.130",
            "invalid": 0,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "a1c3f1b955ac6d0c54b7c4c91456d482",
            "realNumber": "13900000047",
            "bizIP": "192.168.0.240",
            "stopIP": "192.168.0.241",
            "specialIP": "192.168.0.242",
            "offsetBizIP": "192.168.0.243",
            "offsetStopIP": "192.168.0.244",
            "offsetSpecialIP": "192.168.0.245",
            "invalid": 0,
            "insertDate": "2016-06-08 15:00:00.0"
        },
        {
            "id": "adad6ad8f1b5760e195bc9ea95e4daa3",
            "realNumber": "13900000007",
            "bizIP": "192.168.0.37",
            "stopIP": "192.168.0.38",
            "specialIP": "192.168.0.39",
            "offsetBizIP": "192.168.0.40",
            "offsetStopIP": "192.168.0.41",
            "offsetSpecialIP": "192.168.0.42",
            "invalid": 1,
            "insertDate": "2016-06-08 15:00:00.0"
        }
    ],
    "page": {
        "pageNo": 1,
        "pageSize": 10,
        "totalRecord": 172,
        "totalPage": 18,
        "param": {
            "startTime": "2016-06-08",
            "endTime": "2016-06-09"
        },
        "searchUrl": "/sdn/vpncards",
        "pageNoDisp": "1|2|3|4|5|6|7|8|0|17|18",
        "paramJson": "{\"startTime\":\"2016-06-08\",\"endTime\":\"2016-06-09\"}"
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
http://192.168.6.10:8900/sdn/vpnpostion/13912345678/2016-01-01 00:00:00/2016-01-01 23:59:00

全国上网用户分布接口：
http://192.168.6.10:8900/sdn/statistics/vpnpostion/2016-01-01 00:00:00/2016-01-01 23:59:00

查询指定省份上网用户分布接口：
http://192.168.6.10:8900/sdn/statistics/vpnpostion/{region}/2016-01-01 00:00:00/2016-01-01 23:59:00
{region}:省份编码



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





