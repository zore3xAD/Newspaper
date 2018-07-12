package com.android.zore3x.newspaper.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.android.zore3x.newspaper.App;
import com.android.zore3x.newspaper.R;
import com.android.zore3x.newspaper.model.Source;
import com.android.zore3x.newspaper.model.api.ResponseSource;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SourceListDialog extends DialogFragment {

    public static final String EXTRA_SOURCE = "ExtraSource";
    private ListView mSourceListView;
    private ArrayList<Source> mSelectedSourceList = new ArrayList<>(20);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, null);

        mSourceListView = view.findViewById(R.id.listView);

        App.getNewsApi().getSources()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseSource>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseSource responseSource) {
                        mSourceListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, responseSource.getSourceList()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        int d =4;
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mSourceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView item = view.findViewById(android.R.id.text1);
                Source source = (Source) adapterView.getItemAtPosition(i);
                if(!item.isChecked()) {
                    // если источник не выбран
                    if(!mSelectedSourceList.contains(source)){
                        mSelectedSourceList.add(source);
                        item.setChecked(true);
                    }
                } else {
                    // если источник выбран
                    if(mSelectedSourceList.contains(source)) {
                        mSelectedSourceList.remove(source);
                        item.setChecked(false);
                    }
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select source(20 max)")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        String sources = formSourceString(mSelectedSourceList);
                        intent.putExtra(EXTRA_SOURCE, sources);
//                        switch (getTag()) {
//                            case TopHeadlinesFragment.TAG:
//                                ((TopHeadlinesFragment)getActivity()).onActivityResult(App.REQUEST_SELECT_SOURCE_DIALOG, Activity.RESULT_OK, intent);
//                                break;
//                            case EverythingFragment.TAG:
//                                ((EverythingFragment)getActivity()).onActivityResult(App.REQUEST_SELECT_SOURCE_DIALOG, Activity.RESULT_OK, intent);
//                                break;
//                        }
                        getTargetFragment().onActivityResult(App.REQUEST_SELECT_SOURCE_DIALOG, Activity.RESULT_OK, intent);
                    }
                });
        return builder.create();
    }

    private String formSourceString(ArrayList<Source> selectedSourceList) {
        String s = "";

        for(Source source : selectedSourceList) {
            s += source.getId() + ",";
        }
        return s;
    }
}
