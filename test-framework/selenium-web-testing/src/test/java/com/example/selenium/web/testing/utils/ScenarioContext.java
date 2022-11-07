package com.example.selenium.web.testing.utils;

import static java.util.Arrays.stream;

import com.example.selenium.web.testing.enums.StorageKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.NonNull;
import org.assertj.core.api.SoftAssertions;
import org.springframework.stereotype.Component;

@Component
public class ScenarioContext {


    private final Map<StorageKey, Object> keyStorage = new HashMap<>();
    private final Map<Class<?>, List<?>> classStorage = new HashMap<>();

    @Getter
    private final SoftAssertions softAssertions = new SoftAssertions();

    public void addToStorage(StorageKey key, Object value) {
        keyStorage.put(key, value);
    }

    public Object getFromStorage(StorageKey key) {
        return keyStorage.get(key);
    }

    public <T> T getFromStorage(StorageKey key, Class<T> clazz) {
        return clazz.cast(keyStorage.get(key));
    }

    public <T> List<T> getListFromStorage(StorageKey key, Class<T> clazz) {
        ArrayList<T> listOfObjects = new ArrayList<>();
        Object fromStorage = getFromStorage(key);
        if (fromStorage instanceof List) {
            ((List) fromStorage).forEach(el -> listOfObjects.add(clazz.cast(el)));
        } else {
            listOfObjects.add(clazz.cast(fromStorage));
        }
        return listOfObjects;
    }

    public void removeFromStorage(StorageKey... storageKey) {
        stream(storageKey).forEach(keyStorage::remove);
    }

    public <T> void addToStorage(Class<T> clazz, T object) {
        if (classStorage.get(clazz) == null) {
            classStorage.computeIfAbsent(clazz, v -> {
                ArrayList<T> list = new ArrayList<>();
                list.add(object);
                return list;
            });
        } else {
            ((List<T>) classStorage.get(clazz)).add(object);
        }
    }

    public <T> void addToStorage(@NonNull T object) {
        addToStorage((Class<T>) object.getClass(), object);
    }

    public <T> void addToStorage(Class<T> clazz, List<T> object) {
        if (classStorage.get(clazz) == null) {
            classStorage.put(clazz, object);
        } else {
            ((List<T>) classStorage.get(clazz)).addAll(object);
        }
    }

    public <T> List<T> getFromStorage(Class<T> clazz) {
        return (List<T>) classStorage.get(clazz);
    }

    public <T> T getLastFromStorage(Class<T> clazz) {
        if (getFromStorage(clazz) == null) {
            return null;
        }
        return getFromStorage(clazz).get(getFromStorage(clazz).size() - 1);
    }

    public <T> T getLastFromStorageOrElse(Class<T> clazz, Supplier<T> tCreator) {
        T t = getLastFromStorage(clazz);
        if (t == null) {
            t = tCreator.get();
            addToStorage(clazz, t);
        }
        return t;
    }

    public <T> void removeFromStorage(Class<T> clazz) {
        classStorage.remove(clazz);
    }
}
