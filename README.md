# LineBotExample

## 준비물
* 라인 비즈니스 센터 가입(대충 자영업으로 신청) & 라인봇 신청
* https 적용된 도메인
* 라인쪽에서 호출할, callbackURL을 서비스하는 웹서버

## 라인봇 가입
https://business.line.me/ko/products/4/introduction

## 빌드(tc 수행하지 않을 때)
```
mvn clean package -Dmaven.test.skip=true
```

## 빌드(tc 수행시)
```
mvn clean package -Dline.bot.channelId=본인꺼 -Dline.bot.channelMid=본인꺼 -Dline.bot.channelSecret=본인꺼
```

## 실행
```
java -jar -server LineBot-0.0.1-SNAPSHOT.jar --line.bot.channelId=본인꺼 --line.bot.channelMid=본인꺼 --line.bot.channelSecret=본인꺼
```
