# This is a simple 90 min Timer.

## The following is a simple guide to build and run the application for yourself or you can simply download the executable file from the Releases tab.

### Open the terminal in the root folder of the project

```bash
...\tyr-aegis\
```

### Compile the code using the command
```bash
mvn clean javafx:jlink
```

### then build the exe file using the command

```bash
jpackage --type app-image --name TyrAegis --dest target/deploy --runtime-image target/image --module-path target/classes --module com.chrono.desktop/com.chrono.desktop.App
```

#### Then go to the target/deploy folder and run the TyrAegis.exe file to run the application

### You can now copy the file where you want to and run it from there. The 'tyr_ledger.csv' or the file in which the completed tasks are stored will be present in the following path.
```
C:\Users\<user-id>\Documents
```
