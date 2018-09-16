package hashcode.thefob.contentActivities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hashcode.thefob.Entities.AccountType;
import hashcode.thefob.R;

public class AccountTypeAdapter extends ArrayAdapter<AccountType>
{
    public AccountTypeAdapter(Context context, ArrayList<AccountType> types)
    {
        //resource id is zero because we use custom resource layout
        super(context, 0, types);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {

        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        AccountType currentAccountType = getItem(position);

        TextView nameTextView =  listItemView.findViewById(R.id.AccountTypeName);
        ImageView imageTextView = listItemView.findViewById(R.id.AccountTypeImage);

        try
        {
            assert currentAccountType != null;
            nameTextView.setText(currentAccountType.getAccountTypeName());
            imageTextView.setImageResource(currentAccountType.getIconId());
        }
        catch (NullPointerException e)
        {
            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
        return listItemView;
    }
}
