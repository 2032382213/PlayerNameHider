package com.powergg.playernamehider.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UidUtils {
    public static final Map<UUID, Integer> map = new HashMap<>();
    public static int availableUid = 1000;

    public static void reset() {
        map.clear();
        availableUid = 1000;
    }

    public static int getUid(UUID uuid) {
        return map.containsKey(uuid) ? map.get(uuid) : getAvailableUid(uuid);
    }

    public static int getAvailableUid(UUID uuid) {
        map.put(uuid, availableUid++);
        return availableUid;
    }
}
