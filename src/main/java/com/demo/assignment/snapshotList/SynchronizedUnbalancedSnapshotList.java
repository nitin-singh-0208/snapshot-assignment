package com.demo.assignment.snapshotList;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SynchronizedUnbalancedSnapshotList<E> extends ArrayList<E> implements SnapshotList<E> {
    private int currentVersion = 0;

    /*This map stores the version number as key and the end index for that version as the value.
     * */
    private final LinkedHashMap<Integer, Integer> versions = new LinkedHashMap<>();

    @Override
    public void dropPriorSnapshots(int version) {
        if (version >= currentVersion) {
            throw new InvalidVersionException("Expected version lesser than the current version");
        }
        for (int i = 1; i < version; i++) {
            versions.remove(i);
        }
    }

    @Override
    public E getAtVersion(int index, int version) {
        if (version > currentVersion) {
            throw new InvalidVersionException("Queried version less than or equal to current version");
        }
        if (index > versions.get(version)) {
            throw new IndexOutOfBoundsException("Could not find index " + index + " in version " + version);
        } else {
            return get(index);
        }
    }

    @Override
    public int snapshot() {
        versions.put(++currentVersion, size() - 1);
        return currentVersion;
    }

    @Override
    public int version() {
        return currentVersion;
    }

    @Override
    public String toString() {
        return "Latest Version : " + version() + ", List Elements : " + super.toString();
    }


}
