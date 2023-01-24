package com.example.drivewayparking.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Activity.LoginActivity;
import com.example.drivewayparking.Activity.Signup;
import com.example.drivewayparking.Model.Admin;
import com.example.drivewayparking.Model.ImageRequest;
import com.example.drivewayparking.Model.ImageResponse;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.Model.UserRequest;
import com.example.drivewayparking.R;

import org.java_websocket.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Profile fragment.
 * @author: Varun Advani
 */
public class ProfileFragment extends Fragment {

    private CircleImageView user_image;

    private Bitmap bmp;
    /**
     * The Image.
     */
    ImageView image,
    /**
     * The Password show.
     */
    password_show;
    /**
     * The User name.
     */
    TextView user_name,
    /**
     * The User email.
     */
    user_upload,
    /**
     * The User email print.
     */
    user_email_print,
    /**
     * The Change user password.
     */
    change_userPassword,
    /**
     * The User phone.
     */
    user_phone,
    /**
     * The Show password.
     */
    showPassword;
    /**
     * The Logout button.
     */
    Button logout_button,
    /**
     * The Delete account button.
     */
    delete_account_button;
    /**
     * The Email.
     */
    String email,
    /**
     * Admin Email
     */
     admin_email,
    /**
     * The Password.
     */
    password,
    /**
     * The Name.
     */
    name,
    /**
     * The Phone.
     */
    phone;
    /**
     * The Change password.
     */
    EditText changePassword,
    /**
     * The Change email.
     */
    changeEmail;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Long userId;
    private Long temp_id, admin_temp_id;
    private Bundle savedState = null;
    private final int IMAGE_REQUEST_ID = 1;
    private static final String PREFERENCES_CONTADORLIGA =  "ChangeThisTextWithSomethingYouPrefer";
    private static final String PREFERENCES_CONTADORLIGA_KEY = "ChangeThisTextWithSomethingYouPrefer";
    int contadorliga = 0;

