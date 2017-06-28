## spark web api

### 运行前准备
    登陆hbase并创建表
    create 'sefon:cdrinfo', 'cf'
    put 'sefon:cdrinfo', '18628353733','cf:info','this is a message'
    
    验证数据
    get 'sefon:cdrinfo','18628353733'
    
### 部署
    1. 解压 spark-web-1.0.0-package.tar.gz
        tar -zxf spark-web-1.0.0-package.tar.gz
    2. 拷贝生产环境的hdfs-site.xml 和 hbse-site.xml 文件到 conf 目录下
    3. 编辑conf目录下面的配置文件
        主要编辑api-server.properties 这个文件
    
### 运行
    直接运行 ./run_server.sh 
    其中的端口，是配置文件中指定的
    http://ip:4568/calls/18628353733