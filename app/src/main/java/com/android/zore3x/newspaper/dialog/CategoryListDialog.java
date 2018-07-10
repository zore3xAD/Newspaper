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
import com.android.zore3x.newspaper.activity.TopHeadlinesActivity;
import com.android.zore3x.newspaper.model.api.Category;

public class CategoryListDialog extends DialogFragment {

    public static final String EXTRA_CATEGORY = "ExtraSource";

    private ListView mCategoryListView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, null);

        mCategoryListView = view.findViewById(R.id.listView);
        mCategoryListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_single_choice, Category.values()));
        mCategoryListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select category")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        Category category = Category.values()[mCategoryListView.getCheckedItemPosition()];
                        intent.putExtra(EXTRA_CATEGORY, category);
                        ((TopHeadlinesActivity)getActivity()).onActivityResult(App.REQUEST_SELECT_CATEGORY_DIALOG, Activity.RESULT_OK, intent);
                    }
                });
        return builder.create();
    }
}
