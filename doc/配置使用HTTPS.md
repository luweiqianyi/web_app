# 配置web使用HTTPS

* 使用JDK下的keytool.exe生成免费数字HTTPS证书

  ```cmd
  D:\>cd D:\Java\jdk1.8.0_271\bin
  
  D:\Java\jdk1.8.0_271\bin>keytool -genkey -alias tomcathttps -keyalg RSA -keysize 2048 -keystore httpscertificate.p12 -validity 365
  输入密钥库口令:(此处输入的密码为:123456)
  再次输入新口令:(此处输入的密码为:123456)
  您的名字与姓氏是什么?
    [root]:  root
  您的组织单位名称是什么?
    [root]:  root
  您的组织名称是什么?
    [root]:  root
  您所在的城市或区域名称是什么?
    [hz]:  hz
  您所在的省/市/自治区名称是什么?
    [zj]:  zj
  该单位的双字母国家/地区代码是什么?
    [cn]:  cn
  CN=root, OU=root, O=root, L=hz, ST=zj, C=cn是否正确?
    [否]:  是
  
  输入 <tomcathttps> 的密钥口令
          (如果和密钥库口令相同, 按回车):(此处输入的密码为:123456)
  再次输入新口令:(此处输入的密码为:123456)
  
  Warning:
  JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore httpscertificate.p12 -destkeystore httpscertificate.p12 -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
  
  D:\Java\jdk1.8.0_271\bin>
  ```

  注意：以上证书的名字中是数字1而不是L的小写。

* 复制证书到项目的根目录

* 在application.properties中配置刚才的证书相关的信息

* Spring BOot不支持同时在配置中启动HTTP和HTTPS，所以需要进行服务器的配置(TomcatConfig.Java中将HTTP 8080端口重定向到HTTPS的8081端口)