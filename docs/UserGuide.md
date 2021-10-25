---
layout: page
title: User Guide
---

InternSHIP is a **desktop app for university students to manage their internship applications.** 
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of 
a Graphical User Interface (GUI). If you can type fast, InternSHIP can get your internship management tasks 
done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `InternSHIP.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your InternSHIP app.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 
   Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** 
   and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all the entries in the internship list.

    * **`add c/shopee p/software engineer d/2021-12-12`**: 
   Adds a company `shopee` with the position `software engineer` at deadline `2021-12-12` to the application list.

    * **`delete 3`** : Deletes the 3rd entry in the application list.

    * **`clear`** : Deletes all the application entries.

    * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add Shopee`.

* Items in square brackets are optional. <br>
  e.g `c/COMPANY_NAME [t/TAG]` can be used as `c/Shopee t/Selective` or as `c/Shopee`.

* Items with `…​` after them can be used multiple times including zero times. <br>
e.g. `[r/REQUIREMENT]…​` can be used as `r/` (0 time), `r/CV` (1 time), `r/CV r/Resume` (2 times) etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/COMPANY_NAME p/INTERNSHIP_POSITION`, `p/INTERNSHIP_POSITION c/COMPANY_NAME` 
  is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times,
  only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `c/Grab c/Shopee`, only `c/Shopee` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an application : `add`

Adds a new entry to the application list of InternSHIP. <br>

Minimally, the following fields are required when a new entry is added: 
- `COMPANY_NAME`
- `APPLIED_POSITION`
- `APPLICATION_DEADLINE` <br>

There are three fields that receive their default values.
These fields can be changed later by the `edit` command.
- `COMPLETION_STATUS`  set as `Uncompleted`
- `COMPANY_DECISION` (i.e., application status) set as `Pending`
- `APPLICATION_PRIORITY`: set as `Medium` <br>

Additionally, `APPLICATION_REQUIREMENTS` is an optional field that can either be specified or not.
If not specified at this stage, it can still be added and edited later by the `edit` command.

Format:
`add c/COMPANY_NAME p/APPLIED_POSITION d/APPLICATION_DEADLINE [r/REQUIREMENTS]…​`

Examples:
- `add c/Shopee p/software engineer d/2021-12-12`
- `add c/Shopee p/software engineer d/2021-12-12 r/resume`


### Editing the details of an existing entry : `edit`

Edits the company name, position, deadline, priority, and/or requirements of an existing entry at the specific index
in the application list of InternSHIP.

To edit a specific field, prefix the edited value with the corresponding prefix of that field.

The fields available for updating are `COMPANY_NAME`, `APPLIED_POSITION`, `APPLICATION_DEADLINE`,
`APPLICATION_PRIORITY`, and `APPLICATION_REQUIREMENTS`. At least one of these fields needs to be edited. 
Multiple fields can also be edited at a time.

Existing values will be overwritten and updated to the new input values, while the optional fields that are not 
initialized will take on the new values.

Format: `edit INDEX [c/COMPANY_NAME] [p/APPLIED_POSITION] [d/APPLICATION_DEADLINE] [pr/APPLICATION_PRIORITY] 
[r/APPLICATION_REQUIREMENTS]`

- Edits the application at the specified `INDEX`. 
- The index refers to the index number shown in the currently displayed application list. 
- The index must be a **positive integer** 1, 2, 3, …​


Examples:
* `edit 1 c/Grab d/2021-12-20` Edits the company name and deadline of the 1st application to be `grab` 
and `2021-12-20` respectively.
* `edit 2 p/UI designer` Edits the internship position of the 2nd application to be `UI designer`.


### Completing an application : `complete`

Marks the specified entry in the application list as completed, meaning the user has completed 
the application for that internship.

Format: `complete INDEX`

- Marks the entry at the specified `INDEX` as completed
- The index refers to the index number shown in the currently displayed application list.
- The index must be a **positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `complete 2` marks the 2nd entry in the whole application list as completed.


### Updating the application status : `accept/reject`

Updates the status of an application.

**IMPORTANT NOTE** <br>
The status of an application is the **decision provided by the company**
on whether the candidate has been `Accepted` or `Rejected`. 

When a new application is added, the status is `Pending` by default as it is assumed that the user has not received 
the decision from the company yet. The status can later be changed to either `Accepted` or `Rejected` using the
`accept` or `reject` command.

Format: `accept INDEX` or `reject INDEX`

- Marks the entry at the specified `INDEX` as 'Accepted' or 'Rejected'. 
- The index refers to the index number shown in the currently displayed application list.
- The index must be a **positive integer** 1, 2, 3, …​

Examples:
* `accept` followed by `1` marks the first entry as 'Accepted'.
* `reject` followed by `2` marks the second entry as 'Rejected'.


### Listing all entries : `list`

Shows a list of all the entries of the application list in InternSHIP.

Format: `list`

### Deleting an entry : `delete`

Deletes the specified entry from the application list in Internship.

Format: `delete INDEX`

* Deletes the entry at the specified `INDEX`. 
- The index refers to the index number shown in the currently displayed application list.
- The index must be a **positive integer** 1, 2, 3, …​

Examples:

* `list` followed by `delete 2` deletes the 2nd entry in the application list.


### Clearing all entries : `clear`

Clears all entries from the application list in InternSHIP.

Format: `clear`


### Sorting the entries : `sort`

Sorts all entries in the application list that is currently displayed.

Format: `sort PREFIX`

The fields available to sort by are:
  * COMPANY_NAME `c/` (ascending alphabetical order)
  * APPLIED_POSITION `p/` (ascending alphabetical order)
  * APPLICATION_DEADLINE `d/` (sooner to later deadline)
  * APPLICATION_PRIORITY `pr/` (higher to lower priority)

Examples:

* `list` followed by `sort c/` shows all the applications saved in the current InternSHIP sorted by company name, 
in alphabetical order.
* `find c/Grab` followed by `sort pr/` shows all the applications for the company Grab, sorted by priority, 
from higher to lower priority.


### Finding entries by keyword: `find`

Finds all entries from the application list that match the keyword in the given field.

More than one keyword and field can be provided.

Format: `find PREFIX keyword`

* The fields available to be found are:
    * COMPANY_NAME `c/` 
    * APPLIED_POSITION `p/` 
    * APPLICATION_DEADLINE `d/`
    * COMPLETION_STATUS `c1/` 
    * COMPANY_DECISION (i.e. STATUS) `s/`
    * APPLICATION_PRIORITY `pr/`
    * APPLICATION_REQUIREMENTS `r/`

Examples:

* `find pr/High` shows all the applications whose priority is High.
* `find c1/Completed s/Pending` shows all the applications that match any of the given field and keyword.


### SOON : `soon`

Shows all the applications that are close to the deadlines.

Format: `soon DAYS`
- Find applications whose deadlines are within `DAYS` days
- The number of days must be an **integer** 0, 1, 2, …​

Examples:

* `soon 5` shows applications whose deadlines are within 5 days from today's date.


### UNDO : `undo`

Undoes a change made to the application list. 

**IMPORTANT NOTE** <br>
This command is to be used after a command that actually **does some change** to the application list. 
Inappropriate use may result in unexpected behaviors.

Commands that 'actually does changes' to the application list:
- `add`
- `delete`
- `edit`
- `clear`

Commands that make no change to the application list:
- `list`
- `find`
- `sort`
- `soon`

When successive changes are made, the `undo` command will undo the **most recent** change. 
Successive `undo` commands will undo the previous changes from the most recent to the oldest, until there's no changes
to be restored.

Format: `undo`

Examples:
* `delete 1` followed by `undo` undoes the deletion of the first entry. As a result, that entry will reappear
in the application list.
* `edit 1 p/tester` followed  by `undo` undoes the edition of the first entry's position field. 
As a result, the `position` field of that entry will be restored to its previous value.

### REDO : `redo`

Redoes a change made to the application list.

**IMPORTANT NOTE** <br>
This command is to be used **directly after** an `undo` command.
If an `undo` command undoes a change (e.g., `delete 1` followed by `undo` restores the first entry), 
and it is followed by a command that makes some new changes to the application list (e.g., `edit 2 pr/High` changes
the priority of the second entry to 'High'), then the previous undone action cannot be redone (i.e., the `delete 1`
command which is previously undone cannot be redone).

When successive undone actions are made, the `redo` command will redo the **most recent** undone action.
Successive `redo` commands will redo the undone actions from the most recent to the oldest, until there's no undone
actions to be redone.

Format: `redo`

Examples:
* `delete 1` followed by `undo` undoes the deletion of the first entry. As a result, that entry will reappear
  in the application list.
* `edit 1 p/tester` followed  by `undo` undoes the edition of the first entry's position field.


### Exiting the program : `exit`

Exits the InternSHIP app.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates 
with the file that contains the data of your previous InterSHIP app home folder.

**Q**: How can I save my data in the app?<br>
**A**: InternSHIP data is saved in the hard disk automatically after any command changes the data. 
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format | Example | Notes
--------|-------|----|----
**Accept/Reject** | `DECISION INDEX` | `accept 2` | `DECISION` can only be either `accept` or `reject`
**Add** | `add c/COMPANY_NAME p/INTERNSHIP_POSITION d/DEADLINE_OF_APPLICATION` | `add c/Shopee p/software engineer d/2021-12-12` |
**Clear** | `clear`
**Complete** | `complete INDEX` | `complete 1` | 
**Delete** | `delete INDEX` | `delete 3` |
**Edit** | `edit INDEX [c/COMPANY_NAME] [p/INTERNSHIP_POSITION] [d/DEADLINE_OF_APPLICATION] [pr/PRIORITY] [r/REQUIREMENTS]` | `edit 1 c/Grab d/2021-12-20`
**Exit** | `exit`
**Find** | `find PREFIX KEYWORD` |  `find pr/High` | `PREFIX` refers to the field to match the keyword (`c/` COMPANY_NAME, `p/` APPLIED_POSITION, `d/` APPLICATION_DEADLINE, `c1/` COMPLETION_STATUS, `s/` COMPANY_DECISION (i.e. STATUS), `pr/` APPLICATION_PRIORITY `r/` APPLICATION_REQUIREMENTS)
**Help** | `help` | `help`
**List** | `list`| `list`
**Redo** | `redo` | `redo`
**Soon** | `soon DAYS`| `soon 5` |
**Sort** | `sort PREFIX` | `sort c/` | `PREFIX` refers to the application detail to be sorted by (`c/` COMPANY_NAME, `p/` APPLIED_POSITION, `d/` APPLICATION_DEADLINE, `pr/` APPLICATION_PRIORITY)
**Undo** | `undo`  | `undo`







