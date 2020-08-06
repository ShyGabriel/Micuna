package com.example.micuna.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.micuna.R;
import com.example.micuna.interfaces.iComunicaFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    CardView mcardView1;
    CardView mcardView2;
    CardView mcardView3;
    CardView mcardView4;
    CardView mcardView5;
    CardView mcardView6;


    CardView mcardView7;
    CardView mcardView8;
    CardView mcardView9;
    CardView mcardView10;
    CardView mcardView11;

    iComunicaFragment interfaceFragment;
    View vista;
    Activity actividad;



    public HomewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomewFragment newInstance(String param1, String param2) {
        HomewFragment fragment = new HomewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista =inflater.inflate(R.layout.fragment_homew, container, false);

        mcardView1 = vista.findViewById(R.id.pollo);
        mcardView2 = vista.findViewById(R.id.pizza);
        mcardView3 = vista.findViewById(R.id.donuts);
        mcardView4 = vista.findViewById(R.id.hamburguesa);
        mcardView5 = vista.findViewById(R.id.gaseosa);
        mcardView6 = vista.findViewById(R.id.taco);


        mcardView7 = vista.findViewById(R.id.favoritos);
        mcardView8 = vista.findViewById(R.id.peruanito);
        mcardView9 = vista.findViewById(R.id.fastfood);
        mcardView10 = vista.findViewById(R.id.antojos);
        mcardView11 = vista.findViewById(R.id.bebidas);

        mcardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.pollo();

            }
        });

        mcardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.pizza();

            }
        });

        mcardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.donuts();

            }
        });

        mcardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.hamburguesa();

            }
        });

        mcardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.gaseosa();

            }
        });

        mcardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.tacos();

            }
        });


        mcardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interfaceFragment.favoritos();
            }
        });

        mcardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.peruanito();
            }
        });

        mcardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.fastfood();
            }
        });

        mcardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.antojos();
            }
        });

        mcardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragment.bebidas();
            }
        });

        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            actividad= (Activity) context;
            interfaceFragment = (iComunicaFragment) actividad;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
