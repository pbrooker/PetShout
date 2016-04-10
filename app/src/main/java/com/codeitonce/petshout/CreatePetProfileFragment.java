package com.codeitonce.petshout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.local.UserTokenStorageFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePetProfileFragment extends Fragment
{

    private Spinner mSpecies;
    private String species;
    private EditText mName;
    private EditText mBreed;
    private RadioGroup mGender;
    private EditText mAge;
    private CheckBox mSpayedNeutered;
    private EditText mDescription;
    private EditText mAdditionalInfo;
    private Button mSubmitButton;
    private String gender;
    private ImageView mImageView;
    private Bitmap resizedImage;
    private Uri selectedImage;
    private File img;
    private String photoID;
    private String filePath;
    private File petShoutPictures;
    private static String remoteURL;
    private BackendlessUser currentUser;
    private String addInfo = "";
    private boolean isSpayed = false;
    private Bitmap yourSelectedImage;
    private BackendlessUser user;
    private Serializable userID;



    public CreatePetProfileFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        userID = b.getSerializable(Constents.SAVED_CURRENT_USER);
        Log.i("currentUser", userID.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_pet_profile, container, false);

        mName = (EditText) view.findViewById(R.id.pet_name);
        mBreed = (EditText) view.findViewById(R.id.breed_input);
        mGender = (RadioGroup) view.findViewById(R.id.gender_group);
        mAge = (EditText) view.findViewById(R.id.age_input);
        mSpayedNeutered = (CheckBox) view.findViewById(R.id.spayedNeuteredCheckBox);
        mDescription = (EditText) view.findViewById(R.id.descipriptionColoring);
        mAdditionalInfo = (EditText) view.findViewById(R.id.additional_information);
        mSubmitButton = (Button) view.findViewById(R.id.submit_button);
        mSpecies = (Spinner) view.findViewById(R.id.species_spinner);
        mImageView = (ImageView) view.findViewById(R.id.pet_image);




        ArrayAdapter<CharSequence> mArrayAdapter;
        mArrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_data, R.layout.spinner_item);

        mSpecies.setAdapter(mArrayAdapter);


        //get selected gender
        mSubmitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                try
                {
                    switch (mGender.getCheckedRadioButtonId())
                    {
                        case R.id.male_radio:
                            gender = "M";
                            break;
                        case R.id.female_radio:
                            gender = "F";
                            break;
                    }
                } catch (NullPointerException n)
                {

                }

                mSpayedNeutered.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        isSpayed = true;
                    }
                });

                //check editable field status
                if(!(isEmpty(mName)) && !(isEmpty(mBreed)) && !(isEmpty(mAge)) && !(isEmpty(mDescription)) && isRadioButtonChecked(mGender))
                {

                    String mID = UUID.randomUUID().toString();
                    if(!(isEmpty(mAdditionalInfo)))
                    {
                        addInfo = mAdditionalInfo.getText().toString();
                    }

                    //create local database entry
                    DBHandler db = new DBHandler(getActivity());
                    Pets pet = new Pets(mName.getText().toString(), species, isSpayed, gender, mBreed.getText().toString(), mAge.getText().toString(),
                            mDescription.getText().toString(),
                            addInfo, remoteURL, mID );


                    db.addPet(pet);

                    //get current login info or if not logged in, send to login
                    String currentUserObjectId = Backendless.UserService.loggedInUser();
                    Backendless.UserService.findById(userID.toString(), new AsyncCallback<BackendlessUser>()
                    {
                        @Override
                        public void handleResponse(BackendlessUser response)
                        {
                            Backendless.UserService.setCurrentUser(response);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault)
                        {
                            LoginFragment fragment;
                            fragment = new LoginFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.mainFrame, fragment);
                            ft.commit();
                        }
                    });

                    String userToken = UserTokenStorageFactory.instance().getStorage().get();

                    if(userToken != null && !userToken.equals(""))
                    {
                        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>()
                        {
                            @Override
                            public void handleResponse(Boolean response)
                            {
                                if (response)
                                {
                                    currentUser = Backendless.UserService.CurrentUser();
                                }
                            }

                            @Override
                            public void handleFault(BackendlessFault fault)
                            {
                                LoginFragment fragment;
                                fragment = new LoginFragment();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.mainFrame, fragment);
                                ft.commit();
                            }
                        });
                    }

                    String userEmail = currentUser.getEmail().toString();
                    db.addUserPet(userEmail, mID);
                    //upload image
                    try
                    {
                        uploadAsync(img, filePath);
                        //Log.i("Image URL" , remoteURL.toString());
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.i("Image Status", "Not uploaded");
                    }
                    currentUser.setProperty(Constents.USERS_PET_ID, mID);
                    currentUser.setProperty(Constents.TABLE_PETS, pet);
                    Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>()
                    {
                        @Override
                        public void handleResponse(BackendlessUser response)
                        {
                            Toast.makeText(getActivity(), "Pet profile successfully Added", Toast.LENGTH_SHORT).show();
                            MainActivityLoggedInFragment fragment;
                            fragment = new MainActivityLoggedInFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.mainFrame, fragment);
                            ft.commit();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault)
                        {
                            Toast.makeText(getActivity(), "Pet profile was not added, please try again", Toast.LENGTH_SHORT).show();
                        }
                    });

                    }
                }

            });


        //populate spinner
        mSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int num = position;
                species = "";

                switch (num)
                {
                    case 0:
                        species = getString(R.string.dog);
                        break;
                    case 1:
                        species = getString(R.string.cat);
                        break;
                    case 2:
                        species = getString(R.string.bird);
                        break;
                    case 3:
                        species = getString(R.string.rat);
                        break;
                    case 4:
                        species = getString(R.string.hedgehog);
                        break;
                    case 5:
                        species = getString(R.string.pig);
                        break;
                    case 6:
                        species = getString(R.string.horse);
                        break;
                    case 7:
                        species = getString(R.string.reptile);
                        break;
                    case 8:
                        species = getString(R.string.guinea_pig);
                        break;
                    case 9:
                        species = getString(R.string.rabbit);
                        break;
                    case 10:
                        species = getString(R.string.other);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        mImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Constents.SELECT_PHOTO);

            }
        });

        return view;
    }


    private boolean isRadioButtonChecked(RadioGroup radioGroup)
    {
        boolean isChecked;

        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getActivity(), "Please select a gender", Toast.LENGTH_SHORT).show();
            isChecked = false;
            //no radio buttons are checked
            return isChecked;
        } else
        {

            isChecked = true;
            return isChecked;
            // one of the radio buttons is checked
        }

    }

    private boolean isEmpty(EditText etText)
    {
        return etText.getText().toString().trim().length() == 0;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
    {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode)
        {
            case Constents.SELECT_PHOTO:
                if (resultCode == Activity.RESULT_OK)
                {

                    Uri selectedImage = imageReturnedIntent.getData();
                    mImageView.setImageURI(selectedImage);

                    try
                    {
                        Bitmap yourSelectedImage = decodeUri(getActivity(), selectedImage);
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }

                }
        }
    }

    private Bitmap decodeUri(Context c, Uri selectedImage) throws FileNotFoundException
    {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        resizedImage = BitmapFactory.decodeStream(c.getContentResolver().openInputStream(selectedImage), null, o2);
        photoID = UUID.randomUUID().toString()+ ".jpg";

        String MEDIA_MOUNTED = "mounted";
        String diskState = Environment.getExternalStorageState();

        if (diskState.equals(MEDIA_MOUNTED) )
        {
            petShoutPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }

        img = new File(petShoutPictures, photoID);
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(img);
            resizedImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        filePath = petShoutPictures.getPath();
        Log.i("File Path", petShoutPictures.getPath().toString());


        return resizedImage;

    }
    private static void uploadAsync(File pic, String filePath ) throws Exception
    {

        Backendless.Files.upload(pic, filePath, new AsyncCallback<BackendlessFile>()
        {
            @Override
            public void handleResponse(BackendlessFile response)
            {
                remoteURL = response.getFileURL().toString();
                Log.i("RemoteURL", remoteURL);
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                Log.i("Server  error - ",  fault.getMessage());
            }
        });
    }
}
