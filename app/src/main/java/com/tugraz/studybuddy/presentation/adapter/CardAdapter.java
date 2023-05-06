package com.tugraz.studybuddy.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tugraz.studybuddy.R;
import com.tugraz.studybuddy.data.model.CardModel;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewFrontText;
        public TextView textViewBackText;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewFrontText = itemView.findViewById(R.id.textViewFrontText);
            textViewBackText = itemView.findViewById(R.id.textViewBackText);
        }
        public void bind(CardModel card) {
            textViewFrontText.setText(card.getFrontText());
            textViewBackText.setText(card.getBackText());
            //itemView.setOnClickListener(v -> onClickListener.onItemClick(card));
        }
    }

    private final List<CardModel> cards;
    //private final CardAdapter.OnClickListener onClickListener;

    public CardAdapter(List<CardModel> cards) {
        this.cards = cards;
        //this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.card_list_item, parent, false);
        return new CardAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnClickListener {
        void onItemClick(CardModel card);
    }
}
