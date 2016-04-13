package com.codeitonce.petshout;

/**
 * Created by Paul on 4/4/2016.
 */
public class Pets
{
    private String petName;
    private String petSpecies;
    private String petNeutered;
    private String petGender;
    private String petBreed;
    private String petAge;
    private String petDescription;
    private String addInfo;
    private String imagePath;
    private String objectId;
    private String petId;

    public Pets()
    {
        //empty constructor for populating PetArray
    }



    public Pets(String petName, String petSpecies, String petNeutered, String petGender, String petBreed, String petAge, String petDescription, String addInfo, String imagePath, String petId)
    {
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.petNeutered = petNeutered;
        this.petGender = petGender;
        this.petBreed = petBreed;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.addInfo = addInfo;
        this.imagePath = imagePath;
        this.petId = petId;
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

    public String isPetNeutered()
    {
        return petNeutered;
    }

    public void setPetNeutered(String petNeutered)
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

    public String getPetAge()
    {
        return petAge;
    }

    public void setPetAge(String petAge)
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

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId( String objectId )
    {
        this.objectId = objectId;
    }

    public String getPetId()
    {
        return petId;
    }

    public void setPetId(String petId)
    {
        this.petId = petId;
    }
}
