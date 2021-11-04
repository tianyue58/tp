---
layout: page
title: Tianyue's Project Portfolio Page
 ---

### Project: internSHIP

InternSHIP is a desktop app for university students to manage their internship applications.
InternSHIP has been optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Morphing Code Base**: Made changes to code base to make it more align with InternSHIP and updated add and edit features.
    * Justification: This change is necessary in order for subsequent implementation of our features to go smoothly.
    * Highlights: This enhancement affects existing commands such as add and edit. A lot of the test cases are changed as well.
    * The change was tedious as it required a lot of updates to existing functional codes and test cases.

* **New Feature**: Added the ability to find application(s).
    * What it does: allows the user to find application(s) based on a given criterion and keyword.
    * Justification: This feature improves the product significantly because a user could find applications with specific characteristics in a long list.
    * This serves as a filter feature as well, helping users to view only the entries of their current interest.
    * Highlights: This enhancement affects existing commands and many test cases needed to be changed too.
    * The implementation was challenging as it required changes to existing commands and perhaps future commands.
    * This enhancement went through a few stages. In the beginning, `find` only allowed finding by company name and position.
    * It was then improved to allow the user to specify other fields such as completion status, application outcome, priority and requirements.
    * In the final version, the `find` command can also check if input for keyword corresponds to the input restriction for different parameters.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=tianyue58)

* **Project management**:
    * Set up the GitHub team codecov
    * Updated Readme (Pull request [\#28](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/28))

* **Enhancements to existing features**:
    * Changed the product icon (Pull request [\#51](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/51))
    * Fixed bugs from Practical Examination Dry Run (Pull request [\#214](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/214))

* **Documentation**:
    * User Guide:
        * Added documentation for the `find` feature (Pull request [\#113](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/113), [\#221](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/221))
    * Developer Guide:
        * Added implementation details of the `find` feature. (Pull request [\#105](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/105)
        * Updated appendix for team effort summary (Pull request [\#223](https://github.com/AY2122S1-CS2103T-W17-1/tp/pull/223)
  
