package JavaToUML;
// This class handles the storage of class relations.
public class Relation
{
    // As of now, we are mainly concerned with basic relation storage,
    // storing the type and classes involved.
    private String relationType;
    private ClassInfo class1;
    private ClassInfo class2;
    // and storing them appropriately.
    public Relation(String relationType, ClassInfo class1, ClassInfo class2)
    {
        this.relationType = relationType;
        this.class1 = class1;
        this.class2 = class2;
    }
}
