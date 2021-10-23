---
layout: page
title: User Guide
---

InternSHIP is a **desktop app for university students to manage their internship applications.** It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InternSHIP can get your internship management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `InternSHIP.jar` from [here](https://github.com).

3. Copy the file to the folder you want to use as the _home folder_ for your InternSHIP app.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all the entries in the internship list.

    * **`add`**`c/shopee p/software engineer d/2021-12-12` : Adds a company `shopee` with the position `software engineer` at deadline `2021-12-12` to the internship list.

    * **`delete`**`3` : Deletes the 3rd entry in the internship list.

    * **`clear`** : Deletes all the internship entries.

    * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add Shopee`.

* Items in square brackets are optional.
  e.g `c/COMPANY_NAME [t/TAG]` can be used as `c/Shopee t/Selective` or as `c/Shopee`.

* Items with `…​` after them can be used multiple times including zero times.
e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Selective`, `t/Selective t/Important` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/COMPANY_NAME p/INTERNSHIP_POSITION`, `p/INTERNSHIP_POSITION c//COMPANY_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `c/Grab c/Shopee`, only `c/Shopee` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an application : `add`

Adds a new entry to the list of applications. Application is set as uncompleted, and application status is set as pending by default.

Format: `add c/COMPANY_NAME p/INTERNSHIP_POSITION d/DEADLINE_OF_APPLICATION [t/TAG]…​`

Examples:
* `add c/Shopee p/software engineer d/2021-12-12`


### Editing the details of an existing entry : `edit`

Edits the company name, position or deadline of a specific existing entry in InternSHIP.

Format: `edit INDEX [c/COMPANY_NAME] [p/INTERNSHIP_POSITION] [d/DEADLINE_OF_APPLICATION]`

* Edits the application at the specified `INDEX`. The index refers to the index number shown in the displayed application list. The index **must be a positive integer** 1, 2, 3, …​
* The fields available for updating are COMPANY_NAME, INTERNSHIP_POSITION, and DEADLINE_OF_APPLICATION.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 c/Grab d/2021-12-20` Edits the company name and deadline of the 1st application to be `grab` and `2021-12-20` respectively.
* `edit 2 p/UI designer` Edits the internship position of the 2nd application to be `UI designer`.


### Completing an application : `complete`

Marks the specified entry in the list of applications as completed, meaning the user has completed the application for that internship.

Format: `complete INDEX`

* Marks the entry at the specified `INDEX` as completed
* The index refers to the index number shown in the displayed application list.
* The index must be a positive integer e.g. 1, 2, 3,...

Examples:

* `list` followed by `complete 2` marks the 2nd entry in the application list as completed.


### Updating the application status : `accept/reject`

Updates the status of an application.

Format: `DECISION INDEX`

Examples:
* `accept` followed by `1` marks the first entry as Accepted.
* `reject` followed by `2` marks the second entry as Rejected.


### Listing all entries : `list`

Shows a list of all the entries.


### Deleting an entry : `delete`

Deletes the specified entry from the list of applications.

Format: `delete INDEX`

* Deletes the entry at the specified `INDEX`. 
* The index refers to the index number shown in the displayed application list.
* The index must be a positive integer 1, 2, 3, ...

Examples:

* `list` followed by `delete 2` deletes the 2nd entry in the application list.


### Clearing all entries : `clear`

Clears all entries from the application list.

Format: `clear`


### Sorting all visible entries : `sort`

Sorts all entries in the application list currently seen by the user.

Format: `sort PREFIX`

* The fields available to sort by are:
  * COMPANY_NAME `c/` (ascending alphabetical order)
  * INTERNSHIP_POSITION `p/` (ascending alphabetical order)
  * DEADLINE_OF_APPLICATION `d/` (sooner to later deadline)
  * PRIORITY `pr/` (higher to lower priority)
* At least one of the optional fields must be provided.

Examples:

* `list` followed by `sort c/` shows all the applications saved in the current InternSHIP sorted by company name, in alphabetical order.
* `find c/Grab` followed by `sort pr/` shows all the applications for the company Grab, sorted by priority, from higher to lower priority.


### FIND : `find`

Clears all entries from the application list.

Format: `clear`


### SOON : `soon`

Clears all entries from the application list.

Format: `clear`


### UNDO : `undo`

Clears all entries from the application list.

Format: `clear`


### REDO : `redo`

Clears all entries from the application list.

Format: `clear`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterSHIP app home folder.

**Q**: How can I save my data in the app?<br>
**A**: InternSHIP data is saved in the hard disk automatically after any command changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Example | Notes
--------|-------|----|----
**Accept/Reject** | `DECISION INDEX` | `accept 2` | `DECISION` can only be either `accept` or `reject`
**Add** | `add c/COMPANY_NAME p/INTERNSHIP_POSITION d/DEADLINE_OF_APPLICATION` | `add c/Shopee p/software engineer d/2021-12-12` | the fields to be added can be entered in any order, as long as the tag for each field is specified correctly
**Clear** | `clear`
**Complete** | `complete INDEX` | `complete 1` | the index refers to the index number shown in the displayed application list, and it must be a positive integer
**Delete** | `delete INDEX` | `delete 3` | refer to `complete` command notes
**Edit** | `edit INDEX [c/COMPANY_NAME] [p/INTERNSHIP_POSITION] [d/DEADLINE_OF_APPLICATION]` | `edit 1 c/Grab d/2021-12-20` |  multiple fields can be edited at the same time; they can be entered in any order, as long as the tag for each field is specified correctly
**Exit** | `exit`
**Find** | `find`
**Help** | `help`
**List** | `list`
**Redo** | `list`
**Soon** | `list`
**Sort** | `sort PREFIX` | `sort c/` | `PREFIX` refers to the application detail to be sorted by (`c/`: company name, `p/`: internship position, `d/`: application deadline, `pr/`: application priority)
**Undo** | `list`

