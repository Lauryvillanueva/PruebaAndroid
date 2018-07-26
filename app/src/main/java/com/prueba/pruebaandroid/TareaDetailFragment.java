package com.prueba.pruebaandroid;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;



public class TareaDetailFragment extends Fragment {
    private static final String ARG_TAREA_ID = "tareaId";

    private String mTareaId;

    private CollapsingToolbarLayout mCollapsingView;

    private EditText nombre, descripcion;


    private TareasDbHelper mTareasDbHelper;


    public TareaDetailFragment() {
        // Required empty public constructor
    }

    public static TareaDetailFragment newInstance(String lawyerId) {
        TareaDetailFragment fragment = new TareaDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAREA_ID, lawyerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTareaId = getArguments().getString(ARG_TAREA_ID);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tarea_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);

        nombre = (EditText) root.findViewById(R.id.editTextName);
        descripcion = (EditText) root.findViewById(R.id.editTextDescripcion);
        mTareasDbHelper = new TareasDbHelper(getActivity());

        loadLawyer();

        return root;
    }

    private void loadLawyer() {
        new GetLawyerByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showEditScreen();
                break;
            case R.id.action_delete:
                new DeleteLawyerTask().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==TareasFragment.REQUEST_UPDATE_DELETE_LAWYER) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showTarea(Tarea tarea) {
        mCollapsingView.setTitle(tarea.getId());

        nombre.setText(tarea.getNombre());
        descripcion.setText(tarea.getDescripcion());
        // estado.setText(tarea.getEstado());
    }

    private void showEditScreen() {
        Intent intent = new Intent(getActivity(), ActividadTareas.class);
        intent.putExtra(ActivityTareas.EXTRA_TAREA_ID, mTareaId);
        startActivityForResult(intent, TareasFragment.REQUEST_UPDATE_DELETE_LAWYER);
    }

    private void showLawyersScreen(boolean requery) {
        if (!requery) {
            showDeleteError();
        }
        getActivity().setResult(requery ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteError() {
        Toast.makeText(getActivity(),
                "Error al eliminar tarea", Toast.LENGTH_SHORT).show();
    }



    private class GetLawyerByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mTareasDbHelper.getTareaById(mTareaId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showTarea(new Tarea(cursor));
            } else {
                showLoadError();
            }
        }

    }

    private class DeleteLawyerTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return mTareasDbHelper.deleteTarea(mTareaId);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            showLawyersScreen(integer > 0);
        }

    }

}
