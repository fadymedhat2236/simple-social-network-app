package com.example.fady.socialnetwork;
public class heapNode
{
    private String name;
    private int number;
    public heapNode()
    {
        name="";
        number=0;
    }
    public heapNode(int number,String name)
    {
        this.name=name;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
