# Module 3 — Core Java Exercises

## Prerequisites
- JDK 21 (recommended) — https://adoptium.net/
- Any IDE: IntelliJ IDEA / Eclipse / VS Code (with Java Extension Pack)

## How to Compile & Run

### Using Terminal (Command Prompt)
```
cd "c:\Users\hp\Desktop\Local Community Event Portal\exercises\21-java"

# Compile
javac Ex01_HelloWorld.java

# Run
java Ex01_HelloWorld
```

### Using VS Code
1. Open the `21-java` folder
2. Open any `.java` file
3. Click the ▶ Run button above `main()`

---

## Exercise List

| # | File | Topic | Java Version |
|---|------|-------|-------------|
| 01 | Ex01_HelloWorld.java | Hello World | Any |
| 02 | Ex02_SimpleCalculator.java | Arithmetic + Scanner | Any |
| 03 | Ex03_EvenOdd.java | Conditionals | Any |
| 04 | Ex04_LeapYear.java | Nested Conditionals | Any |
| 05 | Ex05_MultiplicationTable.java | For Loop | Any |
| 06 | Ex06_DataTypes.java | Primitive Types | Any |
| 07 | Ex07_TypeCasting.java | Type Casting | Any |
| 08 | Ex08_OperatorPrecedence.java | Operators | Any |
| 09 | Ex09_GradeCalculator.java | if-else | Any |
| 10 | Ex10_NumberGuessingGame.java | Loops + Random | Any |
| 11 | Ex11_Factorial.java | For Loop | Any |
| 12 | Ex12_MethodOverloading.java | Method Overloading | Any |
| 13 | Ex13_RecursiveFibonacci.java | Recursion | Any |
| 14 | Ex14_ArraySumAverage.java | Arrays | Any |
| 15 | Ex15_StringReversal.java | StringBuilder | Any |
| 16 | Ex16_PalindromeChecker.java | String + Regex | Any |
| 17 | Ex17_ClassAndObject.java | OOP — Classes | Any |
| 18 | Ex18_Inheritance.java | Inheritance | Any |
| 19 | Ex19_Interface.java | Interfaces | Any |
| 20 | Ex20_TryCatch.java | Exception Handling | Any |
| 21 | Ex21_CustomException.java | Custom Exception | Any |
| 22 | Ex22_FileWriting.java | File I/O Write | Any |
| 23 | Ex23_FileReading.java | File I/O Read | Any |
| 24 | Ex24_ArrayList.java | ArrayList | Any |
| 25 | Ex25_HashMap.java | HashMap | Any |
| 26 | Ex26_Threads.java | Multithreading | Any |
| 27 | Ex27_LambdaExpressions.java | Lambda | Java 8+ |
| 28 | Ex28_StreamAPI.java | Stream API | Java 8+ |
| 29 | Ex29_Records.java | Records | Java 16+ |
| 30 | Ex30_PatternMatchingSwitch.java | Pattern Matching | Java 21 |
| 31 | Ex31_JDBCConnection.java | JDBC — SELECT | Any |
| 32 | Ex32_JDBCInsertUpdate.java | JDBC — INSERT/UPDATE | Any |
| 33 | Ex33_JDBCTransaction.java | JDBC — Transactions | Any |
| 34 | Ex34_JavaModules.java | Java Modules | Java 9+ |
| 35 | Ex35_TCPChat.java | TCP Sockets | Any |
| 36 | Ex36_HttpClient.java | HTTP Client | Java 11+ |
| 37 | Ex37_BytecodeInspection.java | javap Bytecode | Any |
| 38 | Ex38_Decompile.java | Decompilation | Any |
| 39 | Ex39_Reflection.java | Reflection API | Any |
| 40 | Ex40_VirtualThreads.java | Virtual Threads | Java 21 |
| 41 | Ex41_ExecutorService.java | ExecutorService | Java 8+ |

---

## Notes for JDBC Exercises (31, 32, 33)
1. Download MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/
2. Add the `.jar` to your classpath:
   ```
   javac -cp .;mysql-connector-j-9.x.x.jar Ex31_JDBCConnection.java
   java  -cp .;mysql-connector-j-9.x.x.jar Ex31_JDBCConnection
   ```
3. Replace `your_password` in each file with your MySQL root password.
4. Run the setup SQL comments at the top of each JDBC file in MySQL Workbench first.
