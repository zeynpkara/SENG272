ISO/IEC 15939 Measurement Process Simulator


Student Name: Zeynep Kara
Student ID: [Öğrenci Numaranı Yaz]
Course: Software Project II
Assignment: ISO 15939 Measurement Process Simulator

DESCRIPTION:
This project is a Java Swing desktop application that simulates the 5 core steps of the ISO/IEC 15939 software measurement process standard. It utilizes a "Wizard" structure via CardLayout and adheres strictly to the Model-View-Controller (MVC) architectural pattern and Object-Oriented Programming (OOP) principles[cite: 18, 20, 21].

PROJECT STRUCTURE:
- /com/isoproject/main/MainFrame.java : Entry point and controller for the Wizard navigation (CardLayout).
- /com/isoproject/view/ : Contains all abstract (StepPanel) and concrete UI panels (ProfilePanel, DefinePanel, PlanPanel, CollectPanel, AnalysePanel).
- /com/isoproject/view/SessionData.java : Acts as the central 'Model' for State Management, storing selections and calculated scores across steps.
- screenshot.jpg : A visual snapshot of the application running (Step 3: Plan).

HOW TO COMPILE AND RUN (Command Line):
1. Open your terminal or command prompt.
2. Navigate to the root directory of the project (where the 'com' folder is located).
3. Compile the project using the following command:
   javac com/isoproject/main/*.java com/isoproject/view/*.java
4. Run the application using the following command:
   java com/isoproject.main.MainFrame

NOTE: This project has no external dependencies and runs purely on Java SE 17+[cite: 15, 98].
