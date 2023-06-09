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
import com.tugraz.studybuddy.presentation.contract.IClickListener;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewFrontText;
        private final TextView textViewBackText;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewFrontText = itemView.findViewById(R.id.textViewFrontText);
            textViewBackText = itemView.findViewById(R.id.textViewBackText);
        }

        public void bind(CardModel card, IClickListener<CardModel> clickListener) {
            textViewFrontText.setText(card.getFrontText());
            textViewBackText.setText(card.getBackText());
            itemView.setOnClickListener(v -> clickListener.onItemClick(card));
            itemView.setOnLongClickListener(v -> clickListener.longOnItemClick(card));
        }
    }

    private final List<CardModel> cards;
    private final IClickListener<CardModel> onClickListener;

    public CardAdapter(List<CardModel> cards, IClickListener<CardModel> onClickListener) {
        this.cards = cards;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.card_list_item, parent, false);
        return new CardAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cards.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