    /**
     * Instantiates a new Profile fragment.
     */
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * New instance profile fragment.
     *
     * @param param1 the param 1
     * @param param2 the param 2
     * @return the profile fragment
     */
// TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getActivity().getSharedPreferences(PREFERENCES_CONTADORLIGA, Context.MODE_PRIVATE);
        contadorliga = settings.getInt(PREFERENCES_CONTADORLIGA_KEY, 0);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            email = getArguments().getString("f_email");
            admin_email = getArguments().getString("admin_email");
            temp_id = getArguments().getLong("f_user_id");
            admin_temp_id = getArguments().getLong("admin_id");
            System.out.println("Temp id is " + temp_id);
            System.out.println(email);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                byte[] bytes;
                File file = new File(getActivity().getCacheDir(), "image_user" + temp_id + ".jpeg");
                bmp = BitmapFactory.decodeStream(inputStream);
                user_image.setImageBitmap(bmp);
                user_image.setRotation(90);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
                bytes = output.toByteArray();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"),file);
                MultipartBody.Part image_upload = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), userId.toString());
                Call<Integer> call = ApiClient.getImageApiService().uploadImageUser(image_upload, requestBody);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()){
                            if(response.body() == 0){
                                Toast.makeText(getActivity(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "Image Not Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment

        user_name = view.findViewById(R.id.nameUser_2);
        user_upload = view.findViewById(R.id.upload_image);
        user_email_print = view.findViewById(R.id.print_email_2);
        change_userPassword = view.findViewById(R.id.user_password_2);
        user_phone = view.findViewById(R.id.user_phone_2);
        logout_button  = view.findViewById(R.id.logout_2);
        delete_account_button = view.findViewById(R.id.deleteAccount_2);
        showPassword = view.findViewById(R.id.show_password_2);
        password_show = view.findViewById(R.id.password_toggle_2);
        user_image = view.findViewById(R.id.imageView_user);




        Bundle bundle = new Bundle();
        if(savedInstanceState != null && savedState == null){
            savedState = savedInstanceState.getBundle("data");
        }
        if(savedState != null){
            user_name.setText(savedState.getString("s_name"));
            user_email_print.setText(savedState.getString("s_email"));
            user_phone.setText(savedState.getString("s_phone"));
            showPassword.setText(savedState.getString("s_password"));
        }

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
//        String email_data = this.getArguments().getString("f_email");
        if(email != null) {
            Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
            System.out.println(email);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        user_name.setText(response.body().getFirstName() + " " + response.body().getLastName());
                        name = response.body().getFirstName() + " " + response.body().getLastName();
                        System.out.println(response.body().getFirstName() + " " + response.body().getLastName());
                        user_email_print.setText(response.body().getEmail());
                        user_phone.setText(response.body().getPhoneNumber());
                        showPassword.setText(response.body().getPassword());
                        userId = response.body().getId();
                        System.out.println("User ID: " + userId);
                        ImageRequest imageRequest = new ImageRequest();
                        imageRequest.setUserId(temp_id);
                        Call<List<ImageResponse>> call_image = ApiClient.getImageApiService().downloadImage(imageRequest);
                        System.out.println("User ID check 2: " + userId);
                        call_image.enqueue(new Callback<List<ImageResponse>>() {
                            @Override
                            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                                if(response.isSuccessful()){
                                    if(response.body().size() > 0) {
                                        System.out.println(response.body().get(0).toString());
                                        byte[] decodedString = new byte[0];
                                        try {
                                            decodedString = Base64.decode(response.body().get(0).getContent(), android.util.Base64.DEFAULT);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        user_image.setImageBitmap(decodedByte);
                                        // user_image.setRotation(90);
                                    }
                                }
                                else{
                                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("Error: " + t.getMessage());
                                System.out.println(imageRequest);
                            }
                        });
                        //needs to be figured out
                        //bundle.putInt("user_id", userId);
                        showPassword.setTransformationMethod(new PasswordTransformationMethod());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
        else{
            Call<Admin> call_admin = ApiClient.getAdminApiService().getAdminByEmail(admin_email);
            call_admin.enqueue(new Callback<Admin>() {
                @Override
                public void onResponse(Call<Admin> call, Response<Admin> response) {
                    if(response.isSuccessful()){
                        user_name.setText(response.body().getFirstName() + " " + response.body().getLastName());
                        name = response.body().getFirstName() + " " + response.body().getLastName();
                        user_email_print.setText(response.body().getEmail());
                        user_phone.setText(response.body().getPhoneNumber());
                        showPassword.setText(response.body().getPassword());
                        userId = response.body().getId();
                        System.out.println("User ID: " + userId);
                        //needs to be figured out
                        //bundle.putInt("user_id", userId);
                        ImageRequest imageRequest = new ImageRequest();
                        imageRequest.setAdminId(admin_temp_id);
                        Call<List<ImageResponse>> call_image = ApiClient.getImageApiService().downloadImage(imageRequest);
                        System.out.println("User ID check 2: " + userId);
                        call_image.enqueue(new Callback<List<ImageResponse>>() {
                            @Override
                            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                                if(response.isSuccessful()){
                                    if(response.body().size() > 0) {
                                        System.out.println(response.body().get(0).toString());
                                        byte[] decodedString = new byte[0];
                                        try {
                                            decodedString = Base64.decode(response.body().get(0).getContent(), android.util.Base64.DEFAULT);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                        user_image.setImageBitmap(decodedByte);
                                        // user_image.setRotation(90);
                                    }
                                }
                                else{
                                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
                                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("Error: " + t.getMessage());
                                System.out.println(imageRequest);
                            }
                        });
                        showPassword.setTransformationMethod(new PasswordTransformationMethod());
                    }
                    else{
                        Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Admin> call, Throwable t) {
                     Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        System.out.println(email);
//        ImageRequest imageRequest = new ImageRequest();
//        imageRequest.setUserId(temp_id);
//        Call<List<ImageResponse>> call_image = ApiClient.getImageApiService().downloadImage(imageRequest);
//        System.out.println("User ID check 2: " + userId);
//        call_image.enqueue(new Callback<List<ImageResponse>>() {
//            @Override
//            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
//                if(response.isSuccessful()){
//                    if(response.body().size() > 0) {
//                        System.out.println(response.body().get(0).toString());
//                        byte[] decodedString = new byte[0];
//                        try {
//                            decodedString = Base64.decode(response.body().get(0).getContent(), android.util.Base64.DEFAULT);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        user_image.setImageBitmap(decodedByte);
//                       // user_image.setRotation(90);
//                    }
//                }
//                else{
//                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {
//                 Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                 System.out.println("Error: " + t.getMessage());
//                 System.out.println(imageRequest.toString());
//            }
//        });

        user_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Pick image"),
                    IMAGE_REQUEST_ID);
            }
        });
        //Override the onActivityResult method


        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
               // finish();
            }

        });

        delete_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.confirm_delete);
                Button delete = dialog.findViewById(R.id.confirmDelete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Call<Integer> call = ApiClient.getUserApiService().deleteUser(email);
                        call.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                Toast.makeText(getActivity(), "Account deleted ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), Signup.class));
                               // finish();
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        change_userPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogPlus dialogPlus = DialogPlus.newDialog(getActivity()).setContentHolder(
//                        new ViewHolder(R.layout.update_password)).setExpanded(true, 500).create();
//                dialogPlus.show();
////                UpdatePassword password = new UpdatePassword();
//                changePassword = view.findViewById(R.id.passEditBox);
//                Button confirm_update = findViewById(R.id.passEditUpdate);
//                // changePassword.setText(change_userPassword.getText().toString());
                 Dialog dialog = new Dialog(getActivity());
                //gitDialogFragment dialogFragment = new DialogFragment();
                 dialog.setCancelable(true);
                 dialog.setContentView(R.layout.update_password);
                 changePassword = dialog.findViewById(R.id.passEditBox);
                 Button confirm_update = dialog.findViewById(R.id.passEditUpdate);

                confirm_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // changePassword.setText(change_userPassword.getText().toString());
                        String update = changePassword.getText().toString();
                        if(update.length() < 6){
                            changePassword.setError("Password must be at least 6 characters");
                            return;

                        }
//                        password.setPassword(changePassword.getText().toString());

                        UserRequest userRequest = new UserRequest();
                        userRequest.setPhoneNumber(user_phone.getText().toString());
                        userRequest.setEmail(email);
                        userRequest.setPassword(update);
                        userRequest.setId(userId);
                        userRequest.setFirstName(name.substring(0, name.indexOf(' ')));
                        userRequest.setLastName(name.substring(name.indexOf(' ') + 1));
                        Call<Integer> call = ApiClient.getUserApiService().updateUser(userRequest);
                        System.out.println(update);
                        call.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                if(response.isSuccessful()){
                                    // change_userPassword.setText(changePassword.getText().toString());
                                    //dialog.dismiss();
                                    Toast.makeText(getActivity(), "Password change successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                    //finish();
                                }
                                else{
                                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                               // dialog.dismiss();
                                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("Error: " + t.getMessage());
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        savedState = null;
        return view;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        savedState = saveState();
        user_name = null;
        user_email_print = null;
        user_phone = null;
        showPassword = null;
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getActivity().getSharedPreferences(PREFERENCES_CONTADORLIGA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(PREFERENCES_CONTADORLIGA_KEY, contadorliga);

        // Commit the edits!
        editor.commit();
    }


    @Override
    public void onStart() {
        super.onStart();
        if(savedState != null){
            user_name.setText(savedState.getString("s_name"));
            user_email_print.setText(savedState.getString("s_email"));
            user_phone.setText(savedState.getString("s_phone"));
            showPassword.setText(savedState.getString("s_password"));
            user_image.setImageBitmap(bmp);
        }
        else{
            Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
            System.out.println(email);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        user_name.setText(response.body().getFirstName() + " " + response.body().getLastName());
                        name = response.body().getFirstName() + " " + response.body().getLastName();
                        System.out.println(response.body().getFirstName() + " " + response.body().getLastName());
                        user_email_print.setText(response.body().getEmail());
                        user_phone.setText(response.body().getPhoneNumber());
                        showPassword.setText(response.body().getPassword());
                        userId = response.body().getId();
                        //needs to be figured out
                        showPassword.setTransformationMethod(new PasswordTransformationMethod());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            System.out.println(userId);
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        super.onStart();
        if(savedState != null){
            user_name.setText(savedState.getString("s_name"));
            user_email_print.setText(savedState.getString("s_email"));
            user_phone.setText(savedState.getString("s_phone"));
            showPassword.setText(savedState.getString("s_password"));
            user_image.setImageBitmap(bmp);
        }
        else{
            UserRequest userRequest = new UserRequest();
            userRequest.setEmail(email);
            Call<User> call = ApiClient.getUserApiService().getUserByEmail(email);
            System.out.println(email);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        user_name.setText(response.body().getFirstName() + " " + response.body().getLastName());
                        name = response.body().getFirstName() + " " + response.body().getLastName();
                        System.out.println(response.body().getFirstName() + " " + response.body().getLastName());
                        user_email_print.setText(response.body().getEmail());
                        user_phone.setText(response.body().getPhoneNumber());
                        showPassword.setText(response.body().getPassword());
                        userId = response.body().getId();
                        //needs to be figured out
                        showPassword.setTransformationMethod(new PasswordTransformationMethod());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            System.out.println(email);
        }

    }

    private Bundle saveState(){
        Bundle state = new Bundle();
        state.putString("s_name", user_name.getText().toString());
        state.putString("s_email", user_email_print.getText().toString());
        state.putString("s_phone", user_phone.getText().toString());
        state.putString("s_password", showPassword.getText().toString());
        state.putLong("s_userId", userId);
        return  state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBundle("data", (savedState != null) ? savedState : saveState());
    }
}