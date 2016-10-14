package com.example.ahmed.mal_task_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahmed on 10/14/16.
 */

public class VersionAdapter extends ArrayAdapter<AndroidVersion> {

    VersionAdapter(Context context, List<AndroidVersion> androidVersions){
        super(context, 0, androidVersions);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.android_version_item, parent, false);

        AndroidVersion androidVersion = getItem(position);

        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        TextView apiView = (TextView) convertView.findViewById(R.id.api);
        TextView versionView = (TextView) convertView.findViewById(R.id.version);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        nameView.setText(androidVersion.getName());
        apiView.setText(androidVersion.getApiLevel());
        versionView.setText(androidVersion.getVersion());
        imageView.setImageResource(androidVersion.getImageID());

        return convertView;
    }
}
