FROM openjdk:8-jdk-alpine
MAINTAINER yangz@dibo.ltd

RUN echo -e "https://mirror.tuna.tsinghua.edu.cn/alpine/v3.4/main\n\
https://mirror.tuna.tsinghua.edu.cn/alpine/v3.4/community" > /etc/apk/repositories

RUN apk --update add curl bash openjdk8 ttf-dejavu git && \
      rm -rf /var/cache/apk/*

ENV TZ=Asia/Shanghai
VOLUME /tmp
ADD diboot-online-demo-latest.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]