package hashcode.thefob.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hashcode.thefob.Entities.Account;
import hashcode.thefob.R;

public class AccountAdapter extends ArrayAdapter<Account>
{
    public AccountAdapter(Context context, ArrayList<Account> types)
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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Account currentAccountType = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.AccountTypeName);
        ImageView imageView = listItemView.findViewById(R.id.AccountTypeImage);
        imageView.setVisibility(View.GONE);
        nameTextView.setTextSize(18);
        LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewLayoutParams.setMargins(40, 16, 16, 16);
        nameTextView.setLayoutParams(textViewLayoutParams);

        try
        {
            assert currentAccountType != null;
            nameTextView.setText(currentAccountType.getAccountName());

        } catch (NullPointerException e)
        {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        return listItemView;
    }
}
