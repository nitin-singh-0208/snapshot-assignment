package com.demo.assignment;

import com.demo.assignment.snapshotList.SynchronizedUnbalancedSnapshotList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SnapshotListTest {
    @Test
    void testSnapShotCreation() {
        SynchronizedUnbalancedSnapshotList<Integer> list = new SynchronizedUnbalancedSnapshotList<>();
        Assertions.assertEquals(0, list.version());
        list.add(10);
        list.add(20);
        list.add(30);
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(1, list.snapshot());
        Assertions.assertTrue("Latest Version : 1, List Elements : [10, 20, 30]".equals(list.toString()));
        list.add(100);
        list.add(200);
        list.add(300);
        Assertions.assertEquals(6, list.size());
        Assertions.assertEquals(1, list.version());
        Assertions.assertEquals(2, list.snapshot());
        Assertions.assertTrue("Latest Version : 2, List Elements : [10, 20, 30, 100, 200, 300]".equals(list.toString()));
    }

    @Test
    void testVersionHistory() {
        SynchronizedUnbalancedSnapshotList<Integer> list = new SynchronizedUnbalancedSnapshotList<>();
        list.add(10);
        list.snapshot();
        list.add(100);
        list.add(300);
        list.snapshot();
        list.add(1000);
        list.add(2000);
        list.add(3000);
        list.snapshot();
        Map<Integer, List<Integer>> history = list.getVersionHistory();
        Assertions.assertEquals(3, history.keySet().size());
        Assertions.assertEquals(1,history.get(1).size());
        Assertions.assertEquals(3,history.get(2).size());
        Assertions.assertEquals(6,history.get(3).size());
    }

}
