IMPORTANT Note: Due to a technical git force-push error on March 29, the original commit history was lost. This assignment was originally completed and uploaded on its original deadline. 
# Lab 1: Java Swing GUI & File I/O
[cite_start]**Course:** Software Project II [cite: 3]

## Project Description
[cite_start]This application is a "Software Project Registration Form" that collects essential information about software projects and saves them to a local text file named `projects.txt`[cite: 12, 13]. [cite_start]It demonstrates the use of Java Swing for GUI development and persistent data storage through File I/O[cite: 7].

## Features
* [cite_start]**User Interface:** Developed using `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JButton`, and `JComboBox`[cite: 9].
* [cite_start]**Layout Management:** Uses `GridLayout` to organize form fields and buttons[cite: 55, 57].
* [cite_start]**Event Handling:** Implements `ActionListener` for "Save" and "Clear" button logic[cite: 10, 60, 61, 69].
* [cite_start]**Data Storage:** Saves project entries to `projects.txt` in append mode[cite: 17, 66].
* [cite_start]**Modern Date/Time API:** Captures the dynamic registration time for each entry using Java's modern Date/Time API[cite: 22, 36].

## Form Fields
[cite_start]The application collects the following data[cite: 15]:
- Project Name (Free text)
- Team Leader (Free text)
- Team Size (Options: 1-3, 4-6, 7-10, 10+)
- Project Type (Options: Web, Mobile, Desktop, API)
- Start Date (Format: DD/MM/YYYY)

## How to Run
1. Navigate to the `src` directory.
2. [cite_start]Compile and run `ProjectFormApp.java`[cite: 42, 47].
