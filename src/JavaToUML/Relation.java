package JavaToUML;

public class Relation
{
    private String relationType;
    private ClassInfo class1;
    private ClassInfo class2;

    public Relation(String relationType, ClassInfo class1, ClassInfo class2)
    {
        this.relationType = relationType;
        this.class1 = class1;
        this.class2 = class2;
    }
}
