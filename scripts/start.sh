#!/usr/bin/env bash

# 기본적인 스크립트는 step2의 deploy.sh와 유사
# 다른 점이라면 IDLE_PROFILE을 통해 properties 파일을 가져오고(application-$IDLE_PROFILE.properties), active profile을 지정하는 것(-Dspring.profiles.active=$IDLE_PROFILE)뿐이다.
# 여기서도 IDLE_PROFILE을 사용하니 profile.sh을 가져와야 한다.
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=freehyun-springboot2-webservice

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

echo "  > $JAR_NAME 를 실행합니다."
IDLE_PROFILE=$(find_idle_profile)

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=${IDLE_PROFILE} \
    $JAR_NAME > /dev/null 2> /dev/null < /dev/null &