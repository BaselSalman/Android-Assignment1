package com.example.math_for_elementary_school_students;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_for_elementary_school_students.model.Question;
import com.example.math_for_elementary_school_students.model.QuestionAnswers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final List<Question> questionList;
    private final Context context;
    private final RecyclerView recyclerView;
    private int faultsCount;
    private int numOfAnsweredQuestions;
    private final Animation nonClickableAnim;
    private final Map<Integer, Boolean> questionsStatusMap;

    public QuestionAdapter(List<Question> questionList, Context context, RecyclerView recyclerView) {
        this.questionList = questionList;
        this.context = context;
        this.recyclerView = recyclerView;
        recyclerView.setVerticalScrollBarEnabled(true);
        faultsCount = 0;
        numOfAnsweredQuestions = 0;
        nonClickableAnim = AnimationUtils.loadAnimation(context, R.anim.button_click_not_allowed_animation);
        questionsStatusMap = new HashMap<>();
        for (int i = 0; i < questionList.size(); i++)
            questionsStatusMap.put(i, false);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_question_item, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.bind(question, holder);
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_animation));

        // Previously answered question
        if (questionsStatusMap.get(position)) {
            holder.btnSubmit.setBackgroundColor(Color.RED);

            for (int i = 0; i < holder.radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) holder.radioGroup.getChildAt(i);
                if (radioButton.getText().equals(holder.currentAnswers.getCorrectAnswer()))
                    radioButton.setChecked(true);
                radioButton.setEnabled(false);
            }
        }

        // Not answered question
        else {
            // Reset submit button state
            holder.btnSubmit.setBackgroundColor(Color.parseColor("#0099CC"));

            // Reset radio button states
            for (int i = 0; i < holder.radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) holder.radioGroup.getChildAt(i);
                radioButton.setEnabled(true);
            }

            holder.radioGroup.clearCheck();
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView txtQuestion;
        private final RadioGroup radioGroup;
        private final Button btnSubmit;
        private final Dialog dialog;

        private QuestionAnswers currentAnswers;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.questionImage);
            txtQuestion = itemView.findViewById(R.id.txtQestion);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            btnSubmit = itemView.findViewById(R.id.btnEnd);
            dialog = new Dialog(itemView.getContext());
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!radioGroup.getChildAt(0).isEnabled()) {
                        btnSubmit.startAnimation(nonClickableAnim);
                    } else {
                        int selectedRbId = radioGroup.getCheckedRadioButtonId();
                        if (selectedRbId != -1) {
                            RadioButton selectedRB = itemView.findViewById(selectedRbId);
                            if (selectedRB.getText().equals(currentAnswers.getCorrectAnswer())) {
                                numOfAnsweredQuestions++;
                                openSuccessfulDialog();
                                questionsStatusMap.put(getAdapterPosition(), true);

                                // Show the dialog for 2 seconds and then go to next question
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Disable the submit button after a correct answer
                                        btnSubmit.setBackgroundColor(Color.RED);

                                        // Disable clicking on radio buttons
                                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                                            radioButton.setEnabled(false);
                                        }

                                        // Move to the next question if available
                                        if (numOfAnsweredQuestions < questionList.size()) {
                                            // Scroll to the next unsolved question
                                            int nextPosition = (getAdapterPosition() + 1) % questionList.size();
                                            while (questionsStatusMap.get(nextPosition))
                                                nextPosition = (nextPosition + 1) % questionList.size();
                                            recyclerView.smoothScrollToPosition(nextPosition);
                                        } else {
                                            // Only go to End Activity if all questions are answered
                                            Intent intent = new Intent(context, EndActivity.class);
                                            intent.putExtra("faultsCount", faultsCount);
                                            context.startActivity(intent);
                                        }
                                        dialog.dismiss();
                                    }
                                }, 2000);
                            } else {
                                openWrongDialog();
                                faultsCount++;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                }, 2000);
                            }
                        } else {
                            // No answer selected
                            btnSubmit.startAnimation(nonClickableAnim);
                        }
                    }
                }
            });
        }

        public void bind(Question question, ViewHolder holder) {
            currentAnswers = question.getAnswers();

            txtQuestion.setText(question.getQuestion());
            imageView.setImageResource(question.getImage());

            for (int i = 0; i < currentAnswers.getAnswers().length; i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                radioButton.setText(currentAnswers.getAnswers()[i]);
            }
            radioGroup.setOnCheckedChangeListener(null);
        }

        private void openSuccessfulDialog() {
            dialog.setContentView(R.layout.correct_answer_layout_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }

        private void openWrongDialog() {
            dialog.setContentView(R.layout.wrong_answer_layout_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}
