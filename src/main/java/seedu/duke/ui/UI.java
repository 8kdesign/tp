package seedu.duke.ui;

import seedu.duke.exception.DukeException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.common.Constants.FORMAT_DATE_NORMAL;
import static seedu.duke.common.Constants.INDEX_FIRST;
import static seedu.duke.common.Messages.FORMAT_INDEX_ITEM;
import static seedu.duke.common.Messages.FORMAT_PRINT_TASK;
import static seedu.duke.common.Messages.HEADER_DONE;
import static seedu.duke.common.Messages.HEADER_UNDONE;
import static seedu.duke.common.Messages.MESSAGE_GRADED;
import static seedu.duke.common.Messages.MESSAGE_TASKS_DONE;
import static seedu.duke.common.Messages.MESSAGE_TASKS_EMPTY;
import static seedu.duke.common.Messages.MESSAGE_INVALID_INDICES;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_LIST;
import static seedu.duke.common.Messages.MESSAGE_TASKS_TO_LIST_UNDONE;
import static seedu.duke.common.Messages.NEWLINE;
import static seedu.duke.common.Messages.TAG_GULIO;
import static seedu.duke.common.Messages.TAG_MODULE;

public class UI {
  
    private final Scanner scanner;

    //@@author aliciatay-zls
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints specified message.
     *
     * @param message String to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads input from user.
     *
     * @return String of input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    //@@author 8kdesign
    /**
     * Prints module indicator for user input.
     */
    public void printModuleIndicator() {
        if (ModuleList.getSelectedModule() == null) {
            System.out.print(TAG_GULIO);
        } else {
            String moduleCode = ModuleList.getSelectedModule().getModuleCode();
            System.out.printf(TAG_MODULE, moduleCode);
        }
    }

    //@@author aliciatay-zls
    /**
     * Prints description of all tasks in task list.
     *
     * @param taskList Array list of tasks to print.
     */
    public void printSummarisedTasks(ArrayList<Task> taskList) {
        int tasksCount = 0;
        for (Task task : taskList) {
            tasksCount++;
            String taskListItem = String.format(FORMAT_INDEX_ITEM, tasksCount, task.getDescription());
            printMessage(taskListItem);
        }
    }

    /**
     * Prints all tasks in selected module's task list.
     */
    public void printAllTasks() {
        Module module = ModuleList.getSelectedModule();
        printMessage(String.format(MESSAGE_TASKS_TO_LIST, module.getModuleCode()) + NEWLINE);
        printTasks(module.getTaskList(), false, false);
        printMessage("");
        printTasks(module.getTaskList(), true, false);
    }

    /**
     * Prints all tasks in specified task list.
     *
     * @param taskList Array list of tasks to print.
     * @param isDone Status of tasks in taskList.
     */
    public void printTasks(ArrayList<Task> taskList, Boolean isDone, Boolean isOverview) {
        if (isDone) {
            printMessage(HEADER_DONE);
        } else if (isOverview) {
            printMessage(MESSAGE_TASKS_TO_LIST_UNDONE);
        } else {
            printMessage(HEADER_UNDONE);
        }
        int tasksCount = 0;
        for (Task task : taskList) {
            if (task.getDone() == isDone) {
                tasksCount++;
                printTask(task, tasksCount);
            }
        }
        if (tasksCount == INDEX_FIRST) {
            printEmpty(isDone);
        }
    }

    /**
     * Prints message to indicate task list empty.
     * @param isDone Status of tasks in taskList.
     */
    private void printEmpty(Boolean isDone) {
        if (isDone) {
            printMessage(MESSAGE_TASKS_EMPTY);
        } else {
            printMessage(MESSAGE_TASKS_DONE);
        }
    }

    /**
     * Prints specified task.
     *
     * @param task Task to print.
     * @param tasksCount Position of task in printed list.
     */
    public void printTask(Task task, int tasksCount) {
        String description = task.getDescription();
        String gradedStatus = task.getGraded() ? MESSAGE_GRADED : "";
        String deadline = task.getDeadline().format(DateTimeFormatter.ofPattern(FORMAT_DATE_NORMAL));
        String listItem = String.format(FORMAT_PRINT_TASK, tasksCount, description, gradedStatus, deadline);
        printMessage(listItem);
        if (!task.getRemarks().equals("")) {
            System.out.print("\t" + task.getRemarks() + NEWLINE);
        }
    }

    /**
     * Prints error message of an exception within the program.
     * @param e Exception to be printed
     */
    public void printError(DukeException e) {
        System.out.println(e.getMessage());
    }

    //@@author isaharon

    /**
     * Read input from user and returns list of indices.
     * @param max the maximum accepted index
     * @return an integer arraylist with valid indices
     */
    public ArrayList<Integer> getIndicesFromUser(int max) {
        String userInput = readCommand();
        ArrayList<Integer> indices = Parser.checkIndices(userInput, max);
        return indices;
    }
}
