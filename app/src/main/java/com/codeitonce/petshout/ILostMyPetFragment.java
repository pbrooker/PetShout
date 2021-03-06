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
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import it.sephiroth.android.library.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ILostMyPetFragment extends Fragment
{

    private EditText mBreed;
    private EditText mLocation;
    private EditText mPetDescription;
    private Spinner mSpecies;
    private RadioGroup mGender;
    private Button mSaveImage;
    private Button mSubmitButton;
    private String species;
    private String gender = "";
    private String imagePath;
    private Bitmap resizedImage;
    private Uri selectedImage;
    private File img;
    private String photoID;
    private String filePath;
    private File petShoutPictures;
    private static String remoteURL;
    private String userEmail;
    private String userObjectID;
    private BackendlessUser currentbkuser;
    private Boolean isValidUser;
    private ImageButton mSelectedImage;
    private String mID;


    public ILostMyPetFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String jsonMyObject;
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null)
        {
            jsonMyObject = extras.getString("user");
            isValidUser = extras.getBoolean("validUser");
            Users user = new Gson().fromJson(jsonMyObject, Users.class);
            userEmail = user.getEmail();
            userObjectID = user.getObjectId();

            //for testing
            //Log.i("current user", userObjectID.toString());
            //Log.i("user email", userEmail);
            currentbkuser = new BackendlessUser();

        } else
        {
            isValidUser = false;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ilost_my_pet, container, false);

        mBreed = (EditText) view.findViewById(R.id.breed_input);
        mLocation = (EditText) view.findViewById(R.id.location_input);
        mPetDescription = (EditText) view.findViewById(R.id.pet_description);
        mSpecies = (Spinner) view.findViewById(R.id.species_spinner);
        mGender = (RadioGroup) view.findViewById(R.id.gender_group);
        mSaveImage = (Button) view.findViewById(R.id.image_button);
        mSubmitButton = (Button) view.findViewById(R.id.submit_button);
        mSelectedImage = (ImageButton) view.findViewById(R.id.selected_image_button);


        final RadioButton selection = (RadioButton) view.findViewById(mGender.getCheckedRadioButtonId());


        ArrayAdapter<CharSequence> mArrayAdapter;
        mArrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_data, R.layout.spinner_item);

        mSpecies.setAdapter(mArrayAdapter);


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

                try
                {
                    uploadAsync(img, filePath);

                } catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Image not uploaded, please try again", Toast.LENGTH_SHORT).show();
                }


                if (!(isEmpty(mLocation)) && isRadioButtonChecked(mGender) && (!(isEmpty(mBreed))) && (!(isEmpty(mPetDescription)) && (remoteURL != null)))
                {

                    //testing data flow
                    //Log.i("Add post", "user email is " + userEmail);
                    mID = UUID.randomUUID().toString();

                    Post post = new Post(mLocation.getText().toString(), "L", gender, species, mBreed.getText().toString(),
                            mPetDescription.getText().toString(), remoteURL, userEmail, userObjectID, mID);

                    //db.addPost(post);
                    currentbkuser.setProperty(Constents.TABLE_POSTS, post);
                    currentbkuser.setProperty("objectId", userObjectID);


                    try
                    {

                        Backendless.UserService.update(currentbkuser, new DefaultCallback<BackendlessUser>(getActivity())
                        {
                            @Override
                            public void handleResponse(BackendlessUser backendlessUser)
                            {
                                super.handleResponse(backendlessUser);
                                DBHandler db = new DBHandler(getActivity());
                                db.getPosts();
                                db.close();

                                ThankYouFragment fragment;
                                fragment = new ThankYouFragment();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.mainFrame, fragment);
                                ft.commit();

                            }

                        });
                    } catch (BackendlessException e)
                    {
                        Toast.makeText(getActivity(), "Error - record not added, please try again", Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(getActivity(), R.string.complete_all_fields, Toast.LENGTH_SHORT).show();
                }

            }
        });

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

        //get image from gallery (take photo as option will be a future upgrade)
        mSelectedImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Constents.SELECT_PHOTO);

            }

        });

        mSaveImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    uploadAsync(img, filePath);


                } catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), R.string.image_upload_delayed, Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
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
        while (true)
        {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE)
            {
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
        photoID = UUID.randomUUID().toString() + ".jpg";

        String MEDIA_MOUNTED = "mounted";
        String diskState = Environment.getExternalStorageState();

        if (diskState.equals(MEDIA_MOUNTED))
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

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        filePath = petShoutPictures.getPath();
        Log.i("File Path", petShoutPictures.getPath().toString());


        return resizedImage;

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

                    //InputStream imageStream = null;
                    try
                    {
                        Bitmap yourSelectedImage = decodeUri(getActivity(), selectedImage);
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    Picasso.with(getActivity()).load(selectedImage).placeholder(R.drawable.default_pet_picture).resize(225, 225).centerCrop().into(mSelectedImage);
                }
        }
    }

    private boolean isEmpty(EditText etText)
    {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isEmail(CharSequence target)
    {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
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

    private void uploadAsync(File pic, String filePath) throws Exception
    {

        Backendless.Files.upload(pic, filePath, new AsyncCallback<BackendlessFile>()
        {
            @Override
            public void handleResponse(BackendlessFile response)
            {
                remoteURL = response.getFileURL();
                Log.i("RemoteURL", remoteURL);
                mSubmitButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                Log.i("Server  error - ", fault.getMessage());
            }
        });
    }
}

