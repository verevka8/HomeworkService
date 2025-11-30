package org.example.fileanalysis.application.dto;

import org.example.fileanalysis.domain.model.User;

import java.util.Set;

public class PlagiatorsReport {
    private int groupId;
    private Set<User> plagiators;

    public PlagiatorsReport(int groupId, Set<User> plagiators) {
        this.groupId = groupId;
        this.plagiators = plagiators;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Set<User> getPlagiators() {
        return plagiators;
    }

    public void setPlagiators(Set<User> plagiators) {
        this.plagiators = plagiators;
    }
}
