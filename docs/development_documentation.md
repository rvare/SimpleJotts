**Table of Contents**

- [Introduction](#introduction)
	- [Repository Location](#repository-location)
- [Build Guide](#build-guide)
- [Style Guide](#style0guide)
- [Design](#design)
	- [Activity Diagram](#activity-diagram)
	- [Class Diagram](#class-diagram)

- - -

# Introduction

This manual is to help new people understand the structure and relationships between different parts of the applications.

## Repository Location

The repository can be in any folder anywhere you like in your system.

# Build Guide

Before you can compile and build the project, you will need the package [org.json](https://github.com/stleary/JSON-java) from strealy on GitHub. Put this package in a folder called `lib` and rename the jar file `json-java.jar`.

To build the project, run the following in the `src` folder:

```bash
javac -cp ../lib/json-java.jar; -d ../classes org/simplejotts/main/SimpleJottsMain.java
```

Then, to run the project:

```bash
java -cp ../lib/json-java.jar; org/simplejotts/main/SimpleJottsMain
```

# Style Guide

The following is the styling guide for the source code:

- For class names, use nouns and camel case with the first letter upper-case.
	- Ex: `ClassName`.
- For variable names, use nouns and camel case with the first letter in lower-case.
	- Ex: `variableName`.
- Method names will be verbs and camel case with the first letter in lower-case.
	- Ex: `createObject()`, `saveFile()`
- When a method or class is extremely long, at the closing brace, put a comment with the word "end" and the name of the method or class.
- Use tabs for indentation.
	- Configure your editor to use four spaces to represent tabs, code should align properly.
- Constants are in UPPER CASE.
- Use comments like headings in a document to communicate particular sections of code.
	- You can also use these headings to easily jump around in the source file.

# Design

## Activity Diagram

The following is the activity diagram that shows what actions happen.

```mermaid
stateDiagram-v2
state decision <<choice>>
state saveOrCancel <<choice>>
[*] --> OpenMainGUI
OpenMainGUI --> decision
decision --> CreateNewNote
decision --> OpenNote
decision --> DeleteNote
decision --> ExportSelectedNote
decision --> ExportAllNotes
OpenNote --> EditNote
CreateNewNote --> saveOrCancel
EditNote --> saveOrCancel
saveOrCancel --> decision: Save
saveOrCancel --> decision: Cancel
DeleteNote --> [*]
ExportAllNotes --> [*]
ExportSelectedNote --> [*]
EditNote --> [*]
CreateNewNote --> [*]
```

## UML Class Diagram

The following is the UML class diagram of the application. It may appear weird because of how Mermaid.js draws the diagram.

```mermaid
classDiagram
class SimpleJottsMain

class Model
	Model : -noteList LinkedList~Note~
	Model : -FILE_PATH String
	Model : -dirty_flag boolean
	Model : +Model()
	Model : +getNote() void
	Model : +getList() void
	Model : +openDataFile() void
	Model : +saveDataFile() void
	Model : +crateJsonObject() StringBuilder
	Model : +newNote() void
	Model : +deleteNote() void
	Model : +editNote() void
	Model : +saveNote() void
	Model : +exportSelectedNote(fileChooser JFileChooser, selectedIndex int) void
	Model : +exportAllNotes(fileChooser JFileChooser) void
	Model : +exportAllToHTML(fileWriter FileWriter) void
	Model : +exportSelectedToHTML(fileWriter FileWriter, selectedNote Note) void
	Model : +exportAllToText(fileWriter FileWriter) void
	Model : +exportSelectedToText(fileWriter FileWriter, selectedNote Note) void

class Controller
	Controller : -view View
	Controller : -model Model
	Controller : +Controller(model Model, view View)
	Controller : +refreshViewListModel() void
	Controller : +newOperation() void
	Controller : +editOperation() void
	Controller : +deleteOperation() void

class ActionListener
class ExportedSelectedListener
class ExportAllListener
class DocumentationDialogListener
class AboutDialogListener
class NewButtonListener
class DeleteButtonListener
class ListListener
class DoubleClickListener
class MainWindowCloseListener

class View
	View : -noteContentBuffer String
	View : -menuBar JMenuBar
	View : -menu JMenu
	View : -exportSelectedOption JMenuItem
	View : -exportAllOption JMenuItem
	View : -docOption JMenuItem
	View : -aboutOption JMenuItem
	View : -newButton JButton
	View : -deleteButton JButton
	View : -noteList JList~ListMenuItem~
	View : -listModel DefaultListModel~ListMenuItem~
	View : -DEFAULT_WIDTH int
	View : -DEFAULT_HEIGHT int
	View : -WINDOW_TITLE String
	View : +View()
	View : +getNoteContentBuffer() String
	View : +getNoteList() JList~ListMenuItem~
	View : +setNoteContentBuffer(content String) void
	View : +showMainFrame() void
	View : +displayAboutDialog() void
	View : +displayDocumentationDialogWindow()
	View : +createNoteWindow() NoteWindow
	View : +createNoteWindow(content String) NoteWindow
	View : +createExportSelectedWindow() JFileChooser
	View : +createExportAllWindow() JFileChooser
	View : +addToListModel(ll LinkedList~ListMenuItem~) void
	View : +refreshListModel(ll LinkedList~ListMenuItem~) void
	View : +createErrorWindow(message String) void
	View : +addNewButtonListener(newButtonListener ActionListener) void
	View : +addDeleteButtonListener(deleteButtonListener ActionListener) void
	View : +addAboutDialogListener(aboutOptionListener ActionListener) void
	View : +addDocumentationDialogListener(docOptionListener ActionListener) void
	View : +addExportSelectedListener(exportSelectedListener ActionListener) void
	View : +addExportAllListener(exportAllListener ActionListener) void
	View : +addListSelectionListener(ll ListSelectionListener) void
	View : +addDoubleClickListener(ma MouseAdapter) void
	View : +addMainWindowCloseListener(closeListener WindowAdapter) void

class AboutDialog
	AboutDialog : DEFAULT_WIDTH short
	AboutDialog : DEFAULT_HEIGHT short
	AboutDialog : AboutDialgo()

class DocumentationDialog
	DocumentationDialog : DocumentationDialog()

class NoteWindow
	NoteWindow : -noteContentBuffer String
	NoteWindow : -cancelFlag boolean
	NoteWindow : -saveButton JButton
	NoteWindow : -cancelButton JButton
	NoteWindow : -editorArea JTextArea
	NoteWindow : -DEFAULT_WIDTH int
	NoteWindow : -DEFAULT_HEIGHT int
	NoteWindow : -DEFAULT_EDITOR_ROWS int
	NoteWindow : -DEFAULT_EDITOR_COLS int
	NoteWindow : +NoteWindow(noteContent String)
	NoteWindow : +NoteWindow()
	NoteWindow : +getTextEditorContent() String
	NoteWindow : +getCancelFlag() boolean

class CancelButtonListener
class SaveButtonListener

class Note
	Note : -content String
	Note : -date_created LocalDateTime
	Note : -date_modified LocalDateTime
	Note : +Note()
	Note : +Note(content String)
	Note : +getContent() String
	Note : +getDateCreated() Date
	Note : +getDateModified() Date
	Note : +setContent(String newContent) void
	Note : +setDateCreated(Date createdDate) void
	Note : +setDateModified(Date modifiedDate) void

class LinkedList

class ListMenuItem
	ListMenuItem : -preview String
	ListMenuItem : -dateCreated LocalDateTime
	ListMenuItem : -MAX_LENGTH int
	ListMenuItem : ListMenuItem(noteContent String, dateCreated LocalDateTime)

SimpleJottsMain <-- Model
SimpleJottsMain <-- View
SimpleJottsMain <-- Controller

JFrame <|-- View
JDialog <|-- NoteWindow

Model <-- LinkedList
Model <-- Controller

View <-- Controller
View <-- NoteWindow
View <-- AbouDialog
View <-- DocumentationDialog
View *-- JList
JList <-- DefaultListModel

JList <-- ListMenuItem
DefaultListModel <-- ListMenuItem

JDialog <|-- AboutDialog
JDialog <|-- DocumentationDialog
JDialog <|-- NoteWindow

NoteWindow *-- SaveButtonListener
NoteWindow *-- CancelButtonListener

Controller *-- ExportedSelectedListener
Controller *-- ExportAllListener
Controller *-- DocumentationDialogListener
Controller *-- AboutDialogListener
Controller *-- NewButtonListener
Controller *-- DeleteButtonListener
Controller *-- ListListener
Controller *-- DoubleClickListener
Controller *-- MainWindowCloseListener

ActionListener <-- ActionListener
ActionListener <-- ExportedSelectedListener
ActionListener <-- ExportAllListener
ActionListener <-- DocumentationDialogListener
ActionListener <-- AboutDialogListener
ActionListener <-- NewButtonListener
ActionListener <-- DeleteButtonListener
ActionListener <-- ListListener
ActionListener <-- DoubleClickListener
ActionListener <-- MainWindowCloseListener

LinkedList <-- Note
```

