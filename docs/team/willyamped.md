---
layout: page
title: Willy's Project Portfolio Page
---

### Project: internSHIP

InternSHIP is a desktop app for university students to manage their internship applications. 
InternSHIP has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to complete an application. 
  * What it does: allows the user to mark an application as completed which means that the user has submitted all the documents required.
  * Justification: This feature improves the product significantly because a user can better organise the applications, differentiating completed and uncompleted applications.
  * Highlights: This enhancement affects existing commands.
  * For example, this feature also allows `soon` command to filter out completed applications.
  * The implementation too was challenging as it required changes to existing commands and perhaps future commands.

* **New Feature**: Added the ability to show upcoming deadlines.
  * What it does: allows the user to find application deadlines within specified number of days.
  * Justification: This feature improves the product significantly because a user has the flexibility to enter a the number of days within which application submissions are due.
  * This serves as a reminder feature as well, helping to prevent the user from missing important deadlines.
  * Highlights: This enhancement affects existing command and many test cases needed to be changed too.
  * The implementation too was challenging as it required changes to existing commands and perhaps future commands.
  * This enhancement went through a few stages. In the beginning, `soon` only showed upcoming deadlines within 3 days. 
  * It was then improved to allow the user to specify the number of days.
  * In the final version, the `soon` command can show both upcoming application submission deadlines and interview date and time as well.

* **New GUI**: Changed the GUI from vertical to horizontal view.
  * What it does: allows the user to see the applications more easily and clearly.
  * Justification: This feature improves the product significantly because the GUI is the first thing that a user sees when using the application.
  * Better GUI leaves a good first impression on the user, improving their overall user experience.
  * Highlights: This implementation of the new GUI was challenging as it needed to be constantly changed as more fields were added.
  * Several factors were also taken into consideration, such as the right width for each column, to allow the GUI to be displayed correctly on any platform.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=willyamped)

* **Project management**:
  * Managed releases `v1.3` - (1 release) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI layout (Pull request [\#117](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/117))
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#103](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/103))
  * Fixed bugs from Practical Examination Dry Run (Pull request [\#216](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/216))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `soon` and `complete` (Pull request [\#126](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/126), [\#33](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/33))
  * Developer Guide:
    * Added implementation details of the `soon` and `complete` features. (Pull request [\#146](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/146), [\#96](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/96))

* **Tools**:
  * Integrated a third party library (DateTimeFormatter) to the project (Pull request [\#216](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/216))

