package com.codeitonce.petshout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestorePasswordFragment extends Fragment
{
    private Button restore_password_button;
    private EditText login_field;


    public RestorePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restore_password, container, false);


        restore_password_button = (Button) view.findViewById(R.id.restore_password_button);
        login_field = (EditText) view.findViewById( R.id.login_field );

        restore_password_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onRestorePasswordButtonClicked();

            }

        });

        return view;
    }
            public void onRestorePasswordButtonClicked()
            {
                String login = login_field.getText().toString();
                Backendless.UserService.restorePassword(login, new DefaultCallback<Void>(getActivity())
                {
                    @Override
                    public void handleResponse(Void response)
                    {
                        super.handleResponse(response);
                        Toast.makeText(getActivity(), R.string.password_restore_toast, Toast.LENGTH_SHORT).show();
                        LoginFragment fragment;
                        fragment = new LoginFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrame, fragment);
                        ft.commit();
                    }
                });

            }

}
