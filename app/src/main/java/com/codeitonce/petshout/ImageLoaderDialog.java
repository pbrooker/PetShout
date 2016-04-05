package com.codeitonce.petshout;

/**
 * Created by Paul on 4/5/2016.
 */

//Dialog for choosing between new camera image or gallery image.
//public class ImageLoaderDialog extends android.support.v4.app.DialogFragment {
//    private ImageView targetImageView = null;
//    final int TAKE_PICTURE = 0;
//    final int PICK_PHOTO = 1;
//
//    public ImageLoaderDialog (View view) {
//        targetImageView = (ImageView) view;
//    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Selection");
//        final String[] imageSources = getResources().getStringArray(R.array.imageSources);
//        builder.setItems(imageSources, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int item) {
//                switch(item) {
//                    case TAKE_PICTURE:
//                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        getActivity().startActivityForResult(takePicture, TAKE_PICTURE);
//                        break;
//                    case PICK_PHOTO:
//                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        getActivity().startActivityForResult(pickPhoto, PICK_PHOTO);
//                        break;
//                }
//            }
//        });
//        return builder.create();
//    }

    //Set image to user's selected image.
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        if (resultCode == android.app.Activity.RESULT_OK) {
//            Uri selectedImage = intent.getData();
//            Log.i("IMAGEN", "" + selectedImage);
//            targetImageView.setImageURI(selectedImage);
//        }
//    }
//}
//Any help would be very appreciated.