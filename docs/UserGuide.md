---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

## Introduction
**ResiReg** (**Res**idential **Reg**ulation) is a productivity app designed to help admin staff at Residential Colleges (RCs)* in NUS with their daily tasks. ResiReg allows admin to allocate rooms to students, manage students records, generate billing and OHS reports, and export CSVs for easy reference and sharing.
 
 **ResiReg** has the following main features:
 
 * Manage records of students.
 * Manage allocations of students to rooms in the College
 * Export records of students, rooms or transactions to CSV files for easy reference and sharing.

__ResiReg__ is optimised for OHS who are fast typists who are used to MS Excel, and prefer typing over other means of input. It comes with:
- A Command Line Interface (CLI) which allows you to access all __ResiReg__ features by typing.
- A Graphical User Interface (GUI) that displays the information you need in a grid format.

> __ResiReg__ is currently a work in progress. Here is a mockup of its skeleton. Stay tuned for our progress!
![](images/Ui.png)

## About this Guide
### Basic Information
This User Guide explains how you (as an OHS admin) can use __ResiReg__ to manage tasks at Residential Colleges.

You may refer to [Quick Start](#quick-start) for a short tutorial on how to run __ResiReg__ on your system and use __ResiReg__'s main features. For a full walkthrough of __ResiReg__, please refer to [Features](#features).

## Quick Start
1. Ensure that Java 11 or above is installed in your computer
2. Download the latest `ResiReg.jar` here.
3. Copy the file to the folder you want to use as the home folder for your **ResiReg**.
4. Double-click the file to start the app. The following window should appear within a few seconds - this is the Session Screen, where you can create, open, or delete interview sessions:
5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing `help` and pressing Enter will open this user guide.
6. Some example commands you can try:
    -  `rooms --vacant`: lists all rooms that are vacant.
    - `allocate A0123456X 08-108`: allocate a student with the student ID A0123456X to room number 08-108.
    - `exit`: exits the app.
8. Refer to “Features” for details of all the commands.

<div markdown="block" class="alert alert-info">
This section explains the format of commands in this User Guide.

- Words in `<angular_brackets>` are the parameters to be supplied by the user e.g. in `deallocate <student_name>`, `<student_name>` is a parameter which can be used as `deallocate Jet New`.
- Items in square brackets are optional e.g `<full_name> [-aka <alias>]` can be used as `Jet New -aka JJ` or as `Jet New`.
- Items separated by <Code>|</Code> indicates a choice between items, but only one item is to be used at any time e.g. in `rooms --vacant` or `rooms --allocated`.
- Items with … after them can be used multiple times including zero times, unless otherwise stated e.g. `[/m <mod> /ig <interest_group>]…` can be used as `/m mod /ig ig`, `/m mod1 /ig ig1 /m mod2 /ig ig2` etc.
</div> 

### I. Housing Management

>**ResiReg** allows you to manage rooms in the Residential College.

#### 1. Listing of all rooms : `rooms` 

Shows a list of all rooms in ResiReg.

Format: `rooms`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Rooms can be filtered based on their allocation status. See the following two commands for further details.
</div>

Examples:
* `rooms` switches to the Rooms tab if it is not already selected, and shows the list of rooms on the right pane. 

#### 2. Listing all vacant rooms : `rooms --vacant`

Shows a list of all vacant rooms i.e those which have not been allocated to any student yet. 

Format: `rooms --vacant`

Examples:
* `rooms --vacant` switches to the Rooms tab if it is not already selected, and shows the list of vacant rooms on the right pane. 


#### 3. Listing all allocated rooms : `rooms --allocated`

Shows a list of all allocated rooms i.e. those which have been allocated to a student. 


Format: `rooms --allocated`

Examples:
* `rooms --allocated` switches to the Rooms tab if it is not already selected, and shows the list of allocated rooms on the right pane. 

#### 4. Allocating a room to a student : `allocate`

Allocates a room to a student i.e denotes that the student currently occupies the room. 

Format: `allocate ri/<room_index> si/<student_index>`
* Allocates a room to the student person at the specified `room_index` and `student_index`. The `room_index` refers to the index number shown in the displayed rooms list, 
and the `student_index` refers to the index number shown in the displayed students list. Both indices **must be positive integers** 1, 2, 3, …​
* Both the student and the room must be unallocated when this command is run. Otherwise, an error message is displayed accordingly.

Examples:
* `allocate ri/1 si/1` allocates the room at `room_index` 1 to the student at `student_index` 1. 

#### 5. Deallocating a room for a student : `deallocate`

Deallocates a room for a student i.e denotes that the student no longer occupies the room. 

Format: `deallocate ri/<room_index> si/<student_index>`
* Deallocates a room to the student at the specified `room_index` and `student_index`. The `room_index` refers to the index number shown in the displayed rooms list, 
and the `student_index` refers to the index number shown in the displayed students list. Both indices **must be positive integers** 1, 2, 3, …​
* The room at `room_index` must be allocated to the student at `student_index`. Otherwise, an error message is displayed accordingly. 

Examples:
* `deallocate ri/1 si/1` deallocates the room at `room_index` 1 for the student at `student_index` 1. 

#### 6. Edit a room allocation for a student : `reallocate`

Edits a room allocation for a student, by first deallocating the student's current room, and then allocating a specified room to the student.

Format: `reallocate si/<student_index> ri/<room_index>`

* Reallocates the room at `room_index` to the student at the specified `student_index` and `student_index`. The `room_index` refers to the index number shown in the displayed rooms list, 
and the `student_index` refers to the index number shown in the displayed students list. Both indices **must be positive integers** 1, 2, 3, …​
* The student at `student_index` must currently have a room allocation (which is not the specified room). Otherwise, an error message is displayed accordingly. 
* The room at `room_index` must currently be vacant. Otherwise, an error message is displayed accordingly. 

Examples:
* `reallocate si/1 ri/2` first deallocates the room currently allocated to student with index 1, then  allocates the room at index 2 to the student.

### II. Student Management

>**ResiReg** allows you to manage students in the Residential College.

#### 1. Listing all students : `students`

Shows a list of all students in ResiReg.

Format: `students`

Examples:
* `students` switches to the Students tab if it is not already selected, and shows the list of students on the right pane. 

#### 2. Adding a student : `add`
Adds a student to ResiReg. The following student details are stored: name, student ID, phone, email, faculty, and optionally, tags.

Format: `add n/<student_name> i/<student_id> p/<8_digit_phone_no> e/<email> f/<faculty> [t/<tag_name>]...`
* The student ID must be a unique 8-digit alphanumeric string, starting with `EO` and ending with 6 digits. Otherwise, an error message is displayed accordingly.
* The pairs of type-prefixes and data (eg. `n/<student_name>`) may given be in any order. 
* The student will not be added if some pieces of information is missing. An error message will be displayed instead.

Examples:
* `add  n/Jet New i/E0407889 p/82462157 e/jn@u.nus.edu f/SOC` successfully creates a new student named Jet New whose student ID is E0407889, phone number is 82462157,
email is jn@u.nus.edu, and faculty is Computing (SOC). 
* `add student n/Jet New i/E0407889 e/jn@u.nus.edu` prompts the user with the following error message (because the faculty field is missing): 
        ```
        Invalid command format!
        add: Adds a student to ResiReg.
        Parameters: n/NAME i/STUDENT_ID p/PHONE e/EMAIL f/FACULTY [t/TAG]...
        Example: add n/John Doe s/E0123456 p/98765432 e/johndoe@u.nus.edu f/FASS
        ```

#### 3. Editing a student : `edit`
Edits an existing student in ResiReg.

Format: `edit <index> [n/<student_name>] [i/<student_id>] [p/<8_digit_phone_no>] [e/<email>] [f/<faculty>] [t/<tag_name>]…​`

* Edits the person at the specified `index`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all of the student’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/82462157 e/johnd@comp.nus.edu.sg` Edits the phone number and email address of the first student to be `82462157` and `johnd@comp.nus.edu.sg` respectively.
*  `edit 2 n/Alpha Queue/` Edits the name of the 2nd student to be `Alpha Queue` and clears all existing tags.

#### 4. Finding a student by name : `find`
Finds students whose names contain any of the given keywords.

Format: `find <keyword> [<more_keywords>]...`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

#### 4. Deleting a student : `delete`
Deletes the specified student from ResiReg.

Format: `delete <index>`

* Deletes the person at the specified `index`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in ResiReg.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### III. General

>**ResiReg** has many general features such as Command Line Interface (CLI) sugar for more efficient usage by experienced users.

#### 1. Asking for help as a first time user

As a first-time user, you can get a walkthrough of all the available commands and their syntax to understand the usage of the commands, using the `help` command.

##### Command
```
help
```
##### Execution Example
```
help
```

*Action*: Lists all user command and their purpose. Includes a link to this User Guide.

*Output*:
The Command History panel on the left will display all available command details.
```
Commands available:
add: Adds a student to ResiReg.
allocate: Allocates a student to a room.
clear: Clears list of students.
...other commands...

You can also refer to our user guide at: https://ay2021s1-cs2103-t16-3.github.io/tp/UserGuide.html
```


#### 2. Checking the syntax for a command

Occasionally, you may need to check the syntax of a command if you need to use the command but are unsure of the command usage.

##### Command
```
help <command_word>
```
##### Execution Example
```
help rooms
```

*Action*: Shows details of the command, including syntax and usage.

*Output*:
The Command History panel on the left will display the command details, including syntax and usage.
```
rooms: Lists all rooms within the system. If the --vacant flag is specified, lists only vacant rooms i.e rooms which have no students allocated to them. 
Otherwise, if the --allocated flag is specified, lists only allocated rooms i.e. rooms which have students allocated to them. 
Parameters: [--vacant | --allocated]
Example: rooms
```

#### 3. Clearing all entries : `clear`
Clears all entries (students and rooms) from ResiReg.

Format: `clear`

#### 4. Exiting ResiReg : `exit`
Exits the program.

Format: `exit`

## FAQ
### Where do I get help?
Just type in the `help` command!


### How do I transfer my data to another Computer?
1. Download the JAR file (`resireg.jar`) on your new computer. 
2. Navigate to where the JAR file is.  
3. Double click on `resireg.jar`
4. Delete the `resireg.json` file in the folder
5. Copy over the `resireg.json` file <em>residing in  your previous **ResiReg** home folder</em> that contains data of your previous **ResiReg** session.

## Command Summary
Action   | Format, Examples
---------|--------------------
*list rooms*    | <code>rooms [--allocated &#124; --vacant]</code>
*allocate room* | `allocate si/<student_index> ri/<room_index>` e.g. `allocate si/1 ri/1`
*deallocate room* | `deallocate si/<student_index> ri/<room_index>` e.g. `deallocate si/1 ri/1`
*edit allocation* | `reallocate si/<student_index> ri/<room_index>` e.g. `reallocate si/1 ri/2`
*list students* | `students`
*add student* | `add n/<student_name> i/<student_id> p/<8_digit_phone_no> e/<email> f/<faculty> [t/<tag_name>]...` e.g.`add  n/Jet New i/E0407889 p/82462157 e/jn@u.nus.edu f/SOC`
*edit student* | `edit <index> [n/<student_name>] [i/<student_id>] [p/<8_digit_phone_no>] [e/<email>] [f/<faculty>] [t/<tag_name>]…` e.g.`edit 1 n/Jet New`
*find student* | `find <keyword> [<more_keywords>]... ` e.g.`find John`
*delete student* | `delete <student_index>` e.g.`delete 2`
*help* | `help [command]` e.g. `help` or `help rooms`
*clear* | `clear`
*exit* | `exit`

