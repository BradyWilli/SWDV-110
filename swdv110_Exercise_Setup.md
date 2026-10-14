# SWDV-110 Coding Exercises: One-Time Setup

Every weekly coding exercise in this course is a **Maven** project. Maven is a build tool that:

- compiles your code with a consistent Java version,
- downloads libraries for you (JUnit for testing, the SQLite driver in Week 6, JavaFX in Week 7),
- runs the provided **JUnit tests**, so you can check your work before you submit.

Do this setup once, about **30–45 minutes**, before starting the Week 1 exercise.

---

### Installing Maven

**Windows**

1. Download the **Binary zip archive** (`apache-maven-3.9.x-bin.zip`) from <https://maven.apache.org/download.cgi>.
2. Extract it to `C:\Program Files\Maven` so that `C:\Program Files\Maven\apache-maven-3.9.x\bin` exists.
3. Press Start, type **"environment variables"**, open *Edit the system environment variables* → **Environment Variables…**
4. Under *User variables*:
   - Add `JAVA_HOME` = your JDK folder (for example `C:\Program Files\Eclipse Adoptium\jdk-21.0.5.11-hotspot`).
   - Edit `Path` → **New** → `C:\Program Files\Maven\apache-maven-3.9.x\bin`.
5. **Close and reopen** every terminal and VS Code window.

**macOS**

```bash
brew install maven
```

(No Homebrew? Install it from <https://brew.sh>, or follow the Windows steps and add the `bin` folder to your `PATH` in `~/.zshrc`.)

**Linux**

```bash
sudo apt install maven
```

### Check that it worked

```bash
java -version
mvn -v
```

`mvn -v` must report **Java version: 17** or higher. If it shows an older Java, your `JAVA_HOME` points to the wrong JDK.

---

## 2. What a Maven project looks like

Every exercise uses this layout. The folder names matter, because Maven looks for code in exactly these places.

```
my-exercise/                  <- open THIS folder in VS Code
├── pom.xml                   <- project settings (given in each exercise)
├── README.md                 <- your reflection answers
├── (data files, e.g. contacts.txt)
└── src/
    ├── main/
    │   └── java/
    │       └── edu/cwi/swdv110/<topic>/   <- your program code
    │           └── App.java
    └── test/
        └── java/
            └── edu/cwi/swdv110/<topic>/   <- provided JUnit tests
                └── SomethingTest.java
```

The folders under `src/main/java` must match the `package` line at the top of each `.java` file. For example, `package edu.cwi.swdv110.contacts;` goes in `src/main/java/edu/cwi/swdv110/contacts/`.

### Creating a project

1. Make the project folder and the `src/main/java/...` and `src/test/java/...` folders.
2. Create `pom.xml` in the project folder and paste in the one given in the exercise.
3. In VS Code, use **File → Open Folder…** and open the folder that contains `pom.xml`. The Java extension detects Maven automatically. The first time, it can take a minute to download dependencies.
4. Each exercise lists its starter files, each marked with **File:** and a path. Create each file at exactly that path.

---

## 3. Everyday Maven commands

Run these in a terminal **in the folder that contains `pom.xml`**. In VS Code, use **Terminal → New Terminal**.

| Command | What it does |
|---|---|
| `mvn compile` | Compiles your code and reports errors. |
| `mvn test` | Compiles everything and runs all the JUnit tests. |
| `mvn -Dtest=ContactParserTest test` | Runs just one test class. |
| `mvn -q compile exec:java` | Runs your `App` (the main class is named in `pom.xml`). |
| `mvn clean` | Deletes the `target/` folder (all compiled output). Try this when something "weird" happens. |
| `mvn javafx:run` | Week 7 only: launches a JavaFX app. |

**In VS Code**, you can also:

- click **Run** above `main`,
- open the **Testing** panel (the flask icon) and click ▶ next to any test.

---

## 4. Working with the provided tests

- Each exercise gives you a JUnit test class. **The tests fail at first.** That's expected. Your job is to make them pass.
- A failing test tells you what was **expected** and what it **got**:

  ```
  [ERROR] ContactParserTest.formatPhoneHandlesDots:42
          expected: <(555) 123-4567> but was: <INVALID>
  ```

- **Don't edit the provided tests** to make them pass. You *may* add more test methods of your own, and you'll earn credit for doing it.
- Passing all the tests doesn't mean you're finished. Every exercise also has a `main` program and a README to complete.

---

## 5. Git & submission

1. Create a separate folder in your **SWDV-110** Github repo per weekly project.
2. Add a `.gitignore` file in the project folder so compiled files are not committed:

   ```
   target/
   .vscode/
   *.class
   ```

3. Commit early and often. A good rhythm is one commit per completed Part.
4. Submit the repository link in Canvas.

---

## 6. Troubleshooting

| Symptom | Fix |
|---|---|
| `'mvn' is not recognized…` / `command not found: mvn` | Maven's `bin` folder is not on your `PATH`, or you didn't reopen the terminal. |
| `error: release version 17 not supported` | Maven is using an old JDK. Set `JAVA_HOME` to JDK 17+ and reopen the terminal. |
| `No tests to run` / tests are ignored | The test file is not under `src/test/java/...`, or its package line doesn't match its folder. |
| VS Code shows red errors but `mvn compile` works | Command Palette → **Java: Clean Java Language Server Workspace** → Restart. |
| `Could not resolve dependencies` | You're offline, or a firewall is blocking Maven. Try again on campus Wi-Fi or a hotspot. |
| `WARNING: A restricted method in java.lang.System has been called` (Weeks 6–7, JDK 24+) | Harmless. The SQLite driver and JavaFX load native code, and newer JDKs announce it. On JDK 21 you won't see it. |
| `FileNotFoundException: contacts.txt` | Run from the project folder, the one containing `pom.xml`, so relative paths resolve there. |
