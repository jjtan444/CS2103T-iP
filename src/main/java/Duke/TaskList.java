package Duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {

    private ArrayList<Task> lst;

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Returns the TaskList of tasks that matches the search term.
     * @param input search term.
     * @return the result list.
     */
    public TaskList findTask(String input) {
        TaskList resultList = new TaskList(new ArrayList<Task>());
        for (Task t : lst) {
            if (isSimilar(input, t.getDescription())) {
                resultList.addTask(t);
            }
        }
        return resultList;
    }

    /**
     * Adds task to list.
     * @param t task to add.
     */
    public void addTask(Task t) {
        this.lst.add(t);
    }

    /**
     * Deletes task from list.
     * @param i index of task starting from 1.
     * @return the deleted task.
     */
    public Task deleteTask(int i) {
        Task removed = lst.get(i - 1);
        lst.remove(i - 1);
        return removed;
    }

    /**
     * Mark task as done.
     * @param i index of task starting from 1.
     * @return the marked task.
     */
    public Task markTask(int i) {
        Task t = lst.get(i - 1);
        t.markAsDone();
        return t;
    }

    /**
     * Mark task as done.
     * @param i index of task starting from 1.
     * @return the marked task.
     */
    public Task unmarkTask(int i) {
        Task t = lst.get(i - 1);
        t.markAsNotDone();
        return t;
    }

    /**
     * Prints items in list.
     */
    public void printList() {
        int count = 1;
        for (Task tsk : this.lst) {
            System.out.println(String.valueOf(count++) + "." + tsk);
        }
    }

    /**
     * Converts list to list of string.
     * @return
     */
    public ArrayList<String> toStringList() {
        ArrayList<String> items = new ArrayList<>();
        if (!(this.lst.isEmpty())) {
            for (int i = 0; i < lst.size(); i++) {
                Task t = lst.get(i);
                String type = t.toString().substring(1, 2);
                if (type.equals("T")) {
                    items.add(String.format("%s ~ %s ~ %s\n", type, t.getStatusIcon(), t.getDescription()));
                } else {
                    items.add(String.format("%s ~ %s ~ %s ~ %s\n", type, t.getStatusIcon(),
                            t.getDescription(), t.getDate()));
                }
            }
        }
        return items;
    }

    /**
     * Returns size of list.
     * @return list size.
     */
    public int getSize() {
        return lst.size();
    }

    private static boolean isSimilar(String input, String toCompare){
        Pattern pattern = Pattern.compile(".*" + input + ".*");
        Matcher matcher = pattern.matcher(toCompare);
        return (matcher.find());
    }
}
