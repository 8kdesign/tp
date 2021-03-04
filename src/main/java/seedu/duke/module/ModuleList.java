package seedu.duke.module;

import seedu.duke.storage.Loader;
import seedu.duke.storage.Writer;

import java.util.ArrayList;

public class ModuleList {

    private static final Loader loader = new Loader();
    private static final Writer writer = new Writer();

    private static final ArrayList<String> moduleList = new ArrayList<>();
    public static Module selectedModule;

    public static ArrayList<String> getModuleList() {
        return moduleList;
    }


    /**
     * Adds a new module to the moduleFileList.
     *
     * @param name Module name, excluding ".txt".
     */
    public static void addModule(String name) {
        if (moduleList.contains(name)) {
            //Error, module already exists
            return;
        }
        moduleList.add(name);
        writer.createFile(name);
    }


    /**
     * Removes selected module and deletes module file.
     *
     * @param index index of module to remove.
     */
    public static void removeModule(int index) {
        if (index < 0 || index >= moduleList.size()) {
            return;
        }
        if (writer.deleteFile(moduleList.get(index))) {
            moduleList.remove(index);
        } else {
            //Unable to remove
        }
    }


    /**
     * Loads the current module from storage.
     *
     * @param name Module name, excluding ".txt".
     * @return True if successful, false if unable to find file.
     */
    public static boolean setSelectedModule(String name) {
        if (!moduleList.contains(name)) {
            //Unable to find file
            return false;
        }
        selectedModule = loader.loadModule(name);
        return selectedModule != null;
    }


    /**
     * Resets selected module by setting it to null.
     */
    public static void reset() {
        selectedModule = null;
    }
}