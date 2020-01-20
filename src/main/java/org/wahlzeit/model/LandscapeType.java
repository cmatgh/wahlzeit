package org.wahlzeit.model;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class LandscapeType {

    private static HashMap<String, LandscapeType> landscapeTypes = new HashMap<>();

    private LandscapeType superType;
    private Set<LandscapeType> subTypes;

    private String name;

    private void setSuperType(LandscapeType landscapeType) {
        this.superType = landscapeType;
    }

    public LandscapeType getSuperType() {
        return superType;
    }

    public String getName() {
        return name;
    }

    private LandscapeType(String name){
        superType = null;
        subTypes = new HashSet<>();
        this.name = name;

        assertClassInvariants();
    }

    public static LandscapeType valueOf(String name){
        checkNotNull(name);

        LandscapeType landscapeType = doValueOf(name);

        return landscapeType;
    }

    private static LandscapeType doValueOf(String name) {
        return putIfAbsentAndReturn(name);
    }

    private static LandscapeType putIfAbsentAndReturn(String name) {
        if(!landscapeTypes.containsKey(name)){
            synchronized (landscapeTypes){
                if(!landscapeTypes.containsKey(name)){
                    landscapeTypes.put(name, new LandscapeType(name));
                }
            }
        }
        return landscapeTypes.get(name);
    }

    public boolean isSubType(){
        assertClassInvariants();

        boolean result = doIsSubType();

        assertClassInvariants();
        return result;
    }

    private boolean doIsSubType(){
        return superType != null;
    }


    public void addSubType(LandscapeType landscapeType) {
        assertClassInvariants();
        checkNotNull(landscapeType);
        int size = subTypes.size();

        doAddSubType(landscapeType);

        assert subTypes.size() == size +1;
        assertClassInvariants();
    }

    private void doAddSubType(LandscapeType landscapeType){
        landscapeType.setSuperType(this);
        subTypes.add(landscapeType);
    }

    public Landscape createInstance(){
        assertClassInvariants();

        Landscape landscape = new Landscape(this);

        assertClassInvariants();
        return landscape;
    }

    public Iterator<LandscapeType> getSubTypeIterator() {
        assertClassInvariants();

        Iterator<LandscapeType> iterator = subTypes.iterator();

        assertClassInvariants();
        return iterator;
    }

    @Override
    public int hashCode() {
        assertClassInvariants();

        int code = super.hashCode();

        assertClassInvariants();
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        assertClassInvariants();

        boolean result = this == obj;

        assertClassInvariants();
        return result;
    }

    private void assertClassInvariants() {
        assert name != null;
        assert subTypes != null;
    }

}