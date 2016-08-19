##服务启动命令
```
sdn-etl/bin/ctl.sh start vpnCardETLServer&
sdn-etl/bin/ctl.sh start vpnUserETLServer&
sdn-etl/bin/ctl.sh start vpnPostionETLServer&
默认端口：8900
```
##测试数据
```
TXT测试数据文件目录：/sdn-etl/src/test/resources/sdndata
文件处理：处理完毕的文件会自动移动至：/TXT数据文件夹/finish/当前日期/  目录下
```