package com.yang.software.mm.data.session;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionCache {
    private SessionCache() {
        // 禁止实例化
    }

    private static Map<String, Map<Object, Object>> sessionCache = new ConcurrentHashMap<String, Map<Object, Object>>();

    public static ThreadLocal<String> sessionId = new ThreadLocal<String>();

    public static SessionValue getSessionValue() {
        if (sessionId.get() == null
                || !sessionCache.containsKey(sessionId.get())) {
            return null;
        }

        return (SessionValue) sessionCache.get(sessionId.get()).get(
                SessionCacheKey.SESSION_VALUE);
    }

    public static void setSessionValue(SessionValue sessionValue) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(SessionCacheKey.SESSION_VALUE, sessionValue);
        sessionCache.put(sessionId.get(), map);
    }

    public static boolean isSessionExist(String sessionId) {
        return sessionCache.containsKey(sessionId);
    }

    public static void removeSession(String sessionId) {
        sessionCache.remove(sessionId);
    }

    public static void put(Object key, Object value) {
        sessionCache.get(sessionId.get()).put(key, value);
    }

    public static Object get(Object key) {
        return sessionCache.get(sessionId.get()).get(key);
    }

    public static void remove(Object sessionCacheKey) {
        sessionCache.get(sessionId.get()).remove(sessionCacheKey);
    }
}
