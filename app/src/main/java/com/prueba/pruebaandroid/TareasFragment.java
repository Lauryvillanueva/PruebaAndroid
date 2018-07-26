package com.prueba.pruebaandroid;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TareasFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_LAWYER = 2;

    private TareasDbHelper mTareasDbHelper;

    private ListView mTareaList;
    private TareasCursorAdapter mTareasAdapter;
    private FloatingActionButton mAddButton;


    public TareasFragment() {

    }

    public static TareasFragment newInstance() {
        return new TareasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tarea, container, false);


        mTareaList = (ListView) root.findViewById(R.id.tareas_list);
        mTareasAdapter = new TareasCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        mTareaList.setAdapter(mTareasAdapter);

        mTareaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mTareasAdapter.getItem(i);
                String currenttareaId = currentItem.getString(
                        currentItem.getColumnIndex(TareaDet.TareaEntry.ID));

                showDetailScreen(currenttareaId);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });


        getActivity().deleteDatabase(TareasDbHelper.DATABASE_NAME);

        mTareasDbHelper = new TareasDbHelper(getActivity());

        loadTareas();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
                    showSuccessfullSavedMessage();
                    loadTareas();

        }
    }

    private void loadTareas() {
        new TareasLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Tarea registrada exitosamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), ActividadTareas.class);
        startActivityForResult(intent, ActividadTareas.REQUEST_ADD_TAREA);
    }

    private void showDetailScreen(String IdTarea) {
        Intent intent = new Intent(getActivity(), Agregar.class);
        intent.putExtra(ActivityTareas.EXTRA_TAREA_ID, IdTarea);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_LAWYER);
    }

    private class TareasLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mTareasDbHelper.getAllTareas();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mTareasAdapter.swapCursor(cursor);
            } else {

            }
        }
    }

}
