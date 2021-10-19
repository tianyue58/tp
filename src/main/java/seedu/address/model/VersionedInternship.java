package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedInternship extends Internship {

    private List<Internship> internshipStateList = new ArrayList<>();

    private int currentStatePointer;

    public VersionedInternship() {};

    public VersionedInternship(Internship initialInternshipList) {
        internshipStateList.add(initialInternshipList);
        currentStatePointer = 0;
    }

    public void commit(ReadOnlyInternship currentVersion) {
        if (currentStatePointer != internshipStateList.size() - 1) {
            purgeRedundantStates();
        }
        Internship copied = new Internship(currentVersion); //make a copy of the current version
        internshipStateList.add(copied); //add the copied version to the state list
        currentStatePointer++;
    }

    public ReadOnlyInternship undo() {
        currentStatePointer--;
        return getCurrentVersion();
    }

    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    public ReadOnlyInternship redo() {
        currentStatePointer++;
        return getCurrentVersion();
    }

    public boolean canRedo() {
        return currentStatePointer < internshipStateList.size() - 1;
    }

    public ReadOnlyInternship getCurrentVersion() {
        return internshipStateList.get(currentStatePointer);
    }

    public void purgeRedundantStates() {
        internshipStateList.subList(currentStatePointer + 1, internshipStateList.size()).clear();
    }

    public String toString() {
        String stateList = "===== View of Internship state history ===== \n";
        for (ReadOnlyInternship internshipList: internshipStateList) {
            stateList += internshipList + "\n";
        }
        stateList += "===== End of Internship state history list =====";
        return stateList;
    }

}
