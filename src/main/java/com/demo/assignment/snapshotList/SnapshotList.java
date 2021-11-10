package com.demo.assignment.snapshotList;

import java.util.List;

interface SnapshotList<E> extends List<E> {

    void dropPriorSnapshots(int version);

    E getAtVersion(int index, int version);

    int snapshot();

    int version();
}