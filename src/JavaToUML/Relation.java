package JavaToUML;
// This class handles the storage of class relations.
public class Relation
{
    // As of now, we are mainly concerned with basic relation storage,
    // storing the type and classes involved.
    private String class1;
    private String class2;
    private String relationType;
    // and storing them appropriately.
    public Relation(String class1, String class2, String relationType)
    {
        this.class1 = class1;
        this.class2 = class2;
        this.relationType = relationType;
    }

    public String getClass1()
    {
        return class1;
    }

    public void setClass1(String class1)
    {
        this.class1 = class1;
    }

    public String getClass2()
    {
        return class2;
    }

    public void setClass2(String class2)
    {
        this.class2 = class2;
    }

    public String getRelationType()
    {
        return relationType;
    }

    public void setRelationType(String relationType)
    {
        this.relationType = relationType;
    }
}
