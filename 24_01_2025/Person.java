class Person implements java.io.Serializable{
    int age;
    transient String name; // static also not serialized similar to transient
}
