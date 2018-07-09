# 快速使用文档

最近又有粉丝加Q群讨论netty整合SSM项目的方式等，我在这里抽了休息日的时候整理一下，一步一步的记录，注意的是，本案例仅实现了用netty整合SSM后与单片机等类TCP应用通信。

项目以防止github，接下来的文档代码块均用截图表示，大家可以去github查看具体的代码资料

首先我用IDEA进行项目的初始化

接下初始化对应的pom文件还有配置文件

主要还是版本问题，其他版本我还没试过，所以坑的话，大家还要继续踩踩。

主要是netty中连接池对数据的处理，是在线程中执行的，Spring框架注入的Service组件存在失效的问题，我使用了大部分办法还是无法处理，最后就直接用原生的mybatis方式进行数据操作，所以congif文档是针对netty操作数据库而特别准备的配置文件。mappers可以时mybatis自动生成，也可以是自己写的xml文件。

本项目的mybatis集成Spring也是完好的，静静netty的数据操作需要再定义一层数据持续化池。

## client目录

测试连接的工具包，CRC16myself用于生成对应的通信字符串、TCPIPClient用于发送，最好将其移至其他项目运行

## common目录

SSM项目 API同意返回形式定义

## controller目录

这里仅仅定义了netty层的暴露API，类似查询系统连接数，连接实例Id数组，向某链接实例发送相应的信息

## dao目录

HouseChannelDao是Netty层的数据操作，HouseChannelMapper是原项目的MyBatis

## filter目录

构造外接api的请求头

## listener目录

启动netty的监听器

## netty目录

核心的netty层业务代码

项目通过引入netty5.0，通过监听器形式，启动netty线程（NettyServerThread），于后启动对应的NettyServer，其中启动永久监听还有定义自己的TCPServerhandler,在数据读取方法中，重新定义自己的业务处理。
在接收成功，数据格式正常的情况下，执行对应的数据层操作，且每一个实例连接进来时，系统内存会对其进行ID与实例的绑定与存储，也因此方便Controller层对其链接实例进行查询信息与下发指令。





