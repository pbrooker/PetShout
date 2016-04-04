package com.codeitonce.petshout;

/**
 * Created by Paul on 4/4/2016.
 */
public class Pet
{
    private String petName;
    private String petSpecies;
    private boolean petNeutered;
    private String petGender;
    private String petBreed;
    private int petAge;
    private String petDescription;
    private String addInfo;



    public Pet(String petName, String petSpecies, boolean petNeutered, String petGender, String petBreed, int petAge, String petDescription, String addInfo)
    {
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.petNeutered = petNeutered;
        this.petGender = petGender;
        this.petBreed = petBreed;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.addInfo = addInfo;
    }

    public String getPetName()
    {
        return petName;
    }

    public void setPetName(String petName)
    {
        this.petName = petName;
    }

    public String getPetSpecies()
    {
        return petSpecies;
    }

    public void setPetSpecies(String petSpecies)
    {
        this.petSpecies = petSpecies;
    }

    public boolean isPetNeutered()
    {
        return petNeutered;
    }

    public void setPetNeutered(boolean petNeutered)
    {
        this.petNeutered = petNeutered;
    }

    public String getPetGender()
    {
        return petGender;
    }

    public void setPetGender(String petGender)
    {
        this.petGender = petGender;
    }

    public String getPetBreed()
    {
        return petBreed;
    }

    public void setPetBreed(String petBreed)
    {
        this.petBreed = petBreed;
    }

    public int getPetAge()
    {
        return petAge;
    }

    public void setPetAge(int petAge)
    {
        this.petAge = petAge;
    }

    public String getPetDescription()
    {
        return petDescription;
    }

    public void setPetDescription(String petDescription)
    {
        this.petDescription = petDescription;
    }

    public String getAddInfo()
    {
        return addInfo;
    }

    public void setAddInfo(String addInfo)
    {
        this.addInfo = addInfo;
    }
}
