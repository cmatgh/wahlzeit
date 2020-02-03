package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;


/*
 *
 * LandscapeType/LandscapeType
 * public collaboration ParentChild{
 *   public role Parent{
 *      public void addChild(Child c);
 *      public void removeChild(Child c);
 *      public Iterator<Child> getIterator();
 *   }
 *
 *   public role Child{
 *      public void setParent(Parent p);
 *      public Parent getParent();
 *   }
 *}
 *
 */

/*
 *
 * Landscape/LandscapeType
 * public collaboration TypeObject{
 *   public role BaseObject{
 *      public void setTypeObject(TypeObject to);
 *   }
 *
 *   public role TypeObject{
 *       public BaseObject createInstance();
 *   }
 *}
 *
 */
@PatternInstance(
        patternName = "Value Object",
        participants = { "ValueObject" }
)
public class LandscapeType /* binds TypeObject.TypeObject */ /* binds ParentChild.Parent */ /* binds ParentChild.Child */{

    private static HashMap<String, LandscapeType> landscapeTypes = new HashMap<>();

    private LandscapeType superType;
    private Set<LandscapeType> subTypes;

    private String name;

    /*
    * ParentChild.Child::setParent(Parent o)
     */
    private void setSuperType(LandscapeType landscapeType) {
        this.superType = landscapeType;
    }

    /*
     * ParentChild.Child::getParent()
     */
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

    /*
     * ParentChild.Parent::addChild(Child c)
     */
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

    /*
    * TypeObject.TypeObject::createInstance()
     */
    public Landscape createInstance(){
        assertClassInvariants();

        Landscape landscape = new Landscape(this);

        assertClassInvariants();
        return landscape;
    }

    /*
     * ParentChild.Parent::getIterator()
     */
    public Iterator<LandscapeType> getSubTypeIterator() {
        assertClassInvariants();

        Iterator<LandscapeType> iterator = subTypes.iterator();

        assertClassInvariants();
        return iterator;
    }

    public boolean hasSubType(String subType){
        assertClassInvariants();
        checkNotNull(subType);

        boolean result = doHasSubType(subType);

        assertClassInvariants();
        return result;
    }

    private boolean doHasSubType(String subType){
        if(this.name.equals(subType)) return true;

        for(LandscapeType sub: subTypes){
            if(sub.doHasSubType(subType)) return true;
        }

        return false;
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

    protected static void clearMappings(){
        landscapeTypes.clear();
    }
}