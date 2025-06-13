# Github Activity CLI

이 프로그램은 Github 활동을 Cli로 확인할 수 있는 간단한 프로그램입니다.

[roadmap.sh](https://roadmap.sh)에서 제공하는 [github-user-activity](https://roadmap.sh/projects/github-user-activity) 프로젝트입니다.

## 기능

-   사용자의 이름을 입력하면 해당 사용자가 최근 30일동안 Github에서 활동한 기록을 확인할 수 있습니다.

## 설치 및 실행 방법

1. 이 저장소를 복제합니다.

```bash
git clone https://github.com/binn328/github-activity-cli.git
cd github-activity
```

2. Maven으로 패키징합니다.

```bash
mvn clean package
```

3. 실행합니다.

```bash
java -jar target/github-activity-1.0.0.jar
```
