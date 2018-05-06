#基础镜像：仓库是java，tag是8
FROM java:8
#设置时区
ENV TZ Asia/Shanghai
ENV APP_NAME bookmanager
ENV APP_HOME /u01/app/bookmanager
#创目录
VOLUME /tmp
VOLUME /u01/app/bookmanager
#将打包好的spring程序拷贝到容器中的指定位置
ADD bookmanager/target/*.war  /u01/app/bookmanager/
ADD *.sh /u01/app/bookmanager/
#容器对外暴露8080端口
EXPOSE 9090
#CMD 外部docker run 可替代里面参数
CMD ["sh","-c","java -version;cd /u01/app/bookmanager;touch system_out.log;sh start-bookmanager.sh;tail -100f system_out.log"]