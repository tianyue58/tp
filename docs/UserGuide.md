---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add Shopee`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `COMPANY_NAME/INTERNSHIP_POSITION`, `INTERNSHIP_POSITION/COMPANY_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `delete 1 2`, only `2` will be taken as the index to be deleted.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an application: `add`

Adds a new entry to the list of applications. Application is set as uncompleted, and application status is set as pending by default.

Format: `add c/COMPANY_NAME p/INTERNSHIP_POSITION d/DEADLINE_OF_APPLICATION`

Examples:
* `add c/shopee p/software engineer d/2021-12-12`

### Listing all entries : `list`

Shows a list of all the entries.

### Deleting an entry : `delete`

Deletes the specified entry from the list of applications.

Format: `delete INDEX`

* Deletes the entry at the specified `INDEX`. 
* The index refers to the index number shown in the displayed application list.
* The index must be a positive integer 1, 2, 3, ...

Examples:

* `list` followed by delete 2` deletes the 2nd entry in the application list.

### Clearing all entries : `clear`

Clears all entries from the application list.

Format: `clear`

### Updating the details of an existing entry: `update`

Updates the company name, position or deadline of a specific existing entry in InternSHIP.

Format: `update INDEX/FIELD/NEW_VALUE`

* Updates the specified field at the specified index. 
* Indices start from 1. 
* The fields available for updating are COMPANY_NAME, INTERNSHIP_POSITION, and DEADLINE_OF_APPLICATION.
* To update the COMPANY_NAME, input `name`for the field.
* To update the INTERNSHIP_POSITION, input `position`for the field.
* To update the DEADLINE_OF_APPLICATION, input `deadline`for the field.

Examples:
* `update 2/name/shopee` updates the COMPANY_NAME to `Shopee`
* `update 2/position/web developer` updates the INTERNSHIP_POSITION to `web developer`
* `update 2/deadline/2021-12-09` updates the DEADLINE_OF_APPLICATION to `2021-12-09`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Completing an application : `complete`

Marks a specified entry in InternSHIP as completed, meaning the user has completed the application for that internship.

Format: `complete INDEX`
* Marks an entry as completed at the specified `INDEX`
* The `INDEX` refers to the number shown in the list.
* The index must be a positive integer e.g. 1, 2, 3,...

### Updating the application status : `accept/reject`

Updates the status of an application.

Format: `DECISION INDEX`

Examples:
* `accept` followed by `1` marks the first entry as Accepted.
* `reject` followed by `2` marks the second entry as Rejected.



### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add c/COMPANY_NAME p/INTERNSHIP_POSITION d/DEADLINE_OF_APPLICATION` <br> e.g., `add c/shopee p/software engineer d/2021-12-12`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Complete** | `complete INDEX` <br> e.g., `complete 2`
**Accept** | `accept INDEX` <br> e.g., `accept 1`
**Reject** | `reject INDEX` <br> e.g., `reject 3`
