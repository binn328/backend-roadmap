# Task Tracker CLI

이 프로그램은 CLI에서 구동되는 간단한 작업 추적기입니다. 작업을 추가, 갱신, 삭제하거나 작업의 상태를 설정할 수 있습니다.

## 기능

1. **작업 추가하기**: 새로운 작업을 추가합니다.
2. **작업 갱신하기**: 작업의 설명을 수정합니다.
3. **작업 삭제하기**: 작업을 삭제합니다.
4. **작업 상태 변경하기**: 작업의 상태를 변경합니다. (`todo`, `in-progress`, `done`)
5. **모든 작업 나열하기**: 모든 작업을 나열합니다.
6. **특정 상태의 작업만 나열하기**: 지정된 상태의 작업만 선별하여 나열합니다.

## 설치

### **1. 해당 저장소를 복제합니다.**

```bash
git clone https://github.com/binn328/backend-roadmap.git
cd backend-roadmap/taskTracker
```

### **2-1. 미리 컴파일된 파일을 실행합니다.**

```bash
cd bin
java -cp . TaskTrackerCli <command> [arguments]
```

### **2-2. 혹은 직접 컴파일 후 실행합니다.**

```bash
javac Status.java Task.java TaskManager.java TaskTrackerCli.java
java -cp . TaskTrackerCli <command> [arguments]
```

## 사용방법

```bash
# 새로운 작업 추가하기
java -cp . TaskTrackerCli add "Buy groceries"
# output: Task added successfully (ID: 1)

# 작업 갱신 및 삭제
java -cp . TaskTrackerCli update 1 "Buy groceries and cook dinner"
# output: Task updated succesfully (ID: 1)
java -cp . TaskTrackerCli delete 1
# output: Task deleted succesfully (ID: 1)

# 작업에 진행 중, 완료 표시하기
java -cp . TaskTrackerCli mark-in-progress 1
# output: Task marked to IN_PROGRESS succesfully (ID: 1)
java -cp . TaskTrackerCli mark-done 1
# output: Task marked to DONE succesfully (ID: 0)

# 모든 작업 나열하기
java -cp . TaskTrackerCli list
# output:
# *----*---------------------*-----------*-------------------*-------------------*
# | ID |     Description     |   Status  |      CreateAt     |      UpdateAt     |
# *----*---------------------*-----------*-------------------*-------------------*
# |   0|        clean my room|       TODO|2025-06-12T19:07:19|2025-06-12T19:07:19|
# *----*---------------------*-----------*-------------------*-------------------*
# |   1|          cook dinner|IN_PROGRESS|2025-06-12T19:07:28|2025-06-12T19:07:28|
# *----*---------------------*-----------*-------------------*-------------------*
# |   2|          build my PC|       DONE|2025-06-12T19:07:53|2025-06-12T19:07:53|
# *----*---------------------*-----------*-------------------*-------------------*

# 작업 상태별로 나열하기
java -cp . TaskTrackerCli list done
java -cp . TaskTrackerCli list todo
java -cp . TaskTrackerCli list in-progress
```
