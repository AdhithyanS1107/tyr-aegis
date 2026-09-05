# Tyr Aegis

A simple desktop time-management application built around focused work sessions and intentional use of time.

Tyr Aegis currently provides a **90-minute focus timer**, configurable timer modes, task-based sessions, and session outcome tracking. Completed sessions are recorded locally so that your work history can be reviewed later.

## Features

### Focus Timer

Tyr Aegis provides a simple countdown timer designed for focused work sessions.

The default session length is **90 minutes**, with additional timer durations available for shorter or longer work sessions.

### Timer Modes

Choose the duration appropriate for the task at hand:

* **90 minutes** — Deep-focus session
* **60 minutes** — Standard session
* **30 minutes** — Short session
* **15 minutes** — Quick-start session
* **Custom** — User-defined duration

### Task-Based Sessions

Each session can be associated with a specific task, allowing completed work to be recorded rather than simply tracking elapsed time.

For example:

> **Task:** Java Course — Module 3
> **Duration:** 45 minutes
> **Status:** Completed

### Session Outcome Tracking

When a session ends, the application records its outcome.

Sessions can be marked as:

* Completed
* Intentionally stopped
* Interrupted
* Lost focus
* Emergency
* Other

This makes it possible to distinguish between time that was intentionally completed and sessions that ended prematurely.

## Data Storage

Completed tasks and session information are stored locally in a CSV ledger.

By default, the ledger is stored in:

```text
C:\Users\<user-id>\Documents\tyr_ledger.csv
```

Because the data is stored locally, your work history does not require an external service or account.

## Building From Source

### Requirements

Make sure the following are installed and available from the terminal:

* Java
* Maven
* JavaFX
* `jpackage`

### 1. Open the project directory

Open a terminal in the root directory of the project:

```text
...\tyr-aegis\
```

### 2. Build the application

Run:

```bash
mvn clean javafx:jlink
```

This compiles the application and creates the runtime image required for packaging.

### 3. Build the Windows application

Run:

```bash
jpackage --type app-image --name TyrAegis --dest target/deploy --runtime-image target/image --module-path target/classes --module com.chrono.desktop/com.chrono.desktop.App
```

### 4. Run the application

After the build completes, navigate to:

```text
target/deploy
```

Then run:

```text
TyrAegis.exe
```

The application can then be copied to another location and run independently.

## Releases

A pre-built executable can be downloaded from the project's **Releases** section, allowing users to run Tyr Aegis without compiling the source code themselves.

## Project Philosophy

Tyr Aegis is being developed around a simple principle:

> **Time that is intentionally spent is time reclaimed.**
